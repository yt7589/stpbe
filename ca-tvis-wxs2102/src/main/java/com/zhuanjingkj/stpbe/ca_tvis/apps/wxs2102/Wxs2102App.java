package com.zhuanjingkj.stpbe.ca_tvis.apps.wxs2102;

import com.zhuanjingkj.stpbe.ca_tvis.CaTvisWxs2102Application;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import com.zhuanjingkj.stpbe.common.util.ImageBase64Converter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.io.*;
import java.util.*;

public class Wxs2102App {
    private String serverUrlBase;
    private String testApi;
    private String testImage;
    private String testImageFolder;

    public void startup() {
        serverUrlBase = CaTvisWxs2102Application.readProperty("wxs2102.server-url-base");
        testApi = CaTvisWxs2102Application.readProperty("wxs2102.test-api");
        testImage = CaTvisWxs2102Application.readProperty("wxs2102.test-image");
        testImageFolder = CaTvisWxs2102Application.readProperty("wxs2102.test-image-folder");
        testMain();
    }

    private void testMain() {
        // 列出目录下所有image文件
        List<String> files = new ArrayList<>();
        String response = null;
        files = getFolderFiles(testImageFolder, files);
        for (String fn : files) {
            System.out.println("fn: " + fn + "!");
            response = sendRequest("vehicle/function/" + testApi, fn);
            System.out.println("响应：" + response + "!");
            try{
                String fns[] = fn.split("\\\\");
                String file = fns[fns.length-1] + ".json";
                FileOutputStream writerStream = new FileOutputStream(file);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
                writer.write(response);
                writer.close();
            }catch (Exception e){
            }
        }
    }

    private List<String> getFolderFiles(String folderName, List<String> files) {
        File folderObj = new File(folderName);
        for (File fo : folderObj.listFiles()) {
            if (fo.isFile()) {
                files.add(fo.getAbsolutePath());
            } else {
                getFolderFiles(fo.getAbsolutePath(), files);
            }
        }
        return files;
    }

    private String sendRequest(String cmdUrl, String imageFile) {
        String tp = ImageBase64Converter.convertFileToBase64(imageFile);
        System.out.println("file:" + imageFile + "!");
        System.out.println("tp: " + tp + "!");
        Map<String, Object> params = new HashMap<>();
        params.put("TP", tp);
        String response = null;
        try {
            response = HttpUtil.postString(serverUrlBase + cmdUrl, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void testTruckRecog() {
        System.out.println("测试货车类型识别接口");
        String imageFile = testImage;
        String tp = ImageBase64Converter.convertFileToBase64(imageFile);
        Map<String, Object> params = new HashMap<>();
        params.put("TP", tp);
        String response = null;
        try {
            response = HttpUtil.postString(serverUrlBase + "vehicle/function/truckRecog", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response + "!");
    }

    public void testCarryPerson() {
        System.out.println("测试是否载人接口");
        String imageFile = testImage;
        String tp = ImageBase64Converter.convertFileToBase64(imageFile);
        Map<String, Object> params = new HashMap<>();
        params.put("TP", tp);
        String response = null;
        try {
            response = HttpUtil.postString(serverUrlBase + "vehicle/function/carryPerson", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response + "!");
    }

    public void testBigPlate() {
        System.out.println("测试放大号识别接口");
        String imageFile = testImage;
        String tp = ImageBase64Converter.convertFileToBase64(imageFile);
        Map<String, Object> params = new HashMap<>();
        params.put("TP", tp);
        String response = null;
        try {
            response = HttpUtil.postString(serverUrlBase + "vehicle/function/bigPlate", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response + "!");
    }

    public void testMotorClassify() {
        System.out.println("测试外卖快递识别接口");
        String imageFile = testImage;
        String tp = ImageBase64Converter.convertFileToBase64(imageFile);
        Map<String, Object> params = new HashMap<>();
        params.put("TP", tp);
        String response = null;
        try {
            response = HttpUtil.postString(serverUrlBase + "vehicle/function/motorClassify", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response + "!");
    }
}
