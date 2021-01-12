package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.KsSvsLtviDTO;
import com.zhuanjingkj.stpbe.data.dto.KsSvsSvtvDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.*;
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

    @Autowired
    private KsSvsKsvmcService ksSvsKsvmcService;

    @Autowired
    private KsSvsSvtvService ksSvsSvtvService;

    @Autowired
    private KsSvsLtviService ksSvsLtviService;

    @Autowired
    private KsSvsKsvadsService ksSvsKsvadsService;

    @Autowired
    private KsSvsKsvrpService ksSvsKsvrpService;

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

    /**
     * 地图页面4个重点数据
     * @return
     */
    private KsSvsHtfsDTO getKsSvsHtfsDTO_exp() {
        return ksSvsHtfsService.getKsSvsHtfsDTO_exp();
    }

    /**
     * 地图信息对象
     * @return
     */
    private KsSvsLtviDTO getKsSvsLtviDTO_exp() {
        return ksSvsLtviService.getKsSvsLtviDTO_exp();
    }

    /**
     *本日重点监控车辆车型构成
     * @return
     */
    private List<KsSvsKsvmcDTO> getKsSvsKsvmcDTOs_exp() {
        return ksSvsKsvmcService.getKsSvsKsvmcDTOs_exp();
    }

    /**
     * 本日重点监控车辆区域分布图
     * @return
     */
    private List<KsSvsKsvadDTO> getKsSvsKsvadDTOs_exp() {
        return ksSvsKsvadsService.getKsSvsKsvadDTOs_exp();
    }

    /**
     * 本日重点监控车辆实时图片
     * @return
     */
    private List<KsSvsKsvrpDTO> getKsSvsKsvrpDTOs_exp() {
//        List<KsSvsKsvrpDTO> ksvrps = new ArrayList<>();
//        ksvrps.add(new KsSvsKsvrpDTO(101, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
//        ksvrps.add(new KsSvsKsvrpDTO(102, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        return ksSvsKsvrpService.getKsSvsKsvrpDTOs_exp();
    }

    /**
     * 重点监控车辆列表
     * @return
     */
    private List<KsSvsSvtvDTO> getKsSvsSvtvDTOs_exp() {
        return ksSvsSvtvService.getKsSvsSvtvDTOs_exp();
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

    /**
     * 本日重点监控车辆违法实时图片
     * @return
     */
    private List<KsSvsKsvtvrpDTO> getKsSvsKsvtvrpDTOs_exp() {
        List<KsSvsKsvtvrpDTO> ksvtvrps = new ArrayList<>();
        ksvtvrps.add(new KsSvsKsvtvrpDTO(102, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        ksvtvrps.add(new KsSvsKsvtvrpDTO(101, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg"));
        return ksvtvrps;
    }
}
