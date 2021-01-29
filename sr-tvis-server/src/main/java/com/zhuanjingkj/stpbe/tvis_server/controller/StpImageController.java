package com.zhuanjingkj.stpbe.tvis_server.controller;


import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.SubmitImageDTO;
import com.zhuanjingkj.stpbe.tvis_server.service.impl.StpImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/stp")
public class StpImageController {
    private final static Logger logger = LoggerFactory.getLogger(StpImageController.class);
    @Autowired
    private StpImageService stpImageService;
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
        logger.info("#Yt#: step 1");
        logger.info("#Yt#: step 1.2 image=" + file.getOriginalFilename() + "!");
        byte[] data = null;
        if ("1".equals(tplx)) {
            if (file != null) {
                try {
                    data = file.getBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if ("2".equals(tplx)) {
            data = Base64.getDecoder().decode(tpwj);
        }
        logger.info("#Yt#: step 2");
        return stpImageService.submitImage(cameraId, "0", mrhpt, hphm, data);
    }
}
