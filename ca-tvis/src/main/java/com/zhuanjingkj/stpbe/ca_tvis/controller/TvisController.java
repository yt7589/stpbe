package com.zhuanjingkj.stpbe.ca_tvis.controller;


import com.zhuanjingkj.stpbe.ca_tvis.dto.SaveQuestionImageDTO;
import com.zhuanjingkj.stpbe.ca_tvis.rto.SaveQuestionImageRTO;
import com.zhuanjingkj.stpbe.ca_tvis.service.impl.QuestionImageService;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tvis")
@CrossOrigin(origins = "*")
public class TvisController {
    @Autowired
    private QuestionImageService questionImageService;

    @PostMapping("/saveQuestionImages")
    public ResultDTO<SaveQuestionImageDTO> saveQuestionImages(@RequestBody SaveQuestionImageRTO rto) {
        return questionImageService.saveQuestionImage(rto);
    }
}
