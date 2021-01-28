package com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot;

public class SnapshotTask implements Runnable {
    private long snapshotInterval = 3000;
    private ISnapshotCamera snapshotMachine;

    public SnapshotTask(ISnapshotCamera snapshotMachine) {
        this.snapshotMachine = snapshotMachine;
    }

    @Override
    public void run() {
        System.out.println("抓拍间隔：" + snapshotInterval + "!");
        String imageFile;
        while (true) {
            System.out.println("获取抓拍机图片文件");
            imageFile = snapshotMachine.getSnapshotFile();
            if (imageFile != null) {
                System.out.println("    上报给sr-tvis-server: " + imageFile + "!");
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
}
