package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DcHpMapper;
import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.dto.DcHpDTO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 数据中心首页
 */
@Component
public class DcHpObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DcHpMapper dcHpMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void notifyObserver(VehicleVo vo) {
        /**
         * 1.识别对象
         */
        System.out.println("DcHpObserver...");
        String time = vo.getOccurTime() == null ? LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : vo.getOccurTime();
        String hphm = vo.getVehicleHptzVO().getHphm();
        long cameraId = vo.getCameraId();
        String ilType = "";
        Integer isIl = 0;
        String cllxzfl = vo.getVehicleCxtzVo().getCllxzflCode();
        String cllxfl = vo.getVehicleCxtzVo().getCllxflCode();
        if (StringUtils.isBlank(cllxzfl)) {
            cllxzfl = cllxfl;
        }
        long tvisJsonId = vo.getTvisJsonId();
        String tblName = AppRegistry.tvisJsonTblName;
        DcHpDTO dcHpDTO = null;
        String category = "0";
        long vehIdx = vo.getVehsIdx();
        String hphm_pre = PropUtil.getHphmPre();
        if(!hphm.contains(hphm_pre)) {
            category = "1";
        }
        /**
         * cameraId = -1 时需要根据streamId查找正确的cameraId
         */
        String code = "";
        if (cameraId == -1) {
            long streamId = vo.getStreamId();
            String newCameraId = deviceMapper.getCameraIdByStreamId(streamId);
            if(StringUtils.isNotBlank(newCameraId)) {
                code = newCameraId;
            }
        } else {
            code = cameraId +"";
        }
        String zjsddh = vo.getVehicleJsxwtzVO().getZjsddh();
        if (StringUtils.isNotBlank(zjsddh) && isViolation(zjsddh)) {
            ilType = "ZJSDDH";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String fjsbjaqd = vo.getVehicleJsxwtzVO().getFjsbjaqd();
        if (StringUtils.isNotBlank(fjsbjaqd) && isViolation(fjsbjaqd)) {
            ilType = "FJSBJAQD";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String fjszyb = vo.getVehicleJsxwtzVO().getFjszyb();
        if (StringUtils.isNotBlank(fjszyb) && isViolation(fjszyb)) {
            ilType = "FJSZYB";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String zjsbjaqd = vo.getVehicleJsxwtzVO().getZjsbjaqd();
        if (StringUtils.isNotBlank(zjsbjaqd) && isViolation(zjsbjaqd)) {
            ilType = "ZJSBJAQD";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String zjscy = vo.getVehicleJsxwtzVO().getZjscy();
        if (StringUtils.isNotBlank(zjscy) && isViolation(zjscy)) {
            ilType = "ZJSCY";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String zjsksj = vo.getVehicleJsxwtzVO().getZjsksj();
        if (StringUtils.isNotBlank(zjsksj) && isViolation(zjsksj)) {
            ilType = "ZJSKSJ";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String zjszyb = vo.getVehicleJsxwtzVO().getZjszyb();
        if (StringUtils.isNotBlank(zjszyb) && isViolation(zjszyb)) {
            ilType = "ZJSZYB";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String mtcbdtk = vo.getVehicleJsxwtzVO().getMtcbdtk();
        if (StringUtils.isNotBlank(mtcbdtk) && isViolation(mtcbdtk)) {
            ilType = "MTCBDTK";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        String hType = vo.getVehicleHptzVO().getHpzt(); //牌照异常
        if (!"1".equals(hType)) {
            ilType = "HPYC";
            isIl = 1;
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
            violation(category);
        }
        if (isIl == 0) {
            dcHpDTO = new DcHpDTO(0,time,"" + code,hphm,category,"" + isIl,ilType,"", vehIdx);
            insertItfVehicle(dcHpDTO, tvisJsonId, tblName, cllxzfl);
        }
        System.out.println("DCHP所有驾驶行为：" + zjsddh + "; "+ fjsbjaqd +"; "+ fjszyb +"; " + zjsbjaqd + "; " + zjscy +"; "+ ";" +mtcbdtk + ";" +zjszyb + "; " + zjsksj);
        System.out.println("DCHP识别车辆子分类cllxzfl：" + cllxzfl + "; 违章类型：" + dcHpDTO.getIlType());
        redisTemplate.opsForValue().increment("dchp_vehicle_identification"); //车辆识别数量
        redisTemplate.opsForZSet().incrementScore("tn_vs_site_vehicle", code, 1);
        Integer index = LocalDateTime.now().getHour();
        String tnVsTrend = "tn_vs_trend_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (!redisTemplate.hasKey(tnVsTrend)) {
            redisTemplate.opsForList().rightPushAll(tnVsTrend, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }
        Integer count = (int)(redisTemplate.opsForList().index(tnVsTrend,index));
        redisTemplate.opsForList().set(tnVsTrend, index, count + 1);
    }

    @Override
    public void initialize(Environment env) {

    }
    /**
     * 驾驶行为
     * @param jsxw
     * @return
     */
    private boolean isViolation(String jsxw) {
        boolean flag = Integer.parseInt(StringUtils.isNotBlank(jsxw.replace("_", "")) ? jsxw.replace("_", "") : "0") >= 180;
        System.out.println("DCHP:" + jsxw + "="+ flag);
        return flag;
    }

    private void  insertItfVehicle(DcHpDTO dcHpDTO, long tvisJsonId, String tvisJsonTbl, String cllxzfl) {
        dcHpMapper.insertItfVehicle(dcHpDTO, tvisJsonTbl, tvisJsonId, cllxzfl);
    }

    private void violation(String category) {
        redisTemplate.opsForValue().increment("dchp_vehicle_violation"); //车辆违章数量
        if ("0".equals(category)) {
            redisTemplate.opsForValue().increment("dchp_vehicle_0_violation"); //本市车辆违章数量
        } else if ("1".equals(category)) {
            redisTemplate.opsForValue().increment("dchp_vehicle_1_violation"); //外埠车辆违章数量
        }
    }

}
