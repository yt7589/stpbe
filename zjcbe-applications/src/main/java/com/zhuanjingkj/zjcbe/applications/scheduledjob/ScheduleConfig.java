package com.zhuanjingkj.zjcbe.applications.scheduledjob;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2020/8/28 15:40
 **/
@Configuration
@EnableAsync
public class ScheduleConfig {
	@Bean(name = "taskSchedulerPool")
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}
}
