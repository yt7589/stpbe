package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.*;
import com.zhuanjingkj.stpbe.tmdp.rto.tn.TnVaDeviceRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.tn.TnVaSiteInfoRTO;
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

    /**
     * 设备部署点位
     * @return
     */
    @GetMapping(value = "/va/queryDeviceDeploy")
    public ResultDTO<DbQrsDTO> queryEquipment(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version
    ) {
        return queryDeviceDeploy_exp();
    }

    /**
     * 查询设备数量
     * @return
     */
    @GetMapping(value = "/va/queryDevice")
    public ResultDTO<TnVaDeviceDTO> queryDevice(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version
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
        return querySdInfo_exp();
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
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,20,0,20,0,null);
        List<TnVaDeviceDeployDTO> recs = new ArrayList<>();
        double lng = 116.490094;
        double lat = 39.857702;
        int idx = 0;
        recs.add(new TnVaDeviceDeployDTO(100 + idx,"上地三街10号", lng + idx*0.01, lat + idx*0.01));
        idx++;
        recs.add(new TnVaDeviceDeployDTO(100 + idx,"西三旗", lng + idx*0.01, lat + idx*0.01));
        idx++;
        recs.add(new TnVaDeviceDeployDTO(100 + idx,"清河中街", lng + idx*0.01, lat + idx*0.01));
        idx++;
        recs.add(new TnVaDeviceDeployDTO(100 + idx,"北土城路", lng + idx*0.01, lat + idx*0.01));
        idx++;
        recs.add(new TnVaDeviceDeployDTO(100 + idx,"中关村大街", lng + idx*0.01, lat + idx*0.01));
        idx++;
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<TnVaDeviceDTO> queryDevice_exp() {
        ResultDTO<TnVaDeviceDTO> dto = new ResultDTO<>();
        TnVaDeviceDTO data = new TnVaDeviceDTO(18000, 20,19000, 10);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<TnVaSiteInfoDTO> querySdInfo_exp() {
        ResultDTO<TnVaSiteInfoDTO> dto = new ResultDTO<>();
        TnVaSiteInfoDTO ts = new TnVaSiteInfoDTO();
        List<TnVaSdInfoDTO> recs = new ArrayList<TnVaSdInfoDTO>();
        recs.add(new TnVaSdInfoDTO(20201228,"海淀区>上地","东南","普通摄像头","已接入"));
        recs.add(new TnVaSdInfoDTO(20201228,"海淀区>西二旗","西北","抓拍机","异常"));
        recs.add(new TnVaSdInfoDTO(100,"海淀区>西二旗","西北","抓拍机","已接入"));
        ts.setRecs(recs);
        ts.setCamera(1);
        ts.setSnapshot(2);
        dto.setData(ts);
        return dto;
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
