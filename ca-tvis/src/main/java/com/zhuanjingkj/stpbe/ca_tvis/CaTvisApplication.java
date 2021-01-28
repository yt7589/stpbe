package com.zhuanjingkj.stpbe.ca_tvis;

import com.zhuanjingkj.stpbe.ca_tvis.apps.snapshot.SnapshotApp;
import com.zhuanjingkj.stpbe.common.mgq.MgqEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaTvisApplication implements CommandLineRunner {
	public final static String MODE_MGQS = "mgqs"; // 图搜系统客户端
	public final static String MODE_SNAPSHOT = "snapshot"; // 抓拍机

    public static void main(String[] args) {
		System.out.println("Client Application Tvis v0.0.1");
		MgqEngine.initialize();
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
    	int iDebug = 10;
    	if (1 == iDebug) {
    		return;
		}
    	String cmd = args[0];
    	if (cmd.equals("mgqs")) {
			getMgqsClient().startup(args);
		} else if (cmd.equals(MODE_SNAPSHOT)) {
    		getSnapshotApp().startup(args);
		} else {
    		System.out.println("未知命令：" + cmd + "!");
		}
	}
}
