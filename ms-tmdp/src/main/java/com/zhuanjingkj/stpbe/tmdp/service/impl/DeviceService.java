package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.data.rto.dm.AddDeviceToDsRTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceTypeDTO;
import com.zhuanjingkj.stpbe.data.dto.DmDeviceNodeDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteDeviceFromDsRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateDeviceInfoRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDeviceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private TvisSdkService tvisSdkService;

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
        /**
         * 查询设备rtsp_url 进行解绑
         */
        String rtspUrl = deviceMapper.getRtspUrlByDeviceNo(rto.getDeviceNo());
        tvisSdkService.createRtspBind(rtspUrl, "/end");
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
        /**
         * 添加设备绑定rtsp视频流
         */
        if (rto != null && StringUtils.isNotBlank(rto.getVideoUrl())) {
            ResultDTO<CreateRtspBindDTO> rtsp = tvisSdkService.createRtspBind(rto.getVideoUrl(), "/start");
            String streamId = rtsp.getData().getStreamId();
            rto.setStreamId(streamId);
        }
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
        /**
         * 判断视频流是否改动，改动则更新视频流，不改动则不需要更新视频流
         */
        String rtspUrl = deviceMapper.getRtspUrlByDeviceNo(rto.getDeviceNo()); //设备原数据地址
        if (StringUtils.isNotBlank(rtspUrl) && !rtspUrl.equals(rto.getVideoUrl())) { //原数据地址有改动
            tvisSdkService.createRtspBind(rtspUrl, "/end"); //解绑原数据地址
            rto.setStreamId("-1");
            if (StringUtils.isNotBlank(rto.getVideoUrl())) {
                ResultDTO<CreateRtspBindDTO> rtsp = tvisSdkService.createRtspBind(rto.getVideoUrl(), "/start"); //绑定新数据地址
                String streamId = rtsp.getData().getStreamId(); //新streamId
                rto.setStreamId(streamId);
            }
        } else if (StringUtils.isBlank(rtspUrl) && StringUtils.isNotBlank(rto.getVideoUrl())) {
            ResultDTO<CreateRtspBindDTO> rtsp = tvisSdkService.createRtspBind(rto.getVideoUrl(), "/start"); //绑定新数据地址
            String streamId = rtsp.getData().getStreamId(); //新streamId
            rto.setStreamId(streamId);
        }
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = deviceMapper.updateDeviceInfo(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }


}
