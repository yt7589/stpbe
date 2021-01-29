package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SnapshotTask implements Runnable {
    private long snapshotInterval = 3000;
    private ISnapshotCamera snapshotMachine;
    private static CloseableHttpClient httpclient = null;

    public SnapshotTask(ISnapshotCamera snapshotMachine) {
        this.snapshotMachine = snapshotMachine;
    }

    @Override
    public void run() {
        if (null == httpclient) {
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(20);
            httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();
        }
        System.out.println("抓拍间隔：" + snapshotInterval + "!");
        String imageFile;
        String rst = null;
        while (true) {
            System.out.println("获取抓拍机图片文件");
            imageFile = snapshotMachine.getSnapshotFile();
            if (imageFile != null) {
                rst = submitImage(imageFile);
                System.out.println("    上报给sr-tvis-server: " + imageFile + "; rst=" + rst + "!");
            } else {
                System.out.println("    获取图片为空!");
            }
            try {
                Thread.sleep(snapshotInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String submitImage(String imageFile) {
        File f = new File(imageFile);
        Map<String, Object> map = new HashMap<>();
        map.put("GCXH", "111111");
        map.put("MRHPT", "test");
        map.put("HPHM", "test");
        map.put("MRHPT", "test");
        map.put("cameraId", "101");
        map.put("TPMC", f.getName());
        String response = null;
        return TvisUtil.submitTvisImage(map, f);
    }
}
