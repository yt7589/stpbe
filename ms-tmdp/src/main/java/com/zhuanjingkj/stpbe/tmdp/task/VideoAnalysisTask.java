package com.zhuanjingkj.stpbe.tmdp.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.common.tvis.TvisSodImage;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tmdp.vo.CameraVehicleRecordVO;
import com.zhuanjingkj.stpbe.tmdp.vo.WsmVideoFrameVO;
import com.zhuanjingkj.stpbe.tmdp.vo.WsmVideoFrameVehicleVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VideoAnalysisTask {
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    private static List<String> streamIds = new ArrayList<>();
    private static Map<String, List<WebSocketSession>> streamWsss = new HashMap<>();
    private static Map<String, CameraVehicleRecordVO> cutVehs = new HashMap<>();
    private final static Logger logger = LoggerFactory.getLogger(VideoAnalysisTask.class);

    @Async("tmdpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runVideoAnalysisTask() {
        logger.info("### 视频分析定时任务1");
        String vaImgUrlBase = AppConst.TMDP_BASE_URL + "va/getVaImage?imgFn=";
        if (StringUtils.isBlank(AppRegistry.tvisJsonTblName)) {
            // 获取当前t_tvis_json_*表名
            AppRegistry.tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        }
        logger.info("step 1");
        TvisJsonVO tvisJsonVO = null;
        WsmVideoFrameVO vfv = null;
        List<WsmVideoFrameVehicleVO> wvfvvs = null;
        WsmVideoFrameVehicleVO vfvv = null;
        logger.info("step 2");
        for (String streamId : streamIds) {
            logger.info("streamId: " + streamId + "!");
            // 找到当前原始信息表
            tvisJsonVO = tvisJsonMapper.getLatestStreamFrame(AppRegistry.tvisJsonTblName, Long.parseLong(streamId));
            if (null == tvisJsonVO) {
                continue;
            }
            logger.info("step 3");
            long tvisJsonId = tvisJsonVO.getTvisJsonId();
            // 获取图片
            BufferedImage orgImg = TvisSodImage.downloadIpfsImage(tvisJsonVO.getImageHash());
            logger.info("step 4");
            // 获取JSON结果
            String jsonStr = IpfsClient.getTextFile(tvisJsonVO.getJsonHash());
            JSONObject jo = JSONObject.parseObject(jsonStr);
            JSONObject joRst = jo.getJSONObject("json");
            logger.info("step 5: " + joRst.toJSONString() + "!!!!!!!!!!");
            List<VehicleVo> vehs = TvisUtil.parseTvisJson(jo.getLong("cameraId"), joRst.toJSONString());
            // 在图像上绘制一个矩形框并保存到当前目录下
            CameraVehicleRecordVO vo = null;
            int x, y, w, h; // 检测框位置
            int idx = 0;
            int currentArea = 0;
            int maxArea = 0;
            String cutFileFn = null;
            File cutFileObj = null;
            String imgBaseFolder = "images/";
            String orgFileFn = "n_" + tvisJsonId + ".jpg";
            logger.info("step 6");
            vfv = new WsmVideoFrameVO(tvisJsonVO.getTvisJsonId(), tvisJsonVO.getPts(), vaImgUrlBase + orgFileFn);
            wvfvvs = vfv.getData();
            logger.info("step 7");
            for (VehicleVo veh : vehs) {
                logger.info("step 8 idx=" + veh.getVehsIdx() + "!");
                String clwz = veh.getVehicleWztzVo().getClwz();
                String[] arrs = clwz.split(",");
                x = Integer.parseInt(arrs[0]);
                y = Integer.parseInt(arrs[1]);
                w = Integer.parseInt(arrs[2]);
                h = Integer.parseInt(arrs[3]);
                currentArea = w * h;
                if (!cutVehs.containsKey("" + veh.getTrackId())) {
                    vo = new CameraVehicleRecordVO();
                    vo.setTvisJsonId(veh.getTvisJsonId());
                    vo.setVehsIdx((int)veh.getVehsIdx());
                    vo.setSxh((int)veh.getVehsIdx());
                    vo.setX(x);
                    vo.setY(y);
                    vo.setW(w);
                    vo.setH(h);
                    vo.setOrgImgFn(orgFileFn);
                    TvisSodImage.drawRect(orgImg, Color.RED, x, y, w, h);
                    // 车型特征
                    String ppxhms = veh.getVehicleCxtzVo().getPpxhmsCode();
                    String hphm = veh.getVehicleHptzVO().getHphm();
                    TvisSodImage.drawString(orgImg, Font.BOLD, 25,
                            Color.RED, x, y + 3, hphm + ":" + ppxhms);
                } else {
                    vo = cutVehs.get("" + veh.getTrackId());
                }
                maxArea = vo.getArea();
                if (currentArea > maxArea) {
                    BufferedImage vehImg = orgImg.getSubimage(x, y, w, h);
                    try {
                        cutFileFn = "c_" + tvisJsonId + "_" + idx + ".jpg";
                        cutFileObj = new File(imgBaseFolder + cutFileFn);
                        if (cutFileObj.exists()) {
                            cutFileObj.delete();
                        } else {
                            cutFileObj.createNewFile();
                        }
                        ImageIO.write(vehImg, "jpg", new File(imgBaseFolder + cutFileFn));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    vo.setX(x);
                    vo.setY(y);
                    vo.setW(w);
                    vo.setH(h);
                    vo.setCutImgFn(cutFileFn);
                }
                vfvv = new WsmVideoFrameVehicleVO(veh.getTrackId(), idx,
                        veh.getVehicleCxtzVo().getPpcxCode(),
                        veh.getVehicleHptzVO().getHphm(), vaImgUrlBase + cutFileFn,
                        "50秒前", "无");
                wvfvvs.add(vfvv);
                idx++;
            }
            File orgFileObj = new File(imgBaseFolder + orgFileFn);
            if (!orgFileObj.exists()) {
                try {
                    orgFileObj.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                ImageIO.write(orgImg, "jpg", orgFileObj);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 生成一个定制的URL，可以通过SpringBoot来查看图片内容
            vfv.setData(wvfvvs);
            System.out.println("##### Yantao: WebSocket vfv:" + JSONObject.toJSONString(vfv) + "!");
            List<WebSocketSession> wsss = streamWsss.get("" + streamId);
            for (WebSocketSession wss : wsss) {
                if (wss.isOpen()) {
                    try {
                        wss.sendMessage(new TextMessage(JSONObject.toJSONString(vfv)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        wss.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            wsss.removeIf(wi->{return !wi.isOpen();}); // 移除所有关闭的WebSocket
        }
    }

    /**
     *
     * @param streamId
     * @param wss
     */
    public static void addStream(long streamId, WebSocketSession wss) {
        System.out.println("加入到视频列表中...streamId=" + streamId + "!");
        String streamIdKey = "" + streamId;
        if (!streamIds.contains(streamIdKey)) {
            streamIds.add(streamIdKey);
        }
        if (streamWsss.get("" + streamId) != null) {
            streamWsss.get(""+streamId).add(wss);
        } else {
            List<WebSocketSession> wsss = new ArrayList<>();
            wsss.add(wss);
            streamWsss.put("" + streamId, wsss);
        }
    }
}
