package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

/**
 * 抓拍机将图片通过HTTP POST方式上传到本类
 */
public class SnapshotCameraHttpServer implements ISnapshotCamera {
    @Override
    public String getSnapshotFile() {
        return null;
    }

    @Override
    public long getCameraId() {
        return 0;
    }
}
