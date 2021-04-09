package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsvssKsvrpMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class KsSvsKsvrpObserver implements ITvisStpObserver {

    @Autowired
    private KsvssKsvrpMapper ksvssKsvrpMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void notifyObserver(VehicleVO vo) {
        System.out.println("KsSvsKsvrpObserver...");
        List<String> vNum = ksvssKsvrpMapper.getVTypeNum(); //重点监控车辆编号
        String vZtype = vo.getVehicleCxtzVo().getCllxzflCode(); //车辆类型子分类
        String vType = vo.getVehicleCxtzVo().getCllxflCode(); //车辆类型分类
        String tblName = AppRegistry.tvisJsonTblName;
        if (StringUtils.isBlank(tblName)) {
            return ;
        }
        /**
         * cameraId = -1 时需要根据streamId查找正确的cameraId
         */
        long cameraId = vo.getCameraId();
        String code = "";
        if(cameraId == -1) {
            long streamId = vo.getStreamId();
            String newCameraId = deviceMapper.getCameraIdByStreamId(streamId);
            if(StringUtils.isNotBlank(newCameraId)) {
                code = newCameraId;
            }
        } else {
            code = cameraId + "";
        }
        Map<String, Object> dtMap = dkRtvrMapper.getImageHash(vo.getTvisJsonId(), tblName);
        String imageHash = "" + dtMap.get("image_hash");
        /**
         * 如果是重点监管车辆类型保存到redis两张实时图片 ks_ksvrp_images
         * 在本日重点监控车辆小时分布图对应时间点 ks_ksvrp_vehicle +1
         * 统计重点车辆分布点位 ks_ksvrp_site
         */
        String ym = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        Integer hour = LocalDateTime.now().getHour();
        Integer index = 0;
        if(hour % 2 == 0) {
            index = (hour + 2)/2 - 1;
        } else {
            index = (hour + 1)/2 - 1;
        }
        if(vNum.contains(vZtype)) {
            Integer count = (int)(redisTemplate.opsForList().index("ks_ksvrp_vehicle",index));
            redisTemplate.opsForList().rightPush("ks_ksvrp_images", imageHash); //重点监控车辆实时图片
            redisTemplate.opsForList().set("ks_ksvrp_vehicle", index, count + 1);  //重点监控车辆小时分布图
            //重点监控车辆点位分布图
            redisTemplate.opsForHash().increment("ks_ksvrp_site", code,1);
            //重点监管车辆数量统计
            redisTemplate.opsForValue().increment("dcst_key_vehicle", 1);
            //重点车辆点位排名TOP7
            redisTemplate.opsForZSet().incrementScore("dcst_top7_site_" + ym, code, 1);
        }
        //大货车小时分布图
        if("21".equals(vType)) {
            Integer count = (int)(redisTemplate.opsForList().index("ks_ksvrp_truck",index));
            redisTemplate.opsForList().set("ks_ksvrp_truck", index, count + 1);  //重点监控车辆小时分布图
            //大货车数量统计
            redisTemplate.opsForValue().increment("dcst_key_truck", 1);
            //大货车点位统计
            redisTemplate.opsForZSet().incrementScore("dc_st_truck",code,1);
        }
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_ksvrp_images")) {
            redisTemplate.opsForList().rightPushAll("ks_ksvrp_images", "","");
        }

        if(!redisTemplate.hasKey("ks_ksvrp_vehicle")) {
            redisTemplate.opsForList().rightPushAll("ks_ksvrp_vehicle", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(!redisTemplate.hasKey("ks_ksvrp_truck")) {
            redisTemplate.opsForList().rightPushAll("ks_ksvrp_truck", 0,0,0,0,0,0,0,0,0,0,0,0);
        }
    }
}
