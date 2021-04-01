package com.zhuanjingkj.stpbe.common.tvis;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.util.DebugLogger;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;

import java.util.List;

public class ObserverThread implements Runnable {
    private List<ITvisStpObserver> observers;
    public ObserverThread(List<ITvisStpObserver> observers) {
        this.observers = observers;
    }

    @Override
    public void run() {
        VehicleVo vo = null;
        int batchSize = Integer.parseInt(PropUtil.getValue("stp.observer.batchSize"));
        while (true) {
            synchronized (AppRegistry.vehicleVos) {
                for (int i = 0; i < batchSize; i++) {
                    vo = AppRegistry.vehicleVos.poll();
                }
            }
            DebugLogger.log("从队列中取出数据：vo=" + vo + "!");
            if (vo != null) {
                // 调用所有Observer
                for (ITvisStpObserver ob : observers) {
                    ob.notifyObserver(vo);
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
