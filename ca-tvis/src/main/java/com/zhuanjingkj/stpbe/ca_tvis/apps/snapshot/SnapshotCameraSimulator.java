package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

import com.zhuanjingkj.stpbe.common.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class SnapshotCameraSimulator implements ISnapshotCamera {
    private long cameraId;
    private static List<String> imageFiles;
    private static int idx;
    private boolean isLoop;

    public SnapshotCameraSimulator(long cameraId, String baseFolder) {
        this.cameraId = cameraId;
        imageFiles = new ArrayList<>();
        FileUtil.getFolderFnsRecursive(baseFolder, imageFiles);
        idx = 0;
    }
    @Override
    public String getSnapshotFile() {
        if (idx >= imageFiles.size()) {
            if (!isLoop) {
                return null;
            }
            idx = 0;
        }
        return imageFiles.get(idx++);
    }

    @Override
    public long getCameraId() {
        return cameraId;
    }
}
