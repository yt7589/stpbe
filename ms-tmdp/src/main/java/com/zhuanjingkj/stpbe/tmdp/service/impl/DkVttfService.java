package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfSeriesDTO;
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
