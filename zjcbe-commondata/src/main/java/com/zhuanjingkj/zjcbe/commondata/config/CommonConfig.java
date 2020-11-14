package com.zhuanjingkj.zjcbe.commondata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

	@Value("${vehicle.resources.domain:}")
	private String resouceDomain;

	@Value("${vehicle.resources.rootpath:}")
	private String resourceRootPath;

	@Value("${vehicle.identification.apiurl:}")
	private String identificationApiurl;

	@Value("${vehicle.delay.minutes:}")
	private String delayMinutes;

	/**
	 * 视频解析队列
	 */
	@Value("${rtmp.analysis.queenName:}")
	private String vehicleQueenName;

	/**
	 * 图片队列
	 */
	@Value("${rtmp.analysis.image.queenName:}")
	private String imageQueenName;

	/**
	 * 图片队列
	 */
	@Value("${rtmp.analysis.video.queenName:}")
	private String videoQueenName;

	@Value("${vehicle.video.streamId:}")
	private String videoStreamId;

	@Value("${vehicle.video.rtsp:}")
	private String videoRtspUrl;

	@Value("${vehicle.video.rtmp:}")
	private String videoRtmpUrl;

	@Value("${vehicle.rtmp.stomp:}")
	private String stompUrl;

	public String getResouceDomain() {
		return resouceDomain;
	}

	public String getResourceRootPath() {
		return resourceRootPath;
	}

	public String getIdentificationApiurl() {
		return identificationApiurl;
	}

	public String getVehicleQueenName() {
		return vehicleQueenName;
	}

	public String getImageQueenName() {
		return imageQueenName;
	}

	public String getDelayMinutes() {
		return delayMinutes;
	}

	public String getVideoStreamId() {
		return videoStreamId;
	}

	public String getVideoRtspUrl() {
		return videoRtspUrl;
	}

	public String getVideoRtmpUrl() {
		return videoRtmpUrl;
	}

	public String getVideoQueenName() {
		return videoQueenName;
	}

	public String getStompUrl() {
		return stompUrl;
	}
}
