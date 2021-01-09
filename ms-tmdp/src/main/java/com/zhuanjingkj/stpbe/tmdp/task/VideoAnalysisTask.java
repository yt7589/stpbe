package com.zhuanjingkj.stpbe.tmdp.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.common.tvis.TvisSodImage;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VideoAnalysisTask {
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    private static List<String> streamIds = new ArrayList<>();

    @Async("tmdpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runVideoAnalysisTask() {
        if (StringUtils.isBlank(AppRegistry.tvisJsonTblName)) {
            // 获取当前t_tvis_json_*表名
            AppRegistry.tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        }
        TvisJsonVO tvisJsonVO = null;
        for (String streamId : streamIds) {
            // 找到当前原始信息表
            tvisJsonVO = tvisJsonMapper.getLatestStreamFrame(AppRegistry.tvisJsonTblName, Long.parseLong(streamId));
            if (null == tvisJsonVO) {
                continue;
            }
            long tvisJsonId = tvisJsonVO.getTvisJsonId();
            // 获取图片
            BufferedImage orgImg = TvisSodImage.downloadIpfsImage(tvisJsonVO.getImageHash());
            // 获取JSON结果
            String jsonStr = IpfsClient.getTextFile(tvisJsonVO.getJsonHash());
            JSONObject jo = JSONObject.parseObject(jsonStr);
            JSONObject joRst = jo.getJSONObject("json");
            List<VehicleVo> vehs = TvisUtil.parseTvisJson(jo.getLong("cameraId"), joRst.toJSONString());
            // 在图像上绘制一个矩形框并保存到当前目录下
            int x, y, w, h; // 检测框位置
            for (VehicleVo veh : vehs) {
                String clwz = veh.getVehicleWztzVo().getClwz();
                String[] arrs = clwz.split(",");
                x = Integer.parseInt(arrs[0]);
                y = Integer.parseInt(arrs[1]);
                w = Integer.parseInt(arrs[2]);
                h = Integer.parseInt(arrs[3]);
                TvisSodImage.drawRect(orgImg, Color.RED, x, y, w, h);
                // 车型特征
                String ppxhms = veh.getVehicleCxtzVo().getPpxhmsName();
                String hphm = veh.getVehicleHptzVO().getHphm();
                TvisSodImage.drawString(orgImg, Font.PLAIN, 25,
                        Color.RED, x, y+ 10, "标题：" + ppxhms + "-" + hphm);
            }
            try {
                ImageIO.write(orgImg, "jpg", new File("images/n_" + tvisJsonId + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 生成一个定制的URL，可以通过SpringBoot来查看图片内容
            System.out.println("##### Yantao: tvisJsonVO:" + JSONObject.toJSONString(tvisJsonVO) + "!");
        }
    }

    public static void putStream(long streamId) {
        System.out.println("加入到视频列表中...");
        String streamIdKey = "" + streamId;
        if (!streamIds.contains(streamIdKey)) {
            streamIds.add(streamIdKey);
        }
    }
}
