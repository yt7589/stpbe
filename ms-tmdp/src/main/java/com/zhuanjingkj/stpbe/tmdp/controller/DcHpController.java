package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpDaDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpIlTrendDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpRgTrendDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Center =》 Home Page
 * 数据中心 =》 全部数据
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcHpController {

    /**
     * 数据记录列表
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/hp/queryAllData")
    public ResultDTO<DbQrsDTO> queryAllData(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "direction", required = false) Integer direction,
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime,
        @RequestParam(name = "category", required = false) String category,
        @RequestParam(name = "vType", required = false) String vType,
        @RequestParam(name = "ilType", required = false) String ilType,
        @RequestParam(name = "hphm", required = false) String hphm,
        @RequestParam(name = "fcId", required = false) String fcId,
        @RequestParam(name = "vAddr", required = false) String vAddr
    ){
        return queryAllData_exp(startIndex, amount, direction);
    }

    /**
     * 数据走势
     * @return
     */
    @GetMapping(value = "/hp/queryDataAnalysis")
    public ResultDTO<DcHpDaDTO> queryDataAnalysis() {
        ResultDTO<DcHpDaDTO> dto = new ResultDTO<>();
        DcHpDaDTO data = new DcHpDaDTO();
        List<DcHpIlTrendDTO> dit = getDit_exp();
        List<DcHpRgTrendDTO> drt = getDrt_exp();
        data.setDit(dit);
        data.setDrt(drt);
        data.setTotal_recognition(20000);
        data.setTotal_violation(10000);
        data.setTotal_violation_city(5600);
        data.setTotal_violation_town(4400);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> queryAllData_exp(int startIndex, int amount, Integer direction) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(180,10,0,10,0,null);
        List<DcHpDTO> recs = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            recs.add(new DcHpDTO(startIndex+i,"2020-12-29 14:50:03",
                    "北京" + startIndex + "-" + i,"京A-"+ startIndex + "-" + i,
                    "外埠","是","违章",
                    "http://222.128.117.234:8090/cloud/images/a002.jpg"));
        }
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private List<DcHpIlTrendDTO> getDit_exp() {
        List<DcHpIlTrendDTO> dit = new ArrayList<>();
        for(int i = 0; i < 31; i++) {
            dit.add(new DcHpIlTrendDTO(""+(i+1),(2300000 + i * 100000)));
        }
        return dit;
    }

    private List<DcHpRgTrendDTO> getDrt_exp() {
        List<DcHpRgTrendDTO> drt = new ArrayList<>();
        for(int i = 0; i < 31; i++) {
            drt.add(new DcHpRgTrendDTO(""+(i+1),(2300000 + i * 100000)));
        }
        return drt;
    }

}
