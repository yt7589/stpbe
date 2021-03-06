package com.zhuanjingkj.stpbe.common.tvis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.common.util.DistributeRedisLock;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameVehicleDTO;
import com.zhuanjingkj.stpbe.data.vo.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TvisUtil {
    public final static String ERROR_RESPONSE = "ERROR";
    public static int TVIS_RST_TIMEOUT = 300;
    private final static Logger logger = LoggerFactory.getLogger(TvisUtil.class);

    /**
     * 上传图片返回图片JSON格式识别结果
     * @param map
     * @param f
     * @return
     */
    public static String recognizeImageFile(Map<String, Object> map, File f) {
        String type = "file";
        String url = PropUtil.getValue("TVIS_SERVER_URL");
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
        String url = PropUtil.getValue("TVIS_SERVER_BASE_URL") + PropUtil.getValue("TSC_SUBMIT_IMAGE");
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
        AppRegistry.tvisJsonTblName = PropUtil.getValue("TVIS_JSON_TBL_PREFIX") + String.format("%08d", idx+1);
        AppRegistry.tvisJsonTblRecs = 0;
        tvisJsonMapper.createTvisJsonTbl(AppRegistry.tvisJsonTblName);
    }

    public static void processRawTvisJson(RedisTemplate redisTemplate, TvisJsonMapper tvisJsonMapper, String json) {
        JSONObject jo = JSONObject.parseObject(json);
        String relativeImageFile = jo.getJSONObject("json").getString("ImageUrl");
        if (relativeImageFile==null || relativeImageFile.equals("") || relativeImageFile.length()<2) {
            return;
        }
        String imageFile = "";
        if (jo.getJSONObject("json").getString("StreamID").equals("-1")) {
            imageFile = relativeImageFile;
        } else {
            imageFile = PropUtil.getValue("VIDEO_FRAME_IMG_BASE_DIR") + relativeImageFile.substring(2);
        }
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
        String jf = PropUtil.getValue("JSON_TMP_BASE_DIR") + "json_" + System.currentTimeMillis() + "_" + rand.nextInt() + ".json";
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
            tvisJsonId = redisTemplate.opsForValue().increment(PropUtil.getValue("TVIS_JSON_TBL_ID_KEY"));
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String occurTime = df.format(new Date());
        String cameraIdStr = jo.getString("cameraId");
        long cameraId = Long.parseLong(cameraIdStr);
        JSONObject rstJo = jo.getJSONObject("json");
        String streamIdStr = rstJo.getString("StreamID");
        long streamId = -1;
        try {
            streamId = Long.parseLong(streamIdStr);
        } catch (Exception ex) {}
        String ptsStr = rstJo.getString("TimeStamp");
        long pts = 0;
        try {
            pts = Long.parseLong(ptsStr);
        } catch (Exception ex) {}
        TvisJsonVO vo = new TvisJsonVO(AppRegistry.tvisJsonTblName, tvisJsonId, occurTime,
                cameraId, streamId, pts, imageHash.toString(), jsonHash.toString());
        tvisJsonMapper.insertTvisJson(vo);
        AppRegistry.tvisJsonTblRecs++;
        if (AppRegistry.tvisJsonTblRecs >= Long.parseLong(PropUtil.getValue("TVIS_JSON_TBL_MAX_RECS"))) {
            rotateTvisJsonTbl(tvisJsonMapper);
        }
    }

    public static void processStpTvisJson(List<ITvisStpObserver> observers, String json) {
        JSONObject rawJo = JSONObject.parseObject(json);
        long tvisJsonId = rawJo.getLong("tvisJsonId");
        long cameraId = rawJo.getLong("cameraId");
        JSONObject rstJo = rawJo.getJSONObject("json");
        List<VehicleVO> vehs = TvisUtil.parseTvisJson(cameraId, rstJo.toJSONString());
        long vehsIdx = 0;
        for (VehicleVO veh : vehs) {
            veh.setTvisJsonId(tvisJsonId);
            veh.setVehsIdx(vehsIdx);
            vehsIdx++;
            AppRegistry.vehicleVOS.offer(veh);
            /*for (ITvisStpObserver obs : observers) {
                obs.notifyObserver(veh);
            }*/
        }
    }

    public static WsmVideoFrameDTO getTvisFrameAnalysisResult(TvisJsonVO tvisJsonVO,
                                                              Map<String, CameraVehicleRecordVO> cutVehs) {
        long wsmVfvvIdx = 0;
        String vaImgUrlBase = PropUtil.getValue("TMDP_BASE_URL") + "va/getVaImage?imgFn=";

        WsmVideoFrameDTO vfv = null;
        List<WsmVideoFrameVehicleDTO> wvfvvs = null;
        WsmVideoFrameVehicleDTO vfvv = null;
        if (null == tvisJsonVO) {
            return null;
        }
        long tvisJsonId = tvisJsonVO.getTvisJsonId();
        // 获取图片
        BufferedImage orgImg = TvisSodImage.downloadIpfsImage(tvisJsonVO.getImageHash());
        // 获取JSON结果
        String jsonStr = IpfsClient.getTextFile(tvisJsonVO.getJsonHash());
        JSONObject jo = JSONObject.parseObject(jsonStr);
        JSONObject joRst = jo.getJSONObject("json");
        List<VehicleVO> vehs = TvisUtil.parseTvisJson(jo.getLong("cameraId"), joRst.toJSONString());
        // 在图像上绘制一个矩形框并保存到当前目录下
        CameraVehicleRecordVO vo = null;
        int x, y, w, h; // 检测框位置
        int idx = 0;
        int currentArea = 0;
        int maxArea = 0;
        String cutFileFn = null;
        File cutFileObj = null;
        String imgBaseFolder = "images/";
        String orgFileFn = "n_" + tvisJsonId + ".jpg";
        vfv = new WsmVideoFrameDTO(tvisJsonVO.getTvisJsonId(), tvisJsonVO.getPts(), vaImgUrlBase + orgFileFn);
        wvfvvs = vfv.getData();
        for (VehicleVO veh : vehs) {
            String clwz = veh.getVehicleWztzVo().getClwz();
            String[] arrs = clwz.split(",");
            x = Integer.parseInt(arrs[0]);
            if (x<0) { x = 0; }
            y = Integer.parseInt(arrs[1]);
            if (y < 0) { y = 0; }
            w = Integer.parseInt(arrs[2]);
            h = Integer.parseInt(arrs[3]);
            if (w<=0 || h<=0) {
                continue;
            }
            currentArea = w * h;
            if (!cutVehs.containsKey("" + veh.getTrackId())) {
                vo = new CameraVehicleRecordVO();
                vo.setTvisJsonId(veh.getTvisJsonId());
                vo.setVehsIdx((int)veh.getVehsIdx());
                vo.setSxh((int)veh.getVehsIdx());
                vo.setX(x);
                vo.setY(y);
                vo.setW(w);
                vo.setH(h);
                vo.setOrgImgFn(orgFileFn);
                cutVehs.put("" + veh.getTrackId(), vo);
            } else {
                vo = cutVehs.get("" + veh.getTrackId());
            }
            maxArea = vo.getArea();
            if (1>0 || currentArea >= maxArea) {
                maxArea = currentArea;
                BufferedImage vehImg = orgImg.getSubimage(x, y, w, h);
                try {
                    cutFileFn = "c_" + tvisJsonId + "_" + idx + ".jpg";
                    cutFileObj = new File(imgBaseFolder + cutFileFn);
                    ImageIO.write(vehImg, "jpg", cutFileObj);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("cutFileFn:" + e);
                }
                vo.setX(x);
                vo.setY(y);
                vo.setW(w);
                vo.setH(h);
                vo.setCutImgFn(cutFileFn);
            }
            vfvv = new WsmVideoFrameVehicleDTO(wsmVfvvIdx++, veh.getTrackId(), idx,
                    veh.getVehicleCxtzVo().getPpcxCode(),
                    veh.getVehicleHptzVO().getHphm(), vaImgUrlBase + vo.getCutImgFn(),
                    "50秒前", "无", veh.getVehicleCxtzVo().getPpxhmsCode(),
                    veh.getVehicleWztzVo().getPsfx(), veh.getVehicleCxtzVo().getCllxflCode(),
                    veh.getVehicleCxtzVo().getCsysCode());
            wvfvvs.add(vfvv);
            idx++;
        }
        for (VehicleVO veh : vehs) {
            String clwz = veh.getVehicleWztzVo().getClwz();
            String[] arrs = clwz.split(",");
            x = Integer.parseInt(arrs[0]);
            if (x < 0) {
                x = 0;
            }
            y = Integer.parseInt(arrs[1]);
            if (y < 0) {
                y = 0;
            }
            w = Integer.parseInt(arrs[2]);
            h = Integer.parseInt(arrs[3]);
            if (w <= 0 || h <= 0) {
                continue;
            }
            //
            TvisSodImage.drawRect(orgImg, Color.RED, x, y, w, h);
            // 车型特征
            String ppxhms = veh.getVehicleCxtzVo().getPpxhmsCode();
            String hphm = veh.getVehicleHptzVO().getHphm();
            TvisSodImage.drawString(orgImg, Font.BOLD, 50,
                    Color.RED, x, y + 3, hphm + ":" + ppxhms);
        }
        try {
            ImageIO.write(orgImg, "jpg", new File(imgBaseFolder + orgFileFn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 生成一个定制的URL，可以通过SpringBoot来查看图片内容
        vfv.setData(wvfvvs);
        return vfv;
    }

    public static WsmVideoFrameDTO getTvisVideoAnalysisResult(TvisJsonMapper tvisJsonMapper,
                                                              List<String> streamIds,
                                                              Map<String, CameraVehicleRecordVO> cutVehs,
                                                              long streamId) {
        if (StringUtils.isBlank(AppRegistry.tvisJsonTblName)) {
            // 获取当前t_tvis_json_*表名
            AppRegistry.tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        }
        TvisJsonVO tvisJsonVO = tvisJsonMapper.getLatestStreamFrame(AppRegistry.tvisJsonTblName, streamId);
        if (null == tvisJsonVO) {
            return null;
        }
        return getTvisFrameAnalysisResult(tvisJsonVO, cutVehs);
    }

    public static WsmVideoFrameDTO getTvisImageAnalysisResult(TvisJsonMapper tvisJsonMapper,
                                                              long cameraId, long baseTvisJsonId,
                                                              long direction) {
        if (StringUtils.isBlank(AppRegistry.tvisJsonTblName)) {
            // 获取当前t_tvis_json_*表名
            AppRegistry.tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        }
        System.out.println("##### tbl=" + AppRegistry.tvisJsonTblName + "; baseTvisJsonId=" + baseTvisJsonId + "!");
        TvisJsonVO tvisJsonVO = null;
        if (baseTvisJsonId<0 || 0 == direction) {
            System.out.println("##### get current snapshot...");
            tvisJsonVO = tvisJsonMapper.getLatestCameraFrame(AppRegistry.tvisJsonTblName, cameraId);
        } else {
            if (1 == direction) {
                System.out.println("##### get previous snapshot...");
                tvisJsonVO = tvisJsonMapper.getPrevCameraFrame(AppRegistry.tvisJsonTblName, cameraId, baseTvisJsonId);
            } else if (2 == direction) {
                System.out.println("##### get next snapshot...");
                tvisJsonVO = tvisJsonMapper.getNextCameraFrame(AppRegistry.tvisJsonTblName, cameraId, baseTvisJsonId);
            }
        }
        System.out.println("##### vo=" + tvisJsonVO + "!!!!!!!!!!!!!!!");
        Map<String, CameraVehicleRecordVO> cutVehs = new HashMap<>();
        WsmVideoFrameDTO vfv = getTvisFrameAnalysisResult(tvisJsonVO, cutVehs);
        // 转变为图像识别结果模式
        return vfv;
    }

    /**
     * 获取指定tvisJsonId所对应的t_tvis_json_*表中记录的值对象，首先取出所有t_tvis_json_*表名
     * 列表，由新到老排列，依次从这些表中取tvisJsonId的记录，如果有则返回，如果都没有查到则返回null
     * @param tvisJsonMapper
     * @param tvisJsonId
     * @return
     */
    public static TvisJsonVO getTvisJsonVOById(TvisJsonMapper tvisJsonMapper, long tvisJsonId) {
        List<String> tbls = tvisJsonMapper.getTvisJsonTblNames();
        TvisJsonVO vo = null;
        for (String tbl : tbls) {
            vo = tvisJsonMapper.getFrameByTvisJsonId(tbl, tvisJsonId);
            if (vo != null) {
                return vo;
            }
        }
        return vo;
    }


    private final static String LOCK_NAME_TVIS_WAIT_NUM = "tvis.wait_num";
    private final static String KEY_CONCURRENT_WAIT_NUM = "concurrentWaitNum";
    private static int getWaitNumRedis(RedisTemplate redisTemplate) {
        try {
            if (DistributeRedisLock.lock(LOCK_NAME_TVIS_WAIT_NUM)) {
                if (redisTemplate.opsForValue().get(KEY_CONCURRENT_WAIT_NUM) == null) {
                    redisTemplate.opsForValue().set(KEY_CONCURRENT_WAIT_NUM, "0");
                    DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
                    return 0;
                } else {
                    int num = Integer.parseInt((String) redisTemplate.opsForValue().get("concurrentWaitNum"));
                    if (num < 0) {
                        redisTemplate.opsForValue().set(KEY_CONCURRENT_WAIT_NUM, "0");
                    }
                    DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
                    return num;
                }
            }
        } catch (Exception ex) {
            System.out.println("       $$$$$$$$ getWaitNumRedis 6");
            DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
        }
        return 0;
    }

    private static int increaseWaitNumRedis(RedisTemplate redisTemplate) {
        try {
            if (DistributeRedisLock.lock(LOCK_NAME_TVIS_WAIT_NUM)) {
                int num = getWaitNumRedis(redisTemplate);
                if (num < 0) {
                    DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
                    return -2;
                }
                num++;
                redisTemplate.opsForValue().set("", "" + num);
                DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
                return num;
            }
        } catch (Exception ex) {
            DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
        }
        return -3;
    }

    private static int decreaseWaitNumRedis(RedisTemplate redisTemplate) {
        try {
            if (DistributeRedisLock.lock(LOCK_NAME_TVIS_WAIT_NUM)) {
                int num = getWaitNumRedis(redisTemplate);
                if (num < 0) {
                    DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
                    return -4;
                }
                num--;
                if (num >= 0) {
                    redisTemplate.opsForValue().set(KEY_CONCURRENT_WAIT_NUM, "" + num);
                }
                DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
                return num;
            }
        } catch (Exception ex) {
            DistributeRedisLock.unlock(LOCK_NAME_TVIS_WAIT_NUM);
        }
        return -5;
    }


    private final static String REQUEST_ID_PREFIX = "a_";
    private static int MAX_WAIT_NUM = -1;
    public static String sendRequest(RedisTemplate redisTemplate, RedisTemplate<String, byte[]> redisTemplate2, String requestList, String requestId, Object requestData) {
        String response = null;
        // 根据负载进行限流
        if (MAX_WAIT_NUM < 0) {
            MAX_WAIT_NUM = Integer.parseInt(PropUtil.getValue("tvis.max-wait-num"));
        }
        //int concurrentWaitNum = getWaitNumRedis(redisTemplate);
        if (redisTemplate.boundListOps(requestList).size() > MAX_WAIT_NUM) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
            //System.out.println("    Ln435 ######################### num=" + concurrentWaitNum + "!");
            return "{\"timestamp\":\"" +
                    df.format(Calendar.getInstance().getTime()) + "\"," +
                    "\"status\":505,\"error\":\"queue overflow\"," +
                    "\"message\":\"系统繁忙，请稍后再试！\"}";
        }
        /*if (concurrentWaitNum < 0) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
            System.out.println("    Ln435 !!!!!!!!!!!!!!!!!!!!!! num=" + concurrentWaitNum + "!");
            return "{\"timestamp\":\"" +
                    df.format(Calendar.getInstance().getTime()) + "\"," +
                    "\"status\":506,\"error\":\"Cannot lock\"," +
                    "\"message\":\"无法获取读写锁！\"}";
        }
        if (concurrentWaitNum >= MAX_WAIT_NUM) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
            System.out.println("    Ln435 ######################### num=" + concurrentWaitNum + "!");
            return "{\"timestamp\":\"" +
                    df.format(Calendar.getInstance().getTime()) + "\"," +
                    "\"status\":505,\"error\":\"queue overflow\"," +
                    "\"message\":\"系统繁忙，请稍后再试！\"}";
        }*/
        // 具体处理请求
        if (requestData instanceof String) {
            redisTemplate.opsForList().leftPush(requestList, (String) requestData);
        } else {
            redisTemplate2.opsForList().leftPush(requestList, (byte[]) requestData);
        }
        // ！！！！！ 测试程序，正式环境下需保持注释掉状态 ！！！！！！
        //prepareXaidrRst(redisTemplate, requestList, requestId);
        long startTime = System.currentTimeMillis();
        //concurrentWaitNum = increaseWaitNumRedis(redisTemplate);
        //System.out.println("    " + Thread.currentThread().getId() + ": Ln447 n2=" + concurrentWaitNum + "!");
        do {
            try {
                Thread.sleep(3); // 睡3毫秒读取识别结果
            } catch (InterruptedException ignore) {
            }
            response = (String) redisTemplate.opsForValue().get(requestId);
            if (response != null) {
                //concurrentWaitNum = decreaseWaitNumRedis(redisTemplate);
                //System.out.println("       " + Thread.currentThread().getId() + " @@@@@@@@@@@@@@@@@@@@@@@ n1=" + concurrentWaitNum + "   @@@@@@@@@@@@@@@@@@@@@@@@@@");
                break;
            }
        } while (System.currentTimeMillis() - startTime < TVIS_RST_TIMEOUT);
        if (response == null) {
            //throw new RuntimeException("等待执行结果超时");
            /*concurrentWaitNum = decreaseWaitNumRedis(redisTemplate);
            System.out.println("    " + Thread.currentThread().getId() + ": Ln463 n3=" + concurrentWaitNum + "!");*/
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
            response = "{\"timestamp\":" +
                    "\"" + df.format(Calendar.getInstance().getTime()) + "\",\"status\":404," +
                    "\"error\":\"Timeout\"," +
                    "\"message\":\"请求超时，请稍后再试！\"}";
        }

        return response;
    }

    private static boolean isFirstRun = true;
    private static List<ITvisStpObserver> observers = new ArrayList<>();
    public static RecognizeTvisImageDTO submitTvisImage(Environment environment, StringRedisTemplate redisTemplate,
                                                        RedisTemplate<String, byte[]> redisTemplate2,
                                                        TvisJsonMapper tvisJsonMapper,
                                                        TvisStpOberverManager tvisStpOberverManager,
                                                        String redis_request_queue,
                                                        String cameraId, String streamId, String imageFile,
                                                        byte[] imageData) {
        String rawResp = TvisUtil.sendByteRequest(redisTemplate, redisTemplate2, redis_request_queue, imageData);
        JSONObject jo = JSONObject.parseObject(rawResp);
        jo.put("ImageUrl", imageFile);
        jo.put("StreamID", streamId);
        String response = jo.toJSONString();
        long tvisJsonId = 0;
        StringBuilder msg = null;
        synchronized (redisTemplate) {
            tvisJsonId = redisTemplate.opsForValue().increment(PropUtil.getValue("TVIS_JSON_TBL_ID_KEY"));
            msg = new StringBuilder("{\"cameraId\":" + cameraId + ", \"tvisJsonId\": "
                    + tvisJsonId + ", \"json\": " + response + "}");
        }
        RecognizeTvisImageDTO data = new RecognizeTvisImageDTO();
        if(org.apache.commons.lang3.StringUtils.equals(response,"0")){
            data.setTvisJsonId(-1);
            return data;
        }
        if (isFirstRun) {
            //TvisUtil.rotateTvisJsonTbl(tvisJsonMapper);
            tvisStpOberverManager.initialize(observers, environment);
            isFirstRun = false;
        }
        TvisUtil.processRawTvisJson(redisTemplate, tvisJsonMapper, msg.toString());
        TvisUtil.processStpTvisJson(observers, msg.toString());
        data.setTvisJsonId(tvisJsonId);
        data.setJsonResult(rawResp);
        return data;
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
        logger.info("### TvisUtil.processTvisImage 1");
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
            logger.info("### exception: " + ex.getMessage() + "!");
            return ERROR_RESPONSE;
        }
        if (isSuccessRequest(response)) {
            return response;
        } else {
            logger.info("### error response:" + response + "!");
            return ERROR_RESPONSE;
        }
    }

    private static boolean isSuccessRequest(String response) {
        try {
            JSONObject json = JSONObject.parseObject(response); //JSONUtil.parseObj(response);
            Integer code = json.getIntValue("code"); //json.getInt("CODE");
            if (Integer.valueOf(0).equals(code)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static List<VehicleVO> parseTvisJson(long cameraId, String json) {
        JSONObject rstJson = JSONObject.parseObject(json);
        long streamId = rstJson.getLong("StreamID");

        // 获取特征向量
        JSONArray vehs = rstJson.getJSONArray("VEH");
        JSONObject vehJson = null;
        JSONObject cxtzJson = null;
        JSONObject wztzJson = null;
        JSONObject jsxwtzJson = null;
        JSONObject gxhtzJson = null;
        List<VehicleVO> vos = new ArrayList<>();
        VehicleVO vo = null;
        VehicleWztzVO vehicleWztzVo = null;
        VehicleHptzVO hptzVO = null;
        VehicleCxtzVO vehicleCxtzVo = null;
        VehicleCltzxlVO vehicleCltzxlVo = null;
        VehicleJsxwtzVO vehicleJsxwtzVO = null;
        VehicleGxhtzVO vehicleGxhtzVO = null;
        int vehIdx = 0;
        if (vehs != null) {
            for (Object veh : vehs) {
                vehJson = (JSONObject) veh;
                vo = new VehicleVO();
                vo.setVehsIdx(vehIdx++);
                vo.setOccurTime(vehJson.getString("OCCUR_TIME"));
                vo.setCameraId(cameraId);
                vo.setStreamId(streamId);
                if (vehJson.getLong("TRACK_ID") != null) {
                    vo.setTrackId(vehJson.getLong("TRACK_ID"));
                }
                // 位置特征解析
                vehicleWztzVo = new VehicleWztzVO();
                wztzJson = vehJson.getJSONObject("WZTZ");
                vehicleWztzVo.setPsfx(wztzJson.getString("PSFX"));
                vehicleWztzVo.setClwz(wztzJson.getString("CLWZ"));
                vo.setVehicleWztzVo(vehicleWztzVo);
                // 解析号牌特征
                vo.setVehicleHptzVO(parseHptzJson(vehJson.getJSONObject(PropUtil.getValue("TJ_HPTZ"))));
                // 车型特征
                vehicleCxtzVo = new VehicleCxtzVO();
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
                vehicleCltzxlVo = new VehicleCltzxlVO();
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
        }
        return vos;
    }

    private static VehicleHptzVO parseHptzJson(JSONObject hptzJson) {
        VehicleHptzVO hptzVO = new VehicleHptzVO(
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPZT")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPWZ")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPZL")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPYS")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPGG")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPHM")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_HPKXD")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_MWHPKXD")),
                hptzJson.getString(PropUtil.getValue("TJ_HPTZ_YWLSHP"))
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

    private static void prepareXaidrRst(RedisTemplate redisTemplate, String requestList, String requestId) {
        JSONArray arr = new JSONArray();
        JSONObject item = null;
        //
        item = new JSONObject();
        item.put("SXH", 1);
        item.put("MBWZ", "101,102,103,104");
        item.put("MBKXD", "99");
        arr.add(item);
        //
        item.put("SXH", 2);
        item.put("MBWZ", "201,202,203,204");
        item.put("MBKXD", "98");
        arr.add(item);
        //
        item.put("SXH", 3);
        item.put("MBWZ", "301,302,303,304");
        item.put("MBKXD", "97");
        arr.add(item);
        redisTemplate.opsForValue().set(requestId, arr.toString());
    }

    /**
     * 无锡所2021年2月新接口测试临时测试代码
     */
    private static void prepareWxs2102TestRst(RedisTemplate redisTemplate, String requestList, String requestId) {
        Object obj = redisTemplate.opsForList().rightPop(requestList);
        System.out.println("req:" + obj + "; type=" + obj.getClass().getCanonicalName() + "!");
        if (requestList.equals("truckRecog")) {
            generateTruckRecogResult(redisTemplate, requestId);
        } else if (requestList.equals("carryPerson")) {
            generateCarryPersonResult(redisTemplate, requestId);
        } else if (requestList.equals("bigPlate")) {
            generateBigPlateResult(redisTemplate, requestId);
        } else if (requestList.equals("motorClassify")) {
            generateMotorClassifyResult(redisTemplate, requestId);
        }
    }

    private static void generateTruckRecogResult(RedisTemplate redisTemplate, String requestId) {
        org.json.JSONObject jo = new org.json.JSONObject();
        jo.put("CLLX", "211");
        jo.put("CLLXKXD", "99");
        jo.put("YWFD", "1");
        jo.put("YWFDKXD", "98");
        jo.put("YWPG", "1");
        jo.put("YWPGKXD", "97");
        redisTemplate.opsForValue().set(requestId, jo.toString());
    }

    private static void generateCarryPersonResult(RedisTemplate redisTemplate, String requestId) {
        org.json.JSONObject jo = new org.json.JSONObject();
        jo.put("WZ", "101,102,103,104:201,202,203,204");
        redisTemplate.opsForValue().set(requestId, jo.toString());
    }

    private static void generateBigPlateResult(RedisTemplate redisTemplate, String requestId) {
        org.json.JSONObject jo = new org.json.JSONObject();
        jo.put("YWFDH", "1");
        jo.put("FDHWZ", "100, 200, 300, 400");
        jo.put("HPHM", "京A-XY123");
        jo.put("KXD", "99");
        redisTemplate.opsForValue().set(requestId, jo.toString());
    }

    private static void generateMotorClassifyResult(RedisTemplate redisTemplate, String requestId) {
        org.json.JSONObject jo = new org.json.JSONObject();
        jo.put("LX", "3");
        redisTemplate.opsForValue().set(requestId, jo.toString());
    }
}
