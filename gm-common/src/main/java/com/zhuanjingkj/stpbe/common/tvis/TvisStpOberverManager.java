package com.zhuanjingkj.stpbe.common.tvis;

import com.zhuanjingkj.stpbe.common.tvis.obs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TvisStpOberverManager {
    @Autowired
    private CltzxlObserver cltzxlObserver; // 车辆特征向量保存到Milvus中
    @Autowired
    private DkVtieObserver dkVtieObserver; // 首页数据看板左侧第一行第一个：本地外埠车辆占比
    @Autowired
    private DkVtpObserver dkVtpObserver; // 首页数据看板左侧第一行第二个：车辆类型饼图
    @Autowired
    private DkTitfObserver dkTitfObserver; //首页数据分时段过车统计柱状图
    @Autowired
    private DkHtfsObserver dkHtfsObserver; //首页本月过车量统计
    @Autowired
    private DkVttfObserver dkVttfObserver; //车辆类型流量
    @Autowired
    private DkTjrsObserver dkTjrsObserver; //拥堵路段过车量
    @Autowired
    private DkDctfObserver dkDctfObserver; //区县过车量
    @Autowired
    private DkRtvrObserver dkRtvrObserver; //实时违章记录
    @Autowired
    private KsSvsObserver ksSvsObserver;  //重点监管=》特殊车辆监管
    @Autowired
    private KsSvsKsvrpObserver ksSvsKsvrpObserver; //重点监管=》特殊车辆监管
    @Autowired
    private KsLpsObserver ksLpsObserver; //重点监管牌照异常
    @Autowired
    private KsAsObserver ksAsObserver; //重点监管区域监管
    @Autowired
    private KsRssObserver ksRssObserver; //重点监管路段监管
    @Autowired
    private DcHpObserver dcHpObserver; //数据中心全部数据

    public void initialize(List<ITvisStpObserver> observers, Environment environment) {
        dcHpObserver.initialize(environment);
        observers.add(dcHpObserver);
        cltzxlObserver.initialize(environment);
        observers.add(cltzxlObserver);
        dkVtieObserver.initialize(environment);
        observers.add(dkVtieObserver);
        dkVtpObserver.initialize(environment);
        observers.add(dkVtpObserver);
        dkTitfObserver.initialize(environment);
        observers.add(dkTitfObserver);
        dkHtfsObserver.initialize(environment);
        observers.add(dkHtfsObserver);
        dkVttfObserver.initialize(environment);
        observers.add(dkVttfObserver);
        dkTjrsObserver.initialize(environment);
        observers.add(dkTjrsObserver);
        dkDctfObserver.initialize(environment);
        observers.add(dkDctfObserver);
        dkRtvrObserver.initialize(environment);
        observers.add(dkRtvrObserver);
        ksSvsObserver.initialize(environment);
        observers.add(ksSvsObserver);
        ksSvsKsvrpObserver.initialize(environment);
        observers.add(ksSvsKsvrpObserver);
        ksLpsObserver.initialize(environment);
        observers.add(ksLpsObserver);
        ksAsObserver.initialize(environment);
        observers.add(ksAsObserver);
        ksRssObserver.initialize(environment);
        observers.add(ksRssObserver);
    }
}
