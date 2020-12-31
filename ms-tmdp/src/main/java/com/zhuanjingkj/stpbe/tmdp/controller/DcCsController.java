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
        recs.add(new DcCsDTO(102,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(103,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(104,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(105,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(106,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(107,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(108,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(109,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(110,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(111,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(112,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        recs.add(new DcCsDTO(113,"海淀中关村大街1号","2020-12-30 15:11:26","http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}
