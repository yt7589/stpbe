package com.zhuanjingkj.stpbe.tebs.controller;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tebs.dto.PostTvisJsonDTO;
import com.zhuanjingkj.stpbe.tebs.rto.TvisJsonRTO;
import com.zhuanjingkj.stpbe.tebs.scs.TvisJsonRawListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

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
    public ResultDTO<BaseDTO> grqDemo(@RequestParam(name = "imageFile") String imageFile) {
        // 调用sr-tvis-server求特征向量
        // 调用以图搜图得到结果
        // 返回结果
        return null;
    }
}
