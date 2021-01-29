package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

import com.zhuanjingkj.stpbe.ca_tvis.ITvisClient;
import org.springframework.beans.factory.annotation.Value;

public class SnapshotApp implements ITvisClient {
    @Value("#{'${app.image.folder}'.split(',')}")
    private String[] imageFolders;

    @Override
    public void startup(String[] args) {
        for (String imageFolder : imageFolders) {
            System.out.println("### imageFolder=" + imageFolder + "!");
            Thread thd = new Thread(new SnapshotTask(new SnapshotCameraSimulator(imageFolder)));
            thd.start();
        }
    }
}
