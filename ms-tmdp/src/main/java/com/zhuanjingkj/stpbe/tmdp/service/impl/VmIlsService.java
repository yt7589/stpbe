package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.mapper.VmIlsMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.vo.CameraVehicleRecordVO;
import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VmIlsVdDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VmIlsVsInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vm.VmIlsVsTypeDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IVmIlsService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VmIlsService implements IVmIlsService {

    @Autowired
    private VmIlsMapper vmIlsMapper;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    private static Map<String, Object> VEH_TYPE = new HashMap<>(); //车辆类型

    private static Map<String, Object> VEH_COLOR_CSYS = new HashMap<>(); //车身颜色

    private static Map<String, Object> VEH_COLOR_HPYS = new HashMap<>(); //号牌颜色

    private static Map<String, Object> VEH_HPHM_HPZL = new HashMap<>(); //号牌种类

    private static Map<String, Object> VEH_HPHM_HPZT = new HashMap<>(); //号牌字体

    @Override
    public ResultDTO<DbQrsDTO> queryIllegalVehicle_epx(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime,
                                                       Integer category, String vType, String illType, String hphm, String addr) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }

        if(StringUtils.isNotBlank(hphm)) {
            category = 3;
        }
        if(StringUtils.isNotBlank(startTime)) {
            startTime = startTime + " 00:00:00";
        }
        if(StringUtils.isNotBlank(endTime)) {
            endTime = endTime + " 23:59:59";
        }
        List<VmIlsDTO> recs = vmIlsMapper.getIllegalVehicle(startIndex, amount, startTime, endTime, category, vType, illType, hphm, addr);
        if(recs != null && recs.size() > 0) {
            for(int i = 0; i < recs.size(); i++) {
                Map<String, Object> map = dkRtvrMapper.getImageHash(recs.get(i).getTvisJsonId(), recs.get(i).getTvisJsonTbl().replace("StpDb.", ""));
                if(map != null && map.size() > 0) {
                    recs.get(i).setImageUrl(IpfsClient.getIpfsUrl("" + map.get("image_hash")));
                }
            }
        }
        Integer count = vmIlsMapper.getIllegalVehicleCount(startIndex, amount, startTime, endTime, category, vType, illType, hphm, addr);

//        List<VmIlsDTO> recs = new ArrayList<>();
//        recs.add(new VmIlsDTO(101,"2020-12-21 16:18:52","海淀区西二旗","湘K·UV068","外埠","栏板式货车","副驾驶不系安全带",
//                102,"http://222.128.117.234:9003/imgs/vmfjsbjaqd1.png"));
//
//        recs.add(new VmIlsDTO(102,"2020-12-22 16:18:52","海淀区上地南路","湘C·AS661","外埠","轿车","副驾驶不系安全带",
//                103,"http://222.128.117.234:9003/imgs/vmfjsbjaqd2.png"));
//
//        recs.add(new VmIlsDTO(103,"2020-12-23 16:18:52","海淀区上地西里","渝A·865XB","外埠","面包车","副驾驶不系安全带",
//                104,"http://222.128.117.234:9003/imgs/vmfjsbjaqd3.png"));
//
//        recs.add(new VmIlsDTO(10,"2020-12-24 16:18:52","海淀区知春路","闽C·53245","外埠","栏板式货车","主驾驶不系安全带",
//                105,"http://222.128.117.234:9003/imgs/vmzjsbjaqd1.png"));
//
//        recs.add(new VmIlsDTO(105,"2020-12-25 16:18:52","朝阳区望京","桂A·39655","外埠","中型客车","主驾驶不系安全带",
//                106,"http://222.128.117.234:9003/imgs/vmzjsbjaqd2.png"));
//
//        recs.add(new VmIlsDTO(106,"2020-12-26 16:18:52","朝阳区大屯路","渝B·VB098","外埠","面包车","主驾驶打电话",
//                107,"http://222.128.117.234:9003/imgs/vmzjsddh1.png"));
//
//        recs.add(new VmIlsDTO(107,"2020-12-27 16:18:52","昌平区回南路","京GWM567","本市","轿车","主驾驶打电话",
//                108,"http://222.128.117.234:9003/imgs/vmzjsddh2.png"));
//
//        recs.add(new VmIlsDTO(108,"2020-12-28 16:18:52","朝阳区关庄","云A·918RT","外埠","轿车","主驾驶看手机",
//                109,"http://222.128.117.234:9003/imgs/vmzjsksj1.png"));
//
//        recs.add(new VmIlsDTO(108,"2020-12-29 16:18:52","朝阳区关庄","豫A·9YR78","外埠","轿车","主驾驶看手机",
//                109,"http://222.128.117.234:9003/imgs/vmzjsksj2.png"));

        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        data.setRecs(recs);

        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<List<VmIlsVehicleTypesDTO>> queryVehicleType_exp() {
        ResultDTO<List<VmIlsVehicleTypesDTO>> dto = new ResultDTO<>();
        List<VmIlsVehicleTypesDTO> recs = vmIlsMapper.getVType(2);
        dto.setData(recs);
        return dto;
    }

    @Override
    public ResultDTO<List<VmIlsTypeDTO>> queryIlsTypes_exp() {
        ResultDTO<List<VmIlsTypeDTO>> dto = new ResultDTO<>();
        List<VmIlsTypeDTO> ilsType = vmIlsMapper.getIlsType();
        dto.setData(ilsType);
        return dto;
    }

    @Override
    public ResultDTO<VmIlsVdDTO> queryIlsDat_exp(long tvisJsonId, Integer vehsIdx) {
        TvisJsonVO vo = TvisUtil.getTvisJsonVOById(tvisJsonMapper, tvisJsonId);
        ResultDTO<VmIlsVdDTO> dto = new ResultDTO<>();
        String data = IpfsClient.getTextFile("" + vo.getJsonHash());
        JSONObject dataJson = JSONObject.parseObject(data);
        long cameraId = dataJson.getLong("cameraId");
        String code = "";
        long streamId = vo.getStreamId();
        if(cameraId == -1) {
            String newCameraId = deviceMapper.getCameraIdByStreamId(streamId);
            if(StringUtils.isNotBlank(newCameraId)) {
                code = newCameraId + "";
            }
        } else {
            code = cameraId + "";
        }
        Map<String, CameraVehicleRecordVO> cutVehs = new HashMap<>();
        TvisUtil.getTvisFrameAnalysisResult(vo, cutVehs);
        String imgUrl = AppConst.TMDP_BASE_URL + "va/getVaImage?imgFn=c_" + tvisJsonId + "_" + vehsIdx + ".jpg";
        String ilsName = "" + KsAsService.areaMap.get(code);
        JSONObject rstJson = JSONObject.parseObject(dataJson.getString("json"));
        JSONArray vehs = rstJson.getJSONArray("VEH");
        VmIlsVdDTO vmIlsVdDTO = null;
        String timeStamp = DateUtil.timeStamp2Date(rstJson.getString("TimeStamp"));
        String hphm_pre = PropUtil.getHphmPre();
//        for (Object veh :vehs) {
        JSONObject vehJson  = (JSONObject) vehs.getJSONObject(vehsIdx);
        System.out.println(vehJson);
//        if(!vehJson.get("SXH").equals("" + vehsIdx)) {
//            continue;
//        }
        JSONObject hptzJson = vehJson.getJSONObject("HPTZ");
        JSONObject wztzJson = vehJson.getJSONObject("WZTZ");
        JSONObject jsxwtzJson = vehJson.getJSONObject("JSXWTZ");
        JSONObject cxtzJson = vehJson.getJSONObject("CXTZ");
        JSONObject gxhtz = vehJson.getJSONObject("GXHTZ");

        Integer ccztw = StringUtils.isNotBlank(gxhtz.getString("CCZTW")) ? 0 : gxhtz.getString("CCZTW").split("#").length; //车窗粘贴物
        Integer bj = StringUtils.isBlank(gxhtz.getString("BJ")) ? 0 : gxhtz.getString("BJ").split("#").length; //摆件
        Integer gj = StringUtils.isBlank(gxhtz.getString("GJ")) ? 0 : gxhtz.getString("GJ").split("#").length; //挂件
        Integer tc = StringUtils.isBlank(gxhtz.getString("TC")) ? 0 : 1; //天窗
        String xlj = StringUtils.isBlank(gxhtz.getString("XLJ")) ? "无" : "有"; //行李架
        String dcjqs = Integer.parseInt(gxhtz.getString("DCJQS").replace("_", "")) >= 180 ? "有" : "无"; //倒车镜缺失
        Integer cszt = StringUtils.isBlank(gxhtz.getString("CSZT")) ? 0 : gxhtz.getString("CSZT").split("#").length; //车身张贴
        Integer csps = StringUtils.isBlank(gxhtz.getString("CSPS")) ? 0 : gxhtz.getString("CSPS").split("#").length; //车身破损
        Integer csgh = StringUtils.isBlank(gxhtz.getString("CSGH")) ? 0 : gxhtz.getString("CSGH").split("#").length; //车身刮痕
        Integer csch = StringUtils.isBlank(gxhtz.getString("CSCH")) ? 0 : gxhtz.getString("CSCH").split("#").length; //车身彩绘

        String hphm = hptzJson.getString("HPHM");
        String category = "";
        if(StringUtils.isNotBlank(hphm) && hphm.contains(hphm_pre)) {
            category = "本市";
        } else {
            category = "外埠";
        }
        String direction = Integer.parseInt(wztzJson.getString("PSFX")) == 1 ? "车头" : "车尾";
        Integer md_isPhone = isViolation(jsxwtzJson.getString("ZJSDDH"));  //主驾驶打电话
        Integer md_isWPhone = isViolation(jsxwtzJson.getString("ZJSKSJ")); //主驾驶看手机
        Integer md_isSafetyBelt = isViolation(jsxwtzJson.getString("ZJSBJAQD")); //主驾驶不系安全带
        Integer md_isSmoke = isViolation(jsxwtzJson.getString("ZJSCY")); //主驾驶抽烟
        Integer md_isSunVisor = isViolation(jsxwtzJson.getString("ZJSZYB")); //主驾驶遮阳板
        Integer ct_isSafetyBelt = isViolation(jsxwtzJson.getString("FJSBJAQD")); //副驾驶不系安全带
        Integer ct_isSunVisor = isViolation(jsxwtzJson.getString("FJSZYB")); //副驾驶遮阳板
        //Integer mc_isHelmet = Integer.parseInt(jsxwtzJson.getString("MTCBDTK").replace("_", "")) >= 180 ? 1:0;
        vmIlsVdDTO = new VmIlsVdDTO(0,imgUrl,timeStamp,
                ilsName, category, hphm, "","" + VEH_TYPE.get("C" + cxtzJson.get("CLLXFL")),
                "" + VEH_TYPE.get("C" + cxtzJson.get("CLLXZFL")), direction, md_isPhone,md_isWPhone, md_isSafetyBelt,
                md_isSmoke,md_isSunVisor,ct_isSafetyBelt,ct_isSunVisor,0,"" + VEH_COLOR_CSYS.get(cxtzJson.getString("CSYS")),
                cxtzJson.getString("PPXHMS"), "小型车",cxtzJson.getString("CXNK"),Integer.parseInt(StringUtils.isBlank(cxtzJson.getString("PPXHKXD")) ? "0" : cxtzJson.getString("PPXHKXD")),
                Integer.parseInt(StringUtils.isBlank(hptzJson.getString("HPZT")) ? "0" : hptzJson.getString("HPZT")),"" + VEH_COLOR_HPYS.get(hptzJson.getString("HPYS")),
                "" + VEH_HPHM_HPZT.get("C" + hptzJson.get("HPZL")),"" + VEH_HPHM_HPZL.get("C" + hptzJson.get("HPZL")),
                Integer.parseInt(StringUtils.isBlank(hptzJson.getString("YWLSHP")) ? "0" : hptzJson.getString("YWLSHP")),
                Integer.parseInt(StringUtils.isBlank(hptzJson.getString("HPKXD")) ? "0" : hptzJson.getString("HPKXD")),
                hptzJson.getString("MWHPKXD"),dcjqs,ccztw,gj,cszt,xlj,bj,csch,csps,csgh,tc, vehJson.getString("CLTZXL"));
        dto.setData(vmIlsVdDTO);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> queryIllegalVehicle_epx(String hphm, Integer startIndex, Integer amount, Integer direction) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<VmIlsVhsDTO> recs = vmIlsMapper.getVIlRecord(hphm, startIndex, amount);
        if(recs != null && recs.size() > 0) {
            for (int i =0; i < recs.size(); i++) {
                Map<String, Object> map = dkRtvrMapper.getImageHash(recs.get(i).getTvisJsonId(), recs.get(i).getTvisJsonTbl().replace("StpDb.", ""));
                if(map != null && map.size() > 0) {
                    recs.get(i).setImageUrl(IpfsClient.getIpfsUrl(map.get("image_hash") + ""));
                }

            }
        }
        Integer count = vmIlsMapper.getVIlCount(hphm);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<VmIlsVsInfoDTO> queryIlsVsInfo_exp(String hphm, long tvisJsonId, Integer vehsIdx) {
        TvisJsonVO vo = TvisUtil.getTvisJsonVOById(tvisJsonMapper, tvisJsonId);
        Map<String, CameraVehicleRecordVO> cutVehs = new HashMap<>();

        TvisUtil.getTvisFrameAnalysisResult(vo, cutVehs);
        String imgUrl = AppConst.TMDP_BASE_URL + "va/getVaImage?imgFn=c_" + tvisJsonId + "_" + vehsIdx + ".jpg";

        ResultDTO<VmIlsVsInfoDTO> dto = new ResultDTO<>();
        VmIlsVsInfoDTO vmIlsVsInfoDTO = new VmIlsVsInfoDTO();
        vmIlsVsInfoDTO.setHphm(hphm);
        Integer ilTotal = 0;
        Integer avCount = 0;
        List<VmIlsVsTypeDTO> recs = new ArrayList<>();
        List<Map<String, Object>> ilType = vmIlsMapper.getIlTypeByHphm(hphm);
        if(ilType != null && ilType.size() > 0) {
            for(int i = 0; i < ilType.size(); i++) {
                recs.add(new VmIlsVsTypeDTO("" + ilType.get(i).get("wzlx"), Integer.parseInt(ilType.get(i).get("count") == null ? "0" : "" + ilType.get(i).get("count"))));
                ilTotal += (Integer.parseInt(ilType.get(i).get("count") == null ? "0" : "" + ilType.get(i).get("count")));
            }
        }
        vmIlsVsInfoDTO.setIlsVstype(recs);
        List<VmIlsVsTrendDTO> trend = new ArrayList<>();
        List<Map<String, Object>> ilyType = vmIlsMapper.getIlTypeByYear(hphm);
        Map<String, Integer> sortedMap = DateUtil.yearForMap(8);
        if(ilyType != null && ilyType.size() > 0) {
            for(int i =0; i < ilyType.size(); i++) {
                sortedMap.put("" + ilyType.get(i).get("year"), Integer.parseInt(ilyType.get(i).get("count") == null ? "0" : "" + ilyType.get(i).get("count")));
            }
            for (String key : sortedMap.keySet()) {
                trend.add(new VmIlsVsTrendDTO("" + key, sortedMap.get(key)));
            }
        }

        vmIlsVsInfoDTO.setIlsCount(ilTotal);
        vmIlsVsInfoDTO.setAvCount(avCount);
        vmIlsVsInfoDTO.setImgUrl(imgUrl);
        vmIlsVsInfoDTO.setIlsVsTrend(trend);
        dto.setData(vmIlsVsInfoDTO);
        return dto;
    }

    @Override
    public List<VmIlsTopAreaDTO> queryIllArea(String startTime, String endTime, Integer category) {
        List<VmIlsTopAreaDTO> ilsArea = vmIlsMapper.getIllTopArea(startTime, endTime, category);
        return ilsArea;
    }

    @Override
    public List<VmIlsTopSiteDTO> queryIllSite(String startTime, String endTime, Integer category) {
        List<VmIlsTopSiteDTO> ilsSite = vmIlsMapper.getIlsTopSite(startTime, endTime, category);
        return ilsSite;
    }

    @Override
    public ResultDTO<DbQrsDTO> queryIllegal_exp(Integer startIndex, Integer amount, Integer direction, String startTime, String endTime) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<VmIlssDTO> recs = vmIlsMapper.getIlsSite(startIndex, amount, startTime, endTime);
        Integer count = vmIlsMapper.getIlsSiteCount();
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> querySiteIllegal_exp(String startTime, String endTime, Integer category) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<VmIlsSiteDTO> recs = vmIlsMapper.getIlsMapSite(startTime, endTime, category);;
        DbQrsDTO data = new DbQrsDTO(10,10,0,10,0,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public Integer getIlsCount(String startTime, String endTime, Integer category, String vType, String illType, String hphm, String addr) {
        return vmIlsMapper.getIlsCount(startTime, endTime, category, vType, illType, hphm, addr);
    }

    @Override
    public List<VmIlsDTO> getIlsPart(long startIndex, long amount, Integer direction, String startTime, String endTime,
                                     Integer category, String vType, String illType, String hphm, String addr) {
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
//        String cg = PropUtil.getValue("hphm.native.prefix");
        if(StringUtils.isNotBlank(hphm)) {
            category = 3;
        }
        if(StringUtils.isNotBlank(startTime)) {
            startTime = startTime + " 00:00:00";
        }
        if(StringUtils.isNotBlank(endTime)) {
            endTime = endTime + " 23:59:59";
        }
        List<VmIlsDTO> ils = vmIlsMapper.getIlsPart(startIndex, amount, startTime, endTime, category, vType, illType, hphm, addr);
        return ils;
    }

    @Override
    public Integer getVIlsHistory(String hphm) {
        return vmIlsMapper.getVIlsHistoryCount(hphm);
    }

    @Override
    public List<VmIlsVhsDTO> queryIlsVehicleHistoric(String hphm, Integer startIndex, Integer amount) {
        return vmIlsMapper.getVIlRecord(hphm, startIndex, amount);
    }


    @PostConstruct
    private void getVehicleType() {
        List<VmIlsVehicleTypesDTO> vType = vmIlsMapper.getVType(0);
        if(vType != null && vType.size() > 0) {
            for (int i = 0; i < vType.size(); i++) {
                VEH_TYPE.put("C" + vType.get(i).getTypeId(), vType.get(i).getTypeName());
            }
        }
        List<Map<String, Object>> vColor = vmIlsMapper.getVColor(); //车身颜色
        if(vColor != null && vColor.size() > 0) {
            for (int i = 0; i < vColor.size(); i++) {
                VEH_COLOR_CSYS.put("" + vColor.get(i).get("violation_csys_code"), vColor.get(i).get("violation_csys_name"));
            }
        }

        List<Map<String, Object>> hColor = vmIlsMapper.getHColor(); //号牌颜色
        if(hColor != null && hColor.size() > 0) {
            for (int i = 0; i < hColor.size(); i++) {
                VEH_COLOR_HPYS.put("" + hColor.get(i).get("violation_hpys_code"), hColor.get(i).get("violation_hpys_name"));
            }
        }

        List<Map<String, Object>> hpzl = vmIlsMapper.getHpzl(); //号牌种类
        if(hpzl != null && hpzl.size() > 0) {
            for (int i = 0; i < hpzl.size(); i++) {
                VEH_HPHM_HPZL.put("C" + hpzl.get(i).get("violation_hpzl_code"), hpzl.get(i).get("violation_hpzl_name"));
                VEH_HPHM_HPZT.put("C" + hpzl.get(i).get("violation_hpzl_code"), hpzl.get(i).get("violation_hpzl_desc"));
            }
        }
    }
    /**
     * 驾驶行为
     * @param jsxw
     * @return
     */
    private static Integer isViolation(String jsxw) {
        return Integer.parseInt(StringUtils.isNotBlank(jsxw.replace("_", "")) ? jsxw.replace("_", "") : "0") >= 180 ? 1 : 0;
    }
}
