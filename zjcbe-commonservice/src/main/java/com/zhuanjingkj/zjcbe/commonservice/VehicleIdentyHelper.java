package com.zhuanjingkj.zjcbe.commonservice;

import com.zhuanjingkj.zjcbe.commondata.config.CommonConfig;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleIdentifyMessageDTO;
import com.zhuanjingkj.zjcbe.utility.json.JsonUtils;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class VehicleIdentyHelper {

    private final String CHARSET = "UTF-8";
    private final String TPLX = "1";

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Autowired
    private CommonConfig autoConfig;

    public VehicleIdentifyMessageDTO uploadImage(String imgId, String imgPath) {
        String actionUrl = autoConfig.getIdentificationApiurl() + "/vehicle/function/recognition";
        String responeStr = postFile(actionUrl, imgId, imgPath);
        if (!StringUtils.isBlank(responeStr)) {
            return JsonUtils.parseObject(responeStr, VehicleIdentifyMessageDTO.class);
        }
        return null;
    }

    private String postFile(String actionUrl, String fileId, String filePath) {
        HttpPost httpPost = new HttpPost(actionUrl);
        RequestConfig rc = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(5000)
                .build();
        httpPost.setConfig(rc);
        File file = new File(filePath);
        FileBody fileBody = new FileBody(file);
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .setCharset(Charset.forName(CHARSET))
                .addTextBody("TPLX", TPLX)
                .addTextBody("GCXH", fileId)
                .addPart("TPWJ", fileBody)//添加到请求
                .build();
        httpPost.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
