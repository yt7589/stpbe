package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VehicleTypesDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VmilsDTO;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Violation Management => Illegal Supervision
 * 违章管理=》违章监管
 */
@RestController
@RequestMapping(value = "/vm")
@CrossOrigin(origins = "*")
public class VmIlsController {

    /**
     * 违章车辆列表
     * @param platform
     * @param version
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value ="/ils/queryIllegalVehicle")
    public ResultDTO<DbQrsDTO> queryIllegalVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryIllegalVehicle_epx();
    }

    @GetMapping(value = "/ils/queryVehicleTypes")
    public ResultDTO<List<VehicleTypesDTO>> queryVehicleTypes() {
        return  queryVehicleType_exp();
    }

    @GetMapping(value = "/ils/export")
    public void ilsExport(){
        List<Object> recs = new ArrayList<>();

        recs.add(new VmilsDTO(101,"2020-12-25 16:18:52","上地南路","豫E88858","外埠","大货车","超速行驶",
                102,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(102,"2020-12-25 16:18:52","上地南路","豫E88868","外埠","大货车","超速行驶",
                103,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(103,"2020-12-25 16:18:52","上地南路","豫E88878","外埠","大货车","超速行驶",
                104,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(10,"2020-12-25 16:18:52","上地南路","豫E88888","外埠","大货车","超速行驶",
                105,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(105,"2020-12-25 16:18:52","上地南路","豫E88828","外埠","大货车","超速行驶",
                106,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(106,"2020-12-25 16:18:52","上地南路","豫E88838","外埠","大货车","超速行驶",
                107,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(107,"2020-12-25 16:18:52","上地南路","豫E88818","外埠","大货车","超速行驶",
                108,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(108,"2020-12-25 16:18:52","上地南路","豫E88838","外埠","大货车","超速行驶",
                109,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        String[] columns = {"违章编号", "时间", "地点", "车牌号" ,"类别" , "车辆类型", "违章类型编号", "违章类型", "详情"};

        List<Object> resc = recs;

        FileExpDTO fed = new FileExpDTO("违章监管" + DateUtil.getDayOfMonth(LocalDate.now()),"违章记录", columns, recs, "D://");

        FileUtil.export(fed);
    }

    private ResultDTO<DbQrsDTO> queryIllegalVehicle_epx() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,15,0,15,0,null);
        List<VmilsDTO> recs = new ArrayList<>();
        recs.add(new VmilsDTO(101,"2020-12-25 16:18:52","上地南路","豫E88858","外埠","大货车","超速行驶",
                102,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(102,"2020-12-25 16:18:52","上地南路","豫E88868","外埠","大货车","超速行驶",
                103,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(103,"2020-12-25 16:18:52","上地南路","豫E88878","外埠","大货车","超速行驶",
                104,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(10,"2020-12-25 16:18:52","上地南路","豫E88888","外埠","大货车","超速行驶",
                105,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(105,"2020-12-25 16:18:52","上地南路","豫E88828","外埠","大货车","超速行驶",
                106,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(106,"2020-12-25 16:18:52","上地南路","豫E88838","外埠","大货车","超速行驶",
                107,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(107,"2020-12-25 16:18:52","上地南路","豫E88818","外埠","大货车","超速行驶",
                108,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        recs.add(new VmilsDTO(108,"2020-12-25 16:18:52","上地南路","豫E88838","外埠","大货车","超速行驶",
                109,"http://222.128.117.234:8090/cloud/images/a002.jpg"));

        data.setRecs(recs);

        dto.setData(data);

        return dto;

    }

    private ResultDTO<List<VehicleTypesDTO>> queryVehicleType_exp() {
        ResultDTO<List<VehicleTypesDTO>> dto = new ResultDTO<>();
        List<VehicleTypesDTO> recs = new ArrayList<>();
        recs.add(new VehicleTypesDTO(101,"小轿车"));
        recs.add(new VehicleTypesDTO(102,"平板车"));
        recs.add(new VehicleTypesDTO(103,"大型客车"));
        recs.add(new VehicleTypesDTO(104,"重型货车"));
        recs.add(new VehicleTypesDTO(105,"摩托车"));
        recs.add(new VehicleTypesDTO(106,"挂车"));
        recs.add(new VehicleTypesDTO(107,"SUV"));
        recs.add(new VehicleTypesDTO(108,"普通货车"));
        recs.add(new VehicleTypesDTO(109,"MPV"));
        recs.add(new VehicleTypesDTO(110,"面包车"));
        dto.setData(recs);
        return  dto;
    }

}
