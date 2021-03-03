package com.zhuanjingkj.stpbe.tvis_server.wxsgq.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.tvis_server.wxsgq.service.impl.TsWxsgqService;
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

/**
 * 西安工业检测机器人项目控制器
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicle")
public class TsWxsgqController {
    private final static Logger logger = LoggerFactory.getLogger(TsWxsgqController.class);
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
    private TsWxsgqService tsWxsgqService;

    @GetMapping("/maintain/getVersion")
    public Map<String, String> getVersion() {
        Map<String, String> result = new HashMap<>();
        result.put("BBH", appVersion);
        result.put("FBRQ", buildDate);
        return result;
    }

    private void t1(Object requestData) {
        byte[] data = (byte[])requestData;
        String id = new String(data, 0, 36, Charset.forName("UTF-8"));
        System.out.println("newId=" + id + "!");
    }

    @GetMapping("/maintain/createLib")
    public Map<String, Object> createLib(@RequestParam("NAME") String name) {
        // 所里测试临时方案，返回固定的成功信息
        if (TsWxsgqController.RM_MOCK == apiMode) {
            return createLibMock(name);
        }
        try {
            checkImageEngine();
            return tsWxsgqService.createLib(name);
        } catch (Exception e) {
            logger.error("特征库创建异常, name={}", name, e);
            Map<String, Object> result = new HashMap<>();
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }

    @GetMapping("/maintain/queryLib")
    public Map<String, Object> queryLib(@RequestParam("ID") String id) {
        // 所里测试临时方案，返回固定的成功信息
        if (TsWxsgqController.RM_MOCK == apiMode) {
            return queryLibMock(id);
        }
        try {
            checkImageEngine();
            return tsWxsgqService.queryLib(id);
        } catch (Exception e) {
            logger.error("特征库查询异常, id={}", id, e);
            Map<String, Object> result = new HashMap<>();
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }

    @PostMapping("/maintain/updateLib")
    public Map<String, Object> updateLib(@RequestBody Map<String, Object> params) {
        // 所里测试临时方案，返回固定的成功信息
        if (TsWxsgqController.RM_MOCK == apiMode) {
            return updateLibMock(params);
        }
        try {
            checkImageEngine();
            return tsWxsgqService.updateLib(params);
        } catch (Exception e) {
            logger.error("特征库更新异常", e);
            Map<String, Object> result = new HashMap<>();
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }

    private static int imgIdx = 0;
    @PostMapping("/function/recognition")
    public Map<String, Object> recognition(@RequestParam("GCXH") String gcxh,
                                           @RequestParam("TPLX") String tplx,
                                           @RequestParam(name = "MRHPT", required = false) String mrhpt,
                                           @RequestParam(name = "HPHM", required = false) String hphm,
                                           @RequestParam(name = "cameraId", required = true) String cameraId,
                                           @RequestParam(name = "TPXX", required = false) MultipartFile file,
                                           @RequestParam(name = "TPWJ", required = false) String tpwj) {
        byte[] data = null;
        try {
            checkImageEngine();
            if ("1".equals(tplx)) {
                if (file != null) {
                    data = file.getBytes();
                }
            } else if ("2".equals(tplx)) {
                data = Base64.getDecoder().decode(tpwj);
            }

            if (1 == vbmyMode) {
                String fn = java.net.URLEncoder.encode(file.getOriginalFilename(), "utf-8");
                String rfn = java.net.URLDecoder.decode(fn, "utf-8");
                System.out.println("rfn: " + rfn + "! name=" + file.getName() + "!");
                DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
                df.setMinimumIntegerDigits(6);
                df.setGroupingUsed(false);
                FileImageOutputStream imageOutput = new FileImageOutputStream(new File(
                        vbmySampleFolder + "/i" + df.format(imgIdx) + "_" + fn));
                imgIdx++;
                imageOutput.write(data, 0, data.length);
                imageOutput.close();
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
            System.out.println("VehicleRecognition data:" + data.length + "!");
            for (int i=0; i<50; i++) {
                System.out.println("vr@_" + i + ":" + Integer.toHexString(data[i]) + "!");
            }
            logger.info("before recognition");
            return tsWxsgqService.recognition(cameraId, gcxh, mrhpt, hphm, data);

        } catch (Exception e) {
            logger.error("车辆识别异常, gcxh={}, tplx={}", gcxh, tplx, e);

            Map<String, Object> result = new HashMap<>();
            result.put("GCXH", gcxh);
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }

    @PostMapping("/function/compare")
    public Map<String, Object> compareVehicle(@RequestBody Map<String, String> params) {// 所里测试临时方案，返回固定的成功信息
        if (TsWxsgqController.RM_MOCK == apiMode) {
            return compareVehicleMock(params);
        }
        try {
            checkImageEngine();
            String cltzxx1 = params.get("CLTZXX1");
            String cltzxx2 = params.get("CLTZXX2");
            return tsWxsgqService.compareVehicle(cltzxx1, cltzxx2);
        } catch (Exception e) {
            logger.error("车辆特征比对异常, params={}", JSON.toJSONString(params), e);

            Map<String, Object> result = new HashMap<>();
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }

    @PostMapping("/function/compareInLib")
    public Map<String, Object> compareInLib(@RequestBody Map<String, String> params) {
        if (TsWxsgqController.RM_MOCK == apiMode) {
            return compareInLibMock(params);
        }
        try {
            checkImageEngine();
            String cltzxx = params.get("CLTZXX");
            String kid = params.get("KID");
            String xsdyz = params.get("XSDYZ");
            String fydx = params.get("FYDX");
            String ys = params.get("YS");
            return tsWxsgqService.compareInLib(cltzxx, kid, xsdyz, fydx, ys);
        } catch (Exception e) {
            logger.error("特征库特征比对异常, params={}", JSON.toJSONString(params), e);

            Map<String, Object> result = new HashMap<>();
            result.put("CODE", 0);
            result.put("MSG", e.getMessage());
            return result;
        }
    }

    private boolean checkImageEngine() {
//        if (!redisTemplate.hasKey(VEHICLE_ANALYSER_HEART)) {      //TODO
//            throw new IllegalStateException("图片解析引擎没有启动");
//        }
        return true;
    }

    /**
     * 接口2：在模拟状态下的实现方法，仅返回固定的接口成功信息
     * @param name
     * @return
     */
    private Map<String, Object> createLibMock(String name) {
        Random rdm = new Random();
        int idRange = 10000000;
        int featureId = idRange + rdm.nextInt(idRange);
        Map<String, Object> result = new HashMap<>();
        result.put("NAME", name);
        result.put("CODE", 1);
        result.put("ID", "" + featureId);
        return result;
    }

    /**
     * 接口3：在模拟状态下的实现方法，仅返回固定的接口成功信息
     * @param id
     * @return
     */
    private Map<String, Object> queryLibMock(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("CODE", "1");
        Map<String, Object> libInfo = new HashMap<>();
        libInfo.put("ID", id);
        libInfo.put("NAME", "ZjkjCloud");
        libInfo.put("COUNT", "15378100");
        result.put("LIBINFO", libInfo);
        return result;
    }

    /**
     * 接口4：在模拟状态下的实现方法，仅返回固定的接口成功信息
     * @param params
     * @return
     */
    private Map<String, Object> updateLibMock(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("CODE", "1");
        return result;
    }

    /**
     * 接口6：在模拟状态下的实现方法，仅返回固定的接口成功信息
     * @param params
     * @return
     */
    private Map<String, Object> compareVehicleMock(Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        result.put("CODE", "1");
        result.put("XSD", "" + Math.random());
        return result;
    }

    private Map<String, Object> compareInLibMock(Map<String, String> params) {
        // 获取请求信息
        String cltzxx = params.get("CLTZXX");
        String kid = params.get("KID");
        // 相似度阈值
        int xsdyz = 0;
        try {
            if (params.get("XSDYZ") != null) {
                xsdyz = Integer.parseInt(params.get("XSDYZ"));
            }
        } catch (Exception ex) {
        }
        int fydx = 10;
        try {
            if (params.get("FYDX") != null) {
                fydx = Integer.parseInt(params.get("FYDX"));
            }
        } catch (Exception ex) {
        }
        int ys = 1;
        try {
            if (params.get("YS") != null) {
                ys = Integer.parseInt(params.get("YS"));
            }
        } catch (Exception ex) {
        }
        Map<String, Object> result = new HashMap<>();
        Random rdm = new Random();
        int rdmRange = 1000000;
        result.put("CODE", "1");
        JSONArray bdjg = new JSONArray();
        JSONObject item = null;
        double xsd = 0.0;
        for (int i=0; i<fydx; i++) {
            item = new JSONObject();
            item.put("TZID", "" + (rdmRange + rdm.nextInt(rdmRange)));
            xsd = Math.random();
            while (xsd < xsdyz / 100.0) {
                xsd = Math.random();
            }
            item.put("XSD", "" + xsd);
            bdjg.add(item);
        }
        result.put("BDJG", bdjg);
        return result;
    }
}