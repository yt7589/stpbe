package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
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
        data.setKsvrps(getKsSvsKsvrpDTOs_exp());
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

    private List<KsSvsKsvrpDTO> getKsSvsKsvrpDTOs_exp() {
        List<KsSvsKsvrpDTO> ksvrps = new ArrayList<>();
        ksvrps.add(new KsSvsKsvrpDTO(101, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        ksvrps.add(new KsSvsKsvrpDTO(102, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        return ksvrps;
    }
}
