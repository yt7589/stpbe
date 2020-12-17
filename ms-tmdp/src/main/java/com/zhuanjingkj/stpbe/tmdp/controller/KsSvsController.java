package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsHtfsDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Key Supervision => Special Vehicle Supervision
 * 重点监管=》特殊车辆监管
 */
@RestController
@RequestMapping("/ks")
@CrossOrigin(origins = "*")
public class KsSvsController {
    @GetMapping("getKsSvsMain")
    public ResultDTO<KsSvsDTO> getKsSvsMain() {
        ResultDTO<KsSvsDTO> dto = new ResultDTO<>();
        KsSvsDTO data = new KsSvsDTO();
        data.setHtfs(getKsSvsHtfsDTO_exp());
        dto.setData(data);
        return dto;
    }

    private KsSvsHtfsDTO getKsSvsHtfsDTO_exp() {
        KsSvsHtfsDTO htfs = new KsSvsHtfsDTO();
        htfs.setTodaySvNum(198392);
        htfs.setTodayDevNum(2008);
        htfs.setTodayWarnNum(155);
        htfs.setTodayKakvNum(8188);
        return htfs;
    }
}
