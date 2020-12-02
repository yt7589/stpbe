package com.zhuanjingkj.stpbe.mgqs.service.impl;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.mgqs.mgq.MgqEngine;
import com.zhuanjingkj.stpbe.mgqs.service.IMgqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MgqService implements IMgqService {
    @Autowired
    private MgqEngine mgqEngine;

    @Override
    public ResultDTO<BaseDTO> importDclFds() {
        runExcemple();
        ResultDTO<BaseDTO> dto = new ResultDTO<>();
        dto.setCode(0);
        dto.setMsg("开始建库");
        return dto;
    }




    public void runExcemple() {
        System.out.println("Begin running in new thread ......");
        String partitionTag = MgqEngine.PN_HEAD_TRUCK;
        int seq = 1;
        List<Float> tzxl_1 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第1条记录......");
        List<Float> tzxl_2 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第2条记录......");
        List<Float> tzxl_3 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第3条记录......");
        List<Float> tzxl_4 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第4条记录......");
        List<Float> tzxl_5 = insertOneReIdVec(partitionTag, seq++);
        System.out.println("插入第5条记录......");

        // 查询结果
        //MgqEngine mgqEngine = new MgqEngine();
        List<List<Float>> queryEmbedding = new ArrayList<>();
        queryEmbedding.add(tzxl_2);
        VehicleCxtzVo rstVo = mgqEngine.findTopK(partitionTag, queryEmbedding, 1);
        System.out.println("查询结果：" + rstVo.getPpcx() + "; " +
                rstVo.getPpxhms() + "; id=" + rstVo.getTzxlId() + "!");
    }

    private Map<String, Object> getReIDInfo(int seq) {
        Map<String, Object> infos = new HashMap<>();
        List<List<Float>> embeddings = new ArrayList<>();
        List<VehicleCxtzVo> vos = new ArrayList<>();
        // 定义车型特征值对象
        VehicleCxtzVo vo = new VehicleCxtzVo();
        vo.setCllxfl(seq);
        vo.setCllxflName("类型" + seq);
        vo.setCllxzfl(1000 + seq);
        vo.setCllxzflName("子类型" + seq);
        vo.setCsys(2000 + seq);
        vo.setCsysName("颜色" + seq);
        vo.setClpp(100000 + seq);
        vo.setClppName("品牌" + seq);
        vo.setPpcx(210000 + seq);
        vo.setPpcxName("车型" + seq);
        vo.setCxnk(220000 + seq);
        vo.setCxnkName("年款" + seq);
        vo.setPpxhms(300000 + seq);
        vo.setPpxhmsName("名称" + seq);
        infos.put("vo", vo);
        //
        List<Float> tzxl = createRandomTzxl();
        embeddings.add(tzxl);
        infos.put("tzxl", tzxl);
        return infos;
    }

    private List<Float> insertOneReIdVec(String partitionTag, int seq) {
        Map<String, Object> infos = null;
        //
        List<List<Float>> embeddings = new ArrayList<>();
        List<VehicleCxtzVo> vos = new ArrayList<>();
        List<Float> tzxl;
        VehicleCxtzVo vo = null;
        //
        infos = getReIDInfo(seq);
        vo = (VehicleCxtzVo)infos.get("vo");
        vos.add(vo);
        tzxl = (List<Float>)infos.get("tzxl");
        embeddings.add(tzxl);
        //MgqEngine mgqEngine = new MgqEngine();
        mgqEngine.insertRecord(partitionTag, vos, embeddings);
        return tzxl;
    }

    private List<Float> createRandomTzxl() {
        List<Float> tzxl = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<MgqEngine.REID_DIM; i++) {
            tzxl.add(random.nextFloat());
        }
        return tzxl;
    }
}
