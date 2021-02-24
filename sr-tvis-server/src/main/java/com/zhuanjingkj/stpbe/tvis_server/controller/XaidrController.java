package com.zhuanjingkj.stpbe.tvis_server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tvis_server.service.impl.XaidrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 西安工业检测机器人项目控制器
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("")
public class XaidrController {
    public final static String FLD_CODE = "CODE";
    public final static String FLD_MSG = "MSG";
    public final static String FLD_SQDM = "SQDM";
    public final static String FLD_BJMC = "BJMC";
    public final static String FLD_JCLX = "JCLX";
    public final static String FLD_CXLB = "CXLB";
    public final static String FLD_CLHM = "CLHM";
    public final static String FLD_CXHM = "CXHM";
    public final static String FLD_JLS = "JLS";

    @Autowired
    private XaidrService xaidrService;

    /**
     * 检测图片
     * @param sqdm 授权代码，暂时为18个1
     * @param bjmc 部件名称
     * @param jclx 检测类型
     * @param cxlb 车型类别
     * @param clhm 车辆号码
     * @param cxhm 车厢号码
     * @param tplx 图片类型
     * @param tpxx 图处信息
     * @return JSON格式的识别结果
     */
    @PostMapping("/function_detect")
    public Map<String, Object> functionDetect(
            @RequestParam(name = "SQDM", required = true) String sqdm,
            @RequestParam(name = "BJMC", required = true) String bjmc,
            @RequestParam(name = "JCLX", required = true) String jclx,
            @RequestParam(name = "CXLB", required = true) String cxlb,
            @RequestParam(name = "CLHM", required = true) String clhm,
            @RequestParam(name = "CXHM", required = true) String cxhm,
            @RequestParam(name = "TPLX", required = true) String tplx,
            @RequestParam(name = "TPXX", required = true) String tpxx
    ) {
        Map<String, Object> result = generateBaseResponse(sqdm, bjmc, jclx, cxlb, clhm, cxhm);
        byte[] data = null;
        if (tplx.equals("0")) {
            data = HttpUtil.downloadImage(tpxx);
        }
        if (data == null) {
            result.put(FLD_CODE, 2);
            result.put(FLD_MSG, "图片数据为空");
            return result;
        }
        JSONArray rst = xaidrService.detectXaidrImage(data);
        result.put(FLD_CODE, 1);
        result.put(FLD_MSG, "");
        result.put(FLD_JLS, rst.size());
        result.put("OBJECT", rst);
        return result;
    }

    private Map<String, Object> generateBaseResponse(
            String sqdm, String bjmc, String jclx, String cxlb, String clhm, String cxhm
    ) {
        Map<String, Object> resp = new HashMap<>();
        resp.put(FLD_SQDM, sqdm);
        resp.put(FLD_BJMC, bjmc);
        resp.put(FLD_JCLX, jclx);
        resp.put(FLD_CXLB, cxlb);
        resp.put(FLD_CLHM, clhm);
        resp.put(FLD_CXHM, cxhm);
        return resp;
    }
}
