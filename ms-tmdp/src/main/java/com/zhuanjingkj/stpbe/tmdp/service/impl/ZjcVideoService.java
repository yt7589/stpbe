package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.ZjcVideoMapper;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.dto.CreateRtspBindDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IZjcVideoService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import com.zhuanjingkj.stpbe.tmdp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.*;

@Service
public class ZjcVideoService implements IZjcVideoService {

    @Autowired
    private ZjcVideoMapper zjcVideoMapper;

    @Autowired
    private TvisSdkService tvisSdkService;

    @Override
    public ResultDTO<String> uploadVideo(MultipartFile file, Integer consumerId) {
        String path = PropUtil.getValue("zjc.video.path"); //视频存储路径
        if (file == null) {
            return null;
        }
        String fileName = "v" + System.currentTimeMillis();
        ExecutorService exec = Executors.newFixedThreadPool(4);
        FutureTask uploadVideoTask = new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                System.out.println("视频开始上传 1");
                byte[] data = file.getBytes();
                FileOutputStream fos = new FileOutputStream(new File(path + fileName + ".mp4"));
                fos.write(data);
                fos.flush();
                fos.close();
                String cmd = "/home/ps/yantao/dev/ffmpeg/ffmpeg -i " + path + fileName + ".mp4 -vcodec copy -acodec copy " + path + fileName +".mkv";
                System.out.println("视频上传成功，开始转码 1");
                if (FileUtil.callCMD(cmd) == 0) {
                    System.out.println("视频转码 2");
                    /**
                     * 文件转码成功后绑定视频流
                     */
                    ResultDTO<CreateRtspBindDTO> dto = tvisSdkService.createRtspBind(PropUtil.getValue("video.url.rtsp") + fileName + ".mkv", "/start");
                    zjcVideoMapper.addRtmp(consumerId, fileName, dto.getData().getStreamId(), DateUtil.getLocalDateTime());
                    System.out.println("视频转码 3");
                }
                if (FileUtil.uploadImg(file,  fileName + ".mp4", path)) {
                    /**
                     * 文件上传成功后转 .mkv文件
                     */
                }
                return null;
            }
        });
        exec.submit(uploadVideoTask);
        ResultDTO<String> dto = new ResultDTO<>();
        dto.setData(fileName);
        return dto;
    }

    @Override
    public ResultDTO<String> bindVideo(String rtspUrl, String rtspName, String position) {
        ResultDTO<String> dto = new ResultDTO<>();
        ResultDTO<CreateRtspBindDTO> rtspDto = tvisSdkService.createRtspBind(rtspUrl, "/start");
        String streamId = rtspDto.getData().getStreamId();
        dto.setData(PropUtil.getValue("video.url.rtmp") + streamId);
        return dto;
    }

    @Override
    public ResultDTO<String> getVideoUrl(String fileName) {
        ResultDTO<String> dto = new ResultDTO<>();
        String streamId = zjcVideoMapper.getVideoStreamId(fileName);
        String rtmpUrl = PropUtil.getValue("video.url.rtmp") + streamId;
        dto.setData(rtmpUrl);
        return dto;
    }
}
