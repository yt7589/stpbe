package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.*;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 车辆类型
     * @return
     */
    @GetMapping(value = "/ils/queryVehicleTypes")
    public ResultDTO<List<VehicleTypesDTO>> queryVehicleTypes() {
        return  queryVehicleType_exp();
    }

    /**
     * 违章数据导出
     * @param response
     */
    @GetMapping(value = "/ils/export")
    public void ilsExport(HttpServletResponse response){
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

        FileUtil.export(response, fed);
    }

    /**
     * 违章分类统计
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/ils/queryIllegalDistribution")
    public ResultDTO<IlsTopDTO> queryIllegalDistribution(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<IlsTopDTO> dto = new ResultDTO<>();
        IlsTopDTO data = new IlsTopDTO();
        List<IlsTopAreaDTO> ilsArea = queryIllArea();
        List<IlsTopSiteDTO> ilsSite = queryIllSite();
        data.setIlsAreaDTO(ilsArea);
        data.setIlsSiteDTO(ilsSite);
        dto.setData(data);
        return dto;
    }

    /**
     * 右侧违章列表
     * @param platform
     * @param version
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value ="/ils/queryIllegal")
    public ResultDTO<DbQrsDTO> queryIllegal(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryIllegal_exp();
    }

    /**
     * 违章分布地图点位
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value ="/ils/querySiteIllegal")
    public ResultDTO<DbQrsDTO> querySiteIllegal(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return querySiteIllegal_exp();
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

    private List<IlsTopAreaDTO> queryIllArea() {
       List<IlsTopAreaDTO> ilsArea = new ArrayList<>();
       ilsArea.add(new IlsTopAreaDTO(102,"西二旗",1500000));
       ilsArea.add(new IlsTopAreaDTO(103,"上地1街",1500000));
       ilsArea.add(new IlsTopAreaDTO(104,"上地2街",1500000));
       ilsArea.add(new IlsTopAreaDTO(105,"上地3街",1500000));
       ilsArea.add(new IlsTopAreaDTO(106,"上地4街",1500000));
       ilsArea.add(new IlsTopAreaDTO(107,"上地5街",1500000));
       ilsArea.add(new IlsTopAreaDTO(108,"上地6街",1500000));
       ilsArea.add(new IlsTopAreaDTO(109,"上地7街",1500000));
       ilsArea.add(new IlsTopAreaDTO(101,"上地8街",1500000));
       ilsArea.add(new IlsTopAreaDTO(110,"上地9街",1500000));
       return ilsArea;
    }

    private List<IlsTopSiteDTO> queryIllSite() {
        List<IlsTopSiteDTO> ilsSite = new ArrayList<>();
        ilsSite.add(new IlsTopSiteDTO(102,"西二旗",1500000));
        ilsSite.add(new IlsTopSiteDTO(103,"上地1街",1500000));
        ilsSite.add(new IlsTopSiteDTO(104,"上地2街",1500000));
        ilsSite.add(new IlsTopSiteDTO(105,"上地3街",1500000));
        ilsSite.add(new IlsTopSiteDTO(106,"上地4街",1500000));
        ilsSite.add(new IlsTopSiteDTO(107,"上地5街",1500000));
        ilsSite.add(new IlsTopSiteDTO(108,"上地6街",1500000));
        ilsSite.add(new IlsTopSiteDTO(109,"上地7街",1500000));
        ilsSite.add(new IlsTopSiteDTO(101,"上地8街",1500000));
        ilsSite.add(new IlsTopSiteDTO(110,"上地9街",1500000));
        return ilsSite;
    }

    private ResultDTO<DbQrsDTO> queryIllegal_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(102,10,0,20,0,null);
        List<IlsDTO> recs = new ArrayList<>();
        recs.add(new IlsDTO(101,"北京市海淀区上地8街12号",1000000));
        recs.add(new IlsDTO(102,"北京市海淀区上地8街13号",1000000));
        recs.add(new IlsDTO(103,"北京市海淀区上地8街14号",1000000));
        recs.add(new IlsDTO(104,"北京市海淀区上地8街15号",1000000));
        recs.add(new IlsDTO(105,"北京市海淀区上地8街16号",1000000));
        recs.add(new IlsDTO(106,"北京市海淀区上地8街17号",1000000));
        recs.add(new IlsDTO(107,"北京市海淀区上地8街18号",1000000));
        recs.add(new IlsDTO(108,"北京市海淀区上地8街19号",1000000));
        recs.add(new IlsDTO(109,"北京市海淀区上地8街20号",1000000));
        recs.add(new IlsDTO(110,"北京市海淀区上地8街21号",1000000));
        recs.add(new IlsDTO(111,"北京市海淀区上地8街22号",1000000));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> querySiteIllegal_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,15,0,15,0,null);
        List<IlsSiteDTO> recs = new ArrayList<>();
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街12号",50.9875,90.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街13号",89.9875,80.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街14号",98.9875,70.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街15号",92.9875,60.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街16号",96.9875,79.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街17号",53.9875,59.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街18号",57.9875,20.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街19号",59.9875,82.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街20号",60.9875,45.9875,180000));
        recs.add(new IlsSiteDTO(101,"北京市海淀区上地8街21号",90.9875,80.9875,180000));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}
