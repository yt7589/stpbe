package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcLsvsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcSfvsDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteVehicleFromVcRTO;
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
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryVehicle_exp();
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

    private ResultDTO<DbQrsDTO> queryVehicle_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,5,1,100,0,null);
        List<KsVcDTO> recs = new ArrayList<>();
        recs.add(new KsVcDTO(102, "京A-XA001"));
        recs.add(new KsVcDTO(102, "京A-XA002"));
        recs.add(new KsVcDTO(102, "京A-XA003"));
        recs.add(new KsVcDTO(102, "京A-XA004"));
        recs.add(new KsVcDTO(102, "京A-XA005"));
        recs.add(new KsVcDTO(102, "京A-XA006"));
        recs.add(new KsVcDTO(102, "京A-XA007"));
        recs.add(new KsVcDTO(102, "京A-XA008"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbInsertResultDTO> addVehicle_exp(AddVehicleToVcRTO rto) {
        System.out.println("添加车牌号：" + rto.getHphm());
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<DbInsertResultDTO>();
        DbInsertResultDTO data = new DbInsertResultDTO(101,1);
        dto.setData(data);
        return  dto;
    }

    private ResultDTO<DbDeleteResultDTO> deleteVehicle_exp(DeleteVehicleFromVcRTO rto) {
        System.out.println("删除布控车牌id：" + rto.getVcId());
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(0);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> queryVcSfvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(10,1,10,10,1,null);
        List<KsVcSfvsDTO> recs = new ArrayList<>();
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA001"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA002"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA003"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA004"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA005"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA006"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA007"));
        recs.add(new KsVcSfvsDTO(101, "上地街道",10.085,10.495, 10,"京A-XA008"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbQrsDTO> queryVcLsvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(10,10,1,10,1,null);
        List<KsVcLsvsDTO> recs = new ArrayList<>();
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA001",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA002",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA003",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA004",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA005",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA006",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA007",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA008",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA009",10));
        recs.add(new KsVcLsvsDTO(10,10,101,"上地街道","2020-12-24 18:02:57","京A-XA010",10));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}

