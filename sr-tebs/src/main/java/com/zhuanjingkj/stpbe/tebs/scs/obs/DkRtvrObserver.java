package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsVcMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsvssKsvrpMapper;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
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
import java.util.Random;

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

    private final static Logger logger = LoggerFactory.getLogger(DkRtvrObserver.class);

    @Override
    public void notifyObserver(VehicleVo vo) {
        boolean flag = false;
        /**
         * TODO
         *此cameraId 为测试id
         * cameraId = vo.getCameraId()
         */
        List<String> vNum = ksvssKsvrpMapper.getVTypeNum();
        String  vType = vo.getVehicleCxtzVo().getCllxzflCode();
        String tblName = AppRegistry.tvisJsonTblName;
        logger.info("tableName=" + tblName + "!");
        if (tblName == null) {
            return ;
        }
        Map<String, Object> dtMap = dkRtvrMapper.getImageHash(vo.getTvisJsonId(), tblName);
        String hphm = vo.getVehicleHptzVO().getHphm();
        long vehsIdx = vo.getVehsIdx();
        String imageHash = "";
        String jsonHash ="";
        if(StringUtils.isBlank(dtMap.get("image_hash") +"")) {
            imageHash = "QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV";
        } else {
            imageHash = dtMap.get("image_hash") +"";
        }
        if(StringUtils.isBlank(dtMap.get("json_hash") +"")) {
            jsonHash = "QmPC254a9zCN76wZhcANFSk14dLfDu3EiF2pVrYDuYRo77";
        } else {
            jsonHash = dtMap.get("json_hash") +"";
        }
        if(StringUtils.isBlank(hphm)) {
            hphm = "豫A888888";
        }
        String zjsddh = vo.getVehicleJsxwtzVO().getZjsddh();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        if(StringUtils.isNotBlank(zjsddh)) {
            String state = zjsddh.split("_")[0];
            String rb = zjsddh.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,3, hphm,"主驾驶打电话", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String fjsbjaqd = vo.getVehicleJsxwtzVO().getFjsbjaqd();
        if(StringUtils.isNotBlank(fjsbjaqd)) {
            String state = fjsbjaqd.split("_")[0];
            String rb = fjsbjaqd.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,4, hphm,"副驾驶不系安全带", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String fjszyb = vo.getVehicleJsxwtzVO().getFjszyb();
        if(StringUtils.isNotBlank(fjszyb)) {
            String state = fjszyb.split("_")[0];
            String rb = fjszyb.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,5, hphm,"副驾驶放下遮阳板", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String zjsbjaqd = vo.getVehicleJsxwtzVO().getZjsbjaqd();
        if(StringUtils.isNotBlank(zjsbjaqd)) {
            String state = zjsbjaqd.split("_")[0];
            String rb = zjsbjaqd.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,6, hphm,"主驾驶不系安全带", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String zjscy = vo.getVehicleJsxwtzVO().getZjscy();
        if(StringUtils.isNotBlank(zjscy)) {
            String state = zjscy.split("_")[0];
            String rb = zjscy.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,7, hphm,"主驾驶抽烟", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String zjsksj = vo.getVehicleJsxwtzVO().getZjsksj();
        if(StringUtils.isNotBlank(zjsksj)) {
            String state = zjsksj.split("_")[0];
            String rb = zjsksj.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,8, hphm,"主驾驶看手机", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String zjszyb = vo.getVehicleJsxwtzVO().getZjszyb();
        if(StringUtils.isNotBlank(zjszyb)) {
            String state = zjszyb.split("_")[0];
            String rb = zjszyb.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,9, hphm,"主驾驶放下遮阳板", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String mtcbdtk = vo.getVehicleJsxwtzVO().getMtcbdtk();
        if(StringUtils.isNotBlank(mtcbdtk)) {
            String state = mtcbdtk.split("_")[0];
            String rb = mtcbdtk.split("_")[1];
            if("0".equals(state) && "80".compareTo(rb) == -1) { //违章
                insertViolation(vo,10, hphm,"摩托车不戴头盔", vType, imageHash, jsonHash, tblName, date);
                flag = true;
            }
        }
        String hType = vo.getVehicleHptzVO().getHpzt(); //牌照异常
        if(!"1".equals(hType)) {
            insertViolation(vo,11, hphm,"号牌异常", vType, imageHash, jsonHash, tblName, date);
            flag = true;
        }
        //统计时段违章到redis
        int random = (int)(Math.random()*15) + 1;
        String code ="";
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

            long cameraId = vo.getCameraId();
            List<String> ksvcHphm = ksVcMapper.getKsvcHphm();
            if(random == 0) {
                random = 4;
            }
            if(random < 10) {
                code = "C000000" + random;
            } else {
                code = "C00000" + random;
            }

//            if(ksvcHphm.contains(hphm)) {
                //布控违章记录统计同一辆车在同一个设备下通过的次数
                redisTemplate.opsForHash().increment("ks_vs_ill_total",  hphm + "|" + code, 1);
                redisTemplate.opsForHash().put("ks_vs_ill_time", hphm + "|" + code, date);
                redisTemplate.opsForList().leftPush("ks_vs_ill_list", hphm + "|" + code);
//            }
        }
        //车辆布控动态

        redisTemplate.opsForHash().increment("ks_vs_dyn_total",  hphm + "|" + code, 1);
        redisTemplate.opsForHash().put("ks_vs_dyn_time", hphm + "|" + code, date);
        redisTemplate.opsForList().leftPush("ks_vs_dyn_list", hphm + "|" + code);
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
            redisTemplate.opsForList().rightPushAll("ks_vs_ill_list",0);
        }
        //布控动态
        if(!redisTemplate.hasKey("ks_vs_dny_list")) {
            redisTemplate.opsForList().rightPushAll("ks_vs_dyn_list",0);
        }
    }

    private void insertViolation(VehicleVo vo, Integer cameraId, String hphm, String wzlx, String vType, String imageHash,
                                 String jsonHash, String tblName, String date) {
        dkRtvrMapper.insertViolation(vo.getTvisJsonId(), vo.getVehsIdx(),cameraId, hphm,
                vo.getVehicleCxtzVo().getCsysCode(), vo.getVehicleCxtzVo().getClppCode(), vo.getVehicleCxtzVo().getPpcxCode(), vo.getVehicleCxtzVo().getCxnkCode(),
                vo.getVehicleWztzVo().getPsfx(), vo.getVehicleWztzVo().getClwz(), wzlx, vType, imageHash, jsonHash, tblName, date);
    }

}
