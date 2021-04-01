package com.zhuanjingkj.stpbe.common.tvis;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.util.DebugLogger;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ObserverThread implements Runnable {
    private List<ITvisStpObserver> observers;
    public ObserverThread(List<ITvisStpObserver> observers) {
        this.observers = observers;
    }

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        VehicleVo vo = null;
        int batchSize = Integer.parseInt(PropUtil.getValue("stp.observer.batchSize"));
        while (true) {
            DebugLogger.log("##### yt ObserverThread.run 1");
            try {
                if (lock.tryLock()) {
                    DebugLogger.log("##### yt ObserverThread.run 2");
                    for (int i = 0; i < batchSize; i++) {
                        if (AppRegistry.vehicleVos.size() <= 1) {
                            break;
                        }
                        vo = AppRegistry.vehicleVos.poll();
                    }
                    DebugLogger.log("##### yt ObserverThread.run 3");
                    lock.unlock();
                    DebugLogger.log("##### yt ObserverThread.run 4");
                }
                DebugLogger.log("##### yt ObserverThread.run 5");
                DebugLogger.log("从队列中取出数据：vo=" + vo + "!");
                if (vo != null) {
                    // 调用所有Observer
                    for (ITvisStpObserver ob : observers) {
                        ob.notifyObserver(vo);
                    }
                }
                DebugLogger.log("##### yt ObserverThread.run 6");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
