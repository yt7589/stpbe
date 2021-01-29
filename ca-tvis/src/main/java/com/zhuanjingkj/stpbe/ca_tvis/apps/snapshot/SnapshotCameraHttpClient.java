package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

/**
 * 当抓拍机机提供HTTP服务器，抓拍文件为一定格式URL时使用
 */
public class SnapshotCameraHttpClient implements ISnapshotCamera {
    @Override
    public String getSnapshotFile() {
        return null;
    }

    @Override
    public long getCameraId() {
        return 0;
    }
}
