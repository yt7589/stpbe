package com.zhuanjingkj.stpbe.ca_tvis.controller;


import com.zhuanjingkj.stpbe.ca_tvis.dto.SaveQuestionImageDTO;
import com.zhuanjingkj.stpbe.ca_tvis.rto.SaveQuestionImageRTO;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tvis")
@CrossOrigin(origins = "*")
public class TvisController {
    @PostMapping("/saveQuestionImages")
    public ResultDTO<SaveQuestionImageDTO> saveQuestionImages(@RequestBody SaveQuestionImageRTO rto) {
        ResultDTO<SaveQuestionImageDTO> dto = new ResultDTO<>();
        System.out.println("imageId=" + rto.getImageName() + "; clpp="
                + rto.getBrandId() + "; cxnk=" + rto.getBmyId() + "!");
        SaveQuestionImageDTO data = new SaveQuestionImageDTO();
        data.setState(888);
        dto.setData(data);
        return dto;
    }
}
