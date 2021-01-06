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
        // 生成大型车数据
        items = new ArrayList<>();
        item = new DkVttfSeriesItemDTO("2", lcar.get(0));
        items.add(item);
        item = new DkVttfSeriesItemDTO("4", lcar.get(1));
        items.add(item);
        item = new DkVttfSeriesItemDTO("6", lcar.get(2));
        items.add(item);
        item = new DkVttfSeriesItemDTO("8", lcar.get(3));
        items.add(item);
        item = new DkVttfSeriesItemDTO("10", lcar.get(4));
        items.add(item);
        item = new DkVttfSeriesItemDTO("12", lcar.get(5));
        items.add(item);
        item = new DkVttfSeriesItemDTO("14", lcar.get(6));
        items.add(item);
        item = new DkVttfSeriesItemDTO("16", lcar.get(7));
        items.add(item);
        item = new DkVttfSeriesItemDTO("18", lcar.get(8));
        items.add(item);
        item = new DkVttfSeriesItemDTO("20", lcar.get(9));
        items.add(item);
        item = new DkVttfSeriesItemDTO("22", lcar.get(10));
        items.add(item);
        item = new DkVttfSeriesItemDTO("24", lcar.get(11));
        items.add(item);
        seriesDTO = new DkVttfSeriesDTO();
        seriesDTO.setSeriesName("大型车");
        seriesDTO.setDatas(items);
        seriesDTOS.add(seriesDTO);
        // 生成中型车数据
        items = new ArrayList<>();
        item = new DkVttfSeriesItemDTO("2", car.get(0));
        items.add(item);
        item = new DkVttfSeriesItemDTO("4", car.get(1));
        items.add(item);
        item = new DkVttfSeriesItemDTO("6", car.get(2));
        items.add(item);
        item = new DkVttfSeriesItemDTO("8", car.get(3));
        items.add(item);
        item = new DkVttfSeriesItemDTO("10", car.get(4));
        items.add(item);
        item = new DkVttfSeriesItemDTO("12", car.get(5));
        items.add(item);
        item = new DkVttfSeriesItemDTO("14", car.get(6));
        items.add(item);
        item = new DkVttfSeriesItemDTO("16", car.get(7));
        items.add(item);
        item = new DkVttfSeriesItemDTO("18", car.get(8));
        items.add(item);
        item = new DkVttfSeriesItemDTO("20", car.get(9));
        items.add(item);
        item = new DkVttfSeriesItemDTO("22", car.get(10));
        items.add(item);
        item = new DkVttfSeriesItemDTO("24", car.get(11));
        items.add(item);
        seriesDTO = new DkVttfSeriesDTO();
        seriesDTO.setSeriesName("中型车");
        seriesDTO.setDatas(items);
        seriesDTOS.add(seriesDTO);
        // 生成小型车数据
        items = new ArrayList<>();
        item = new DkVttfSeriesItemDTO("2", scar.get(0));
        items.add(item);
        item = new DkVttfSeriesItemDTO("4", scar.get(1));
        items.add(item);
        item = new DkVttfSeriesItemDTO("6", scar.get(2));
        items.add(item);
        item = new DkVttfSeriesItemDTO("8", scar.get(3));
        items.add(item);
        item = new DkVttfSeriesItemDTO("10", scar.get(4));
        items.add(item);
        item = new DkVttfSeriesItemDTO("12", scar.get(5));
        items.add(item);
        item = new DkVttfSeriesItemDTO("14", scar.get(6));
        items.add(item);
        item = new DkVttfSeriesItemDTO("16", scar.get(7));
        items.add(item);
        item = new DkVttfSeriesItemDTO("18", scar.get(8));
        items.add(item);
        item = new DkVttfSeriesItemDTO("20", scar.get(9));
        items.add(item);
        item = new DkVttfSeriesItemDTO("22", scar.get(10));
        items.add(item);
        item = new DkVttfSeriesItemDTO("24", scar.get(11));
        items.add(item);
        seriesDTO = new DkVttfSeriesDTO();
        seriesDTO.setSeriesName("小型车");
        seriesDTO.setDatas(items);
        seriesDTOS.add(seriesDTO);
        return seriesDTOS;
    }
    //@Override
    /*public DkVttfDTO getDkVttf() {
        return getDkVttfExp();
    }*/






    /*public DkVttfDTO getDkVttfExp() {
        List<DkVttfSeriesDTO> serieses = new ArrayList<>();
        // 小型车
        DkVttfSeriesDTO small = new DkVttfSeriesDTO();
        small.setSeriesName("小型车");
        String[] smallX = {
                "0", "2", "4", "6", "8", "10",
                "12", "14", "16", "18", "20",
                "22", "24"
        };
        String[] smallY = {
                "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12"
        };
        small.setX(smallX);
        small.setY(smallY);
        serieses.add(small);
        // 中型车
        DkVttfSeriesDTO middle = new DkVttfSeriesDTO();
        middle.setSeriesName("中型车");
        String[] middleX = {
                "0", "2", "4", "6", "8", "10",
                "12", "14", "16", "18", "20",
                "22", "24"
        };
        String[] middleY = {
                "201", "202", "203", "204", "205", "206",
                "207", "208", "209", "210", "211", "212"
        };
        middle.setX(middleX);
        middle.setY(middleY);
        serieses.add(middle);
        // 中型车
        DkVttfSeriesDTO large = new DkVttfSeriesDTO();
        large.setSeriesName("大型车");
        String[] largeX = {
                "0", "2", "4", "6", "8", "10",
                "12", "14", "16", "18", "20",
                "22", "24"
        };
        String[] largeY = {
                "201", "202", "203", "204", "205", "206",
                "207", "208", "209", "210", "211", "212"
        };
        large.setX(largeX);
        large.setY(largeY);
        serieses.add(large);
        //
        DkVttfDTO dkVttf = new DkVttfDTO();
        dkVttf.setSerieses(serieses);
        return dkVttf;
    }*/
}
