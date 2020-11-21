package com.zhuanjingkj.stpbe.tebs.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tebs.dto.PostTvisJsonDTO;
import com.zhuanjingkj.stpbe.tebs.rto.TvisJsonRTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 交通视频图片结构化控制器
 */
@RestController
@RequestMapping("/tebs")
public class TvisController {
    @PostMapping("/postTvisJson")
    public ResultDTO<PostTvisJsonDTO> posTvisJson(@RequestBody TvisJsonRTO tvisJsonRTO) {
        // 发送到消息总线，即Kafaka的Topic上，设置Topic过期时间为500毫秒
        // 返回处理成功结果
        PostTvisJsonDTO data = new PostTvisJsonDTO();
        data.setTvisId(100);
        ResultDTO<PostTvisJsonDTO> dto = new ResultDTO<>();
        dto.setData(data);
        return dto;
    }
}
