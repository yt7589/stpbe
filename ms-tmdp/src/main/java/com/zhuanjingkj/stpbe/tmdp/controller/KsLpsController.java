package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
import com.zhuanjingkj.stpbe.tmdp.dto.licencePlate.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Key Supervision => License plate supervision
 * 重点监管=》牌照异常
 */
@RestController
@RequestMapping("/ks")
@CrossOrigin(origins = "*")
public class KsLpsController {

    @GetMapping(value = "/lps/queryAbnormalLicensePlate")
    public ResultDTO<KsLpsDTO> queryAbnormalLicensePlate(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<KsLpsDTO> dto = new ResultDTO<>();
        KsLpsDTO data = new KsLpsDTO();
        List<KsLpsTimeDTO> timeDTO = getTimeAbnormalLicensePlate();
        List<KsLpsAreaDTO> areaDTO = getAreaAbnormalLicensePlate();
        List<KsLpsSiteDTO> siteDTO = getSiteAbnormalLicensePlate();
        List<KsLpsLalpDTO> lalpDTO = getKsLpsLalp();
        data.setWpCount(1234567);
        data.setTpCount(123456);
        data.setJpCount(789456);
        data.setHpzdCount(123456);
        data.setLalp(lalpDTO);
        data.setArea(areaDTO);
        data.setTime(timeDTO);
        data.setSite(siteDTO);
        dto.setData(data);
        return dto;
    }

    /**
     * 时段异常牌照统计
     * @return
     */
    private List<KsLpsTimeDTO> getTimeAbnormalLicensePlate() {
        List<KsLpsTimeDTO> spsTimes = new ArrayList<>();
        for (int i = 1; i < 25; i++ ) {
            spsTimes.add(new KsLpsTimeDTO( "" + (i < 10 ? ("0" + i) : i) +":00", i * 10000));
        }
        return spsTimes;
    }

    /**
     * 区域异常牌照统计
     * @return
     */
    private List<KsLpsAreaDTO> getAreaAbnormalLicensePlate() {
        String[] areas = {"东城区","西城区","朝阳区","丰台区","石景山区","海淀区","顺义区","通州区","大兴区","房山区","门头沟区","昌平区","平谷区","密云区","怀柔区","延庆区"};
        List<KsLpsAreaDTO> lpsAreas = new ArrayList<>();
        for (int i = 0; i < areas.length; i++ ) {
            lpsAreas.add(new KsLpsAreaDTO(areas[i], i * 10000));
        }
        return lpsAreas;
    }

    /**
     * 地图点位异常牌照
     * @return
     */
    private List<KsLpsSiteDTO> getSiteAbnormalLicensePlate() {
        List<KsLpsSiteDTO> lpsSite = new ArrayList<>();
        for (int i = 1; i < 11; i++ ) {
            lpsSite.add(new KsLpsSiteDTO(i + 1, "上地" + i + "街",("豫E" + (2222 + i)),(30 + i),(116.3954 + i * 001),(40.082 + i * 0.02)));
        }
        return lpsSite;
    }

    /**
     * 最新异常车牌记录
     * @return
     */
    private List<KsLpsLalpDTO> getKsLpsLalp() {
        List<KsLpsLalpDTO> lpsSite = new ArrayList<>();
        for (int i = 1; i < 10; i++ ) {
            lpsSite.add(new KsLpsLalpDTO((28 + i),(56 + i),(100 + i), ("上地" + i +"街"),"2020-12-25 14:40:13",("豫E" + (2222 + i)),(30 + i),(25 + i),"http://222.128.117.234:9003/imgs/pzyc.png"));
        }
        return lpsSite;
    }

}
