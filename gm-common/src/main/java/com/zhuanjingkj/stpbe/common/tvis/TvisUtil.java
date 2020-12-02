package com.zhuanjingkj.stpbe.common.tvis;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TvisUtil {
    public final static String ERROR_RESPONSE = "ERROR";

    public static String recognizeImageFile(Map<String, Object> map, File f) {
        System.out.println("处理文件：" + f);
        boolean sendName = true;
        String type = "file";
        String url = AppConst.TVIS_SERVER_URL;
        /**/

        String response = null;
        try {
            if ("file".equals(type)) {
                map.put("TPXX", f);
                map.put("TPLX", "1");
                response = HttpUtil.postFile(url, map);//postFile(url, map);
            } else {
                map.put("TPLX", "2");
                map.put("TPXX", Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(f)));
                response = HttpUtil.postString(url, map);
            }
            map.clear();
            map = null;
        } catch (IOException ex) {
            return ERROR_RESPONSE;
        }

        System.out.println("response:" + response + "!");
        if (isSuccessRequest(response)) {
            return response;
        } else {
            return ERROR_RESPONSE;
        }
    }


    private static boolean isSuccessRequest(String response) {
        try {
            JSONObject json = JSONObject.parseObject(response); //JSONUtil.parseObj(response);
            Integer code = json.getIntValue("CODE"); //json.getInt("CODE");
            if (Integer.valueOf(1).equals(code)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
