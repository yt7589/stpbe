package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceDTO;
import com.zhuanjingkj.stpbe.data.rto.dm.AddDeviceToDsRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteDeviceFromDsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateDeviceInfoRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DeviceService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Device Management
 * 设备管理 =》 设备列表
 */
@RestController
@RequestMapping(value = "/dm")
@CrossOrigin(origins = "*")
public class DmDeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 设备列表
     * @param platform
     * @param version
     * @param type
     * @param code
     * @return
     */
    @GetMapping(value = "/queryDevice")
    public ResultDTO<DbQrsDTO> queryDevice(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
        @RequestParam(name = "type", required = false) String type,
        @RequestParam(name = "code", required = false) String code
    ) {
        return queryDevice_exp(startIndex, amount, direction, type, code);
    }

    /**
     * 设备列表导出
     * @param response
     */
    @GetMapping(value = "/deviceExport")
    public void export(HttpServletResponse response) {
        String[] columns = {"设备编号", "城市编号", "城市名称", "设备类型", "所属节点", "设备朝向", "车辆方向", "视频流地址"};
        List<DmDeviceDTO> recs = new ArrayList<>();
        FileExpDTO fed = new FileExpDTO("设备列表" + DateUtil.getDayOfMonth(LocalDate.now()),"违章记录", columns, recs);
        FileUtil.export(fed);
    }

    /**
     * 删除设备
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/deleteDevice")
    public ResultDTO<DbDeleteResultDTO> deleteDevice(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody DeleteDeviceFromDsRTO rto
    ) {
        return deleteDevice_exp(rto);
    }

    /**
     * 添加设备
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/addDevice")
    public ResultDTO<DbInsertResultDTO> addDevice(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody AddDeviceToDsRTO rto
    ) {
        return addDevice_exp(rto);
    }

    /**
     * 设备安装节点查询
     * @return
     */
    @GetMapping(value = "/queryDeviceNode")
    public ResultDTO<DbQrsDTO> queryDeviceNode() {
        return queryDeviceNode_exp();
    }

    /**
     * 查询设备类型
     * @return
     */
    @GetMapping(value = "/queryDeviceType")
    public ResultDTO<DbQrsDTO> queryDeviceType() {
        return queryDeviceType_exp();
    }

    /**
     * 修改设备信息
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PutMapping(value = "/updateDeviceInfo")
    public ResultDTO<DbDeleteResultDTO> updateDeviceInfo(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestBody UpdateDeviceInfoRTO rto
    ) {
        return updateDeviceInfo_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryDevice_exp(Integer startIndex, Integer amount, Integer direction, String type, String code) {
        return deviceService.queryDevice_exp(startIndex, amount, direction, type, code);
    }

    private ResultDTO<DbDeleteResultDTO> deleteDevice_exp(DeleteDeviceFromDsRTO rto) {
        return deviceService.deleteDevice_exp(rto);
    }

    private ResultDTO<DbInsertResultDTO> addDevice_exp(AddDeviceToDsRTO rto) {
        return deviceService.addDevice_exp(rto);
    }

    public ResultDTO<DbQrsDTO> queryDeviceNode_exp() {
        return deviceService.queryDeviceNode_exp();
    }

    private ResultDTO<DbQrsDTO> queryDeviceType_exp() {
        return deviceService.queryDeviceType_exp();
    }

    private ResultDTO<DbDeleteResultDTO> updateDeviceInfo_exp(UpdateDeviceInfoRTO rto) {
        return  deviceService.updateDeviceInfo_exp(rto);
    }
}
