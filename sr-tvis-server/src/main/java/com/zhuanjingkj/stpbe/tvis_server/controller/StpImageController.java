package com.zhuanjingkj.stpbe.tvis_server.controller;


import com.mysql.cj.util.StringUtils;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.RecognizeTvisImageDTO;
import com.zhuanjingkj.stpbe.data.dto.WsmVideoFrameDTO;
import com.zhuanjingkj.stpbe.tvis_server.service.impl.StpImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

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
    public ResultDTO<RecognizeTvisImageDTO> submitImage(@RequestParam("GCXH") String gcxh,
                                                        @RequestParam("TPLX") String tplx,
                                                        @RequestParam(name = "MRHPT", required = false) String mrhpt,
                                                        @RequestParam(name = "HPHM", required = false) String hphm,
                                                        @RequestParam(name = "cameraId", required = true) String cameraId,
                                                        @RequestParam(name = "TPXX", required = false) MultipartFile file,
                                                        @RequestParam(name = "TPWJ", required = false) String tpwj) {
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
        File imageFile = new File("c" + cameraId + "_" + System.currentTimeMillis() + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stpImageService.submitImage(cameraId, "0", mrhpt, hphm, data, imageFile.getAbsolutePath());
    }

    @GetMapping("/getTvisAnalysisResult")
    public ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(
            @RequestParam("p") String platform,
            @RequestParam("v") String version,
            @RequestParam("cameraId") long cameraId,
            @RequestParam("tvisJsonId") long tvisJsonId,
            @RequestParam("direction") int direction
    ) {
        return stpImageService.getTvisAnalysisResult(cameraId, tvisJsonId, direction);
    }
}
