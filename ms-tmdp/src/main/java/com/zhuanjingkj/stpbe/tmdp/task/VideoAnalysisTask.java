package com.zhuanjingkj.stpbe.tmdp.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.common.tvis.TvisSodImage;
import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
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
            System.out.println("##### ^_^ tableName=" + AppRegistry.tvisJsonTblName + "! #################");
        }
        TvisJsonVO tvisJsonVO = null;
        for (String streamId : streamIds) {
            System.out.println("##### 处理第" + (Long.parseLong(streamId) + 1) + "路视频...");
            // 找到当前原始信息表
            System.out.println("##### 表名：" + AppRegistry.tvisJsonTblName + "!");
            tvisJsonVO = tvisJsonMapper.getLatestStreamFrame(AppRegistry.tvisJsonTblName, Long.parseLong(streamId));
            System.out.println("##### vo:" + tvisJsonVO + "!");
            System.out.println("##### vo1:" + JSONObject.toJSONString(tvisJsonVO) + "!");
            // 获取图片
            BufferedImage orgImg = TvisSodImage.downloadIpfsImage(tvisJsonVO.getImageHash());
            // 获取JSON结果
            IpfsClient.downloadFile(tvisJsonVO.getJsonHash(), "vj.json");
            // 在图像上绘制一个矩形框并保存到当前目录下
            TvisSodImage.drawRect(orgImg, Color.RED, 200, 200, 300, 500);
            try {
                ImageIO.write(orgImg, "jpg", new File("n00" + System.currentTimeMillis() + ".jpg"));
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
