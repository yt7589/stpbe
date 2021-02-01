package com.zhuanjingkj.stpbe.idr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.idr.service.impl.IdrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/idr")
public class IdrController {
    private final static Logger logger = LoggerFactory.getLogger(IdrController.class);
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
    private IdrService idrService;

    private static int imgIdx = 0;
    @PostMapping("/recognizeImage")
    public Map<String, Object> recognition(@RequestParam("GCXH") String gcxh,
                                           @RequestParam("TPLX") String tplx,
                                           @RequestParam(name = "MRHPT", required = false) String mrhpt,
                                           @RequestParam(name = "HPHM", required = false) String hphm,
                                           @RequestParam(name = "cameraId", required = true) String cameraId,
                                           @RequestParam(name = "TPXX", required = false) MultipartFile file,
                                           @RequestParam(name = "TPWJ", required = false) String tpwj) {
        byte[] data = null;
        try {
            if ("1".equals(tplx)) {
                if (file != null) {
                    data = file.getBytes();
                }
            } else if ("2".equals(tplx)) {
                data = Base64.getDecoder().decode(tpwj);
            }
            file = null;
            tpwj = null;
            if (data == null) {
                Map<String, Object> result = new HashMap<>();
                result.put("GCXH", gcxh);
                result.put("CODE", 2);
                result.put("MSG", "无法读取图片");
                return result;
            }
            return idrService.recognition(cameraId, gcxh, mrhpt, hphm, data);
        } catch (Exception e) {
            logger.error("车辆识别异常, gcxh={}, tplx={}", gcxh, tplx, e);
            Map<String, Object> result = new HashMap<>();
            result.put("GCXH", gcxh);
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }
}
