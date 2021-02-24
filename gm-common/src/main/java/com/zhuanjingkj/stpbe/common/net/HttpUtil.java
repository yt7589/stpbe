package com.zhuanjingkj.stpbe.common.net;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static CloseableHttpClient httpclient = null;

    private static CloseableHttpClient getHttpclient() {
        if (null == httpclient) {
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(20);
            httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();
        }
        return httpclient;
    }

    public static String postFile(String url, Map<String, ? extends Object> data) throws IOException {
        httpclient = getHttpclient();
        HttpPost post = new HttpPost(url);
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, ? extends Object> entry : data.entrySet()) {
                if (entry.getValue() instanceof File) {
                    builder.addBinaryBody(entry.getKey(), (File) entry.getValue());
                } else {
                    builder.addTextBody(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            post.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } finally {
        }
    }

    public static String postString(String url, Map<String, Object> data) throws IOException {
        httpclient = getHttpclient();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            List<BasicNameValuePair> pair = new ArrayList<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                pair.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            post.setEntity(new UrlEncodedFormEntity(pair));
            response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public static byte[] downloadImage(String imageUrl) {
        URL url = null;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        //从网络上下载一张图片
        InputStream inputStream = null;
        //建立一个网络链接
        HttpURLConnection con = null;
        try {
            url = new URL(imageUrl);
            con = (HttpURLConnection) url.openConnection();
            inputStream = con.getInputStream();
            int n = -1;
            byte b [] = new byte[1024];
            while ((n = inputStream.read(b)) != -1) {
                //outputStream.write(b, 0, n);
                output.write(b, 0, n);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return output.toByteArray();
    }
}
