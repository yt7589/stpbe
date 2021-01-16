package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddRsToRssRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteRsFromRssRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsRssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Key Supervision =>Road Section Supervision
 * 重点监管=》路段监管
 */
@RestController
@RequestMapping(value ="/ks")
@CrossOrigin(origins = "*")
public class KsRssController {

    @Autowired
    private KsRssService ksRssService;

    /**
     * 查询路段列表
     * @param platform
     * @param version
     * @param rssName
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value = "/rss/queryKsRsSupervision")
    public ResultDTO<DbQrsDTO> queryKsRsSupervision(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "rssName", required = false) String rssName,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        return queryRsSupervision_exp(rssName, startIndex, amount, direction, 0);
    }

    /**
     * 删除监控路段
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/rss/deleteKsRsSupervision")
    public ResultDTO<DbDeleteResultDTO> deleteKsRsSupervision(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody DeleteRsFromRssRTO rto
    ) {
        return deleteKsRsSupervision_exp(rto);
    }

    /**
     * 添加监控路段
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/rss/addRsToRsSupervision")
    public ResultDTO<DbInsertResultDTO> addRsToRsSupervision(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody AddRsToRssRTO rto
    ) {
        return addRsToRsSupervision_exp(rto);
    }

    /**
     * 重点路段查询
     * @param platform
     * @param version
     * @param rssName
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value = "/rss/queryKeyRsSupervision")
    public ResultDTO<DbQrsDTO> queryKeyRsSupervision(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "rssName", required = false) String rssName,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        return queryRsSupervision_exp(rssName, startIndex, amount, direction, 1);
    }

    private ResultDTO<DbQrsDTO> queryRsSupervision_exp(String rssName, Integer startIndex, Integer amount, Integer direction, Integer type) {
        return ksRssService.queryRsSupervision_exp(rssName, startIndex, amount, direction, type);
    }

    private ResultDTO<DbDeleteResultDTO> deleteKsRsSupervision_exp(DeleteRsFromRssRTO rto) {
        return ksRssService.deleteKsRsSupervision_exp(rto);
    }

    private ResultDTO<DbInsertResultDTO> addRsToRsSupervision_exp(AddRsToRssRTO rto) {
        return ksRssService.addRsToRsSupervision_exp(rto);
    }

}
