package com.zhuanjingkj.stpbe.tvis_server.controller;


import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/stp")
public class StpController {
    private static long seq = 0;
    /**
     * 抓拍机上传图片，返回图片处理是否成功
     * @param gcxh
     * @param tplx
     * @param mrhpt
     * @param hphm
     * @param cameraId
     * @param file
     * @param tpwj
     * @return
     */
    @PostMapping("/submitImage")
    public ResultDTO<SubmitImageDTO> submitImage(@RequestParam("GCXH") String gcxh,
                                                 @RequestParam("TPLX") String tplx,
                                                 @RequestParam(name = "MRHPT", required = false) String mrhpt,
                                                 @RequestParam(name = "HPHM", required = false) String hphm,
                                                 @RequestParam(name = "cameraId", required = true) String cameraId,
                                                 @RequestParam(name = "TPXX", required = false) MultipartFile file,
                                                 @RequestParam(name = "TPWJ", required = false) String tpwj) {
        ResultDTO<SubmitImageDTO> rst = new ResultDTO<>();
        SubmitImageDTO data = new SubmitImageDTO();
        data.setTvisJsonId(seq++);
        rst.setData(data);
        return rst;
    }
}
