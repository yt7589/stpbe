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
 * 违章管理 =》 违章监管
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
    public ResultDTO<List<VmIlsVehicleTypesDTO>> queryVehicleTypes(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVehicleType_exp();
    }

    /**
     * 违章类型
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/ils/queryIlsTypes")
    public ResultDTO<List<VmIlsTypeDTO>> queryIlsTypes(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryIlsTypes_exp();
    }
    /**
     * 违章车辆详情
     * @param platform
     * @param version
     * @param ilId
     * @return
     */
    @GetMapping(value = "/ils/queryIlsDat")
    public ResultDTO<VmIlsVdDTO> queryIlsDat(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "ilId", required = false) Integer ilId
    ) {
        return queryIlsDat_exp();
    }
    /**
     * 车辆违章历史
     * @param platform
     * @param version
     * @param hphm 车牌
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value = "/ils/queryIlsVehicleHistoric")
    public ResultDTO<DbQrsDTO> queryIlsVehicleHistoric(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "hphm", required = false) String hphm,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryIlsVehicleHistoric_exp();
    }


    /**
     *
     * 车辆信息及违章历史分类统计
     * @param platform
     * @param version
     * @param hphm  车牌
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value = "/ils/queryIlsVsInfo")
    public ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "hphm", required = false) String hphm,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryIlsVsInfo_exp();
    }
    /**
     * 违章数据导出
     * @param response
     */
    @GetMapping(value = "/ils/export")
    public void ilsExport(HttpServletResponse response){
        List<VmIlsDTO> recs = new ArrayList<VmIlsDTO>();

        recs.add(new VmIlsDTO(101,"2020-12-25 16:18:52","北京市海淀区西二旗街道19号","京A88858","外埠","轿车","副驾驶放下遮阳板",
                102,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(102,"2020-12-25 16:18:52","北京市海淀区上地街道39号","津E88868","本市","轿车","副驾驶不系安全带",
                103,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(103,"2020-12-25 16:18:52","北京市海淀区西直门街道29号","冀E48878","外埠","SUV","主驾驶放下遮阳板",
                104,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(10,"2020-12-25 16:18:52","北京市海淀区知春路街道109号","豫E88888","本市","MPV","主驾驶抽烟",
                105,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(105,"2020-12-25 16:18:52","北京市朝阳区东湖区99号","沪E88828","外埠","面包车","主驾驶不系安全带",
                106,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(106,"2020-12-25 16:18:52","北京市昌平区北七家街道21号","豫A88838","本市","罐式货车","主驾驶看手机",
                107,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(107,"2020-12-25 16:18:52","北京市望京街道59号","豫B88818","外埠","箱式货车","主驾驶打电话",
                108,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(108,"2020-12-25 16:18:52","北京市海淀区回龙观39号","黑E88838","本市","栏板式货车","超速行驶",
                109,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        String[] columns = {"违章编号", "时间", "地点", "车牌号" ,"类别" , "车辆类型", "违章类型编号", "违章类型", "详情"};

        FileExpDTO fed = new FileExpDTO("违章监管" + DateUtil.getDayOfMonth(LocalDate.now()),"违章记录", columns, recs, "D://");

        FileUtil.export(response, fed);
    }

    /**
     * 违章车辆历史数据导出
     * @param response
     */
    @GetMapping(value = "/ils/exportvs")
    public void exportvs(HttpServletResponse response){
        List<VmIlsVhsDTO> recs = new ArrayList<>();

        recs.add(new VmIlsVhsDTO(105,"2020-12-21 12:56:43","北京市海淀区西二旗街道19号","主驾驶打电话",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(106,"2020-12-22 11:56:43","北京市海淀区上地街道39号","主驾驶看手机",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(107,"2020-12-23 10:56:43","北京市海淀区西直门街道29号","主驾驶不系安全带",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(108,"2020-12-24 12:56:43","北京市海淀区知春路街道109号","主驾驶抽烟",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(109,"2020-12-25 03:04:43","北京市朝阳区东湖区99号","主驾驶放下遮阳板",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(110,"2020-12-26 05:56:43","北京市昌平区北七家街道21号","副驾驶不系安全带",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(111,"2020-12-27 06:56:43","北京市望京街道59号","副驾驶放下遮阳板",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(112,"2020-12-28 12:56:43","北京市海淀区回龙观39号","副驾驶放下遮阳板",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsVhsDTO(113,"2020-12-29 14:56:43","北京市海淀区上龙泽23号","副驾驶放下遮阳板",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        String[] columns = {"违章编号", "时间", "地点", "违章类型", "详情"};

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
    public ResultDTO<VmIlsTopDTO> queryIllegalDistribution(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        ResultDTO<VmIlsTopDTO> dto = new ResultDTO<>();
        VmIlsTopDTO data = new VmIlsTopDTO();
        List<VmIlsTopAreaDTO> ilsArea = queryIllArea();
        List<VmIlsTopSiteDTO> ilsSite = queryIllSite();
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
        List<VmIlsDTO> recs = new ArrayList<>();
        recs.add(new VmIlsDTO(101,"2020-12-25 16:18:52","海淀区西二旗","鲁QWV357","外埠","轿车","副驾驶放下遮阳板",
                102,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(102,"2020-12-25 16:18:52","海淀区上地南路","贵QWV357","本市","SUV","副驾驶不系安全带",
                103,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(103,"2020-12-25 16:18:52","海淀区上地西里","赣QWV357","外埠","MPV","主驾驶放下遮阳板",
                104,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(10,"2020-12-25 16:18:52","海淀区知春路","豫AF52301X","本市","面包车","主驾驶抽烟",
                105,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(105,"2020-12-25 16:18:52","朝阳区望京","沪ALE49","本市","罐式货车","主驾驶不系安全带",
                106,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(106,"2020-12-25 16:18:52","朝阳区大屯路","苏GWNS67","外埠","箱式货车","主驾驶看手机",
                107,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(107,"2020-12-25 16:18:52","昌平区回南路","鲁GWM567","本市","栏板式货车","主驾驶打电话",
                108,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        recs.add(new VmIlsDTO(108,"2020-12-25 16:18:52","朝阳区关庄","豫AF52301X","本市","平板式货车","超速行驶",
                109,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));

        data.setRecs(recs);

        dto.setData(data);

        return dto;

    }

    private ResultDTO<List<VmIlsVehicleTypesDTO>> queryVehicleType_exp() {
        ResultDTO<List<VmIlsVehicleTypesDTO>> dto = new ResultDTO<>();
        List<VmIlsVehicleTypesDTO> recs = new ArrayList<>();
        recs.add(new VmIlsVehicleTypesDTO(101,"小轿车"));
        recs.add(new VmIlsVehicleTypesDTO(102,"平板车"));
        recs.add(new VmIlsVehicleTypesDTO(103,"大型客车"));
        recs.add(new VmIlsVehicleTypesDTO(104,"重型货车"));
        recs.add(new VmIlsVehicleTypesDTO(105,"摩托车"));
        recs.add(new VmIlsVehicleTypesDTO(106,"挂车"));
        recs.add(new VmIlsVehicleTypesDTO(107,"SUV"));
        recs.add(new VmIlsVehicleTypesDTO(108,"普通货车"));
        recs.add(new VmIlsVehicleTypesDTO(109,"MPV"));
        recs.add(new VmIlsVehicleTypesDTO(110,"面包车"));
        dto.setData(recs);
        return  dto;
    }

    private ResultDTO<List<VmIlsTypeDTO>> queryIlsTypes_exp() {
        ResultDTO<List<VmIlsTypeDTO>> dto = new ResultDTO<>();
        List<VmIlsTypeDTO> ilsType = new ArrayList<>();
        ilsType.add(new VmIlsTypeDTO(104,"闯红灯"));
        ilsType.add(new VmIlsTypeDTO(105,"行车打电话"));
        ilsType.add(new VmIlsTypeDTO(106,"副驾驶不系安全带"));
        ilsType.add(new VmIlsTypeDTO(107,"违反限行"));
        ilsType.add(new VmIlsTypeDTO(108,"逆行"));
        ilsType.add(new VmIlsTypeDTO(109,"未随车携带行驶证"));
        ilsType.add(new VmIlsTypeDTO(110,"未随车携带驾驶证"));
        ilsType.add(new VmIlsTypeDTO(111,"使用汽车吊车牵引车辆"));
        ilsType.add(new VmIlsTypeDTO(112,"牵引摩托车"));
        dto.setData(ilsType);
        return dto;
    }
    private List<VmIlsTopAreaDTO> queryIllArea() {
       List<VmIlsTopAreaDTO> ilsArea = new ArrayList<>();
       ilsArea.add(new VmIlsTopAreaDTO(102,"西二旗",1100000));
       ilsArea.add(new VmIlsTopAreaDTO(103,"望京",1200000));
       ilsArea.add(new VmIlsTopAreaDTO(104,"东湖区",1300000));
       ilsArea.add(new VmIlsTopAreaDTO(105,"来广营",1400000));
       ilsArea.add(new VmIlsTopAreaDTO(106,"西三旗",1500000));
       ilsArea.add(new VmIlsTopAreaDTO(107,"东直门",1600000));
       ilsArea.add(new VmIlsTopAreaDTO(108,"西直门",1700000));
       ilsArea.add(new VmIlsTopAreaDTO(109,"大钟寺",1800000));
       ilsArea.add(new VmIlsTopAreaDTO(101,"知春路",1900000));
       ilsArea.add(new VmIlsTopAreaDTO(110,"安河桥北",1200000));
       return ilsArea;
    }

    private List<VmIlsTopSiteDTO> queryIllSite() {
        List<VmIlsTopSiteDTO> ilsSite = new ArrayList<>();
        ilsSite.add(new VmIlsTopSiteDTO(102,"西二旗",1100000));
        ilsSite.add(new VmIlsTopSiteDTO(103,"望京",1200000));
        ilsSite.add(new VmIlsTopSiteDTO(104,"东湖区",1300000));
        ilsSite.add(new VmIlsTopSiteDTO(105,"来广营",1400000));
        ilsSite.add(new VmIlsTopSiteDTO(106,"西直门",1500000));
        ilsSite.add(new VmIlsTopSiteDTO(107,"西三旗",1600000));
        ilsSite.add(new VmIlsTopSiteDTO(108,"东湖区",1700000));
        ilsSite.add(new VmIlsTopSiteDTO(109,"望京",1800000));
        ilsSite.add(new VmIlsTopSiteDTO(101,"西二旗",1900000));
        ilsSite.add(new VmIlsTopSiteDTO(110,"安河桥北",2000000));
        return ilsSite;
    }

    private ResultDTO<DbQrsDTO> queryIllegal_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(102,10,0,20,0,null);
        List<VmIlssDTO> recs = new ArrayList<>();
        recs.add(new VmIlssDTO(101,"北京市海淀区上地8街12号",1100000));
        recs.add(new VmIlssDTO(102,"北京市海淀区西二旗8街13号",1200000));
        recs.add(new VmIlssDTO(103,"北京市海淀区龙泽8街14号",1300000));
        recs.add(new VmIlssDTO(104,"北京市海淀区回龙观8街15号",1400000));
        recs.add(new VmIlssDTO(105,"北京市朝阳区望京8街16号",1500000));
        recs.add(new VmIlssDTO(106,"北京市朝阳区东湖渠8街17号",1600000));
        recs.add(new VmIlssDTO(107,"北京市朝阳区来广营8街18号",1700000));
        recs.add(new VmIlssDTO(108,"北京市昌平区北七家8街19号",1800000));
        recs.add(new VmIlssDTO(109,"北京市昌平区小汤山8街20号",1900000));
        recs.add(new VmIlssDTO(110,"北京市海淀区知春路8街21号",2000000));
        recs.add(new VmIlssDTO(111,"北京市东城区东四十条8街22号",21000000));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> querySiteIllegal_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,15,0,15,0,null);
        List<VmIlsSiteDTO> recs = new ArrayList<>();
        recs.add(new VmIlsSiteDTO(101,"北京市海淀区上地8街12号",116.085471,40.085471,110000));
        recs.add(new VmIlsSiteDTO(102,"北京市海淀区西二旗8街13号",116.185471,40.096541,120000));
        recs.add(new VmIlsSiteDTO(103,"北京市海淀区龙泽8街14号",116.285471,40.06584,130000));
        recs.add(new VmIlsSiteDTO(104,"北京市海淀区回龙观8街15号",116.385471,40.056284,140000));
        recs.add(new VmIlsSiteDTO(105,"北京市朝阳区望京8街16号",116.485471,40.024885,150000));
        recs.add(new VmIlsSiteDTO(106,"北京市朝阳区来广营8街18号",116.585471,40.058414,160000));
        recs.add(new VmIlsSiteDTO(107,"北京市昌平区北七家8街19号",116.685471,40.058964,170000));
        recs.add(new VmIlsSiteDTO(108,"北京市东城区东四十条8街22号",116.785471,40.058954,180000));
        recs.add(new VmIlsSiteDTO(109,"北京市海淀区知春路8街21号",116.885471,40.058741,190000));
        recs.add(new VmIlsSiteDTO(110,"北京市昌平区小汤山8街20号",116.985471,40.052695,200000));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> queryIlsVehicleHistoric_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,15,0,15,0,null);
        List<VmIlsVhsDTO> recs = new ArrayList<>();
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市昌平区小汤山8街20号","主驾驶未系安全带",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市海淀区知春路8街21号","主驾驶抽烟",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市昌平区北七家8街19号","主驾驶打电话",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市朝阳区来广营8街18号","主驾驶放下遮阳板",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市朝阳区望京8街16号","主驾驶看手机",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市海淀区西二旗8街13号","主驾驶放下遮阳板",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市海淀区上地8街12号","主驾驶抽烟",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市昌平区小汤山8街20号","主驾驶打电话",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        recs.add(new VmIlsVhsDTO(105,"2020-12-28 12:56:43","北京市海淀区回龙观8街15号","主驾驶看手机",
                "http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo_exp() {
        ResultDTO<VmIlsVsInfoDTO> dto = new ResultDTO<>();
        VmIlsVsInfoDTO vmIlsVsInfoDTO = new VmIlsVsInfoDTO();
        vmIlsVsInfoDTO.setHphm("豫E88452");
        vmIlsVsInfoDTO.setIlsCount(1234567);
        vmIlsVsInfoDTO.setAvCount(12345);
        List<VmIlsVsTypeDTO> types = new ArrayList<>();
        types.add(new VmIlsVsTypeDTO("闯红灯", 30));
        types.add(new VmIlsVsTypeDTO("行车打电话", 10));
        types.add(new VmIlsVsTypeDTO("副驾驶不系安全带", 15));
        types.add(new VmIlsVsTypeDTO("违反限行", 15));
        types.add(new VmIlsVsTypeDTO("逆行", 20));
        vmIlsVsInfoDTO.setIlsVstype(types);
        List<VmIlsVsTrendDTO> trend = new ArrayList<>();
        trend.add(new VmIlsVsTrendDTO("" + 2013,35));
        trend.add(new VmIlsVsTrendDTO("" + 2014,35));
        trend.add(new VmIlsVsTrendDTO("" + 2015,35));
        trend.add(new VmIlsVsTrendDTO("" + 2016,35));
        trend.add(new VmIlsVsTrendDTO("" + 2017,35));
        trend.add(new VmIlsVsTrendDTO("" + 2018,35));
        trend.add(new VmIlsVsTrendDTO("" + 2019,35));
        trend.add(new VmIlsVsTrendDTO("" + 2020,35));
        vmIlsVsInfoDTO.setIlsVsTrend(trend);
        dto.setData(vmIlsVsInfoDTO);
        return dto;
    }

    private ResultDTO<VmIlsVdDTO> queryIlsDat_exp() {
        ResultDTO<VmIlsVdDTO> dto = new ResultDTO<>();
        VmIlsVdDTO vmIlsVdDTO = new VmIlsVdDTO(98,"http://192.168.2.68:9095/ipfs/QmR89Qr8aA4wjBh64TpL1z7Y59G7s4RexTWjkQzD5LehbV","2020-12-28 15:26:30",
                "北京市海淀区上地三街123号", "本市",
                "京A48520", "未系安全带","大货车", "挂车","车头", 0,0,
                0,0,0,0,0,0,"白色", "大众",
                "小型车", "2020",99,1,"白色","蓝底白字","民用车牌",0,
                98,"京98 A97...");
        dto.setData(vmIlsVdDTO);
        return dto;
    }
}
