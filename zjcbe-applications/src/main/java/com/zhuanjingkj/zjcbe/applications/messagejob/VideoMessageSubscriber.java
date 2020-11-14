package com.zhuanjingkj.zjcbe.applications.messagejob;

import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleVideoMessageDTO;
import com.zhuanjingkj.zjcbe.utility.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/28 13:46
 **/
@Service
public class VideoMessageSubscriber implements MessageListener {

	Process process = null;

	private static Logger logger = LoggerFactory.getLogger(VideoMessageSubscriber.class);

	@Override
	public void onMessage(Message message, byte[] pattern) {
		logger.error("Message received: " + message.toString());
		videoMessageHandle(message);
	}

	/**
	 * 消息处理
	 *
	 * @param message
	 */
	private void videoMessageHandle(Message message) {
		try {
			if (message != null) {
				VehicleVideoMessageDTO messageDTO = JsonUtils.parseObject(message.toString(), VehicleVideoMessageDTO.class);
				if (messageDTO != null) {
					File videoFile = new File(messageDTO.getVideoPath());
					if (videoFile.exists()) {
						pushVideoAsRTSP(messageDTO);
					}
				} else {
					logger.info("视频文件路径错误：" + messageDTO.getVideoPath());
				}
			}
		} catch (Exception ex) {
			logger.info("获取消息异常，消息格式有误：" + message.toString());
		}
	}

	/**
	 * 视频流推送
	 *
	 * @param messageDTO
	 */
	private void pushVideoAsRTSP(VehicleVideoMessageDTO messageDTO) {
		try {
			// 视频切换时，先销毁进程，全局变量Process process，方便进程销毁重启，即切换推流视频
			if (process != null) {
				process.destroy();
				logger.info(">>>>>>>>>>推流视频切换<<<<<<<<<<");
			}
			String command = "ffmpeg -re ";
			command += String.format(" -i %s", messageDTO.getVideoPath());
			command += " -rtsp_transport tcp -vcodec copy ";//视频转换控制参数
			command += " -f rtsp " + messageDTO.getRstpUrl();
			logger.info("ffmpeg推流命令：" + command);
			// 运行cmd命令，获取其进程
			process = Runtime.getRuntime().exec(command);
			// 输出ffmpeg推流日志
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				logger.info("视频推流信息[" + line + "]");
			}
			process.waitFor();
		} catch (Exception ex) {
			logger.info("视频推流信息[" + ex.getMessage() + "]", ex);
		}
	}
}