package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsVcMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsvssKsvrpMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * 首页违章数据统计
 */
@Component
public class DkRtvrObserver implements ITvisStpObserver {

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KsvssKsvrpMapper ksvssKsvrpMapper;

    @Autowired
    private KsVcMapper ksVcMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    private final static Logger logger = LoggerFactory.getLogger(DkRtvrObserver.class);

    @Override
    public void notifyObserver(VehicleVo vo) {
        /**
         * TODO
         *此cameraId 为测试id
         * cameraId = vo.getCameraId()
         */
        System.out.println("DkRtvrObserver...");
        boolean flag = false;
        List<String> vNum = ksvssKsvrpMapper.getVTypeNum();
        String  vType = vo.getVehicleCxtzVo().getCllxzflCode();
        String tblName = AppRegistry.tvisJsonTblName;
        if (StringUtils.isBlank(tblName)) {
            return ;
        }

//        Map<String, Object> dtMap = dkRtvrMapper.getImageHash(vo.getTvisJsonId(), tblName);
        logger.info("###### tblName=" + tblName + "; tvisJsonId=" + vo.getTvisJsonId() + "!");

        Map<String, Object> dtMap = dkRtvrMapper.getImageHash(vo.getTvisJsonId(), tblName);

        String hphm = vo.getVehicleHptzVO().getHphm();
        /**
         * cameraId = -1 时需要根据streamId查找正确的cameraId
         */
        String code ="";
        long cameraId = vo.getCameraId();
        if(cameraId == -1) {
            long streamId = vo.getStreamId();
            String newCameraId = deviceMapper.getCameraIdByStreamId(streamId);
            if(StringUtils.isNotBlank(newCameraId)) {
                code = newCameraId;
            }
        } else if(StringUtils.isNotBlank(cameraId + "")){
            code = cameraId + "";
        } else {
            return;
        }
        String imageHash = "";
        if(StringUtils.isNotBlank(dtMap.get("image_hash") +"")) {
            imageHash = dtMap.get("image_hash") +"";
        }
        Integer category = 0;
        String hphm_pre = PropUtil.getHphmPre();
        if(!hphm.contains(hphm_pre)) {
            category = 1;
        }
        if(StringUtils.isBlank(hphm)) {
            hphm = "NULL";
        }
        String zjsddh = vo.getVehicleJsxwtzVO().getZjsddh();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        if(StringUtils.isNotBlank(zjsddh)) {
            if(isViolation(zjsddh)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"ZJSDDH", vType, tblName, category, date);
                flag = true;
            }
        }
        String fjsbjaqd = vo.getVehicleJsxwtzVO().getFjsbjaqd();
        if(StringUtils.isNotBlank(fjsbjaqd)) {
            if(isViolation(fjsbjaqd)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"FJSBJAQD", vType, tblName, category, date);
                flag = true;
            }
        }
        String fjszyb = vo.getVehicleJsxwtzVO().getFjszyb();
        if(StringUtils.isNotBlank(fjszyb)) {
            if(isViolation(fjszyb)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"FJSZYB", vType, tblName, category, date);
                flag = true;
            }
        }
        String zjsbjaqd = vo.getVehicleJsxwtzVO().getZjsbjaqd();
        if(StringUtils.isNotBlank(zjsbjaqd)) {
            if(isViolation(zjsbjaqd)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"ZJSBJAQD", vType, tblName, category, date);
                flag = true;
            }
        }
        String zjscy = vo.getVehicleJsxwtzVO().getZjscy();
        if(StringUtils.isNotBlank(zjscy)) {
            if(isViolation(zjscy)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"ZJSCY", vType, tblName, category, date);
                flag = true;
            }
        }
        String zjsksj = vo.getVehicleJsxwtzVO().getZjsksj();
        if(StringUtils.isNotBlank(zjsksj)) {
            if(isViolation(zjsksj)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"ZJSKSJ", vType, tblName, category, date);
                flag = true;
            }
        }
        String zjszyb = vo.getVehicleJsxwtzVO().getZjszyb();
        if(StringUtils.isNotBlank(zjszyb)) {
            if(isViolation(zjszyb)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"ZJSZYB", vType, tblName, category, date);
                flag = true;
            }
        }
        String mtcbdtk = vo.getVehicleJsxwtzVO().getMtcbdtk();
        if(StringUtils.isNotBlank(mtcbdtk)) {
            if(isViolation(mtcbdtk)) { //违章
                insertViolation(vo,Long.parseLong(code), hphm,"MTCBDTK", vType, tblName, category, date);
                flag = true;
            }
        }
        String hType = vo.getVehicleHptzVO().getHpzt(); //牌照异常
        if(!"1".equals(hType)) {
            insertViolation(vo,Long.parseLong(code), hphm,"HPYC", vType, tblName, category, date);
            flag = true;
        }
        //统计时段违章到redis
        if(flag) {
            Integer hour = LocalDateTime.now().getHour();
            if(hour >= 0 && hour < 2) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",0));
                redisTemplate.opsForList().set("dk_rtvr_violation", 0, count + 1);
            } else if(hour >= 2 && hour < 4) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",1));
                redisTemplate.opsForList().set("dk_rtvr_violation", 1, count + 1);
            } else if(hour >= 4 && hour < 6) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",2));
                redisTemplate.opsForList().set("dk_rtvr_violation", 2, count + 1);
            } else if(hour >= 6 && hour < 8) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",3));
                redisTemplate.opsForList().set("dk_rtvr_violation", 3, count + 1);
            } else if(hour >= 8 && hour < 10) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",4));
                redisTemplate.opsForList().set("dk_rtvr_violation", 4, count + 1);
            } else if(hour >= 10 && hour < 12) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",5));
                redisTemplate.opsForList().set("dk_rtvr_violation", 5, count + 1);
            } else if(hour >= 12 && hour < 14) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",6));
                redisTemplate.opsForList().set("dk_rtvr_violation", 6, count + 1);
            } else if(hour >= 14 && hour < 16) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",7));
                redisTemplate.opsForList().set("dk_rtvr_violation", 7, count + 1);
            } else if(hour >= 16 && hour < 18) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",8));
                redisTemplate.opsForList().set("dk_rtvr_violation", 8, count + 1);
            } else if(hour >= 18 && hour < 20) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",9));
                redisTemplate.opsForList().set("dk_rtvr_violation", 9, count + 1);
            } else if(hour >= 20 && hour < 22) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",10));
                redisTemplate.opsForList().set("dk_rtvr_violation", 10, count + 1);
            } else if(hour >= 22 && hour < 24) {
                Integer count = (int)(redisTemplate.opsForList().index("dk_rtvr_violation",11));
                redisTemplate.opsForList().set("dk_rtvr_violation", 11, count + 1);
            }

            if(vNum.contains(vo.getVehicleCxtzVo().getCllxzflCode())) {
                redisTemplate.opsForList().rightPush("ks_ksvtvrps_images", imageHash); //重点监控车辆实时图片
            }
            List<String> ksvcHphm = ksVcMapper.getKsvcHphm();
            if(ksvcHphm.contains(hphm)) {
                //布控违章记录统计同一辆车在同一个设备下通过的次数
                redisTemplate.opsForHash().increment("ks_vs_ill_total",  hphm + "|" + code, 1);
                redisTemplate.opsForHash().put("ks_vs_ill_time", hphm + "|" + code, date);
                redisTemplate.opsForList().leftPush("ks_vs_ill_list", hphm + "|" + code);
                redisTemplate.opsForValue().increment("dcst_vehicle_alerts", 1);
            }
        }
        //车辆布控动态
        redisTemplate.opsForHash().increment("ks_vs_dyn_total",  hphm + "|" + code, 1);
        redisTemplate.opsForHash().put("ks_vs_dyn_time", hphm + "|" + code, date);
        redisTemplate.opsForList().leftPush("ks_vs_dyn_list", hphm + "|" + code);
        //数据中心车辆识别统计
        redisTemplate.opsForValue().increment("dcst_vehicle_total");  //过车总数
        if(category == 0) {
            redisTemplate.opsForValue().increment("dcst_category_0_total");  //本市
        } else if(category == 1) {
            redisTemplate.opsForValue().increment("dcst_category_1_total");  //外埠
        }
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("dk_rtvr_violation")) {
            redisTemplate.opsForList().rightPushAll("dk_rtvr_violation", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(!redisTemplate.hasKey("ks_ksvtvrps_images")) {
            redisTemplate.opsForList().rightPushAll("ks_ksvtvrps_images", "","");
        }
        //布控报告
        if(!redisTemplate.hasKey("ks_vs_ill_list")) {
            redisTemplate.opsForList().rightPushAll("ks_vs_ill_list",0 + "|" + 0);
        }
        //布控动态
        if(!redisTemplate.hasKey("ks_vs_dny_list")) {
            redisTemplate.opsForList().rightPushAll("ks_vs_dyn_list",0 + "|" + 0);
        }
    }

    /**
     * 驾驶行为
     * @param jsxw
     * @return
     */
    private boolean isViolation(String jsxw) {
        return Integer.parseInt(StringUtils.isNotBlank(jsxw.replace("_", "")) ? jsxw.replace("_", "") : "0") >= 180;
    }
    private void insertViolation(VehicleVo vo, long cameraId, String hphm, String wzlx, String vType, /**String imageHash,
                                 String jsonHash,*/ String tblName, Integer category, String date) {
        String psfx = vo.getVehicleWztzVo().getPsfx();
        if (null == psfx || psfx.equals("")) {
            psfx = "3";
        }
        dkRtvrMapper.insertViolation(vo.getTvisJsonId(), vo.getVehsIdx(),cameraId, hphm,
                vo.getVehicleCxtzVo().getCsysCode(), vo.getVehicleCxtzVo().getClppCode(), vo.getVehicleCxtzVo().getPpcxCode(), vo.getVehicleCxtzVo().getCxnkCode(),
                psfx, vo.getVehicleWztzVo().getClwz(), wzlx, vType, category, tblName, date);
    }

}
