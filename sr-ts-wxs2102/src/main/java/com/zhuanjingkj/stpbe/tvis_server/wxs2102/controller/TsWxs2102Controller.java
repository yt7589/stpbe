package com.zhuanjingkj.stpbe.tvis_server.wxs2102.controller;


import com.zhuanjingkj.stpbe.tvis_server.wxs2102.service.impl.TsWxs2102Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 西安工业检测机器人项目控制器
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicle")
public class TsWxs2102Controller {
    private final static Logger logger = LoggerFactory.getLogger(TsWxs2102Controller.class);
    private final static String VEHICLE_ANALYSER_HEART = "VEHICLE_ANALYSER_ALIVE";
    // 配置信息
    @Value("${app.version}")
    private String appVersion;
    @Value("${app.buildDate}")
    private String buildDate;
    private final static int RM_NORMAL = 1; // 正常运行模式
    private final static int RM_MOCK = 2; // 测试模拟接口返回正常响应模式
    @Value("${api.mode}")
    private int apiMode;
    @Value("${vbmy.mode}")
    private int vbmyMode;
    @Value("${vbmy.sample.folder}")
    private String vbmySampleFolder;
    @Autowired
    private TsWxs2102Service tsWxs2102Service;

    /**
     * 货车车型识别
     * @param tp 图片的Basement4编码
     * @return
     */
    @PostMapping("/function/truckRecog")
    public Map<String, Object> truckRecog(
            @RequestParam(name = "TP", required = true) String tp
    ) {
        return tsWxs2102Service.truckRecog(tp);
    }

    /**
     * 载人识别接口
     * @param tp
     * @return
     */
    @PostMapping("/function/carryPerson")
    public Map<String, Object> carryPerson(
            @RequestParam(name = "TP", required = true) String tp
    ) {
        return tsWxs2102Service.carryPerson(tp);
    }

    /**
     * 放大号识别接口
     * @param tp
     * @return
     */
    @PostMapping("/function/bigPlate")
    public Map<String, Object> bigPlate(
            @RequestParam(name = "TP", required = true) String tp
    ) {
        return tsWxs2102Service.bigPlate(tp);
    }

    /**
     * 外卖快递识别接口
     * @param tp
     * @return
     */
    @PostMapping("/function/motorClassify")
    public Map<String, Object> motorClassify(
            @RequestParam(name = "TP", required = true) String tp
    ) {
        return tsWxs2102Service.motorClassify(tp);
    }
}