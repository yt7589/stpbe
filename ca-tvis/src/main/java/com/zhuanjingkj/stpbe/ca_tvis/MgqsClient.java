package com.zhuanjingkj.stpbe.ca_tvis;

import com.zhuanjingkj.stpbe.common.BmyDao;
import com.zhuanjingkj.stpbe.common.mgq.MgqEngine;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.BmyDTO;
import com.zhuanjingkj.stpbe.data.dto.BrandDTO;
import com.zhuanjingkj.stpbe.data.dto.ModelDTO;
import com.zhuanjingkj.stpbe.data.vo.VehicleCltzxlVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleWztzVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * 启动参数：mgqs d:\awork\work\es_images\images http://192.168.2.68:9510/vehicle/function/recognition http://192.168.2.15 http://
 */
@Service
public class MgqsClient implements ITvisClient {
    public final static int ARGS_ROOT_PATH_IDX = 1;
    public final static int ARGS_TVIS_URL = 2;
    public final static int ARGS_MGQS_URL = 3;
    @Autowired
    private MongoTemplate mongoTemplate;
    private final static Logger logger = LoggerFactory.getLogger(MgqsClient.class);

    public void startup(String[] args) {
        System.out.println("启动图搜客户端......");
        long cameraId = 101;
        // 1. 读出指定目录图片列表
        String imagePath = args[ARGS_ROOT_PATH_IDX];
        String tvisUrl = args[ARGS_TVIS_URL];
        String mgqsUrl = args[ARGS_MGQS_URL];
        System.out.println("参数：图片目录：" + imagePath + "; 识别地址：" + tvisUrl + "; 图搜地址：" + mgqsUrl + "!");
        List<File> dsFiles = getFolderAndSubFiles(imagePath);
        // 2. 依次处理每个文件：
        StringBuilder gImages = new StringBuilder("let g_images = [\r\n");
        StringBuilder gSdkClpps = new StringBuilder("let g_sdk_clpps = [\r\n");
        StringBuilder gSdkPpcxs = new StringBuilder("let g_sdk_ppcxs = [\r\n");
        StringBuilder gSdkCxnks = new StringBuilder("let g_sdk_cxnks = [\r\n");
        StringBuilder gMilvusClpps = new StringBuilder("let g_milvus_clpps = [\r\n");
        StringBuilder gMilvusPpcxs = new StringBuilder("let g_milvus_ppcxs = [\r\n");
        StringBuilder gMilvusCxnks = new StringBuilder("let g_milvus_cxnks = [\r\n");
        FileOutputStream fosDs = null;
        OutputStreamWriter oswDs = null;
        try {
            fosDs = new FileOutputStream("d:/awork/work/es_images/ds.txt");
            oswDs = new OutputStreamWriter(fosDs, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            for (File f : dsFiles) {
                System.out.println("### " + f + "!");
                // 2.1. 调用图片识别服务
                Map<String, Object> map = new HashMap<>();
                map.put("GCXH", "111111");
                map.put("MRHPT", "test");
                map.put("HPHM", "test");
                map.put("MRHPT", "test");
                map.put("cameraId", "" + cameraId);
                map.put("TPMC", f.getName());
                String result = TvisUtil.recognizeImageFile(map, f);
                if (result.equals(TvisUtil.ERROR_RESPONSE)) {
                    System.out.println("识别图片失败");
                } else {
                    // SDK识别结果
                    List<VehicleVo> vvos = TvisUtil.parseTvisJson(cameraId, result);
                    VehicleWztzVo vehicleWztzVo;
                    VehicleCxtzVo vehicleCxtzVo;
                    VehicleCltzxlVo vehicleCltzxlVo;
                    BrandDTO brandDTO = null;
                    BrandDTO brandDTOMilvus = null;
                    int clppSdk = 0;
                    int clppMilvus = 0;
                    ModelDTO modelDTO = null;
                    ModelDTO modelDTOMilvus = null;
                    int ppcxSdk = 0;
                    int ppcxMilvus = 0;
                    BmyDTO bmyDTO = null;
                    BmyDTO bmyDTOMilvus = null;
                    int cxnkSdk = 0;
                    int cxnkMilvus = 0;
                    for (VehicleVo vvo : vvos) {
                        vehicleWztzVo = vvo.getVehicleWztzVo();
                        vehicleCxtzVo = vvo.getVehicleCxtzVo();
                        brandDTO = BmyDao.getBrandDTOByCode(mongoTemplate, vehicleCxtzVo.getClppCode());
                        clppSdk = brandDTO.getBrandId();
                        modelDTO = BmyDao.getModelDTOByCode(mongoTemplate, vehicleCxtzVo.getPpcxCode());
                        ppcxSdk = modelDTO.getModelId();
                        logger.info("##### bmy_code=" + vehicleCxtzVo.getCxnkCode() + "!");
                        bmyDTO = BmyDao.getBmyDTOByCode(mongoTemplate, vehicleCxtzVo.getCxnkCode());
                        logger.info("##### bmyId=" + bmyDTO.getBmyId() + "; bmyCode="
                                + bmyDTO.getBmyCode() + "; bmyName=" + bmyDTO.getBmyName() + "!");
                        cxnkSdk = bmyDTO.getBmyId();
                        List<List<Float>> queryEmbedding = new ArrayList<>();
                        queryEmbedding.add(vvo.getVehicleCltzxlVo().getCltzxl());
                        String partitionTag = MgqEngine.getPartitionTag(vehicleWztzVo.getPsfx(),
                                vehicleCxtzVo.getCllxflCode(),
                                vehicleCxtzVo.getCllxzflCode());
                        VehicleCxtzVo rstVo = MgqEngine.findTopK(partitionTag, queryEmbedding, 1);
                        clppMilvus = rstVo.getClpp();
                        ppcxMilvus = rstVo.getPpcx();
                        cxnkMilvus = rstVo.getCxnk();
                        if (clppSdk == clppMilvus && ppcxSdk == ppcxMilvus) {
                            System.out.println("###### 识别结果一致(" + f + "):");
                            System.out.println("    正确结果：" + brandDTO.getBrandName()
                                    + "-" + modelDTO.getModelName() + "!");
                            oswDs.write(f + "*" + bmyDTO.getBmyId() + "*" + brandDTO.getBrandId() + "\r\n");
                            oswDs.flush();
                        } else {
                            System.out.println("@@@@@@ 识别结果不一致（" + f + "）：");
                            brandDTOMilvus = BmyDao.getBrandDTOById(mongoTemplate, clppMilvus);
                            modelDTOMilvus = BmyDao.getModelDTOById(mongoTemplate, ppcxMilvus);
                            bmyDTOMilvus = BmyDao.getBmyDTOById(mongoTemplate, cxnkMilvus);
                            if (gImages.length() > 25) {
                                gImages.append(",\r\n'" + f.getAbsolutePath().replace(
                                        "\\", "\\\\") + "'");
                                gSdkClpps.append(",\r\n{'id': " + brandDTO.getBrandId()
                                        + ", 'code': '" + brandDTO.getBrandCode()
                                        + "', 'name': '" + brandDTO.getBrandName() + "'}");
                                gSdkPpcxs.append(",\r\n{'id': " + modelDTO.getModelId()
                                        + ", 'code': '" + modelDTO.getModelCode()
                                        + "', 'name': '" + modelDTO.getModelName() + "'}");
                                gSdkCxnks.append(",\r\n{'id': " + bmyDTO.getBmyId()
                                        + ", 'code': '" + bmyDTO.getBmyCode()
                                        + "', 'name': '" + bmyDTO.getBmyName() + "'}");
                                gMilvusClpps.append(",\r\n{'id': " + brandDTOMilvus.getBrandId()
                                        + ", 'code': '" + brandDTOMilvus.getBrandCode()
                                        + "', 'name': '" + brandDTOMilvus.getBrandName() + "'}");
                                gMilvusPpcxs.append(",\r\n{'id': " + modelDTOMilvus.getModelId()
                                        + ", 'code': '" + modelDTOMilvus.getModelCode()
                                        + "', 'name': '" + modelDTOMilvus.getModelName() + "'}");
                                gMilvusCxnks.append(",\r\n{'id': " + bmyDTOMilvus.getBmyId()
                                        + ", 'code': '" + bmyDTOMilvus.getBmyCode()
                                        + "', 'name': '" + bmyDTOMilvus.getBmyName() + "-" +
                                        bmyDTOMilvus.getBmyCode() + "'}");
                            } else {
                                gImages.append("'" +
                                        f.getAbsolutePath().replace("\\", "\\\\") +
                                        "'");
                                gSdkClpps.append("{'id': " + brandDTO.getBrandId()
                                        + ", 'code': '" + brandDTO.getBrandCode()
                                        + "', 'name': '" + brandDTO.getBrandName() + "'}");
                                gSdkPpcxs.append("{'id': " + modelDTO.getModelId()
                                        + ", 'code': '" + modelDTO.getModelCode()
                                        + "', 'name': '" + modelDTO.getModelName() + "'}");
                                gSdkCxnks.append("{'id': " + bmyDTO.getBmyId()
                                        + ", 'code': '" + bmyDTO.getBmyCode()
                                        + "', 'name': '" + bmyDTO.getBmyName() + "'}");
                                gMilvusClpps.append("{'id': " + brandDTOMilvus.getBrandId()
                                        + ", 'code': '" + brandDTOMilvus.getBrandCode()
                                        + "', 'name': '" + brandDTOMilvus.getBrandName() + "'}");
                                gMilvusPpcxs.append("{'id': " + modelDTOMilvus.getModelId()
                                        + ", 'code': '" + modelDTOMilvus.getModelCode()
                                        + "', 'name': '" + modelDTOMilvus.getModelName() + "'}");
                                gMilvusCxnks.append("{'id': " + bmyDTOMilvus.getBmyId()
                                        + ", 'code': '" + bmyDTOMilvus.getBmyCode()
                                        + "', 'name': '" + bmyDTOMilvus.getBmyName() + "-" +
                                        bmyDTOMilvus.getBmyCode() + "'}");
                            }
                            System.out.println("    识别结果："
                                    + brandDTO.getBrandName() + "(" + brandDTO.getBrandId() + ")"
                                    + "; " + modelDTO.getModelName() + "(" + modelDTO.getModelId() + ")");
                            System.out.println("    查询结果："
                                    + brandDTOMilvus.getBrandName() + "(" + brandDTOMilvus.getBrandId() + ")"
                                    + "; " + modelDTOMilvus.getModelName() + "(" + modelDTOMilvus.getModelId() + ")");
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            fosDs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gImages.append("]\r\n");
        gSdkClpps.append("]\r\n");
        gSdkPpcxs.append("]\r\n");
        gSdkCxnks.append("]\r\n");
        gMilvusClpps.append("]\r\n");
        gMilvusPpcxs.append("]\r\n");
        gMilvusCxnks.append("]\r\n");
        StringBuilder html = new StringBuilder();
        html.append(generateBeginHtml());
        html.append(gImages.toString());
        html.append(gSdkClpps.toString());
        html.append(gSdkPpcxs.toString());
        html.append(gSdkCxnks.toString());
        html.append(gMilvusClpps.toString());
        html.append(gMilvusPpcxs.toString());
        html.append(gMilvusCxnks.toString());
        html.append(generateEndHtml());
        generateHtmlFile("d:/awork/work/es_images/question_images.html", html.toString());
    }
    // 主要是看重这家公司里能做人工智能技术，我目前做的是根据图片识别车辆品牌车型年款。不过公司现在让我做智慧交通应用平台，做人工智能这边只能是兼职了。

    public void generateHtmlFile(String fn, String html) {
        System.out.println(html);
        try {
            FileOutputStream fos = new FileOutputStream(fn);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(html);
            osw.flush();
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateGImages() {
        StringBuilder html = new StringBuilder();
        html.append("let g_images = [\n" +
                "\t'E:\\\\temp\\\\es_images_bk\\\\v001.jpg',\n" +
                "\t'E:\\\\temp\\\\es_images_bk\\\\v002.jpg',\n" +
                "\t'E:\\\\temp\\\\es_images_bk\\\\v003.jpg',\n" +
                "\t'E:\\\\temp\\\\es_images_bk\\\\v004.jpg',\n" +
                "\t'E:\\\\temp\\\\es_images_bk\\\\v005.jpg'\n" +
                "]\r\n");
        return html.toString();
    }

    public String generateGSdkClpps() {
        StringBuilder html = new StringBuilder();
        html.append("let g_sdk_clpps = [\n" +
                "\t{'id': 101, 'name': '奥迪1'},\n" +
                "\t{'id': 102, 'name': '奥迪2'},\n" +
                "\t{'id': 103, 'name': '奥迪3'},\n" +
                "\t{'id': 104, 'name': '奥迪4'},\n" +
                "\t{'id': 105, 'name': '奥迪5'}\n" +
                "]\r\n");
        return html.toString();
    }

    public String generateGSdkPpcxs() {
        StringBuilder html = new StringBuilder();
        html.append("let g_sdk_ppcxs = [\n" +
                "\t{'id': 201, 'name': '奥迪1-车型1'},\n" +
                "\t{'id': 202, 'name': '奥迪2-车型1'},\n" +
                "\t{'id': 203, 'name': '奥迪3-车型1'},\n" +
                "\t{'id': 204, 'name': '奥迪4-车型1'},\n" +
                "\t{'id': 205, 'name': '奥迪5-车型1'}\n" +
                "]\r\n");
        return html.toString();
    }

    public String generateGMilvusClpps() {
        StringBuilder html = new StringBuilder();
        html.append("let g_milvus_clpps = [\n" +
                "\t{'id': 301, 'name': '奔驰1'},\n" +
                "\t{'id': 302, 'name': '奔驰2'},\n" +
                "\t{'id': 303, 'name': '奔驰3'},\n" +
                "\t{'id': 304, 'name': '奔驰4'},\n" +
                "\t{'id': 305, 'name': '奔驰5'}\n" +
                "]\r\n");
        return html.toString();
    }

    public String generateGMilvusPpcxs() {
        StringBuilder html = new StringBuilder();
        html.append("let g_milvus_ppcxs = [\n" +
                "\t{'id': 401, 'name': '奔驰1-车型1'},\n" +
                "\t{'id': 402, 'name': '奔驰2-车型1'},\n" +
                "\t{'id': 403, 'name': '奔驰3-车型1'},\n" +
                "\t{'id': 404, 'name': '奔驰4-车型1'},\n" +
                "\t{'id': 405, 'name': '奔驰5-车型1'}\n" +
                "]\r\n");
        return html.toString();
    }

    public String generateBeginHtml() {
        StringBuilder html = new StringBuilder();
        html.append("<html>\n" +
                "<style>\n" +
                ".box_td {\n" +
                "\tborder: 1px solid blue;\n" +
                "}\n" +
                "\n" +
                ".td_title {\n" +
                "\tborder: 1px solid blue;\n" +
                "\twidth: 20%;\n" +
                "}\n" +
                "\n" +
                ".td_content {\n" +
                "\tborder: 1px solid blue;\n" +
                "\twidth: 80%;\n" +
                "}\n" +
                "</style>\n" +
                "<script>\n" +
                "let g_idx = 0\n" +
                "let g_urlBase = 'http://192.168.2.68:5000/'\n" +
                "let g_sdkIdx = 0\n" +
                "let g_milvusIdx = 0\r\n");
        return html.toString();
    }

    public String generateEndHtml() {
        StringBuilder html = new StringBuilder();
        html.append("function showPage() {\n" +
                "\tgetImgUrl(g_sdk_cxnks[g_idx].id, g_sdkIdx, document.getElementById('imgSdk'))\n" +
                "\tgetImgUrl(g_milvus_cxnks[g_idx].id, g_milvusIdx, document.getElementById('imgMilvus'))\n" +
                "\tdocument.getElementById('imgFile').innerText = g_images[g_idx]\n" +
                "\tdocument.getElementById('imgObj').src = g_images[g_idx]\n" +
                "\t// SDK品牌\n" +
                "\tdocument.getElementById('clppIdSdk').innerText = g_sdk_clpps[g_idx].id\n" +
                "\tdocument.getElementById('clppCodeSdk').innerText = g_sdk_clpps[g_idx].code\n" +
                "\tdocument.getElementById('clppNameSdk').innerText = g_sdk_clpps[g_idx].name\n" +
                "\t// SDK车型\n" +
                "\tdocument.getElementById('ppcxIdSdk').innerText = g_sdk_ppcxs[g_idx].id\n" +
                "\tdocument.getElementById('ppcxCodeSdk').innerText = g_sdk_ppcxs[g_idx].code\n" +
                "\tdocument.getElementById('ppcxNameSdk').innerText = g_sdk_ppcxs[g_idx].name\n" +
                "\t// SDK年款\n" +
                "\tdocument.getElementById('cxnkIdSdk').innerText = '' + g_sdk_cxnks[g_idx].id\n" +
                "\tdocument.getElementById('cxnkCodeSdk').innerText = '' + g_sdk_cxnks[g_idx].code\n" +
                "\tdocument.getElementById('cxnkNameSdk').innerText = '' + g_sdk_cxnks[g_idx].name\n" +
                "\t// Milvus品牌\n" +
                "\tdocument.getElementById('clppIdMilvus').innerText = g_milvus_clpps[g_idx].id\n" +
                "\tdocument.getElementById('clppCodeMilvus').innerText = g_milvus_clpps[g_idx].code\n" +
                "\tdocument.getElementById('clppNameMilvus').innerText = g_milvus_clpps[g_idx].name\n" +
                "\t// Milvus车型\n" +
                "\tdocument.getElementById('ppcxIdMilvus').innerText = g_milvus_ppcxs[g_idx].id\n" +
                "\tdocument.getElementById('ppcxCodeMilvus').innerText = g_milvus_ppcxs[g_idx].code\n" +
                "\tdocument.getElementById('ppcxNameMilvus').innerText = g_milvus_ppcxs[g_idx].name\n" +
                "\t// Milvus年款\n" +
                "\tdocument.getElementById('cxnkIdMilvus').innerText = '' + g_milvus_cxnks[g_idx].id\n" +
                "\tdocument.getElementById('cxnkCodeMilvus').innerText = '' + g_milvus_cxnks[g_idx].code\n" +
                "\tdocument.getElementById('cxnkNameMilvus').innerText = '' + g_milvus_cxnks[g_idx].name\n" +
                "}\n" +
                "\n" +
                "function goPrev() {\n" +
                "\tg_idx--\n" +
                "\tif (g_idx<0) {\n" +
                "\t\tg_idx = 0\n" +
                "\t}\n" +
                "\tshowPage()\n" +
                "}\n" +
                "function goNext() {\n" +
                "\tg_idx++\n" +
                "\tif (g_idx>=g_images.length-1) {\n" +
                "\t\tg_idx=g_images.length-1\n" +
                "\t}\n" +
                "\tshowPage()\n" +
                "}\n" +
                "\n" +
                "let g_appUrlBase = 'http://192.168.2.37:9520/'\n" +
                "function chooseAnswer(idx) {\n" +
                "\tlet brandId = 0\n" +
                "\tlet bmyId = 0\n" +
                "\tif (1 == idx) {\n" +
                "\t\tconsole.log('分类结果正确')\n" +
                "\t\tbrandId = g_sdk_clpps[g_idx].id\n" +
                "\t\tbmyId = g_sdk_cxnks[g_idx].id\n" +
                "\t} else {\n" +
                "\t\tconsole.log('查询结果正确')\n" +
                "\t\tbrandId = g_milvus_clpps[g_idx].id\n" +
                "\t\tbmyId = g_milvus_cxnks[g_idx].id\n" +
                "\t}\n" +
                "\tconsole.log('sdk=' + document.getElementById('cxnkIdSdk').innerText + '; milvus=' + document.getElementById('cxnkIdMilvus').innerText)\n" +
                "\tvar request = new XMLHttpRequest()\n" +
                "\trequest.open('POST', g_appUrlBase + 'tvis/saveQuestionImages')\n" +
                "\trequest.onreadystatechange = function() {\n" +
                "        if(this.readyState === 4 && this.status === 200) {\n" +
                "            console.log(this.responseText)\n" +
                "        }\n" +
                "    }\n" +
                "\tlet body = {\n" +
                "\t\t'imageName': g_images[g_idx],\n" +
                "\t\t'brandId': brandId,\n" +
                "\t\t'bmyId': bmyId\n" +
                "\t}\n" +
                "\trequest.setRequestHeader('Content-Type', 'application/json')\n" +
                "\trequest.send(JSON.stringify(body))\n" +
                "}\n" +
                "\n" +
                "function getImgUrl(bmyId, idx, imgElement) {\n" +
                "\tvar request = new XMLHttpRequest()\n" +
                "\trequest.open('GET', g_urlBase + 'getBmyIdExampleImgFile?bmyId=' + bmyId + '&imgIdx=' + idx)\n" +
                "\trequest.onreadystatechange = function() {\n" +
                "        if(this.readyState === 4 && this.status === 200) {\n" +
                "            console.log(this.responseText)\n" +
                "\t\t\tlet jsonObj = JSON.parse(this.responseText)\n" +
                "\t\t\tlet imgFile = escape(encodeURI(jsonObj.data.imgFile))\n" +
                "\t\t\timgElement.src = g_urlBase + 'displayImage?imgFile=' + imgFile\n" +
                "        }\n" +
                "    }\n" +
                "\trequest.send()\n" +
                "}\n" +
                "\n" +
                "function goPrevSdkImg() {\n" +
                "\tg_sdkIdx--\n" +
                "\tif (g_sdkIdx < 0) {\n" +
                "\t\tg_sdkIdx = 0\n" +
                "\t}\n" +
                "\tgetImgUrl(g_sdk_cxnks[g_idx].id, g_sdkIdx, document.getElementById('imgSdk'))\n" +
                "}\n" +
                "\n" +
                "function goNextSdkImg() {\n" +
                "\tg_sdkIdx++\n" +
                "\tgetImgUrl(g_sdk_cxnks[g_idx].id, g_sdkIdx, document.getElementById('imgSdk'))\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "function goPrevMilvusImg() {\n" +
                "\tg_milvusIdx--\n" +
                "\tif (g_milvusIdx < 0) {\n" +
                "\t\tg_milvusIdx = 0\n" +
                "\t}\n" +
                "\tgetImgUrl(g_milvus_cxnks[g_idx].id, g_milvusIdx, document.getElementById('imgMilvus'))\n" +
                "}\n" +
                "\n" +
                "function goNextMilvusImg() {\n" +
                "\tg_milvusIdx++\n" +
                "\tgetImgUrl(g_milvus_cxnks[g_idx].id, g_milvusIdx, document.getElementById('imgMilvus'))\n" +
                "}\n" +
                "\n" +
                "</script><body onload='showPage()'>\n" +
                "图片文件：<span id='imgFile'></span><br />\n" +
                "<br />\n" +
                "\n" +
                "<table style=\"width: 100%;\">\n" +
                "<tr>\n" +
                "<td>\n" +
                "\n" +
                "<!-- 原始图片 -->\n" +
                "<table style='text-align: center; width: 33%;'>\n" +
                "\t<tr>\n" +
                "\t\t<td>原始图片</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td><img id='imgObj' src='' style='height: 450px;' /></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>\n" +
                "\t\t\t<table style='width: 100%; text-align: center;'>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t<input type='button' id='btnPrev' value='上一张' onclick='goPrev()' />&nbsp; &nbsp; &nbsp;\n" +
                "\t\t\t\t\t\t<input type='button' id='btnNext' value='下一张' onclick='goNext()' />\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<table style='width: 100%;'>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>&nbsp;</td>\n" +
                "\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t&nbsp;<br />\n" +
                "\t\t\t\t\t\t&nbsp;<br />\n" +
                "\t\t\t\t\t\t&nbsp;\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>&nbsp;</td>\n" +
                "\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t&nbsp;<br />\n" +
                "\t\t\t\t\t\t&nbsp;<br />\n" +
                "\t\t\t\t\t\t&nbsp;\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>&nbsp;</td>\n" +
                "\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t&nbsp;<br />\n" +
                "\t\t\t\t\t\t&nbsp;<br />\n" +
                "\t\t\t\t\t\t&nbsp;\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>&nbsp;</td>\n" +
                "\t\t\t\t\t<td>&nbsp;</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "\n" +
                "</td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<td>\n" +
                "<!-- 原始图片 -->\n" +
                "<table style='text-align: center; width: 33%;'>\n" +
                "\t<tr>\n" +
                "\t\t<td>SDK结果</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td><img id='imgSdk' src='' style='height: 450px;' /></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>\n" +
                "\t\t\t<table style='width: 100%; text-align: center;'>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t<input type='button' id='btnSdkPrev' value='上一张' onclick='goPrevSdkImg()' />&nbsp; &nbsp; &nbsp;\n" +
                "\t\t\t\t\t\t<input type='button' id='btnSdkNext' value='下一张' onclick='goNextSdkImg()' />\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<table style='width: 100%; border: 1px solid blue;'>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>品牌</td>\n" +
                "\t\t\t\t\t<td class='td_content'><span id='clppIdSdk'></span><br /><span id='clppCodeSdk'></span><br /><span id='clppNameSdk'></span></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>车型</td>\n" +
                "\t\t\t\t\t<td class='td_content'><span id='ppcxIdSdk'></span><br /><span id='ppcxCodeSdk'></span><br /><span id='ppcxNameSdk'></span></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>年款</td>\n" +
                "\t\t\t\t\t<td class='td_content'><span id='cxnkIdSdk'></span><br /><span id='cxnkCodeSdk'></span><br /><span id='cxnkNameSdk'></span></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>操作</td>\n" +
                "\t\t\t\t\t<td class='td_content'><input type=\"button\" value=\"正确\" onclick=\"chooseAnswer(1)\" /></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "\n" +
                "<td>\n" +
                "<!-- 原始图片 -->\n" +
                "<table style='text-align: center; width: 33%;'>\n" +
                "\t<tr>\n" +
                "\t\t<td>查询结果</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td><img id='imgMilvus' src='' style='height: 450px;' /></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td>\n" +
                "\t\t\t<table style='width: 100%; text-align: center;'>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t<input type='button' id='btnMilvusPrev' value='上一张' onclick='goPrevMilvusImg()' />&nbsp; &nbsp; &nbsp;\n" +
                "\t\t\t\t\t\t<input type='button' id='btnMilvusNext' value='下一张' onclick='goNextMilvusImg()' />\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<table style='width: 100%; border: 1px solid blue;'>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>品牌</td>\n" +
                "\t\t\t\t\t<td class='td_content'>\n" +
                "\t\t\t\t\t\t<span id='clppIdMilvus'></span><br />\n" +
                "\t\t\t\t\t\t<span id='clppCodeMilvus'></span><br />\n" +
                "\t\t\t\t\t\t<span id='clppNameMilvus'></span>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>车型</td>\n" +
                "\t\t\t\t\t<td class='td_content'>\n" +
                "\t\t\t\t\t\t<span id='ppcxIdMilvus'></span><br />\n" +
                "\t\t\t\t\t\t<span id='ppcxCodeMilvus'></span><br />\n" +
                "\t\t\t\t\t\t<span id='ppcxNameMilvus'></span>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>年款</td>\n" +
                "\t\t\t\t\t<td class='td_content'>\n" +
                "\t\t\t\t\t\t<span id='cxnkIdMilvus'></span><br />\n" +
                "\t\t\t\t\t\t<span id='cxnkCodeMilvus'></span><br />\n" +
                "\t\t\t\t\t\t<span id='cxnkNameMilvus'></span>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td class='td_title'>操作</td>\n" +
                "\t\t\t\t\t<td class='td_content'><input type=\"button\" value=\"正确\" onclick=\"chooseAnswer(2)\" /></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        return html.toString();
    }

    public List<File> getFolderAndSubFiles(String folderName) {
        File file = new File(folderName);
        List<File> dirList = new LinkedList<>();
        List<File> fileList = new ArrayList<>();
        getFolderFiles(file, dirList, fileList);
        File tmp;
        while (!dirList.isEmpty()) {
            tmp = (File)((LinkedList)dirList).removeFirst();
            getFolderFiles(tmp, dirList, fileList);
        }
        return fileList;
    }

    public void getFolderFiles(File file, List<File> dirList, List<File> fileList) {
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                dirList.add(f);
            } else {
                // 这里列出当前文件夹根目录下的所有文件,并添加到fileList列表中
                fileList.add(f);
                // System.out.println("file==>" + f);
            }

        }
    }
}
