package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SnapshotTask implements Runnable {
    private long snapshotInterval = 3000;
    private ISnapshotCamera snapshotCamera;
    private static CloseableHttpClient httpclient = null;
    private final static Logger logger = LoggerFactory.getLogger(SnapshotTask.class);

    public SnapshotTask(ISnapshotCamera snapshotCamera) {
        this.snapshotCamera = snapshotCamera;
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
            imageFile = snapshotCamera.getSnapshotFile();
            if (imageFile != null) {
                rst = submitImage(snapshotCamera.getCameraId(), imageFile);
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

    private String submitImage(long cameraId, String imageFile) {
        File f = new File(imageFile);
        Map<String, Object> map = new HashMap<>();
        map.put("GCXH", "111111");
        map.put("MRHPT", "test");
        map.put("HPHM", "test");
        map.put("MRHPT", "test");
        map.put("cameraId", "" + cameraId);
        map.put("TPMC", f.getName());
        String response = null;
        return TvisUtil.submitTvisImage(map, f);
    }
}
