package com.zhuanjingkj.stpbe.common.tvis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.BmyDao;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.BmyDTO;
import com.zhuanjingkj.stpbe.data.dto.BrandDTO;
import com.zhuanjingkj.stpbe.data.dto.ModelDTO;
import com.zhuanjingkj.stpbe.data.vo.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TvisUtil {
    public final static String ERROR_RESPONSE = "ERROR";
    public final static int TVIS_RST_TIMEOUT = 300;
    private final static Logger logger = LoggerFactory.getLogger(TvisUtil.class);

    /**
     * 上传图片返回图片JSON格式识别结果
     * @param map
     * @param f
     * @return
     */
    public static String recognizeImageFile(Map<String, Object> map, File f) {
        String type = "file";
        String url = AppConst.TVIS_SERVER_URL;
        return processTvisImage(url, type, map, f);
    }

    /**
     * 提交抓拍机图片，只返回成功失败处理结果
     * @param map
     * @param f
     * @return
     */
    public static String submitTvisImage(Map<String, Object> map, File f) {
        String type = "file";
        String url = AppConst.TVIS_SERVER_BASE_URL + AppConst.TSC_SUBMIT_IMAGE;
        return processTvisImage(url, type, map, f);
    }



    public static String sendByteRequest(RedisTemplate redisTemplate, RedisTemplate<String, byte[]> redisTemplate2, String requestQueue, byte[] data) {
        String requestId = UUID.randomUUID().toString();
        byte[] id = requestId.getBytes(Charset.forName("UTF-8"));
        byte[] requestData = new byte[id.length + data.length];
        System.arraycopy(id, 0, requestData, 0, id.length);
        System.arraycopy(data, 0, requestData, id.length, data.length);
        data = null;
        return sendRequest(redisTemplate, redisTemplate2, requestQueue, requestId, requestData);
    }

    public static String sendStringRequest(RedisTemplate redisTemplate, RedisTemplate<String, byte[]> redisTemplate2, String requestQueue, String request) {
        String requestId = UUID.randomUUID().toString();
        return sendRequest(redisTemplate, redisTemplate2, requestQueue, requestId, joinParams(requestId, request));
    }

    public static String sendMapRequest(RedisTemplate redisTemplate, RedisTemplate<String, byte[]> redisTemplate2, String requestQueue, Map<String, Object> request) {
        String requestId = UUID.randomUUID().toString();
        request.put("requestId", requestId);
        return sendRequest(redisTemplate, redisTemplate2, requestQueue, requestId, JSON.toJSONString(request));
    }

    public static String joinParams(String... param) {
        return String.join("||", param);
    }



    public static void rotateTvisJsonTbl(TvisJsonMapper tvisJsonMapper) {
        // 生成当前数据表
        String tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        String[] arrs = tvisJsonTblName.split("_");
        long idx = Long.parseLong(arrs[arrs.length - 1]);
        AppRegistry.tvisJsonTblName = AppConst.TVIS_JSON_TBL_PREFIX + String.format("%08d", idx+1);
        AppRegistry.tvisJsonTblRecs = 0;
        tvisJsonMapper.createTvisJsonTbl(AppRegistry.tvisJsonTblName);
    }

    public static void processRawTvisJson(RedisTemplate redisTemplate, TvisJsonMapper tvisJsonMapper, String json) {
        System.out.println("###### TvisJsonRawListener.listen 1");
        JSONObject jo = JSONObject.parseObject(json);
        String relativeImageFile = jo.getJSONObject("json").getString("ImageUrl");
        if (relativeImageFile==null || relativeImageFile.equals("") || relativeImageFile.length()<2) {
            return;
        }
        String imageFile = AppConst.VIDEO_FRAME_IMG_BASE_DIR + relativeImageFile.substring(2);
        Optional<String> imgRst = IpfsClient.uploadFile(imageFile);
        final StringBuilder imageHash = new StringBuilder();
        imgRst.ifPresent((str) -> {
            imageHash.append(str);
        });
        // 生成临时JSON文件，上传到IPFS得到jsonHash
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        Random rand = new Random();
        String jf = AppConst.JSON_TMP_BASE_DIR + "json_" + System.currentTimeMillis() + "_" + rand.nextInt() + ".json";
        try {
            fos = new FileOutputStream(new File(jf));
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(json);
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        final StringBuilder jsonHash = new StringBuilder();
        Optional<String> jsonRst = IpfsClient.uploadFile(jf);
        jsonRst.ifPresent((jsonStr)->{
            jsonHash.append(jsonStr);
        });
        // 获取imageHash、cameraId、streamId、pts，将其存入mysql数据库中
        long tvisJsonId = 0;
        if (jo.containsKey("tvisJsonId")) {
            tvisJsonId = jo.getLong("tvisJsonId");
        } else {
            tvisJsonId = redisTemplate.opsForValue().increment(AppConst.TVIS_JSON_TBL_ID_KEY);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String occurTime = df.format(new Date());
        String cameraIdStr = jo.getString("cameraId");
        long cameraId = Long.parseLong(cameraIdStr);
        JSONObject rstJo = jo.getJSONObject("json");
        String streamIdStr = rstJo.getString("StreamID");
        long streamId = Long.parseLong(streamIdStr);
        String ptsStr = rstJo.getString("TimeStamp");
        long pts = Long.parseLong(ptsStr);
        TvisJsonVO vo = new TvisJsonVO(AppRegistry.tvisJsonTblName, tvisJsonId, occurTime,
                cameraId, streamId, pts, imageHash.toString(), jsonHash.toString());
        tvisJsonMapper.insertTvisJson(vo);
        AppRegistry.tvisJsonTblRecs++;
        if (AppRegistry.tvisJsonTblRecs >= AppConst.TVIS_JSON_TBL_MAX_RECS) {
            rotateTvisJsonTbl(tvisJsonMapper);
        }
    }

    public static void processStpTvisJson(List<ITvisStpObserver> observers, String json) {
        JSONObject rawJo = JSONObject.parseObject(json);
        long tvisJsonId = rawJo.getLong("tvisJsonId");
        long cameraId = rawJo.getLong("cameraId");
        JSONObject rstJo = rawJo.getJSONObject("json");
        logger.info("cameraId=" + cameraId + "; json=" + rstJo + "; \r\njson:" + rstJo.toJSONString() + "!!!!!!!!");
        List<VehicleVo> vehs = TvisUtil.parseTvisJson(cameraId, rstJo.toJSONString());
        long vehsIdx = 0;
        for (VehicleVo veh : vehs) {
            veh.setTvisJsonId(tvisJsonId);
            veh.setVehsIdx(vehsIdx);
            vehsIdx++;
            for (ITvisStpObserver obs : observers) {
                obs.notifyObserver(veh);
            }
        }
    }






    private final static String REQUEST_ID_PREFIX = "a_";
    public static String sendRequest(RedisTemplate redisTemplate, RedisTemplate<String, byte[]> redisTemplate2, String requestList, String requestId, Object requestData) {
        if (requestData instanceof String) {
            redisTemplate.opsForList().leftPush(requestList, (String) requestData);
        } else {
            /*
            synchronized (this){
                logger.info("sendRequest 3.1");
                // 设置请求编号超时时间
                redisTemplate.opsForValue().set(REQUEST_ID_PREFIX + requestId, "0", REQUEST_EXPIRED_TIME, TimeUnit.MILLISECONDS);
                logger.info("sendRequest 3.2");
                // 取出最老请求的请求编号
                long rlSize = redisTemplate2.opsForList().size(requestList);
                logger.info("sendRequest 3.3 rlSize=" + rlSize + "!");
                byte[] topVal;
                if(rlSize > 0){
                    logger.info("sendRequest 3.4");
                    topVal = redisTemplate2.opsForList().range(requestList, rlSize-1, rlSize).get(0);
                    logger.info("sendRequest 3.5");
                    StringBuilder oldRequestId = new StringBuilder(REQUEST_ID_PREFIX);
                    String oldRawRequestId = new String(topVal, 0, REQUEST_ID_LEN, Charset.forName("UTF-8"));
                    oldRequestId.append(new String(topVal, 0, REQUEST_ID_LEN, Charset.forName("UTF-8")));
                    logger.info("sendRequest 3.6 oldRequestId=" + oldRequestId + "!");
                    // 如果最老请求编号在Redis中不存在，证明该请求已过期，则删除该请求，继续比较接下来的元素
                    while (redisTemplate.opsForValue().get(oldRequestId.toString()) == null) {
                        logger.info("sendRequest 3.7");
                        redisTemplate2.opsForList().rightPop(requestList);
                        logger.info("sendRequest 3.8");
                        rlSize = redisTemplate2.opsForList().size(requestList);
                        logger.info("sendRequest 3.9");
                        if(rlSize<=0)
                            break;
                        logger.info("sendRequest 3.10");
                        topVal = redisTemplate2.opsForList().range(requestList, rlSize-1, rlSize).get(0);
                        logger.info("sendRequest 3.11");
                        oldRequestId = new StringBuilder(REQUEST_ID_PREFIX);
                        oldRequestId.append(new String(topVal, 0, 36, Charset.forName("UTF-8")));
                        logger.info("sendRequest 3.12");
                    }
                }
                redisTemplate2.opsForList().leftPush(requestList, (byte[]) requestData);
            }*/
            redisTemplate2.opsForList().leftPush(requestList, (byte[]) requestData);
        }

        long startTime = System.currentTimeMillis();
        String response = null;
        do {
            try {
                Thread.sleep(3);
            } catch (InterruptedException ignore) {
            }
            response = (String) redisTemplate.opsForValue().get(requestId);
            if (response != null) {
                break;
            }
        } while (System.currentTimeMillis() - startTime < TVIS_RST_TIMEOUT);
        if (response == null) {
            //throw new RuntimeException("等待执行结果超时");
            response = "{\"timestamp\":\"2020-11-26T08:29:34.273+0000\",\"status\":404,\"error\":\"Not Found\",\"message\":\"No message available TvisImageRecogService Ln227\",\"path\":\"/vehicle/function/recognition\"}";
        }

        return response;
    }












    /**
     * 通过HTTP POST方式调用sr-tvis-server来进行图片识别
     * @param url
     * @param type
     * @param map
     * @param f
     * @return
     */
    private static String processTvisImage(String url, String type, Map<String, Object> map, File f) {
        boolean sendName = true;

        String response = null;
        try {
            if ("file".equals(type)) {
                map.put("TPXX", f);
                map.put("TPLX", "1");
                System.out.println("recognizeImageFile.recognizeImageFile 1");
                response = HttpUtil.postFile(url, map);
                System.out.println("recognizeImageFile.recognizeImageFile 2 response:" + response + "!");
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

    public static List<VehicleVo> parseTvisJson(long cameraId, String json) {
        JSONObject rstJson = JSONObject.parseObject(json);
        long streamId = rstJson.getLong("StreamID");

        // 获取特征向量
        JSONArray vehs = rstJson.getJSONArray("VEH");
        JSONObject vehJson = null;
        JSONObject cxtzJson = null;
        JSONObject wztzJson = null;
        JSONObject jsxwtzJson = null;
        JSONObject gxhtzJson = null;
        List<VehicleVo> vos = new ArrayList<>();
        VehicleVo vo = null;
        VehicleWztzVo vehicleWztzVo = null;
        VehicleHptzVO hptzVO = null;
        VehicleCxtzVo vehicleCxtzVo = null;
        VehicleCltzxlVo vehicleCltzxlVo = null;
        VehicleJsxwtzVO vehicleJsxwtzVO = null;
        VehicleGxhtzVO vehicleGxhtzVO = null;
        for (Object veh : vehs) {
            vehJson = (JSONObject) veh;
            vo = new VehicleVo();
            vo.setOccurTime(vehJson.getString("OCCUR_TIME"));
            vo.setCameraId(cameraId);
            vo.setStreamId(streamId);
            vo.setTrackId(vehJson.getLong("TRACK_ID"));
            // 位置特征解析
            vehicleWztzVo = new VehicleWztzVo();
            wztzJson = vehJson.getJSONObject("WZTZ");
            vehicleWztzVo.setPsfx(wztzJson.getString("PSFX"));
            vehicleWztzVo.setClwz(wztzJson.getString("CLWZ"));
            vo.setVehicleWztzVo(vehicleWztzVo);
            // 解析号牌特征
            vo.setVehicleHptzVO(parseHptzJson(vehJson.getJSONObject(AppConst.TJ_HPTZ)));
            // 车型特征
            vehicleCxtzVo = new VehicleCxtzVo();
            cxtzJson = vehJson.getJSONObject("CXTZ");
            vehicleCxtzVo.setCllxflCode(cxtzJson.getString("CLLXFL"));
            vehicleCxtzVo.setCllxzflCode(cxtzJson.getString("CLLXZFL"));
            vehicleCxtzVo.setClppCode(cxtzJson.getString("CLPP"));
            vehicleCxtzVo.setPpcxCode(cxtzJson.getString("PPCX"));
            vehicleCxtzVo.setCxnkCode(cxtzJson.getString("CXNK"));
            vehicleCxtzVo.setPpxhmsCode(cxtzJson.getString("PPXHMS"));
            vehicleCxtzVo.setCsysCode(cxtzJson.getString("CSYS"));
            vo.setVehicleCxtzVo(vehicleCxtzVo);
            // 车辆特征向量
            vehicleCltzxlVo = new VehicleCltzxlVo();
            vehicleCltzxlVo.setCltzxl(generateTzxl(vehJson.getString("CLTZXL")));
            vo.setVehicleCltzxlVo(vehicleCltzxlVo);
            // 驾驶行为特征
            vehicleJsxwtzVO = new VehicleJsxwtzVO();
            jsxwtzJson = vehJson.getJSONObject("JSXWTZ");
            vehicleJsxwtzVO.setFjsbjaqd(jsxwtzJson.getString("FJSBJAQD"));
            vehicleJsxwtzVO.setFjszyb(jsxwtzJson.getString("FJSZYB"));
            vehicleJsxwtzVO.setMtcbdtk(jsxwtzJson.getString("MTCBDTK"));
            vehicleJsxwtzVO.setZjsbjaqd(jsxwtzJson.getString("ZJSBJAQD"));
            vehicleJsxwtzVO.setZjscy(jsxwtzJson.getString("ZJSCY"));
            vehicleJsxwtzVO.setZjsddh(jsxwtzJson.getString("ZJSDDH"));
            vehicleJsxwtzVO.setZjsksj(jsxwtzJson.getString("ZJSKSJ"));
            vehicleJsxwtzVO.setZjszyb(jsxwtzJson.getString("ZJSZYB"));
            vo.setVehicleJsxwtzVO(vehicleJsxwtzVO);
            // 个性化特征
            vehicleGxhtzVO = new VehicleGxhtzVO();
            gxhtzJson = vehJson.getJSONObject("GXHTZ");
            vehicleGxhtzVO.setCcztw(gxhtzJson.getString("CCZTW"));
            vehicleGxhtzVO.setBj(gxhtzJson.getString("BJ"));
            vehicleGxhtzVO.setGj(gxhtzJson.getString("GJ"));
            vehicleGxhtzVO.setTc(gxhtzJson.getString("TC"));
            vehicleGxhtzVO.setXlj(gxhtzJson.getString("XLJ"));
            vehicleGxhtzVO.setDcjqs(gxhtzJson.getString("DCJQS"));
            vehicleGxhtzVO.setCszt(gxhtzJson.getString("CSZT"));
            vehicleGxhtzVO.setCsps(gxhtzJson.getString("CSPS"));
            vehicleGxhtzVO.setCsgh(gxhtzJson.getString("CSGH"));
            vehicleGxhtzVO.setCsch(gxhtzJson.getString("CSCH"));
            vo.setVehicleGxhtzVO(vehicleGxhtzVO);

            vos.add(vo);
        }
        return vos;
    }

    private static VehicleHptzVO parseHptzJson(JSONObject hptzJson) {
        VehicleHptzVO hptzVO = new VehicleHptzVO(
                hptzJson.getString(AppConst.TJ_HPTZ_HPZT),
                hptzJson.getString(AppConst.TJ_HPTZ_HPWZ),
                hptzJson.getString(AppConst.TJ_HPTZ_HPZL),
                hptzJson.getString(AppConst.TJ_HPTZ_HPYS),
                hptzJson.getString(AppConst.TJ_HPTZ_HPGG),
                hptzJson.getString(AppConst.TJ_HPTZ_HPHM),
                hptzJson.getString(AppConst.TJ_HPTZ_HPKXD),
                hptzJson.getString(AppConst.TJ_HPTZ_MWHPKXD),
                hptzJson.getString(AppConst.TJ_HPTZ_YWLSHP)
        );
        return hptzVO;
    }



    private static List<Float> generateTzxl(String vecStr) {
        List<Float> tzxl = new ArrayList<>();
        String[] arrs = vecStr.split(",");
        for (String item : arrs) {
            try {
                tzxl.add(Float.parseFloat(item));
            } catch (Exception ex) {
                tzxl.add(0.0f);
            }
        }
        return tzxl;
    }
}
