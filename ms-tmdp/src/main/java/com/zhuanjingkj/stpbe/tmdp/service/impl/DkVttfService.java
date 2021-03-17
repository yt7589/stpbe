package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfSeriesDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfSeriesItemDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVttfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DkVttfService implements IDkVttfService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DkVttfDTO getDkVttf() {
        return null;
    }

    @Override
    public List<DkVttfSeriesDTO> getDkVttfSeriesDTOs_exp() {
        List<DkVttfSeriesDTO> seriesDTOS = new ArrayList<>();
        DkVttfSeriesDTO seriesDTO = new DkVttfSeriesDTO();
        List<DkVttfSeriesItemDTO> items = null;
        DkVttfSeriesItemDTO item = null;
        List<Integer> lcar = redisTemplate.opsForList().range("dk_vttf_lcar",0,11);
        List<Integer> car = redisTemplate.opsForList().range("dk_vttf_car",0,11);
        List<Integer> scar = redisTemplate.opsForList().range("dk_vttf_scar",0,11);
        if (lcar != null && lcar.size() > 0) {
            // 生成大型车数据
            items = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                item = new DkVttfSeriesItemDTO("" + i * 2, lcar.get(i - 1));
                items.add(item);
            }
//            item = new DkVttfSeriesItemDTO("4", lcar.get(1));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("6", lcar.get(2));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("8", lcar.get(3));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("10", lcar.get(4));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("12", lcar.get(5));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("14", lcar.get(6));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("16", lcar.get(7));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("18", lcar.get(8));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("20", lcar.get(9));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("22", lcar.get(10));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("24", lcar.get(11));
//            items.add(item);
            seriesDTO = new DkVttfSeriesDTO();
            seriesDTO.setSeriesName("大型车");
            seriesDTO.setDatas(items);
            seriesDTOS.add(seriesDTO);
        } else {
            // 生成大型车数据
            items = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                item = new DkVttfSeriesItemDTO("" + i * 2, 0);
                items.add(item);
            }
            seriesDTO = new DkVttfSeriesDTO();
            seriesDTO.setSeriesName("大型车");
            seriesDTO.setDatas(items);
            seriesDTOS.add(seriesDTO);
        }

        if (car != null && car.size() > 0) {
            // 生成中型车数据
            items = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                item = new DkVttfSeriesItemDTO("" + i * 2, car.get(i - 1));
                items.add(item);
            }

//            item = new DkVttfSeriesItemDTO("4", car.get(1));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("6", car.get(2));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("8", car.get(3));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("10", car.get(4));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("12", car.get(5));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("14", car.get(6));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("16", car.get(7));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("18", car.get(8));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("20", car.get(9));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("22", car.get(10));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("24", car.get(11));
//            items.add(item);
            seriesDTO = new DkVttfSeriesDTO();
            seriesDTO.setSeriesName("中型车");
            seriesDTO.setDatas(items);
            seriesDTOS.add(seriesDTO);
        } else {
            // 生成中型车数据
            items = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                item = new DkVttfSeriesItemDTO("" + i * 2, 0);
                items.add(item);
            }
            seriesDTO = new DkVttfSeriesDTO();
            seriesDTO.setSeriesName("中型车");
            seriesDTO.setDatas(items);
            seriesDTOS.add(seriesDTO);
        }

        if (scar != null && scar.size() > 0) {
            // 生成小型车数据
            items = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                item = new DkVttfSeriesItemDTO("" + i * 2, scar.get(i - 1));
                items.add(item);
            }
//            item = new DkVttfSeriesItemDTO("4", scar.get(1));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("6", scar.get(2));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("8", scar.get(3));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("10", scar.get(4));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("12", scar.get(5));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("14", scar.get(6));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("16", scar.get(7));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("18", scar.get(8));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("20", scar.get(9));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("22", scar.get(10));
//            items.add(item);
//            item = new DkVttfSeriesItemDTO("24", scar.get(11));
//            items.add(item);
            seriesDTO = new DkVttfSeriesDTO();
            seriesDTO.setSeriesName("小型车");
            seriesDTO.setDatas(items);
            seriesDTOS.add(seriesDTO);
        } else {
            items = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                item = new DkVttfSeriesItemDTO("" + i * 2, 0);
                items.add(item);
            }
            seriesDTO = new DkVttfSeriesDTO();
            seriesDTO.setSeriesName("小型车");
            seriesDTO.setDatas(items);
            seriesDTOS.add(seriesDTO);
        }
        return seriesDTOS;
    }
}
