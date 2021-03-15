package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.DcHpDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.FileExpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpDaDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpIlTrendDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpRgTrendDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcHpService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Data Center =》 Home Page
 * 数据中心 =》 全部数据
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcHpController {

    @Autowired
    private DcHpService dcHpService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Long ROW_MAX_COUNT = 60000L;

    /**
     * 数据记录列表
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/hp/queryAllData")
    public ResultDTO<DbQrsDTO> queryAllData(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime,
        @RequestParam(name = "category", required = false) String category,
        @RequestParam(name = "vType", required = false) String vType,
        @RequestParam(name = "ilType", required = false) String ilType,
        @RequestParam(name = "hphm", required = false) String hphm,
        @RequestParam(name = "vAddr", required = false) String vAddr
    ) {
        return queryAllData_exp(startIndex, amount, direction, startTime, endTime, category, vType, ilType, hphm, vAddr);
    }

    /**
     * 数据走势
     * @return
     */
    @GetMapping(value = "/hp/queryDataAnalysis")
    public ResultDTO<DcHpDaDTO> queryDataAnalysis() {
        ResultDTO<DcHpDaDTO> dto = new ResultDTO<>();
        DcHpDaDTO data = new DcHpDaDTO();
        List<DcHpIlTrendDTO> dit = getDit_exp();
        List<DcHpRgTrendDTO> drt = getDrt_exp();
        data.setDit(dit);
        data.setDrt(drt);
        data.setTotal_recognition(redisTemplate.opsForValue().get("dchp_vehicle_identification") == null ? 0 : Integer.parseInt("" + redisTemplate.opsForValue().get("dchp_vehicle_identification")));
        data.setTotal_violation(redisTemplate.opsForValue().get("dchp_vehicle_violation") == null ? 0 : Integer.parseInt("" + redisTemplate.opsForValue().get("dchp_vehicle_violation")));
        data.setTotal_violation_city(redisTemplate.opsForValue().get("dchp_vehicle_0_violation") == null ? 0 : Integer.parseInt("" + redisTemplate.opsForValue().get("dchp_vehicle_0_violation")));
        data.setTotal_violation_town(redisTemplate.opsForValue().get("dchp_vehicle_1_violation") == null ? 0 : Integer.parseInt("" + redisTemplate.opsForValue().get("dchp_vehicle_1_violation")));
        dto.setData(data);
        return dto;
    }

    /**
     * 数据导出
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/hp/exportAllData")
    public void exportAllData(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "vType", required = false) String vType,
            @RequestParam(name = "ilType", required = false) String ilType,
            @RequestParam(name = "hphm", required = false) String hphm,
            @RequestParam(name = "vAddr", required = false) String vAddr,
            HttpServletResponse response
    ) {
        if(StringUtils.isBlank(category)) {
            category = "3";
        }
        List<DcHpDTO> recs = new ArrayList<>();
        String[] columns = {"id", "tvisJsonId", "tvisJsonTbl", "时间", "地址", "车牌号", "类别", "是否违章", "违章类型", "图片地址", "图片下标"};
        Integer allMaxCount = dcHpService.getVehicleCount();
        String fileName = "数据中心" + DateUtil.getDayOfMonth(LocalDate.now());
        long count = 1;
        if(allMaxCount > ROW_MAX_COUNT) {
            count = allMaxCount/ ROW_MAX_COUNT + 1;
            for(int i = 0; i < count; i++) {
                recs.clear();
                recs = dcHpService.getVehicleData(i *  ROW_MAX_COUNT.intValue(), ROW_MAX_COUNT.intValue(), 1, startTime, endTime, category, vType, ilType, hphm, vAddr);
                FileExpDTO fed = new FileExpDTO(fileName + "_" + i,"数据记录", columns, recs);
                FileUtil.export(fed);
            }
        } else {
            recs = dcHpService.getVehicleData(0, ROW_MAX_COUNT.intValue(), 1, startTime, endTime, category, vType, ilType, hphm, vAddr);
            FileExpDTO fed = new FileExpDTO(fileName + "_"+ 0,"数据记录", columns, recs);
            FileUtil.export(fed);
        }
        FileUtil.doZip(fileName, count);
        FileUtil.downZip(response, fileName);
        FileUtil.deleteZip(fileName);
    }

    private ResultDTO<DbQrsDTO> queryAllData_exp(int startIndex, int amount, Integer direction, String startTime, String endTime, String category,
                                                 String vType, String ilType, String hphm, String vAddr) {
        return dcHpService.queryAllData_exp(startIndex, amount, direction, startTime, endTime, category, vType, ilType, hphm, vAddr);
    }

    private List<DcHpIlTrendDTO> getDit_exp() {
        return dcHpService.getDit_exp();
    }

    private List<DcHpRgTrendDTO> getDrt_exp() {
        return dcHpService.getDrt_exp();
    }

}
