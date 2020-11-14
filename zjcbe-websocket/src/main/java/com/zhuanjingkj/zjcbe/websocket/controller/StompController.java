package com.zhuanjingkj.zjcbe.websocket.controller;

import com.zhuanjingkj.zjcbe.business.dto.analysis.VehicleAnalysisResponseDTO;
import com.zhuanjingkj.zjcbe.business.service.analysis.IVehicleAnalysisService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.websocket.config.StompUserPools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class StompController {

	private static final Logger logger = LoggerFactory.getLogger(StompController.class);

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	StompUserPools userPools;

	@Autowired
	IVehicleAnalysisService analysisService;

	@MessageMapping("/send")
	public void sendmess(Principal user, String testStr) {
		simpMessagingTemplate.convertAndSend(String.format("/queue/stream/%s", 0), testStr);
	}

	@PostMapping("/vehicle/stomp/push")
	public void sendToRobotTopic(@RequestParam("streamId") String streamId) {
		try {
			ResultDTO<VehicleAnalysisResponseDTO> resultDTO = analysisService.getAnalysisResult(streamId);
			if (resultDTO != null) {
				simpMessagingTemplate.convertAndSend(String.format("/queue/stream/%s", streamId), resultDTO);
			}
		} catch (Exception ex) {
			logger.error(String.format("车辆解析结果推送异常：%s", ex.getMessage()), ex);
		}
	}
}
