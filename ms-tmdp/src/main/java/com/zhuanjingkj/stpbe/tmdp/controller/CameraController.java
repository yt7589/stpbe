package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.ImageDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.CameraListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.SiteListDTO;
import com.zhuanjingkj.stpbe.tmdp.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author by guoqiang
 * date on
 **/

@RestController
@RequestMapping("/camera")
@Validated
@CrossOrigin(origins = "*")
public class CameraController {

    @Autowired
    CameraService cameraService;

    @GetMapping("/site")
    public ResultDTO<Object> getSite(){
//        SiteListDTO siteListDTO = new SiteListDTO();
//        List<SiteInfoDTO> siteInfoList = cameraService.getSite();
//        CameraInfoDTO cameraInfoDTO = cameraService.getCameraInfo();
//        siteListDTO.setSiteInfoList(siteInfoList);
//        siteListDTO.setCameraInfo(cameraInfoDTO);
        String s = "{\n" +
                "\"siteInfoList\":[\n" +
                "{\n" +
                "\"siteName\":\"上地六街十字\",\n" +
                "\"lng\":\"192.168\",\n" +
                "\"lat\":\"192.169\",\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "},\n" +
                "{\n" +
                "\"siteName\":\"上地五街十字\",\n" +
                "\"lng\":\"192.167\",\n" +
                "\"lat\":\"192.123\",\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "},\n" +
                "{\n" +
                "\"siteName\":\"上地七街十字\",\n" +
                "\"lng\":\"192.169\",\n" +
                "\"lat\":\"192.124\",\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "},\n" +
                "{\n" +
                "\"siteName\":\"上地八街\",\n" +
                "\"lng\":\"192.17\",\n" +
                "\"lat\":\"192.125\",\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "}\n" +
                "],\n" +
                "\"cameraInfo\":{\n" +
                "\"allCamera\":1,\n" +
                "\"allSnapMachine\":2,\n" +
                "\"brokenCamera\":0,\n" +
                "\"brokenSnapMachine\":1\n" +
                "}\n" +
                "}";
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }

    @GetMapping("/site/device")
    public ResultDTO<Object> getSiteCamera(@NotNull(message = "点位ID不能为空") Long siteId){
//        CameraListDTO cameraListDTO = new CameraListDTO();
//
//        List<CameraDTO> cameraList = cameraService.getCameraInfoBySite(siteId.toString());
//        Integer cameraNum = cameraService.getAllCamera(siteId.toString());
//        Integer snapMachineNum = cameraService.getAllSnapMachine(siteId.toString());
//
//        cameraListDTO.setCameraList(cameraList);
//        cameraListDTO.setCameraNum(cameraNum);
//        cameraListDTO.setSnapMachineNum(snapMachineNum);
        String s = "{\n" +
                "\"cameraList\":[\n" +
                "{\n" +
                "\"cameraId\":1,\n" +
                "\"cameraCode\":\"adfasdf\",\n" +
                "\"siteId\":1,\n" +
                "\"startTime\":\"2020-11-2518:52:41\",\n" +
                "\"endTime\":\"2020-11-2518:52:44\",\n" +
                "\"cameraTypeId\":1,\n" +
                "\"cameraTypeName\":\"抓拍机\",\n" +
                "\"status\":1,\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "},\n" +
                "{\n" +
                "\"cameraId\":2,\n" +
                "\"cameraCode\":\"sdfsadf\",\n" +
                "\"siteId\":1,\n" +
                "\"startTime\":\"2020-11-2518:52:54\",\n" +
                "\"endTime\":\"2020-11-2518:52:56\",\n" +
                "\"cameraTypeId\":2,\n" +
                "\"cameraTypeName\":\"摄像头\",\n" +
                "\"status\":1,\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "},\n" +
                "{\n" +
                "\"cameraId\":3,\n" +
                "\"cameraCode\":\"dsfa\",\n" +
                "\"siteId\":1,\n" +
                "\"startTime\":\"2020-11-2518:53:12\",\n" +
                "\"endTime\":\"2020-11-2518:53:14\",\n" +
                "\"cameraTypeId\":1,\n" +
                "\"cameraTypeName\":\"抓拍机\",\n" +
                "\"status\":0,\n" +
                "\"regionId\":2,\n" +
                "\"regionName\":\"海淀\"\n" +
                "}\n" +
                "],\n" +
                "\"cameraNum\":1,\n" +
                "\"snapMachineNum\":2\n" +
                "}";
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }

    @GetMapping("/image")
    public ResultDTO<Object> getImgByCameraId(@NotNull(message = "设备ID不能为空") Long cameraId){
//        ImageDTO imageDTO = cameraService.getImgByCameraId(cameraId.toString());
        String s = "{\n" +
                "\"imageId\":123,\n" +
                "\"imageUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\",\n" +
                "\"imagePath\":\"sadfdsa\",\n" +
                "\"uploadTime\":\"2020-11-2118:56:05\",\n" +
                "\"cameraId\":1\n" +
                "}";
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }
}
