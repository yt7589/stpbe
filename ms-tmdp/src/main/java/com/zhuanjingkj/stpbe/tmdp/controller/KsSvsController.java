package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsHtfsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvadDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsSvsKsvmcDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        data.setKsvmcs(getKsSvsKsvmcDTOs_exp());
        data.setKsvads(getKsSvsKsvadDTOs_exp());
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

    private List<KsSvsKsvmcDTO> getKsSvsKsvmcDTOs_exp() {
        List<KsSvsKsvmcDTO> ksvmcs = new ArrayList<>();
        ksvmcs.add(new KsSvsKsvmcDTO("平板式货车", 189035));
        ksvmcs.add(new KsSvsKsvmcDTO("厢式货车", 109035));
        ksvmcs.add(new KsSvsKsvmcDTO("罐式货车", 69035));
        ksvmcs.add(new KsSvsKsvmcDTO("栏板式货车", 129035));
        ksvmcs.add(new KsSvsKsvmcDTO("仓栅式货车", 239035));
        ksvmcs.add(new KsSvsKsvmcDTO("普通货车", 289035));
        return ksvmcs;
    }

    private List<KsSvsKsvadDTO> getKsSvsKsvadDTOs_exp() {
        List<KsSvsKsvadDTO> ksvads = new ArrayList<>();
        ksvads.add(new KsSvsKsvadDTO("东直门地区", 182910));
        ksvads.add(new KsSvsKsvadDTO("西直门地区", 223989));
        ksvads.add(new KsSvsKsvadDTO("天安门地区", 313956));
        ksvads.add(new KsSvsKsvadDTO("上地地区", 109876));
        ksvads.add(new KsSvsKsvadDTO("六里桥地区", 91234));
        ksvads.add(new KsSvsKsvadDTO("清河地区", 82468));
        ksvads.add(new KsSvsKsvadDTO("西三旗地区", 65432));
        return ksvads;
    }
}
