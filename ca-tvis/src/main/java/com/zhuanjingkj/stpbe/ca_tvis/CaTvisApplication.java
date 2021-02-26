package com.zhuanjingkj.stpbe.ca_tvis;

import com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot.SnapshotApp;
import com.zhuanjingkj.stpbe.ca_tvis.apps.wxs2102.Wxs2102App;
import com.zhuanjingkj.stpbe.common.mgq.MgqEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CaTvisApplication implements CommandLineRunner {
	public final static String MODE_MGQS = "mgqs"; // 图搜系统客户端
	public final static String MODE_SNAPSHOT = "snapshot"; // 抓拍机

	@Value("${app.run-mode}")
	private String appRunMode;
	@Autowired
	private Environment environment;

    public static void main(String[] args) {
		System.out.println("Client Application Tvis v0.0.1");
		ConfigurableApplicationContext ctx = SpringApplication.run(CaTvisApplication.class, args);
    }

    @Bean
	public MgqsClient getMgqsClient() {
    	return new MgqsClient();
	}

	@Bean
	public SnapshotApp getSnapshotApp() {
    	return new SnapshotApp();
	}

	@Override
	public void run(String... args) throws Exception {
    	if (appRunMode.equals("3")) {
			Wxs2102App app = new Wxs2102App();
			app.startup(environment);
			return;
		}
    	int iDebug = 10;
    	if (1 == iDebug) {
    		return;
		}
    	String cmd = args[0];
    	if (cmd.equals("mgqs")) {
    		MgqEngine.initialize();
			getMgqsClient().startup(args);
		} else if (cmd.equals(MODE_SNAPSHOT)) {
    		getSnapshotApp().startup(args);
		} else {
    		System.out.println("未知命令：" + cmd + "!");
		}
	}
}
