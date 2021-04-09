package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 首页路段过车统计
 */
@Component
public class DkTjrsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void notifyObserver(VehicleVO vo) {
        System.out.println("DkTjrsObserver...");
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
        redisTemplate.opsForHash().increment("dk_tjrs_road", code, 1);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000001", 1);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000002", 2);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000003", 3);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000004", 4);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000005", 5);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000006", 6);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000007", 7);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000008", 8);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000009", 9);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000010", 10);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000011", 11);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000012", 12);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000013", 13);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000014", 14);
//        redisTemplate.opsForHash().increment("dk_tjrs_road", "C0000015", 10);
    }

    @Override
    public void initialize(Environment env) {

    }
}
