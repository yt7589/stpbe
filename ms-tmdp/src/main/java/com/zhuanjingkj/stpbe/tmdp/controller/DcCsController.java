package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcCsDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Center =》 Car Search
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcCsController {

    /**
     * 以图搜车
     * @param platform
     * @param version
     * @param startTime
     * @param endTime
     * @param image
     * @return
     */
    @GetMapping(value = "/cs/queryVehicle")
    public ResultDTO<DbQrsDTO> queryVehicle(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime,
        @RequestParam(name = "image", required = false) String image
    ) {
        return queryVehicle_exp();
    }

    private ResultDTO<DbQrsDTO> queryVehicle_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,20,0,20,0,null);
        List<DcCsDTO> recs = new ArrayList<>();
        recs.add(new DcCsDTO(102,"北京市海淀区西二旗","2020-12-01 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(103,"北京市海淀区上地","2020-12-02 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(104,"北京市海淀区西直门","2020-12-03 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(105,"北京市海淀区知春路","2020-12-04 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(106,"北京市朝阳区东湖区","2020-12-05 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(107,"北京市昌平区北七家","2020-12-06 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(108,"北京市望京","2020-12-07 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(109,"北京市海淀区回龙观","2020-12-08 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(110,"北京市海淀区龙泽","2020-12-09 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(111,"北京市海淀区西北旺","2020-12-10 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(112,"北京市海淀区霍营","2020-12-12 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        recs.add(new DcCsDTO(113,"北京市海淀区魏公村","2020-12-11 15:11:26","http://222.128.117.234:8090/www/images/ytsc_001.jpg"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}
