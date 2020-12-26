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

import java.io.File;
import java.io.IOException;
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
        System.out.println("HttpUrl.postFile 1 url:" + url + "!");
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
            System.out.println("HttpUrl.postFile 1.5 resp: " + post.getURI() + "!");
            CloseableHttpResponse response = httpclient.execute(post);
            System.out.println("HttpUrl.postFile 2 resp: " + response + "!");
            System.out.println("HttpUrl.postFile 3 resp: " + response.getEntity() + "!");
            System.out.println("HttpUrl.postFile 4 resp: " + response.getEntity().toString() + "!");
            return EntityUtils.toString(response.getEntity());
        } finally {
        }
    }

    public static String postString(String url, Map<String, Object> data) throws IOException {
        httpclient = getHttpclient();
        HttpPost post = new HttpPost(url);
        try {
            List<BasicNameValuePair> pair = new ArrayList<>();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                pair.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            post.setEntity(new UrlEncodedFormEntity(pair));
            CloseableHttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } finally {
        }
    }
}
