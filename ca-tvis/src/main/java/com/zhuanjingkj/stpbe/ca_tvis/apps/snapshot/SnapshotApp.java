package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

import com.zhuanjingkj.stpbe.ca_tvis.ITvisClient;

public class SnapshotApp implements ITvisClient {
    @Override
    public void startup(String[] args) {
        Thread thd = new Thread(new SnapshotTask(new SnapshotCameraSimulator("D:/awork/work/es_images/bak")));
        thd.start();
    }
}
