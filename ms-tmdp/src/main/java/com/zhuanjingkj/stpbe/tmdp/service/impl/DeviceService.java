package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.data.rto.dm.AddDeviceToDsRTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceTypeDTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceNodeDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteDeviceFromDsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateDeviceInfoRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ResultDTO<DbQrsDTO> queryDevice_exp(Integer startIndex, Integer amount, Integer direction, String type, String code) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<DmDeviceDTO> recs = deviceMapper.getDmDevice(startIndex, amount, type, code);
        Integer count = deviceMapper.getDmDeviceCount(type, code);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteDevice_exp(DeleteDeviceFromDsRTO rto) {
        System.out.println("deviceno:" + rto.getDeviceNo());
        Integer affectedRows = deviceMapper.deleteDevice(rto.getDeviceNo());
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<DbDeleteResultDTO>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addDevice_exp(AddDeviceToDsRTO rto) {
        System.out.println(rto.getDeviceNo());
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = deviceMapper.addDevice(rto);
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getDeviceId(),affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> queryDeviceType_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<DmDeviceTypeDTO> recs = deviceMapper.getDmDeviceType();
        DbQrsDTO data = new DbQrsDTO(50,recs.size(),0,20,1,recs);
//        List<DmDeviceTypeDTO> recs = new ArrayList<>();
//        recs.add(new DmDeviceTypeDTO("DT0001", "卡口相机"));
//        recs.add(new DmDeviceTypeDTO("DT0002", "视频监控"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> queryDeviceNode_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<DmDeviceNodeDTO> recs = deviceMapper.queryDeviceNode();
        DbQrsDTO data = new DbQrsDTO(100,recs.size(),0,20,0,recs);
//        List<DmDeviceNodeDTO> recs = new ArrayList<>();
//        recs.add(new DmDeviceNodeDTO("DN00015", "海淀区上地八街1号位"));
//        recs.add(new DmDeviceNodeDTO("DN00016", "海淀区上地八街2号位"));
//        recs.add(new DmDeviceNodeDTO("DN00017", "海淀区上地八街3号位"));
//        recs.add(new DmDeviceNodeDTO("DN00018", "海淀区上地八街4号位"));
//        recs.add(new DmDeviceNodeDTO("DN00019", "海淀区上地八街5号位"));
//        recs.add(new DmDeviceNodeDTO("DN00014", "海淀区上地八街6号位"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> updateDeviceInfo_exp(UpdateDeviceInfoRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = deviceMapper.updateDeviceInfo(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }
}
