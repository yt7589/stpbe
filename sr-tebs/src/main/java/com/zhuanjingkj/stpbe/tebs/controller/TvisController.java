package com.zhuanjingkj.stpbe.tebs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.mgq.GrqEngine;
import com.zhuanjingkj.stpbe.common.net.HttpUtil;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.vo.*;
import com.zhuanjingkj.stpbe.tebs.dto.GrqDemoDTO;
import com.zhuanjingkj.stpbe.tebs.dto.PostTvisJsonDTO;
import com.zhuanjingkj.stpbe.tebs.rto.TvisJsonRTO;
import com.zhuanjingkj.stpbe.tebs.scs.TvisJsonRawListener;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 交通视频图片结构化控制器
 */
@RestController
@RequestMapping("/tebs")
public class TvisController {
    @Autowired
    private TvisJsonRawListener tvisListener;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @PostMapping("/postTvisJson")
    public ResultDTO<PostTvisJsonDTO> postTvisJson(@RequestBody TvisJsonRTO tvisJsonRTO) {
        // 发送到消息总线，即Kafaka的Topic上，设置Topic过期时间为500毫秒
        kafkaTemplate.send("tvis", 0, "Hello kafka of tvis topic");
        kafkaTemplate.flush();
        // 返回处理成功结果
        PostTvisJsonDTO data = new PostTvisJsonDTO();
        data.setTvisId(100);
        ResultDTO<PostTvisJsonDTO> dto = new ResultDTO<>();
        dto.setData(data);
        return dto;
    }

    @GetMapping("/grqDemo")
    public ResultDTO<GrqDemoDTO> grqDemo(@RequestParam(name = "imageFile") String imageFile) {
        System.out.println("查询图片：" + imageFile + "!");
        // 调用sr-tvis-server求特征向量
        final File f = new File(imageFile);
        final Map<String, Object> map = new HashMap<>();
        map.put("GCXH", "111111");
        map.put("MRHPT", "test");
        map.put("HPHM", "test");
        map.put("MRHPT", "test");
        map.put("cameraId", "101");
        map.put("TPMC", f.getName());
        // 返回结果
        ResultDTO<GrqDemoDTO> dto = new ResultDTO<>();
        String response = TvisUtil.recognizeImageFile(map, f);
        System.out.println("识别结果：" + response + "!!!!!!!!!!!!!!!!!!!!!!");
        if (response != null && !response.equals("")) {
            // 调用以图搜图得到结果
            List<VehicleVo> vehs = TvisUtil.parseTvisJson(response);
            if (vehs.size() > 0) {
                VehicleVo vo = vehs.get(0);
                VehicleWztzVo wztzVo = vo.getVehicleWztzVo();
                VehicleCltzxlVo cltzxlVo = vo.getVehicleCltzxlVo();
                VehicleCxtzVo cxtzVo = vo.getVehicleCxtzVo();
                String partitionName = GrqEngine.getPartitionTag(wztzVo.getPsfx(),
                        cxtzVo.getCllxflCode(), cxtzVo.getCllxzflCode());
                List<List<Float>> embeddings = new ArrayList<>();
                embeddings.add(cltzxlVo.getCltzxl());
                long topK = 20;
                TvisGrqRstVo grv = GrqEngine.findTopK(partitionName, embeddings, topK);
                GrqDemoDTO data = new GrqDemoDTO(grv.getGrqId(), grv.getTvisJsonId(), grv.getVehsIdx());
                dto.setData(data);
            }
        }
        return dto;
    }
}
