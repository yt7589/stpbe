package com.zhuanjingkj.stpbe.tmdp.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class DeleteNodeFromNdRTO extends BaseRTO {
    @JSONField(name = "nodeId")
    private long nodeId;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }
}
