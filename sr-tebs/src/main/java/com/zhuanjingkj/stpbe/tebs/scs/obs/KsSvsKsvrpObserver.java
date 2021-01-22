package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsvssKsvrpMapper;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    @Override
    public void notifyObserver(VehicleVo vo) {
        int iDebug = 1;
        if (1 == iDebug) {
            return ;
        }
        List<String> vNum = ksvssKsvrpMapper.getVTypeNum();
        String vZtype = vo.getVehicleCxtzVo().getCllxzflCode(); //车辆类型子分类
        String vType = vo.getVehicleCxtzVo().getCllxflCode(); //车辆类型分类
        String tblName = AppRegistry.tvisJsonTblName;
        if (tblName == null) {
            return ;
        }
        Map<String, Object> dtMap = dkRtvrMapper.getImageHash(vo.getTvisJsonId(), tblName);
        String imageHash = "" + dtMap.get("image_hash");
        /**
         * 如果是重点监管车辆类型保存到redis两张实时图片 ks_ksvrp_images
         * 在本日重点监控车辆小时分布图对应时间点 ks_ksvrp_vehicle +1
         * 统计重点车辆分布点位 ks_ksvrp_site
         */
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
//          redisTemplate.opsForHash().increment("ks_ksvrp_site", vo.getCameraId(),1);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000001", 1);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000002", 2);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000003", 3);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000004", 4);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000005", 5);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000006", 6);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000007", 7);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000008", 8);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000009", 9);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000010", 10);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000011", 11);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000012", 12);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000013", 13);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000014", 14);
            redisTemplate.opsForHash().increment("ks_ksvrp_site", "C0000015", 10);
        }
        //大货车小时分布图
        if("21".equals(vType)) {
            Integer count = (int)(redisTemplate.opsForList().index("ks_ksvrp_truck",index));
            redisTemplate.opsForList().set("ks_ksvrp_vehicle", index, count + 1);  //重点监控车辆小时分布图
        }
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_ksvrp_images")) {
            redisTemplate.opsForList().rightPushAll("ks_ksvrp_images", "","");
        }

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
