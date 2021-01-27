package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.VmIlsService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VmIlsService vmIlsService;

    private static final Long ROW_MAX_COUNT = 60000L;
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
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "category", required = false) Integer category,
            @RequestParam(name = "vType", required = false) String vType,
            @RequestParam(name = "illType", required = false) String illType,
            @RequestParam(name = "hphm", required = false) String hphm,
            @RequestParam(name = "addr", required = false) String addr
    ) {
        return queryIllegalVehicle_epx(startIndex, amount, direction, startTime, endTime, category, vType, illType, hphm, addr);
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
        return queryIlsDat_exp(ilId);
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
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        return queryIlsVehicleHistoric_exp(hphm, startIndex, amount, direction);
    }


    /**
     *
     * 车辆信息及违章历史分类统计
     * @param platform
     * @param version
     * @param hphm  车牌
     * @return
     */
    @GetMapping(value = "/ils/queryIlsVsInfo")
    public ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "hphm", required = false) String hphm
    ) {
        return queryIlsVsInfo_exp(hphm);
    }
    /**
     * 违章数据导出
     * @param response
     */
    @GetMapping(value = "/ils/export")
    public void ilsExport(HttpServletResponse response,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "category", required = false) Integer category,
            @RequestParam(name = "vType", required = false) String vType,
            @RequestParam(name = "illType", required = false) String illType,
            @RequestParam(name = "hphm", required = false) String hphm,
            @RequestParam(name = "addr", required = false) String addr){
        List<VmIlsDTO> recs = new ArrayList<VmIlsDTO>();
//
//        recs.add(new VmIlsDTO(101,"2020-12-25 16:18:52","北京市海淀区西二旗街道19号","京A88858","外埠","轿车","副驾驶放下遮阳板",
//                102,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(102,"2020-12-25 16:18:52","北京市海淀区上地街道39号","津E88868","本市","轿车","副驾驶不系安全带",
//                103,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(103,"2020-12-25 16:18:52","北京市海淀区西直门街道29号","冀E48878","外埠","SUV","主驾驶放下遮阳板",
//                104,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(10,"2020-12-25 16:18:52","北京市海淀区知春路街道109号","豫E88888","本市","MPV","主驾驶抽烟",
//                105,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(105,"2020-12-25 16:18:52","北京市朝阳区东湖区99号","沪E88828","外埠","面包车","主驾驶不系安全带",
//                106,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(106,"2020-12-25 16:18:52","北京市昌平区北七家街道21号","豫A88838","本市","罐式货车","主驾驶看手机",
//                107,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(107,"2020-12-25 16:18:52","北京市望京街道59号","豫B88818","外埠","箱式货车","主驾驶打电话",
//                108,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsDTO(108,"2020-12-25 16:18:52","北京市海淀区回龙观39号","黑E88838","本市","栏板式货车","超速行驶",
//                109,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));

        String[] columns = {"违章编号", "时间", "地点", "车牌号" ,"类别" , "车辆类型", "违章类型编号", "违章类型", "详情"};
        long allMaxCount = vmIlsService.getIlsCount(startTime, endTime, category, vType, illType, hphm, addr);
        if(allMaxCount > ROW_MAX_COUNT) {
            long count = allMaxCount/ROW_MAX_COUNT + 1;
            for (long i = 0; i < count; i++) {
                recs.clear();
                recs = vmIlsService.getIlsPart(i * ROW_MAX_COUNT, ROW_MAX_COUNT, 1, startTime, endTime, category, vType, illType, hphm, addr );
                FileExpDTO fed = new FileExpDTO("违章监管" + DateUtil.getDayOfMonth(LocalDate.now()) + "_" + i,"违章记录", columns, recs, "D://");
                FileUtil.export(response, fed);
            }
        } else {
            recs = vmIlsService.getIlsPart(0, ROW_MAX_COUNT, 1, startTime, endTime, category, vType, illType, hphm, addr );
            FileExpDTO fed = new FileExpDTO("违章监管" + DateUtil.getDayOfMonth(LocalDate.now()) + "_" + 0,"违章记录", columns, recs, "D://");
            FileUtil.export(response, fed);
        }

    }

    /**
     * 违章车辆历史数据导出
     * @param response
     */
    @GetMapping(value = "/ils/exportvs")
    public void exportvs(HttpServletResponse response,
            @RequestParam(name = "hphm", required = false) String hphm){
        List<VmIlsVhsDTO> recs = new ArrayList<>();

//        recs.add(new VmIlsVhsDTO(105,"2020-12-21 12:56:43","北京市海淀区西二旗街道19号","主驾驶打电话",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(106,"2020-12-22 11:56:43","北京市海淀区上地街道39号","主驾驶看手机",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(107,"2020-12-23 10:56:43","北京市海淀区西直门街道29号","主驾驶不系安全带",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(108,"2020-12-24 12:56:43","北京市海淀区知春路街道109号","主驾驶抽烟",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(109,"2020-12-25 03:04:43","北京市朝阳区东湖区99号","主驾驶放下遮阳板",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(110,"2020-12-26 05:56:43","北京市昌平区北七家街道21号","副驾驶不系安全带",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(111,"2020-12-27 06:56:43","北京市望京街道59号","副驾驶放下遮阳板",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(112,"2020-12-28 12:56:43","北京市海淀区回龙观39号","副驾驶放下遮阳板",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//
//        recs.add(new VmIlsVhsDTO(113,"2020-12-29 14:56:43","北京市海淀区上龙泽23号","副驾驶放下遮阳板",
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));

        Integer allMaxCount = vmIlsService.getVIlsHistory(hphm);
        String[] columns = {"违章编号", "时间", "地点", "违章类型", "详情"};
        if(allMaxCount > ROW_MAX_COUNT) {
            long count = allMaxCount/ ROW_MAX_COUNT + 1;
            for(int i = 0; i < count; i++) {
                recs.clear();
                recs = vmIlsService.queryIlsVehicleHistoric(hphm, i *  ROW_MAX_COUNT.intValue(), ROW_MAX_COUNT.intValue());
                FileExpDTO fed = new FileExpDTO("违章监管" + DateUtil.getDayOfMonth(LocalDate.now()),"违章记录", columns, recs, "D://");
                FileUtil.export(response, fed);
            }
        } else {
            recs = vmIlsService.queryIlsVehicleHistoric(hphm, 0, ROW_MAX_COUNT.intValue());
            FileExpDTO fed = new FileExpDTO("违章监管" + DateUtil.getDayOfMonth(LocalDate.now()),"违章记录", columns, recs, "D://");
            FileUtil.export(response, fed);
        }

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
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "category", required = false, defaultValue = "0") Integer category
    ) {
        ResultDTO<VmIlsTopDTO> dto = new ResultDTO<>();
        VmIlsTopDTO data = new VmIlsTopDTO();
        List<VmIlsTopAreaDTO> ilsArea = queryIllArea(startTime, endTime, category);
        List<VmIlsTopSiteDTO> ilsSite = queryIllSite(startTime, endTime, category);
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
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime
    ) {
        return queryIllegal_exp(startIndex, amount, direction, startTime, startTime);
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
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "category", required = false) Integer category
    ) {
        return querySiteIllegal_exp(startTime, endTime, category);
    }

    private ResultDTO<DbQrsDTO> queryIllegalVehicle_epx(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime,
                                                        Integer category, String vType, String illType, String hphm, String addr) {
        return vmIlsService.queryIllegalVehicle_epx(startIndex, amount, direction, startTime, endTime, category, vType, illType, hphm, addr);
    }

    private ResultDTO<List<VmIlsVehicleTypesDTO>> queryVehicleType_exp() {
        return  vmIlsService.queryVehicleType_exp();
    }

    private ResultDTO<List<VmIlsTypeDTO>> queryIlsTypes_exp() {
        return vmIlsService.queryIlsTypes_exp();
    }

    private List<VmIlsTopAreaDTO> queryIllArea(String startTime, String endTime, Integer category) {
        return vmIlsService.queryIllArea(startTime, endTime, category);
    }

    private List<VmIlsTopSiteDTO> queryIllSite(String startTime, String endTime, Integer category) {
        return vmIlsService.queryIllSite(startTime, endTime, category);
    }

    private ResultDTO<DbQrsDTO> queryIllegal_exp(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime) {
        return vmIlsService.queryIllegal_exp(startIndex, amount, direction, startTime, endTime);
    }

    private ResultDTO<DbQrsDTO> querySiteIllegal_exp(String startTime, String endTime, Integer category) {
        return vmIlsService.querySiteIllegal_exp(startTime, endTime, category);
    }

    private ResultDTO<DbQrsDTO> queryIlsVehicleHistoric_exp(String hphm, Integer startIndex, Integer amount, Integer direction) {
        return vmIlsService.queryIllegalVehicle_epx(hphm, startIndex, amount, direction);
    }

    private ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo_exp(String hphm) {
        return vmIlsService.queryIlsVsInfo_exp(hphm);
    }

    private ResultDTO<VmIlsVdDTO> queryIlsDat_exp(long tvId) {
        return vmIlsService.queryIlsDat_exp(tvId);
    }
}
