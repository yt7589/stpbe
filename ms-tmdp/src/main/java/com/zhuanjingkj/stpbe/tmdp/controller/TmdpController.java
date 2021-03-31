package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.CreateRtspBindRTO;
import com.zhuanjingkj.stpbe.tmdp.dto.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/tmdp")
@CrossOrigin(origins = "*")
public class TmdpController {
    @Autowired
    private DkVtieService dkVtieService;
    @Autowired
    private DkVtpService dkVtpService;
    @Autowired
    private DkTitfService dkTitfService;
    @Autowired
    private DkVttfService dkVttfService;
    @Autowired
    private TvisSdkService tvisSdkService;
    @Autowired
    private TmdpService tmdpService;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    private final static Logger logger = LoggerFactory.getLogger(TmdpController.class);
    @Value("${base-image-folder}")
    private String baseImgFolder;
    @Value("${default-image-fn}")
    private String defaultImgFn;

    @GetMapping("/va/getVaImage")
    public ResponseEntity<?> getVaImage(@RequestParam(name = "imgFn") String imgFn) {
        System.out.println("##### baseImgFolder=" + baseImgFolder + "! #####");
        File imgFile = new File(baseImgFolder + imgFn);
        if (!imgFile.exists()) {
            imgFile = new File(defaultImgFn);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + imgFile.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(imgFile.length())
                .contentType(MediaType.parseMediaType("image/jpeg")).
                        body(new FileSystemResource(imgFile));
    }



    @GetMapping("/getTvisAnalysisResult")
    public ResultDTO<WsmVideoFrameDTO> getTvisAnalysisResult(
            @RequestParam("p") String platform,
            @RequestParam("v") String version,
            @RequestParam("cameraId") long cameraId,
            @RequestParam("tvisJsonId") long tvisJsonId,
            @RequestParam("direction") int direction
    ) {
        logger.info("call service.getTvisAnalysisResult");
        return tmdpService.getTvisAnalysisResult(cameraId, tvisJsonId, direction);
    }



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
    @PostMapping("/recognizeTvisImage")
    public ResultDTO<RecognizeTvisImageDTO> recognizeTvisImage(@RequestParam(name = "GCXH", required = false) String gcxh,
                                                               @RequestParam(name = "TPLX", required = false) String tplx,
                                                               @RequestParam(name = "MRHPT", required = false) String mrhpt,
                                                               @RequestParam(name = "HPHM", required = false) String hphm,
                                                               @RequestParam(name = "cameraId", required = true) String cameraId,
                                                               @RequestParam(name = "TPXX", required = false) MultipartFile file,
                                                               @RequestParam(name = "TPWJ", required = false) String tpwj) {
        System.out.println("############### recognizeTvisImage step 1");
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
        System.out.println("############### recognizeTvisImage step 2");
        File imageFile = new File("c" + cameraId + "_" + System.currentTimeMillis() + ".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);
            fos.write(data);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("############### recognizeTvisImage step 3");
        return tmdpService.recognizeTvisImage(cameraId, "0", mrhpt, hphm, data, imageFile.getAbsolutePath());
    }















    @GetMapping("/t001")
    public ResultDTO<BaseDTO> t001(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version) {
        String tblName = tvisJsonMapper.getLatesTvisJsonTblName();
        System.out.println("tblName=" + tblName + "!");
        ResultDTO<BaseDTO> dto = new ResultDTO<>();
        return dto;
    }

    @PostMapping("/tvis-sdk/createRtspBind")
    public ResultDTO<CreateRtspBindDTO> createRtspBind(
            @RequestBody CreateRtspBindRTO rto) {
        System.out.println("### creteRtspBind 1");
        return tvisSdkService.createRtspBind(rto.getRtspUrl(), "/start");
    }

    /**
     * 首页数据看板页面总体数据请求接口
     * @param platform
     * @param version
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @GetMapping("/dk/getDkMain")
    public ResultDTO<DkMainDTO> getDkMain(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
            ) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String userIdStr = request.getHeader("suid");
        ResultDTO<DkMainDTO> dto = new ResultDTO<>();
        DkMainDTO data = new DkMainDTO();
        // 本地外地车辆占比
        DkVtieDTO dkVtie = dkVtieService.getDkVtie();
        data.setDkVtie(dkVtie);
        // 车辆类型占比
        DkVtpDTO dkVtp = dkVtpService.getDkVtp();
        //data.setDkVtp(dkVtp);
        // 获取交通流量分时段显示
        DkTitfDTO dkTitf = dkTitfService.countTrafficFlow();
        data.setDkTitf(dkTitf);
        // 显示车辆类型分时段交通流量
        DkVttfDTO dkVttf = dkVttfService.getDkVttf();
        //data.setDkVttf(dkVttf);
        //
        dto.setData(data);
        return dto;
    }

    /**
     * 首页数据看板左侧第二行交通流量分时段显示柱状图接口
     * @param platform
     * @param version
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @GetMapping("/dk/getDkTitf")
    public ResultDTO<DkTitfDTO> getDkTitf(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
    ) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String userIdStr = request.getHeader("suid");
        ResultDTO<DkTitfDTO> dto = new ResultDTO<>();
        DkTitfDTO dkTitf = dkTitfService.countTrafficFlow();
        dto.setData(dkTitf);
        return dto;
    }

    /**
     * 首页数据看板左侧第一行车辆类型本地和外地占比
     * @param platform
     * @param version
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @GetMapping("/dk/getDkVtie")
    public ResultDTO<DkVtieDTO> getDkVtie(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
    ) {
        ResultDTO<DkVtieDTO> dto = new ResultDTO<>();
        dto.setData(dkVtieService.getDkVtie());
        return dto;
    }

    /**
     * 首页数据看板左侧第一行车辆类型占比统计
     * @param platform
     * @param version
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @GetMapping("/dk/getDkVtp")
    public ResultDTO<DkVtpDTO> getDkVtp(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
    ) {
        ResultDTO<DkVtpDTO> dto = new ResultDTO<>();
        dto.setData(dkVtpService.getDkVtp());
        return dto;
    }

    /**
     * 首页
     * @param platform
     * @param version
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @GetMapping("/dk/getDkVttf")
    public ResultDTO<DkVttfDTO> getDkVttf(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
    ) {
        ResultDTO<DkVttfDTO> dto = new ResultDTO<>();
        dto.setData(dkVttfService.getDkVttf());
        return dto;
    }
}
