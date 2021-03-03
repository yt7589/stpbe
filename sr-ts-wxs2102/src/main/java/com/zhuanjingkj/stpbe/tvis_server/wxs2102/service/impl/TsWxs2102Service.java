package com.zhuanjingkj.stpbe.tvis_server.wxs2102.service.impl;

import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.common.util.ImageBase64Converter;
import com.zhuanjingkj.stpbe.tvis_server.wxs2102.service.ITsWxs2102Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class TsWxs2102Service implements ITsWxs2102Service {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource(name = "redisTemplate2")
    private RedisTemplate<String, byte[]> redisTemplate2;

    /**
     * 货车车型识别
     * @param tp 图片的Basement4编码
     * @return
     */
    @Override
    public Map<String, Object> truckRecog(String tp) {
        byte[] data = ImageBase64Converter.convertBase64ToBytes(tp);
        Map<String, Object> params = new HashMap<>();
        params.put("apiName", "truckRecog");
        params.put("TP", tp);
        //String jsonResp = TvisUtil.sendMapRequest(redisTemplate, redisTemplate2, "truckRecog-list", params);
        String jsonResp = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, "truckRecog-list", data);
        Map<String, Object> rst = new HashMap<>();
        JSONObject joRst = new JSONObject(jsonResp);
        rst.put("CODE", "1");
        rst.put("MSG", "");
        rst.put("CLLX", joRst.getString("CLLX"));
        rst.put("CLLXKXD", joRst.getString("CLLXKXD"));
        rst.put("YWFD", joRst.getString("YWFD"));
        rst.put("YWFDKXD", joRst.getString("YWFDKXD"));
        rst.put("YWPG", joRst.getString("YWPG"));
        rst.put("YWPGKXD", joRst.getString("YWPGKXD"));
        return rst;
    }

    @Override
    public Map<String, Object> carryPerson(String tp) {
        byte[] data = ImageBase64Converter.convertBase64ToBytes(tp);
        Map<String, Object> params = new HashMap<>();
        params.put("apiName", "carryPerson");
        params.put("TP", tp);
        String jsonResp = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, "carryPerson-list", data);
        System.out.println("resp:" + jsonResp + "!##############");
        Map<String, Object> rst = new HashMap<>();
        rst.put("CODE", "1");
        rst.put("MSG", "");
        rst.put("RST", jsonResp);
        /*JSONObject joRst = new JSONObject(jsonResp);
        rst.put("WZ", joRst.getString("WZ"));*/
        return rst;
    }



    @Override
    public Map<String, Object> bigPlate(String tp) {
        byte[] data = ImageBase64Converter.convertBase64ToBytes(tp);
        String jsonResp = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, "bigPlate-list", data);
        Map<String, Object> rst = new HashMap<>();
        System.out.println("##### BigPlate json: " + jsonResp + "!!!!!!!!!!");
        rst.put("CODE", "1");
        rst.put("MSG", "");
        rst.put("RST", jsonResp);
        /*
        JSONObject joRst = new JSONObject(jsonResp);rst.put("YWFDH", joRst.getString("YWFDH"));
        rst.put("FDHWZ", joRst.getString("FDHWZ"));
        rst.put("HPHM", joRst.getString("HPHM"));
        rst.put("KXD", joRst.getString("KXD"));*/
        return rst;
    }

    @Override
    public Map<String, Object> motorClassify(String tp) {
        byte[] data = ImageBase64Converter.convertBase64ToBytes(tp);
        Map<String, Object> params = new HashMap<>();
        params.put("apiName", "motorClassify");
        params.put("TP", tp);
        String jsonResp = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, "motorClassify-list", data);
        System.out.println("##### resp: " + jsonResp + "!");
        Map<String, Object> rst = new HashMap<>();
        rst.put("CODE", "1");
        rst.put("MSG", "");
        rst.put("RST", jsonResp);
        /*JSONObject joRst = new JSONObject(jsonResp);
        rst.put("LX", joRst.getString("LX"));*/
        return rst;
    }


    public Map<String, Object> temp(String tp) {
        Map<String, Object> params = new HashMap<>();
        params.put("apiName", "carryPerson");
        params.put("TP", tp);
        String jsonResp = TvisUtil.sendMapRequest(redisTemplate, redisTemplate2, "carryPerson", params);
        Map<String, Object> rst = new HashMap<>();
        rst.put("CODE", "1");
        rst.put("MSG", "");
        rst.put("RST", jsonResp);
        /*JSONObject joRst = new JSONObject(jsonResp);
        rst.put("WZ", joRst.getString("WZ"));*/
        return rst;
    }
}
