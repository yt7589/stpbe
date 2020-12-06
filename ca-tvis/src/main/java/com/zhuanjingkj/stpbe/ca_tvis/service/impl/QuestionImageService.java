package com.zhuanjingkj.stpbe.ca_tvis.service.impl;

import com.zhuanjingkj.stpbe.ca_tvis.dto.SaveQuestionImageDTO;
import com.zhuanjingkj.stpbe.ca_tvis.rto.SaveQuestionImageRTO;
import com.zhuanjingkj.stpbe.ca_tvis.service.IQuestionImageService;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class QuestionImageService implements IQuestionImageService {
    @Override
    public ResultDTO<SaveQuestionImageDTO> saveQuestionImage(SaveQuestionImageRTO rto) {
        boolean rst = saveQuestionImage(rto.getImageName(), rto.getBrandId(), rto.getBmyId());
        ResultDTO<SaveQuestionImageDTO> dto = new ResultDTO<>();
        System.out.println("imageId=" + rto.getImageName() + "; clpp="
                + rto.getBrandId() + "; cxnk=" + rto.getBmyId() + "!");
        SaveQuestionImageDTO data = new SaveQuestionImageDTO();
        if (rst) {
            data.setState(0);
        } else {
            data.setState(1);
        }
        dto.setData(data);
        return dto;
    }

    @Override
    public boolean saveQuestionImage(String imageName, int brandId, int bmyId) {
        FileOutputStream fosDs = null;
        OutputStreamWriter oswDs = null;
        try {
            fosDs = new FileOutputStream("e:/temp/es_images_bk/qi.txt", true);
            oswDs = new OutputStreamWriter(fosDs, "UTF-8");
            oswDs.write(imageName + "*" + bmyId + "*" + brandId + "\r\n");
            oswDs.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fosDs.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
