package com.zhuanjingkj.zjcbe.business.service.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhuanjingkj.zjcbe.business.dto.analysis.*;
import com.zhuanjingkj.zjcbe.business.dto.analysis.*;
import com.zhuanjingkj.zjcbe.business.dto.analysis.*;
import com.zhuanjingkj.zjcbe.business.service.analysis.analysishandle.AnalysisFileUploadService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.commondata.config.CommonConfig;
import com.zhuanjingkj.zjcbe.commondata.enums.VehicleResourceEnum;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleIdentifyMessageDTO;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleImageMessageDTO;
import com.zhuanjingkj.zjcbe.domain.entity.ImageRectifyEntity;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleRtmpShotEntity;
import com.zhuanjingkj.zjcbe.domain.entity.VehicleRtspconfigEntity;
import com.zhuanjingkj.zjcbe.domain.po.*;
import com.zhuanjingkj.zjcbe.utility.image.ImageUtils;
import com.zhuanjingkj.zjcbe.utility.map.ObjectMapperUtils;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.repository.analysis.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleAnalysisService implements IVehicleAnalysisService {

	private static Logger logger = LoggerFactory.getLogger(VehicleAnalysisService.class);

	@Autowired
	VehicleImagesRepository vehicleImagesRepository;

	@Autowired
	ImageRectifyRepository imageRectifyRepository;

	@Autowired
	VehicleAnalysisRepository analysisRepository;

	@Autowired
	VehicleAnalysisWztzRepository vehicleAnalysisWztzRepository;
	@Autowired
	VehicleAnalysisHptzRepository vehicleAnalysisHptzRepository;
	@Autowired
	VehicleAnalysisCxtzRepository vehicleAnalysisCxtzRepository;
	@Autowired
	VehicleAnalysisJsxwtzRepository vehicleAnalysisJsxwtzRepository;
	@Autowired
	VehicleAnalysisGxhtzRepository vehicleAnalysisGxhtzRepository;

	@Autowired
	AnalysisFileUploadService fileUploadService;

	@Autowired
	VehicleRtspConfigRepository rtspConfigRepository;

	@Autowired
	VehicleRtmpShotRepository shotRepository;

	@Autowired
	CommonConfig autoConfig;

	@Override
	public ResultDTO<VehicleAnalysisResponseDTO> imageAnalysis(MultipartFile file, boolean isDemo) {
		return fileUploadService.imageAnalysis(file, isDemo);
	}

	@Override
	public SimpleResultDTO saveRtmpAnalysisMessage(VehicleIdentifyMessageDTO responseDTO) {
		return fileUploadService.rtmpAnalysis(responseDTO);
	}

	@Override
	public SimpleResultDTO saveRtmpImageMessage(VehicleImageMessageDTO imageMessageDTO) {
		try {
			String suffixName = ".jpg";
			VehicleRtmpShotEntity shotEntity = new VehicleRtmpShotEntity();
			shotEntity.setStreamId(Integer.parseInt(imageMessageDTO.getStreamId()));
			shotEntity.setTimeStamp(Integer.parseInt(imageMessageDTO.getTimeStamp()));
			shotEntity.setSxh(imageMessageDTO.getSxh());

			VehicleResourceDTO resourceDTO = fileUploadService.getVehicleResourceByType(VehicleResourceEnum.SHOT, ".jpg");
			logger.info("### getResoucePath=" + resourceDTO.getResoucePath() + "!");
			ImageUtils.getBase64Image(imageMessageDTO.getImageByteStr(), resourceDTO.getResoucePath());
			shotEntity.setImagePath(resourceDTO.getResoucePath());
			shotEntity.setImageUrl(resourceDTO.getResouceUrl());
			shotEntity.setImageType(suffixName);
			shotEntity.setCreatetime(new Date());
			shotRepository.add(shotEntity);
			return CustomOutputUtility.excuteSuccess();
		} catch (Exception ex) {
			logger.error(String.format("处理图片消息出错，原因：%s", ex.getMessage(), ex));
			return CustomOutputUtility.excuteFail(ex.getMessage());
		}
	}

	@Override
	public ResultDTO<VideoUploadResponseDTO> videoUpload(MultipartFile file) {
		return fileUploadService.videoUpload(file);
	}

	@Override
	public ResultDTO<VehicleAnalysisResponseDTO> getAnalysisResult(String rstpId) {
		VehicleImagesPO imagesPO = vehicleImagesRepository.getLastVehicleImage(rstpId);
		if (imagesPO == null) {
			return CustomOutputUtility.excuteFail("未获取到最新解析数据");
		}
		VehicleAnalysisResponseDTO resultDTO = new VehicleAnalysisResponseDTO();
		ObjectMapperUtils.map(imagesPO, resultDTO);
		List<VehicleAnalysisPO> analysisPOS = analysisRepository.getAnalysisListByImageId(imagesPO.getImageId());
		if (analysisPOS != null) {
			resultDTO.setVeh(new ArrayList<>());
			for (VehicleAnalysisPO po : analysisPOS
			) {
				VehicleAnalysisDetailDTO detailDTO = new VehicleAnalysisDetailDTO();
				detailDTO.setSxh(po.getSxh());
				if (po.getSxh() == imagesPO.getSxh()) {
					System.out.println("##### imagesPO.getThumbImage()=" + imagesPO.getThumbImage() + "!");
					detailDTO.setThumbImage(imagesPO.getThumbImage());
				}
				detailDTO.setCxtz(new VehicleAnalysisCxtzDTO());
				ObjectMapperUtils.map(po, detailDTO.getCxtz());

				detailDTO.setGxhtz(new VehicleAnalysisGxhtzDTO());
				ObjectMapperUtils.map(po, detailDTO.getGxhtz());

				detailDTO.setHptz(new VehicleAnalysisHptzDTO());
				ObjectMapperUtils.map(po, detailDTO.getHptz());

				detailDTO.setJsxztz(new VehicleAnalysisJsxwtzDTO());
				ObjectMapperUtils.map(po, detailDTO.getJsxztz());

				detailDTO.setWztz(new VehicleAnalysisWztzDTO());
				ObjectMapperUtils.map(po, detailDTO.getWztz());

				fileUploadService.initAnalysisResponseDTO(detailDTO);
				resultDTO.getVeh().add(detailDTO);
			}
		}
		return CustomOutputUtility.excuteSuccess(resultDTO);
	}

	@Override
	public ResultDTO<RtmpConfigResponseDTO> getRtmpConfigByUrl(String rtmpUrl) {
		RtmpConfigResponseDTO rtmpConfigResponseDTO = new RtmpConfigResponseDTO();
		System.out.println("VehicleAnalysisService.getRtmpConfigByUrl: " + rtmpUrl + "!");
		VehicleRtspconfigEntity rtspconfigEntity = rtspConfigRepository.getByRtmpUrl(rtmpUrl);
		logger.info("rtspconfigEntity=" + rtspconfigEntity + "!");
		ObjectMapperUtils.map(rtspconfigEntity, rtmpConfigResponseDTO);
		return CustomOutputUtility.excuteSuccess(rtmpConfigResponseDTO);
	}

	@Override
	public ResultDTO<RectifyImgRecgRstDTO> rectifyImage(String imageId, String userJson, String userNotes) {
		RectifyImgRecgRstDTO dto = new RectifyImgRecgRstDTO();
		dto.setImageId(imageId);
		ImageRectifyEntity entity = new ImageRectifyEntity();
		entity.setImageId(imageId);
		entity.setImageState(2);
		entity.setUserJson(userJson);
		if (imageRectifyRepository.add(entity)) {
			return CustomOutputUtility.excuteSuccess(dto);
		} else {
			return CustomOutputUtility.excuteFail("添加图片纠错内容失败");
		}
	}

	@Override
	public ResultDTO<VehicleAnalysisResponseDTO> getImageRecgJson(String imageId) {
		VehicleAnalysisResponseDTO dto = new VehicleAnalysisResponseDTO();
		VehicleImagesPO vehicleImagesPO = vehicleImagesRepository.getImageInfo(imageId);
		dto.setImageId(imageId);
		dto.setImageUrl(vehicleImagesPO.getImageUrl());
		dto.setImageName(vehicleImagesPO.getImageName());
		List<VehicleAnalysisDetailDTO> detailDTOS = new ArrayList<>();
		VehicleAnalysisDetailDTO vadDTO = null;
		List<VehicleAnalysisPO> vpos = analysisRepository.getAnalysisListByImageId(imageId);
		String analysisId = null;
		VehicleAnalysisWztzDTO vaWztzDTO = null;
		for (VehicleAnalysisPO vpo : vpos) {
			analysisId = vpo.getAnalysisId();
			vadDTO = new VehicleAnalysisDetailDTO();
			vadDTO.setSxh(vpo.getSxh());
			vadDTO.setWztz(getWztzDTO(analysisId));
			vadDTO.setHptz(getHptzDTO(analysisId));
			vadDTO.setCxtz(getCxtzDTO(analysisId));
			vadDTO.setJsxztz(getJsxwtzDTO(analysisId));
			vadDTO.setGxhtz(getGxhtzDTO(analysisId));
			detailDTOS.add(vadDTO);
		}
		dto.setVeh(detailDTOS);
		return CustomOutputUtility.excuteSuccess(dto);
	}

	/**
	 * 获取位置特征
	 * @param analysisId
	 * @return
	 */
	private VehicleAnalysisWztzDTO getWztzDTO(String analysisId) {
		VehicleAnalysisWztzDTO vaWztzDTO = new VehicleAnalysisWztzDTO();
		VehicleAnalysisWztzPO wztz = vehicleAnalysisWztzRepository.getVehicleAnalysisWztz(analysisId);
		vaWztzDTO.setClwz(wztz.getClwz());
		vaWztzDTO.setPsfx(wztz.getPsfx());
		return vaWztzDTO;
	}

	/**
	 * 获取号牌特征
	 * @param analysisId
	 * @return
	 */
	private VehicleAnalysisHptzDTO getHptzDTO(String analysisId) {
		VehicleAnalysisHptzDTO vaHptzDTO = new VehicleAnalysisHptzDTO();
		VehicleAnalysisHptzPO hptzPO = vehicleAnalysisHptzRepository.getVehicleAnalysisHptz(analysisId);
		vaHptzDTO.setHpzt(hptzPO.getHpzt()); // 1. 号牌状态
		vaHptzDTO.setHpwz(hptzPO.getHpwz()); // 2. 号牌位置
		vaHptzDTO.setHpzl(hptzPO.getHpzl()); // 3. 号牌种类
		vaHptzDTO.setHpys(hptzPO.getHpys()); // 4. 号牌颜色
		vaHptzDTO.setHpgg(hptzPO.getHpgg()); // 5. 号牌字符样式
		vaHptzDTO.setHphm(hptzPO.getHphm()); // 6. 号牌号码
		vaHptzDTO.setHpkxd(hptzPO.getHpkxd()); // 7. 号牌可信度
		vaHptzDTO.setMwhpkxd(hptzPO.getMwhpkxd()); // 8. 每位号牌可信度
		vaHptzDTO.setYwlshp(hptzPO.getYwlshp()); // 9. 有无临时号牌
		return vaHptzDTO;
	}

	/**
	 * 获取品牌-车型-年款等信息
	 * @param analysisId
	 * @return
	 */
	private VehicleAnalysisCxtzDTO getCxtzDTO(String analysisId) {
		VehicleAnalysisCxtzDTO vaCxtzDTO = new VehicleAnalysisCxtzDTO();
		VehicleAnalysisCxtzPO cxtzPO = vehicleAnalysisCxtzRepository.getVehicleAnalysisCxtz(analysisId);
		vaCxtzDTO.setCllxfl(cxtzPO.getCllxfl());
		vaCxtzDTO.setCllxzfl(cxtzPO.getCllxzfl());
		vaCxtzDTO.setCsys(cxtzPO.getCsys());
		vaCxtzDTO.setClpp(cxtzPO.getClpp());
		vaCxtzDTO.setPpcx(cxtzPO.getPpcx());
		vaCxtzDTO.setCxnk(cxtzPO.getCxnk());
		vaCxtzDTO.setPpxhms(cxtzPO.getPpxhms());
		vaCxtzDTO.setPpxhkxd(cxtzPO.getPpxhkxd());
		return vaCxtzDTO;
	}

	/**
	 * 获取驾驶行为特征
	 * @param analysisId
	 * @return
	 */
	private VehicleAnalysisJsxwtzDTO getJsxwtzDTO(String analysisId) {
		VehicleAnalysisJsxwtzDTO vaJsxwtzDTO = new VehicleAnalysisJsxwtzDTO();
		VehicleAnalysisJsxwtzPO jsxwtzPO = vehicleAnalysisJsxwtzRepository.getVehicleAnalysisJsxwtz(analysisId);
		vaJsxwtzDTO.setZjsddh(jsxwtzPO.getZjsddh());
		vaJsxwtzDTO.setZjsksj(jsxwtzPO.getZjsksj());
		vaJsxwtzDTO.setZjsbjaqd(jsxwtzPO.getZjsbjaqd());
		vaJsxwtzDTO.setZjscy(jsxwtzPO.getZjscy());
		vaJsxwtzDTO.setZjszyb(jsxwtzPO.getZjszyb());
		vaJsxwtzDTO.setFjsbjaqd(jsxwtzPO.getFjsbjaqd());
		vaJsxwtzDTO.setFjszyb(jsxwtzPO.getFjszyb());
		vaJsxwtzDTO.setMtcbdtk(jsxwtzPO.getMtcbdtk());
		return vaJsxwtzDTO;
	}

	/**
	 * 获取车辆个性化特征
	 * @param analysisId
	 * @return
	 */
	private VehicleAnalysisGxhtzDTO getGxhtzDTO(String analysisId) {
		VehicleAnalysisGxhtzDTO vaGxhtzDTO = new VehicleAnalysisGxhtzDTO();
		VehicleAnalysisGxhtzPO gxhtzPO = vehicleAnalysisGxhtzRepository.getVehicleAnalysisGxhtz(analysisId);
		vaGxhtzDTO.setCcztw(gxhtzPO.getCcztw());
		vaGxhtzDTO.setBj(gxhtzPO.getBj());
		vaGxhtzDTO.setGj(gxhtzPO.getGj());
		vaGxhtzDTO.setTc(gxhtzPO.getTc());
		vaGxhtzDTO.setXlj(gxhtzPO.getXlj());
		vaGxhtzDTO.setDcjqs(gxhtzPO.getDcjqs());
		vaGxhtzDTO.setCszt(gxhtzPO.getCszt());
		vaGxhtzDTO.setCsps(gxhtzPO.getCsps());
		vaGxhtzDTO.setCsgh(gxhtzPO.getCsgh());
		vaGxhtzDTO.setCsch(gxhtzPO.getCsch());
		return vaGxhtzDTO;
	}
}
