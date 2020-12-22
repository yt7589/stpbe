package com.zhuanjingkj.stpbe.tvis_server.controller;


import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tvis_server.dto.UploadIpfsFileDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/ipfs")
public class IpfsController {
    @PostMapping("/uploadFile")
    public ResultDTO<UploadIpfsFileDTO> uploadIpfsFile(
            @RequestParam(name = "arg", required = false) MultipartFile arg) {
        // 保存监时文件
        Random rand = new Random();
        String fn = "/tmp/f" + System.currentTimeMillis() + rand.nextInt();
        File tmpFile = new File(fn);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tmpFile);
            fos.write(arg.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Optional<String> rst = IpfsClient.uploadFile(fn);
        // 删除临时文件
        tmpFile.delete();
        ResultDTO<UploadIpfsFileDTO> dto = new ResultDTO<>();
        UploadIpfsFileDTO data = new UploadIpfsFileDTO("");
        rst.ifPresent((str) -> {data.setFileHash(str);});
        dto.setData(data);
        return dto;
    }
}
