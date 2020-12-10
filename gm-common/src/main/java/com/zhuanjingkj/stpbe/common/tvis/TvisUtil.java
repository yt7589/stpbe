package com.zhuanjingkj.stpbe.common.tvis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.BmyDao;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import com.zhuanjingkj.stpbe.data.dto.BmyDTO;
import com.zhuanjingkj.stpbe.data.dto.BrandDTO;
import com.zhuanjingkj.stpbe.data.dto.ModelDTO;
import com.zhuanjingkj.stpbe.data.vo.VehicleCltzxlVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleWztzVo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TvisUtil {
    public final static String ERROR_RESPONSE = "ERROR";

    public static String recognizeImageFile(Map<String, Object> map, File f) {
        System.out.println("处理文件：" + f);
        boolean sendName = true;
        String type = "file";
        String url = AppConst.TVIS_SERVER_URL;

        String response = null;
        try {
            if ("file".equals(type)) {
                map.put("TPXX", f);
                map.put("TPLX", "1");
                response = HttpUtil.postFile(url, map);
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

    public static List<VehicleVo> parseTvisJson(String json) {
        JSONObject rstJson = JSONObject.parseObject(json);
        // 获取特征向量
        JSONArray vehs = rstJson.getJSONArray("VEH");
        JSONObject vehJson = null;
        JSONObject cxtzJson = null;
        JSONObject wztzJson = null;
        List<VehicleVo> vos = new ArrayList<>();
        VehicleVo vo = null;
        VehicleWztzVo vehicleWztzVo = null;
        VehicleCxtzVo vehicleCxtzVo = null;
        VehicleCltzxlVo vehicleCltzxlVo = null;
        for (Object veh : vehs) {
            vehJson = (JSONObject) veh;
            vo = new VehicleVo();
            // 位置特征解析
            vehicleWztzVo = new VehicleWztzVo();
            wztzJson = vehJson.getJSONObject("WZTZ");
            vehicleWztzVo.setPsfx(wztzJson.getString("PSFX"));
            vo.setVehicleWztzVo(vehicleWztzVo);
            // 车型特征
            vehicleCxtzVo = new VehicleCxtzVo();
            cxtzJson = vehJson.getJSONObject("CXTZ");
            vehicleCxtzVo.setCllxflCode(cxtzJson.getString("CLLXFL"));
            vehicleCxtzVo.setCllxzflCode(cxtzJson.getString("CLLXZFL"));
            vehicleCxtzVo.setClppCode(cxtzJson.getString("CLPP"));
            vehicleCxtzVo.setPpcxCode(cxtzJson.getString("PPCX"));
            vehicleCxtzVo.setCxnkCode(cxtzJson.getString("CXNK"));
            vehicleCxtzVo.setPpxhmsCode(cxtzJson.getString("PPXHMS"));
            vo.setVehicleCxtzVo(vehicleCxtzVo);
            // 车辆特征向量
            vehicleCltzxlVo = new VehicleCltzxlVo();
            vehicleCltzxlVo.setCltzxl(generateTzxl(vehJson.getString("CLTZXL")));
            vo.setVehicleCltzxlVo(vehicleCltzxlVo);
            vos.add(vo);
        }
        return vos;
    }



    private static List<Float> generateTzxl(String vecStr) {
        List<Float> tzxl = new ArrayList<>();
        String[] arrs = vecStr.split(",");
        System.out.println("arrs.length=" + arrs.length + "!!!!!!!!!!!!!!!!!");
        for (String item : arrs) {
            tzxl.add(Float.parseFloat(item));
        }
        System.out.println("tzxl.size=" + tzxl.size() + "!!!!!!!!!!!!!!!!!!");
        return tzxl;
    }
}
