package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfSeriesDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfSeriesItemDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVttfService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DkVttfService implements IDkVttfService {
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
        // 生成大型车数据
        items = new ArrayList<>();
        item = new DkVttfSeriesItemDTO("2", 1120000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("4", 1230000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("6", 1350000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("8", 1660000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("10", 1590000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("12", 1480000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("14", 1280000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("16", 1390000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("18", 1980000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("20", 1720000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("22", 1520000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("24", 1330000);
        items.add(item);
        seriesDTO = new DkVttfSeriesDTO();
        seriesDTO.setSeriesName("大型车");
        seriesDTO.setDatas(items);
        seriesDTOS.add(seriesDTO);
        // 生成中型车数据
        items = new ArrayList<>();
        item = new DkVttfSeriesItemDTO("2", 1520000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("4", 1730000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("6", 1650000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("8", 1860000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("10", 1790000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("12", 1380000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("14", 1180000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("16", 1690000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("18", 1780000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("20", 2120000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("22", 2520000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("24", 2330000);
        items.add(item);
        seriesDTO = new DkVttfSeriesDTO();
        seriesDTO.setSeriesName("中型车");
        seriesDTO.setDatas(items);
        seriesDTOS.add(seriesDTO);
        // 生成小型车数据
        items = new ArrayList<>();
        item = new DkVttfSeriesItemDTO("2", 520000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("4", 730000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("6", 950000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("8", 3860000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("10", 2790000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("12", 1080000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("14", 980000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("16", 1690000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("18", 2780000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("20", 4120000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("22", 2520000);
        items.add(item);
        item = new DkVttfSeriesItemDTO("24", 330000);
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
