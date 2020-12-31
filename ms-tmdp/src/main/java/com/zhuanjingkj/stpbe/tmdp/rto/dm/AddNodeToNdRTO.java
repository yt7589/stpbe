package com.zhuanjingkj.stpbe.tmdp.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddNodeToNdRTO extends BaseRTO {
    @JSONField(name = "nodeName")
    private String nodeName;
    @JSONField(name = "nodeAddr")
    private String nodeAddr;
    @JSONField(name = "lng")
    private String lng;
    @JSONField(name = "lat")
    private String lat;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeAddr() {
        return nodeAddr;
    }

    public void setNodeAddr(String nodeAddr) {
        this.nodeAddr = nodeAddr;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
