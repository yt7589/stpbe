package com.zhuanjingkj.stpbe.ca_tvis;

import com.zhuanjingkj.stpbe.common.BmyDao;
import com.zhuanjingkj.stpbe.common.mgq.MgqEngine;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.BrandDTO;
import com.zhuanjingkj.stpbe.data.dto.ModelDTO;
import com.zhuanjingkj.stpbe.data.vo.VehicleCltzxlVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleWztzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class MgqsClient implements ITvisClient {
    public final static int ARGS_ROOT_PATH_IDX = 1;
    public final static int ARGS_TVIS_URL = 2;
    public final static int ARGS_MGQS_URL = 3;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void startup(String[] args) {
        System.out.println("启动图搜客户端......");
        // 1. 读出指定目录图片列表
        String imagePath = args[ARGS_ROOT_PATH_IDX];
        String tvisUrl = args[ARGS_TVIS_URL];
        String mgqsUrl = args[ARGS_MGQS_URL];
        System.out.println("参数：图片目录：" + imagePath + "; 识别地址：" + tvisUrl + "; 图搜地址：" + mgqsUrl + "!");
        List<File> dsFiles = getFolderAndSubFiles(imagePath);
        // 2. 依次处理每个文件：
        for (File f : dsFiles) {
            System.out.println("### " + f + "!");
            // 2.1. 调用图片识别服务
            Map<String, Object> map = new HashMap<>();
            map.put("GCXH", "111111");
            map.put("MRHPT", "test");
            map.put("HPHM", "test");
            map.put("MRHPT", "test");
            map.put("cameraId", "101");
            map.put("TPMC", f.getName());
            String result = TvisUtil.recognizeImageFile(map, f);
            if (result.equals(TvisUtil.ERROR_RESPONSE)) {
                System.out.println("识别图片失败");
            } else {
                // SDK识别结果
                List<VehicleVo> vvos = TvisUtil.parseTvisJson(result);
                VehicleWztzVo vehicleWztzVo;
                VehicleCxtzVo vehicleCxtzVo;
                VehicleCltzxlVo vehicleCltzxlVo;
                BrandDTO brandDTO = null;
                int clppSdk = 0;
                int clppMilvus = 0;
                ModelDTO modelDTO = null;
                int ppcxSdk = 0;
                int ppcxMilvus = 0;
                for (VehicleVo vvo : vvos) {
                    vehicleWztzVo = vvo.getVehicleWztzVo();
                    vehicleCxtzVo = vvo.getVehicleCxtzVo();
                    brandDTO = BmyDao.getBrandDTO(mongoTemplate, vehicleCxtzVo.getClppCode());
                    clppSdk = brandDTO.getBrandId();
                    modelDTO = BmyDao.getModelDTO(mongoTemplate, vehicleCxtzVo.getPpcxCode());
                    ppcxSdk = modelDTO.getModelId();
                    List<List<Float>> queryEmbedding = new ArrayList<>();
                    queryEmbedding.add(vvo.getVehicleCltzxlVo().getCltzxl());
                    String partitionTag = MgqEngine.getPartitionTag(vehicleWztzVo.getPsfx(),
                            vehicleCxtzVo.getCllxflCode(),
                            vehicleCxtzVo.getCllxzflCode());
                    VehicleCxtzVo rstVo = MgqEngine.findTopK(partitionTag, queryEmbedding, 1);
                    System.out.println("识别结果：clpp:" + vehicleCxtzVo.getClppCode() +
                            "---" + clppSdk + "; " +
                            vehicleCxtzVo.getPpcxCode() + "---" + ppcxSdk + "!");
                    System.out.println("查询结果：clpp=" + rstVo.getClpp() + "; ppcx=" +
                            rstVo.getPpcx() + "; id=" + rstVo.getTzxlId() + "!");
                }
            }
            // 2.2. 从图搜系统查出结果：上传图片、查出结果、删除上传图片
            // 2.3. 二者相等则写入正确文件列表文件，并有一个网页可以浏览
            // 2.4. 二者不相等，则写入错误列表文件，并有另一个网页可以浏览
        }
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
