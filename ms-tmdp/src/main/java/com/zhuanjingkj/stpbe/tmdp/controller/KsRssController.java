package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsRssDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddRsToRssRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteRsFromRssRTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Key Supervision =>Road Section Supervision
 * 重点监管=》路段监管
 */
@RestController
@RequestMapping(value ="/ks")
@CrossOrigin(origins = "*")
public class KsRssController {

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
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryRsSupervision_exp();
    }

    /**
     * 删除监控路段
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/rss/deleteKsRsSupervision")
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
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "driection", required = false) Integer direction
    ) {
        return queryRsSupervision_exp();
    }

    private ResultDTO<DbQrsDTO> queryRsSupervision_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<DbQrsDTO>();
        DbQrsDTO data = new DbQrsDTO(100,5,0,10,5,null);
        List<KsRssDTO> recs = new ArrayList<>();
        recs.add(new KsRssDTO(101, "上地", 0,  "10001"));
        recs.add(new KsRssDTO(102, "上地8街", 1,  "100021"));
        recs.add(new KsRssDTO(103, "上地9街", 1,  "100022"));
        recs.add(new KsRssDTO(104, "上地10街", 1,  "100023"));
        recs.add(new KsRssDTO(105, "上地5街", 1,  "100024"));
        recs.add(new KsRssDTO(106, "上地4街", 1,  "100025"));
        recs.add(new KsRssDTO(107, "上地3街", 1,  "100026"));
        recs.add(new KsRssDTO(108, "上地2街", 1,  "100027"));
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbDeleteResultDTO> deleteKsRsSupervision_exp(DeleteRsFromRssRTO rto) {
        System.out.println("delete rss: " + rto.getRssId() + "!");
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(1);
        dto.setData(data);
        return dto;
    }

    private ResultDTO<DbInsertResultDTO> addRsToRsSupervision_exp(AddRsToRssRTO rto) {
        List<Integer> rssIds = rto.getRssIds();
        for (int i = 0 ; i < rssIds.size() ; i ++) {
            System.out.println("rssId:" + rssIds.get(i));
        }
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(108, 1);
        dto.setData(data);
        return dto;
    }

}
