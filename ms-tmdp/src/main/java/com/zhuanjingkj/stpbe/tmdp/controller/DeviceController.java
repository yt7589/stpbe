package com.zhuanjingkj.stpbe.tmdp.controller;

import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.common.util.Insert;
import com.zhuanjingkj.stpbe.common.util.Update;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.CameraRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.DeviceRTO;
import com.zhuanjingkj.stpbe.tmdp.service.DeviceService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.10
 **/

@RestController
@RequestMapping("/device")
@CrossOrigin(origins = "*")
@Validated
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @PostMapping
    public ResultDTO<PageInfo> getDevice(@Valid @RequestBody DeviceRTO deviceRTO) {

        PageInfo pageInfo = deviceService.getDevice(deviceRTO);
        return ResultDTO.success(pageInfo);
    }

    @GetMapping("/type")
    public ResultDTO<List> getDeviceType() {
        return ResultDTO.success(deviceService.getDeviceType());
    }

    @GetMapping("/delete")
    public ResultDTO<Object> deleteDevice(@NotNull(message = "设备ID不能为空") Integer cameraId) {
        deviceService.deleteDevice(cameraId);
        return ResultDTO.success();
    }

    @PostMapping("/insert")
    public ResultDTO<Object> insertDevice(@Validated({Insert.class}) @RequestBody CameraRTO cameraRTO) {
        deviceService.insertDevice(cameraRTO);
        return ResultDTO.success();
    }

    @PostMapping("/update")
    public ResultDTO<Object> updateDevice(@Validated({Update.class}) @RequestBody CameraRTO cameraRTO) {
        deviceService.updateDevice(cameraRTO);
        return ResultDTO.success();
    }
}
