package com.zhuanjingkj.zjcbe.business.service.analysis.analysishandle;

import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.contentext.AuthContextHolder;
import com.zhuanjingkj.zjcbe.business.dto.analysis.*;
import com.zhuanjingkj.zjcbe.business.redismessage.VideoMessagePublisher;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.commondata.config.CommonConfig;
import com.zhuanjingkj.zjcbe.commondata.enums.VehicleResourceEnum;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleIdentifyDetailDTO;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleIdentifyMessageDTO;
import com.zhuanjingkj.zjcbe.commondata.vehicleDTO.VehicleVideoMessageDTO;
import com.zhuanjingkj.zjcbe.commonservice.VehicleIdentyHelper;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.utility.guid.GuidUtil;
import com.zhuanjingkj.zjcbe.utility.image.ImageUtils;
import com.zhuanjingkj.zjcbe.utility.json.JsonUtils;
import com.zhuanjingkj.zjcbe.utility.map.ObjectMapperUtils;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import com.zhuanjingkj.zjcbe.utility.time.DateTimeUtil;
import com.zhuanjingkj.zjcbe.business.dto.analysis.*;
import com.zhuanjingkj.zjcbe.business.dto.analysis.*;
import com.zhuanjingkj.zjcbe.domain.entity.*;
import com.zhuanjingkj.zjcbe.repository.analysis.*;
import com.zhuanjingkj.zjcbe.repository.vehicledic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnalysisFileUploadService {

	private static Logger logger = LoggerFactory.getLogger(AnalysisFileUploadService.class);

	@Autowired
	VehicleIdentyHelper identyHelper;

	@Autowired
	CommonConfig autoConfig;

	@Autowired
	VehicleImagesRepository vehicleImagesRepository;

	@Autowired
	VehicleAnalysisRepository analysisRepository;

	@Autowired
	VehicleAnalysisCxtzRepository cxtzRepository;

	@Autowired
	VehicleAnalysisGxhtzRepository gxhtzRepository;

	@Autowired
	VehicleAnalysisJsxwtzRepository jsxwtzRepository;

	@Autowired
	VehicleAnalysisHptzRepository hptzRepository;

	@Autowired
	VehicleAnalysisWztzRepository wztzRepository;

	@Autowired
	DicNumberTypeRepository dicNumberTypeRepository;

	@Autowired
	DicNumberColorRepository dicNumberColorRepository;

	@Autowired
	DicBodyColorRepository dicBodyColorRepository;

	@Autowired
	DicVehicleTypeRepository vehicleTypeRepository;

	@Autowired
	DicVehicleBranchTypeRepository branchTypeRepository;

	@Autowired
	VehicleRtmpShotRepository shotRepository;

	@Autowired
	VehicleVideosRepository videosRepository;

	@Autowired
	VideoMessagePublisher videoMessagePublisher;

	/**
	 * 图片解析
	 *
	 * @param file
	 * @param isDemo
	 * @return
	 */
	public ResultDTO<VehicleAnalysisResponseDTO> imageAnalysis(MultipartFile file, boolean isDemo) {
		try {
			VehicleImagesEntity imagesEntity = saveImageFile(file, isDemo);
			if (imagesEntity == null) {
				return CustomOutputUtility.excuteFail("图片上传失败");
			}
			VehicleIdentifyMessageDTO identifyResult = identyHelper.uploadImage(UUID.randomUUID().toString(), imagesEntity.getImagePath());
			VehicleAnalysisResponseDTO resultDTO = getAnalysisResponseDTO(imagesEntity, identifyResult);
			if (!isDemo) {
				saveAnalysisResult(imagesEntity, identifyResult);
			}
			if (resultDTO != null) {
				return CustomOutputUtility.excuteSuccess(resultDTO);
			} else {
				return CustomOutputUtility.excuteFail(JsonUtils.toJSONString(identifyResult));
			}
		} catch (Exception ex) {
			return CustomOutputUtility.excuteFail(ex.getMessage());
		}
	}

	/**
	 * 视频流解析
	 *
	 * @param identifyResult
	 * @return
	 */
	public SimpleResultDTO rtmpAnalysis(VehicleIdentifyMessageDTO identifyResult) {
		try {
			if (StringUtils.isBlank(identifyResult.getTimeStamp()) || StringUtils.isBlank(identifyResult.getStreamId())) {
				return CustomOutputUtility.excuteFail("数据无效，缺少streamId和时间戳");
			}
			VehicleImagesEntity imagesEntity = new VehicleImagesEntity();
			imagesEntity.setImageId(GuidUtil.newId());
			imagesEntity.setUploadTime(new Date());
			imagesEntity.setTimeStamp(Long.parseLong(identifyResult.getTimeStamp()));
			imagesEntity.setStreamId(Integer.parseInt(identifyResult.getStreamId()));
			saveAnalysisResult(imagesEntity, identifyResult);
			return CustomOutputUtility.excuteSuccess();
		} catch (Exception ex) {
			return CustomOutputUtility.excuteFail(ex.getMessage());
		}
	}

	/**
	 * 保存车辆图片识别结果
	 *
	 * @param imagesEntity
	 * @param identifyResult
	 */
	private void saveAnalysisResult(VehicleImagesEntity imagesEntity, VehicleIdentifyMessageDTO identifyResult) {

		List<VehicleAnalysisEntity> analysisEntityList = new ArrayList<>();
		List<VehicleAnalysisCxtzEntity> cxtzEntities = new ArrayList<>();
		List<VehicleAnalysisGxhtzEntity> gxhtzEntities = new ArrayList<>();
		List<VehicleAnalysisHptzEntity> hptzEntities = new ArrayList<>();
		List<VehicleAnalysisJsxwtzEntity> jsxwtzEntities = new ArrayList<>();
		List<VehicleAnalysisWztzEntity> wztzEntities = new ArrayList<>();

		if (identifyResult != null && identifyResult.getCode() == 1 && identifyResult.getVeh() != null && identifyResult.getVeh().size() > 0) {
			for (VehicleIdentifyDetailDTO identifyDetailDTO : identifyResult.getVeh()) {

				String analysisId = GuidUtil.newId();
				String imageId = imagesEntity.getImageId();
				String gcxh = identifyResult.getGcxh();

				VehicleAnalysisEntity analysisEntity = new VehicleAnalysisEntity();
				ObjectMapperUtils.map(identifyDetailDTO, analysisEntity);
				analysisEntity.setAnalysisId(analysisId);
				analysisEntity.setImageId(imageId);
				analysisEntity.setGcxh(gcxh);
				analysisEntity.setCreateTime(new Date());
				analysisEntityList.add(analysisEntity);

				VehicleAnalysisCxtzEntity cxtzEntity = new VehicleAnalysisCxtzEntity();
				ObjectMapperUtils.map(identifyDetailDTO.getCxtz(), cxtzEntity);
				cxtzEntity.setAnalysisId(analysisEntity.getAnalysisId());
				cxtzEntity.setCreateTime(new Date());
				cxtzEntities.add(cxtzEntity);

				VehicleAnalysisGxhtzEntity gxhtzEntity = new VehicleAnalysisGxhtzEntity();
				ObjectMapperUtils.map(identifyDetailDTO.getGxhtz(), gxhtzEntity);
				gxhtzEntity.setAnalysisId(analysisEntity.getAnalysisId());
				gxhtzEntity.setCreateTime(new Date());
				gxhtzEntities.add(gxhtzEntity);

				VehicleAnalysisHptzEntity hptzEntity = new VehicleAnalysisHptzEntity();
				ObjectMapperUtils.map(identifyDetailDTO.getHptz(), hptzEntity);
				hptzEntity.setAnalysisId(analysisEntity.getAnalysisId());
				hptzEntity.setCreateTime(new Date());
				hptzEntities.add(hptzEntity);

				VehicleAnalysisJsxwtzEntity jsxwtzEntity = new VehicleAnalysisJsxwtzEntity();
				ObjectMapperUtils.map(identifyDetailDTO.getJsxwtz(), jsxwtzEntity);
				jsxwtzEntity.setAnalysisId(analysisEntity.getAnalysisId());
				jsxwtzEntity.setCreateTime(new Date());
				jsxwtzEntities.add(jsxwtzEntity);

				VehicleAnalysisWztzEntity wztzEntity = new VehicleAnalysisWztzEntity();
				ObjectMapperUtils.map(identifyDetailDTO.getWztz(), wztzEntity);
				wztzEntity.setAnalysisId(analysisEntity.getAnalysisId());
				wztzEntity.setCreateTime(new Date());
				wztzEntities.add(wztzEntity);
			}
			saveAnalysisEntity(imagesEntity, analysisEntityList, cxtzEntities, gxhtzEntities, hptzEntities, jsxwtzEntities, wztzEntities);
		}
	}

	/**
	 * 编码转名称
	 *
	 * @param analysisDetailDTO
	 */
	public void initAnalysisResponseDTO(VehicleAnalysisDetailDTO analysisDetailDTO) {

		//图片输出转base64编码
		analysisDetailDTO.setThumbImage(ImageUtils.getBase64Url(analysisDetailDTO.getThumbImage()));

		//号牌种类
		DicNumberTypeEntity numberTypeEntity = dicNumberTypeRepository.getByCode(analysisDetailDTO.getHptz().getHpzl());
		analysisDetailDTO.getHptz().setHpzl(numberTypeEntity != null ? numberTypeEntity.getName() : "");

		//号牌颜色
		DicNumberColorEntity numberColorEntity = dicNumberColorRepository.getByCode(analysisDetailDTO.getHptz().getHpys());
		analysisDetailDTO.getHptz().setHpys(numberTypeEntity != null ? numberColorEntity.getName() : "");

		//车辆分类
		DicVehicleTypeEntity vehicleTypeEntity = vehicleTypeRepository.getByCode(analysisDetailDTO.getCxtz().getCllxfl());
		analysisDetailDTO.getCxtz().setCllxfl(vehicleTypeEntity != null ? vehicleTypeEntity.getName() : "");

		//车辆子分类
		DicVehicleBranchTypeEntity branchTypeEntity = branchTypeRepository.getByCode(analysisDetailDTO.getCxtz().getCllxzfl());
		analysisDetailDTO.getCxtz().setCllxzfl(branchTypeEntity != null ? branchTypeEntity.getName() : "");

		//车身颜色
		DicBodyColorEntity bodyColorEntity = dicBodyColorRepository.getByCode(analysisDetailDTO.getCxtz().getCsys());
		analysisDetailDTO.getCxtz().setCsys(bodyColorEntity != null ? bodyColorEntity.getName() : "");

	}

	/**
	 * 车辆识别结果入库
	 *
	 * @param imagesEntity
	 * @param analysisEntity
	 * @param cxtzEntity
	 * @param gxhtzEntity
	 * @param hptzEntity
	 * @param jsxwtzEntity
	 * @param wztzEntity
	 */
	@Transactional
	@SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
	public void saveAnalysisEntity(VehicleImagesEntity imagesEntity, List<VehicleAnalysisEntity> analysisEntity, List<VehicleAnalysisCxtzEntity> cxtzEntity, List<VehicleAnalysisGxhtzEntity> gxhtzEntity, List<VehicleAnalysisHptzEntity> hptzEntity, List<VehicleAnalysisJsxwtzEntity> jsxwtzEntity, List<VehicleAnalysisWztzEntity> wztzEntity) {
		analysisRepository.batchAdd(analysisEntity);
		cxtzRepository.batchAdd(cxtzEntity);
		gxhtzRepository.batchAdd(gxhtzEntity);
		hptzRepository.batchAdd(hptzEntity);
		jsxwtzRepository.batchAdd(jsxwtzEntity);
		wztzRepository.batchAdd(wztzEntity);
		vehicleImagesRepository.add(imagesEntity);
	}

	/**
	 * 获取车辆解析输出DTO
	 *
	 * @param imagesEntity
	 * @param identifyResult
	 * @return
	 */
	private VehicleAnalysisResponseDTO getAnalysisResponseDTO(VehicleImagesEntity imagesEntity, VehicleIdentifyMessageDTO identifyResult) {
		if (identifyResult != null && identifyResult.getCode() == 1 && identifyResult.getVeh() != null && identifyResult.getVeh().size() > 0) {
			VehicleAnalysisResponseDTO resultDTO = new VehicleAnalysisResponseDTO();
			resultDTO.setImageId(imagesEntity.getImageId());
			resultDTO.setImageName(imagesEntity.getImageName());
			resultDTO.setImageUrl(imagesEntity.getImageUrl());
			resultDTO.setVeh(new ArrayList<>());
			for (VehicleIdentifyDetailDTO identifyDetailDTO : identifyResult.getVeh()) {
				VehicleAnalysisDetailDTO analysisDetailDTO = new VehicleAnalysisDetailDTO();
				ObjectMapperUtils.map(identifyDetailDTO, analysisDetailDTO);

				analysisDetailDTO.setCxtz(new VehicleAnalysisCxtzDTO());
				ObjectMapperUtils.map(identifyDetailDTO.getCxtz(), analysisDetailDTO.getCxtz());

				analysisDetailDTO.setGxhtz(new VehicleAnalysisGxhtzDTO());
				ObjectMapperUtils.map(identifyDetailDTO.getGxhtz(), analysisDetailDTO.getGxhtz());

				analysisDetailDTO.setHptz(new VehicleAnalysisHptzDTO());
				ObjectMapperUtils.map(identifyDetailDTO.getHptz(), analysisDetailDTO.getHptz());

				analysisDetailDTO.setJsxztz(new VehicleAnalysisJsxwtzDTO());
				ObjectMapperUtils.map(identifyDetailDTO.getJsxwtz(), analysisDetailDTO.getJsxztz());

				analysisDetailDTO.setWztz(new VehicleAnalysisWztzDTO());
				ObjectMapperUtils.map(identifyDetailDTO.getWztz(), analysisDetailDTO.getWztz());

				initAnalysisResponseDTO(analysisDetailDTO);
				resultDTO.getVeh().add(analysisDetailDTO);
			}
			return resultDTO;
		}
		return null;
	}

	/**
	 * 文件上传
	 *
	 * @param file
	 * @param isDemo
	 * @return
	 */
	private VehicleImagesEntity saveImageFile(MultipartFile file, boolean isDemo) {
		String originalfileName = file.getOriginalFilename();
		String suffixName = originalfileName.substring(originalfileName.lastIndexOf("."));
		VehicleResourceDTO resourceDTO = isDemo ? getVehicleResourceByType(VehicleResourceEnum.TEMP, suffixName) : getVehicleResourceByType(VehicleResourceEnum.IMAGE, suffixName);
		try {
			saveFileByPath(file, resourceDTO.getResoucePath());
			VehicleImagesEntity entity = new VehicleImagesEntity();
			entity.setImageId(GuidUtil.newId());
			entity.setImageName(originalfileName);
			entity.setImagePath(resourceDTO.getResoucePath());
			entity.setImageUrl(resourceDTO.getResouceUrl());
			entity.setImageSize(file.getSize());
			entity.setImageType(suffixName);
			entity.setUploadTime(new Date());
			entity.setUploadBy(getCurrAccountId());
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存上传文件
	 *
	 * @param file
	 * @param filePath
	 */
	private void saveFileByPath(MultipartFile file, String filePath) {
		try {
			File dest = new File(filePath);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(filePath);
			out.write(file.getBytes());
			out.flush();
			out.close();
		} catch (Exception ex) {
			logger.error(String.format("保存文件失败！原因：%s", ex.getMessage()), ex);
		}
	}

	/**
	 * 获取车辆资源文件DTO
	 *
	 * @param resourceType
	 * @return
	 */
	public VehicleResourceDTO getVehicleResourceByType(VehicleResourceEnum resourceType, String suffixName) {

		String rootPath = autoConfig.getResourceRootPath();
		String rootUrl = autoConfig.getResouceDomain();
		String branchPath = resourceType.getName();
		String fileName = DateTimeUtil.getDateInt(new Date()) + "/" + GuidUtil.newId() + suffixName;

		VehicleResourceDTO resourceDTO = new VehicleResourceDTO();
		resourceDTO.setResoucePath(String.format("%s%s/%s", rootPath, branchPath, fileName));
		resourceDTO.setResouceUrl(String.format("%s%s/%s", rootUrl, branchPath, fileName));
		resourceDTO.setResouceType(resourceType);
		return resourceDTO;
	}

	/**
	 * 获取当前用户Id
	 *
	 * @return
	 */
	private int getCurrAccountId() {
		AccountContextBO contextBO = AuthContextHolder.getAuthContext();
		if (contextBO != null) {
			return contextBO.getAccountId();
		}
		return 0;
	}

	/**
	 * 视频上传
	 *
	 * @param file
	 * @return
	 */
	public ResultDTO<VideoUploadResponseDTO> videoUpload(MultipartFile file) {
		String originalfileName = file.getOriginalFilename();
		String suffixName = originalfileName.substring(originalfileName.lastIndexOf("."));

		Pattern pattern = Pattern.compile("\\.(mp4|avi|flv)$");
		Matcher matcher = pattern.matcher(originalfileName.toLowerCase());
		if (!matcher.find()) {
			return CustomOutputUtility.excuteFail("目前仅支持mp4、avi上传，其他视频格式暂不支持！");
		}

		VehicleResourceDTO resourceDTO = getVehicleResourceByType(VehicleResourceEnum.VIDEO, suffixName);
		try {
			saveFileByPath(file, resourceDTO.getResoucePath());
			VehicleVideosEntity videosEntity = new VehicleVideosEntity();
			videosEntity.setVideoId(GuidUtil.newId());
			videosEntity.setVideoName(originalfileName);
			videosEntity.setVideoType(suffixName);
			videosEntity.setVideoSize(file.getSize());
			videosEntity.setVideoUrl(resourceDTO.getResouceUrl());
			videosEntity.setVideoPath(resourceDTO.getResoucePath());
			videosEntity.setStreamId(Integer.parseInt(autoConfig.getVideoStreamId()));
			videosEntity.setRtmpUrl(autoConfig.getVideoRtmpUrl());
			videosEntity.setRtspUrl(autoConfig.getVideoRtspUrl());
			videosEntity.setCreateTime(new Date());
			videosEntity.setCreateBy(getCurrAccountId());
			if (videosRepository.add(videosEntity)) {
				VehicleVideoMessageDTO messageDTO = new VehicleVideoMessageDTO();
				messageDTO.setStreamId(videosEntity.getStreamId());
				messageDTO.setVideoPath(videosEntity.getVideoPath());
				messageDTO.setRtmpUrl(videosEntity.getRtmpUrl());
				messageDTO.setRstpUrl(videosEntity.getRtspUrl());
				videoMessagePublisher.publish(messageDTO);

				VideoUploadResponseDTO responseDTO = new VideoUploadResponseDTO();
				responseDTO.setStreamId(videosEntity.getStreamId());
				responseDTO.setRstpUrl(videosEntity.getRtspUrl());
				responseDTO.setRtmpUrl(videosEntity.getRtmpUrl());
				return CustomOutputUtility.excuteSuccess(responseDTO);
			} else {
				return CustomOutputUtility.excuteFail("上传视频失败！");
			}
		} catch (Exception ex) {
			logger.error(String.format("上传视频失败！原因：%s", ex.getMessage()), ex);
			return CustomOutputUtility.excuteFail(ex.getMessage());
		}

	}
}
