package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.TnVaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Traffic Network => Video Analysis
 * 路网实况 =》 视频分析
 */
@RestController
@RequestMapping("/tn")
@CrossOrigin(origins = "*")
public class TnVaController {

    @Autowired
    private TnVaService tnVaService;

    /**
     * 设备部署点位
     * @return
     */
    @GetMapping(value = "/va/queryDeviceDeploy")
    public ResultDTO<DbQrsDTO> queryEquipment(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryDeviceDeploy_exp();
    }

    /**
     * 查询设备数量
     * @return
     */
    @GetMapping(value = "/va/queryDevice")
    public ResultDTO<TnVaDeviceDTO> queryDevice(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryDevice_exp();
    }

    /**
     * 点位设备信息
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value  = "/va/querySdInfo")
    public ResultDTO<TnVaSiteInfoDTO> querySdInfo(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
        @RequestParam(name = "siteId", required = false) long siteId
    ) {
        return querySdInfo_exp(startIndex, amount, direction, siteId);
    }

    /**
     * 点位视图详情
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/va/querySdPic")
    public ResultDTO<DbQrsDTO> querySdPic(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "diId") long diId
    ) {
        return querySdPic_exp();
    }

    private ResultDTO<DbQrsDTO> queryDeviceDeploy_exp() {
        return tnVaService.queryDeviceDeploy_exp();
    }

    private ResultDTO<TnVaDeviceDTO> queryDevice_exp() {
        return tnVaService.queryDevice_exp();
    }

    private ResultDTO<TnVaSiteInfoDTO> querySdInfo_exp(Integer startIndex, Integer amount, Integer direction, long siteId) {
        return tnVaService.querySdInfo_exp(startIndex, amount, direction, siteId);
    }

    private ResultDTO<DbQrsDTO> querySdPic_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,15,0,15,0,null);
        List<TnVaSdPicDTO> recs = new ArrayList<>();
        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00022","未系安全带","2020-12-28 17:27:33"));

        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00032","主驾驶打电话","2020-12-28 17:27:33"));

        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00042","主驾驶看手机","2020-12-28 17:27:33"));

        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00052","未系安全带","2020-12-28 17:27:33"));

        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00062","未系安全带","2020-12-28 17:27:33"));

        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00072","未系安全带","2020-12-28 17:27:33"));

        recs.add(new TnVaSdPicDTO(10250,"http://222.128.117.234:8090/cloud/images/a002.jpg","http://222.128.117.234:8090/cloud/images/a002.jpg",108,
                "上地三街12号","小型车","2020","京B00082","未系安全带","2020-12-28 17:27:33"));

        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}
