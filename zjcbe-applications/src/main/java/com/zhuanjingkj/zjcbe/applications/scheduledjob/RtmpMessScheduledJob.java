package com.zhuanjingkj.zjcbe.applications.scheduledjob;

import com.zhuanjingkj.zjcbe.business.service.analysis.IVehicleAnalysisService;
import com.zhuanjingkj.zjcbe.commondata.config.CommonConfig;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleIdentifyMessageDTO;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleImageMessageDTO;
import com.zhuanjingkj.zjcbe.utility.json.JsonUtils;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import com.zhuanjingkj.zjcbe.utility.web.SimpleHttpUtils;
import com.zhuanjingkj.zjcbe.utilityredis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/19 17:18
 **/

@Component
public class RtmpMessScheduledJob {

	private static Logger logger = LoggerFactory.getLogger(RtmpMessScheduledJob.class);

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	CommonConfig autoConfig;

	@Autowired
	SimpleHttpUtils httpUtils;

	@Autowired
	IVehicleAnalysisService analysisService;

	@Async("taskSchedulerPool")
	@Scheduled(cron = "*/1 * * * * ?")
	public void vehicleHandle() {
		while (true) {
			try {
				Object message = redisUtil.rightPop(autoConfig.getVehicleQueenName());
				if (message == null) {
					return;
				}
				logger.info("闫涛 save json -> get message:" + JsonUtils.toJSONString(message) + "!");
				VehicleIdentifyMessageDTO identifyResponseDTO = JsonUtils.parseObject(message.toString(), VehicleIdentifyMessageDTO.class);
				if (identifyResponseDTO != null
						&& identifyResponseDTO.getCode() == 1
						&& identifyResponseDTO.getVeh() != null
						&& identifyResponseDTO.getVeh().size() > 0) {

					if (StringUtils.isBlank(identifyResponseDTO.getStreamId()) || StringUtils.isBlank(identifyResponseDTO.getTimeStamp())) {
						continue;
					}
					analysisService.saveRtmpAnalysisMessage(identifyResponseDTO);
				}
				Thread.currentThread().sleep(1);
			} catch (Exception ex) {
				logger.error(String.format("获取图像识别消息异常,错误信息：s%", ex.getMessage()), ex);
			}
		}
	}

	@Async("taskSchedulerPool")
	@Scheduled(cron = "*/1 * * * * ?")
	public void imageHandle() {
		while (true) {
			try {
				Object message = redisUtil.rightPop(autoConfig.getImageQueenName());
				if (message == null) {
					return;
				}
				logger.info("process cutted image ########## get message:" + message.toString().substring(0, 10) + "...");
				VehicleImageMessageDTO imageMessageDTO = JsonUtils.parseObject(message.toString(), VehicleImageMessageDTO.class);
				if (imageMessageDTO != null) {
					analysisService.saveRtmpImageMessage(imageMessageDTO);
					pushVehicleInfo(imageMessageDTO.getStreamId());
				}
				Thread.currentThread().sleep(1);
			} catch (Exception ex) {
				logger.error(String.format("获取图像识别消息异常,错误信息：s%", ex.getMessage()), ex);
			}
		}
	}

	/**
	 * 推送车辆解析消息
	 *
	 * @param streamId
	 */
	private void pushVehicleInfo(String streamId) {
		try {
			String actionUrl = autoConfig.getStompUrl() + "/vehicle/stomp/push";
			Map params = new HashMap();
			params.put("streamId", streamId);
			httpUtils.post(actionUrl, params);
		} catch (Exception ex) {
			logger.error("推送车辆解析信息失败！");
		}
	}
}
