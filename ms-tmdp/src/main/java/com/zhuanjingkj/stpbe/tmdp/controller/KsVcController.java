package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcLsvsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcSfvsDTO;
import com.zhuanjingkj.stpbe.data.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.data.rto.ks.DeleteVehicleFromVcRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsVcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Key Supervision =>Vehicle control
 * 重点监管=》车辆布控
 */
@RestController
@RequestMapping(value = "/ks")
@CrossOrigin(origins = "*")
public class KsVcController {

    @Autowired
    private KsVcService ksVcService;

    /**
     * 布控车辆列表
     * @param platform
     * @param version
     * @param hphm  车牌号码
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value = "/vc/queryVehicle")
    public ResultDTO<DbQrsDTO> queryVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "hphm", required = false) String hphm,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "0") Integer direction
    ) {
        return queryVehicle_exp(hphm, startIndex, amount, direction);
    }

    /**
     * 添加布控车辆
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/vc/addVehicle")
    public ResultDTO<DbInsertResultDTO> addVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody AddVehicleToVcRTO rto
    ) {
        return addVehicle_exp(rto);
    }

    /**
     * 删除布控车辆
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/vc/deleteVehicle")
    public ResultDTO<DbDeleteResultDTO> deleteVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody DeleteVehicleFromVcRTO rto
    ) {
        return deleteVehicle_exp(rto);
    }

    /**
     * 布控车辆地图点位
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vc/queryVcSfvs")
    public ResultDTO<DbQrsDTO> queryVcSfvs(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVcSfvs_exp();
    }

    /**
     * 车辆布控最新动态
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vc/queryVcLsvs")
    public ResultDTO<DbQrsDTO> queryVcLsvs(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVcLsvs_exp();
    }

    /**
     * 车辆布控最新违章报警
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vc/queryVcLtvis")
    public ResultDTO<DbQrsDTO> queryVcLtvis(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVcLsvs_exp();
    }

    private ResultDTO<DbQrsDTO> queryVehicle_exp(String hphm, Integer startIndex, Integer amount, Integer direction) {
        return ksVcService.queryVehicle_exp(hphm, startIndex, amount, direction);
    }

    private ResultDTO<DbInsertResultDTO> addVehicle_exp(AddVehicleToVcRTO rto) {
        return  ksVcService.addVehicle_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> deleteVehicle_exp(DeleteVehicleFromVcRTO rto) {
        return ksVcService.deleteVehicle_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryVcSfvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(10,1,10,10,1,null);
        List<KsVcSfvsDTO> recs = new ArrayList<>();
        recs.add(new KsVcSfvsDTO(101, "北京市海淀区西二旗街道19号",116.0185,40.0495, 1,"鲁KL9687"));
        recs.add(new KsVcSfvsDTO(101, "北京市海淀区上地街道39号",116.0285,40.1495, 2,"贵Q817S2"));
        recs.add(new KsVcSfvsDTO(101, "北京市海淀区西直门街道29号",116.0385,40.24295, 4,"赣Q817S2"));
        recs.add(new KsVcSfvsDTO(101, "北京市海淀区知春路街道109号",116.0485,40.3495, 5,"赣Q817S2"));
        recs.add(new KsVcSfvsDTO(101, "北京市朝阳区东湖区99号",116.0585,40.4495, 6,"沪KL9687"));
        recs.add(new KsVcSfvsDTO(101, "北京市昌平区北七家街道21号",116.0685,40.5495, 9,"蒙Q817S2"));
        recs.add(new KsVcSfvsDTO(101, "北京市望京街道59号",116.0785,40.6495, 3,"蒙Q817S2"));
        recs.add(new KsVcSfvsDTO(101, "北京市海淀区回龙观39号",116.0885,40.7495, 6,"赣Q817S2"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> queryVcLsvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(10,10,1,10,1,null);
        List<KsVcLsvsDTO> recs = new ArrayList<>();
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区西二旗街道19号","2020-12-21 18:02:57","赣Q817S2",1));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区上地街道39号","2020-12-22 18:02:57","豫KL9687",2));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区西直门街道29号","2020-12-23 18:02:57","豫Q817S2",3));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区知春路街道109号","2020-12-29 18:02:57","赣KL9687",4));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市朝阳区东湖区99号","2020-12-28 18:02:57","苏FTET72",5));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市昌平区北七家街道21号","2020-12-27 18:02:57","鲁P7ET79",6));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区回龙观39号","2020-12-26 18:02:57","鲁P7ET15",7));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市望京街道59号","2020-12-25 18:02:57","京P7ET75",8));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区回龙观39号","2020-12-24 18:02:57","蒙P7ET75",9));
        recs.add(new KsVcLsvsDTO(10,10,101,"北京市海淀区回龙观39号","2020-12-22 18:02:57","贵P7ET75",10));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}

