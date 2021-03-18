package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.net.TcpClient;
import com.zhuanjingkj.stpbe.data.dto.CreateRtspBindDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITvisSdkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TvisSdkService implements ITvisSdkService {

    private final static Logger logger = LoggerFactory.getLogger(TvisSdkService.class);

    @Override
    public ResultDTO<CreateRtspBindDTO> createRtspBind(String rtspUrl) {
        System.out.println("TvisSdkService.createRtspService 1");
        ResultDTO<CreateRtspBindDTO> dto = new ResultDTO<>();
        CreateRtspBindDTO data = new CreateRtspBindDTO();
        data.setStreamId("1008");
        dto.setData(data);
        StringBuilder req = new StringBuilder(rtspUrl);
        req.append("/start");
        logger.info("socket req:" + req.toString() + "!");
        byte[] reqBytes = req.toString().getBytes();
        byte[] respBytes = null;
        try {
            System.out.println("Yt001 addr:" + AppConst.VIDEO_TVIS_ADDR + ":" + AppConst.VIDEO_TVIS_PORT + "!");
            respBytes = TcpClient.sendRequest(
                    AppConst.VIDEO_TVIS_ADDR, AppConst.VIDEO_TVIS_PORT,
                    reqBytes);
        } catch (Exception ex) {
            System.out.println("########### exception: " + ex.getMessage() + "!");
        }
        logger.info("response: " + respBytes + "!");
        if (null == respBytes) {
            data.setStreamId("-1");
            data.setState(1);
        } else {
            String resp = new String(respBytes);
            logger.info("resp: " + resp + "!");
            String[] arrs = resp.split("/");
            String streamId = arrs[arrs.length - 1];
            data.setState(0);
            data.setStreamId(streamId);
        }
        return dto;
    }

    @Override
    public void deleteRtspBind(String rtspUrl) {

    }
}
