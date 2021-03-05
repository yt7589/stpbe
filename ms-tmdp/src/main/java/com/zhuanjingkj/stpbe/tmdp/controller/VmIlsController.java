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
     * @return
     */
    @GetMapping(value = "/ils/queryIlsDat")
    public ResultDTO<VmIlsVdDTO> queryIlsDat(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "tvisJsonId", required = false) Integer tvisJsonId,
            @RequestParam(name = "vehIdx", required = false) Integer vehIdx
    ) {
        System.out.println("tvisJsonId:" + tvisJsonId + "; vehIdx:" + vehIdx);
        return queryIlsDat_exp(tvisJsonId, vehIdx);
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
            @RequestParam(name = "hphm", required = false) String hphm,
            @RequestParam(name = "tvisJsonId", required = false) long tvisJsonId,
            @RequestParam(name = "vehsIdx", required = false) Integer vehsIdx
    ) {
        return queryIlsVsInfo_exp(hphm, tvisJsonId, vehsIdx);
    }
    /**
     * 违章数据导出
     * @param response
     */
    @GetMapping(value = "/ils/export")
    public void ilsExport(HttpServletResponse response,
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "category", required = false) Integer category,
            @RequestParam(name = "vType", required = false) String vType,
            @RequestParam(name = "illType", required = false) String illType,
            @RequestParam(name = "hphm", required = false) String hphm,
            @RequestParam(name = "addr", required = false) String addr){
        List<VmIlsDTO> recs = new ArrayList<VmIlsDTO>();
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
            @RequestParam(name = "category", required = false) Integer category
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

    private ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo_exp(String hphm, long tvisJsonId, Integer vehsIdx) {
        return vmIlsService.queryIlsVsInfo_exp(hphm, tvisJsonId, vehsIdx);
    }

    private ResultDTO<VmIlsVdDTO> queryIlsDat_exp(long tvisJsonId, Integer vehIdx) {
        return vmIlsService.queryIlsDat_exp(tvisJsonId, vehIdx);
    }
}
