package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsSvsHtfsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private KsSvsHtfsService ksSvsHtfsService;

    @GetMapping("getKsSvsMain")
    public ResultDTO<KsSvsDTO> getKsSvsMain() {
        ResultDTO<KsSvsDTO> dto = new ResultDTO<>();
        KsSvsDTO data = new KsSvsDTO();
        data.setHtfs(getKsSvsHtfsDTO_exp());
        data.setLtvi(getKsSvsLtviDTO_exp());
        data.setKsvmcs(getKsSvsKsvmcDTOs_exp());
        data.setKsvads(getKsSvsKsvadDTOs_exp());
        data.setKsvrps(getKsSvsKsvrpDTOs_exp());
        data.setSvtvs(getKsSvsSvtvDTOs_exp());
        data.setKsvtitfs(getKsSvsKsvtitfsDTOs_exp());
        data.setKsvsss(getKsSvsKsvssDTOs_exp());
        data.setKsvtvrps(getKsSvsKsvtvrpDTOs_exp());
        dto.setData(data);
        return dto;
    }

    private KsSvsHtfsDTO getKsSvsHtfsDTO_exp() {
        return ksSvsHtfsService.getKsSvsHtfsDTO_exp();
    }

    private KsSvsLtviDTO getKsSvsLtviDTO_exp() {
        return new KsSvsLtviDTO(101, "上地三街", "东南",
                "车头", 201, "主驾驶不系安全带", "京A-SY270",
                "东风",8, 301,
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg",
                    116.31129731152342, 40.03570782927839,
                "2020-12-19 08:32:59");
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

    private List<KsSvsSvtvDTO> getKsSvsSvtvDTOs_exp() {
        List<KsSvsSvtvDTO> svtvs = new ArrayList<>();
        KsSvsSvtvDTO svtv = null;
        svtv = new KsSvsSvtvDTO(1, "京A-xy001", "奥迪-A6L",
                1, "驾驶员不系安全带",
                101, "上地三街路口", "2020-12-17 16:56:29");
        svtvs.add(svtv);
        svtv = new KsSvsSvtvDTO(2, "京N-xy998", "奔驰-E级",
                2, "主驾驶打电话",
                102, "西三旗环岛", "2020-12-17 16:56:29");
        svtvs.add(svtv);
        svtv = new KsSvsSvtvDTO(3, "京Q-xy001", "宝马-3系",
                3, "主驾驶看电话",
                103, "西小口", "2020-12-17 16:56:29");
        svtvs.add(svtv);
        svtv = new KsSvsSvtvDTO(4, "京B-xy001", "北汽-索纳塔",
                4, "副驾驶不系安全带",
                104, "西直门", "2020-12-17 16:56:29");
        svtvs.add(svtv);
        svtv = new KsSvsSvtvDTO(105, "京C-xy801", "沃尔沃-S90",
                1, "驾驶员不系安全带",
                105, "六里桥AO3", "2020-12-17 16:56:29");
        svtvs.add(svtv);
        return svtvs;
    }

    private List<KsSvsKsvtitfsDTO> getKsSvsKsvtitfsDTOs_exp() {
        List<KsSvsKsvtitfsDTO> rst = new ArrayList<>();
        List<KsSvsKsvtitfDTO> ksvtitfs = null;
        // 重点车
        ksvtitfs = new ArrayList<>();
        ksvtitfs.add(new KsSvsKsvtitfDTO("2", 38976));
        ksvtitfs.add(new KsSvsKsvtitfDTO("4", 28989));
        ksvtitfs.add(new KsSvsKsvtitfDTO("6", 32345));
        ksvtitfs.add(new KsSvsKsvtitfDTO("8", 153567));
        ksvtitfs.add(new KsSvsKsvtitfDTO("10", 167891));
        ksvtitfs.add(new KsSvsKsvtitfDTO("12", 89765));
        ksvtitfs.add(new KsSvsKsvtitfDTO("14", 81234));
        ksvtitfs.add(new KsSvsKsvtitfDTO("16", 99321));
        ksvtitfs.add(new KsSvsKsvtitfDTO("18", 118909));
        ksvtitfs.add(new KsSvsKsvtitfDTO("20", 189883));
        ksvtitfs.add(new KsSvsKsvtitfDTO("22", 138976));
        ksvtitfs.add(new KsSvsKsvtitfDTO("24", 76543));
        rst.add(new KsSvsKsvtitfsDTO("重点车", ksvtitfs));
        // 大货车
        ksvtitfs = new ArrayList<>();
        ksvtitfs.add(new KsSvsKsvtitfDTO("2", 18976));
        ksvtitfs.add(new KsSvsKsvtitfDTO("4", 38989));
        ksvtitfs.add(new KsSvsKsvtitfDTO("6", 22345));
        ksvtitfs.add(new KsSvsKsvtitfDTO("8", 253567));
        ksvtitfs.add(new KsSvsKsvtitfDTO("10", 367891));
        ksvtitfs.add(new KsSvsKsvtitfDTO("12", 79765));
        ksvtitfs.add(new KsSvsKsvtitfDTO("14", 61234));
        ksvtitfs.add(new KsSvsKsvtitfDTO("16", 79321));
        ksvtitfs.add(new KsSvsKsvtitfDTO("18", 128909));
        ksvtitfs.add(new KsSvsKsvtitfDTO("20", 159883));
        ksvtitfs.add(new KsSvsKsvtitfDTO("22", 138976));
        ksvtitfs.add(new KsSvsKsvtitfDTO("24", 86543));
        rst.add(new KsSvsKsvtitfsDTO("大货车", ksvtitfs));
        return rst;
    }

    private List<KsSvsKsvssDTO> getKsSvsKsvssDTOs_exp() {
        List<KsSvsKsvssDTO> ksvsss = new ArrayList<>();
        ksvsss.add(new KsSvsKsvssDTO(101, "上地三街", 1231));
        ksvsss.add(new KsSvsKsvssDTO(102, "西三旗", 2345));
        ksvsss.add(new KsSvsKsvssDTO(103,"西二旗", 1102));
        ksvsss.add(new KsSvsKsvssDTO(104, "王道口", 12345));
        ksvsss.add(new KsSvsKsvssDTO(105, "西直门", 19321));
        ksvsss.add(new KsSvsKsvssDTO(106, "六里桥", 15335));
        ksvsss.add(new KsSvsKsvssDTO(107, "王府井", 18221));
        return ksvsss;
    }

    private List<KsSvsKsvtvrpDTO> getKsSvsKsvtvrpDTOs_exp() {
        List<KsSvsKsvtvrpDTO> ksvtvrps = new ArrayList<>();
        ksvtvrps.add(new KsSvsKsvtvrpDTO(102, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        ksvtvrps.add(new KsSvsKsvtvrpDTO(101, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        return ksvtvrps;
    }
}
