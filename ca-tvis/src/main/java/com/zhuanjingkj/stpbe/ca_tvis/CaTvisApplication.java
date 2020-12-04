package com.zhuanjingkj.stpbe.ca_tvis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaTvisApplication implements CommandLineRunner {
	public final static String MODE_MGQS = "mgqs"; // 图搜系统客户端

    public static void main(String[] args) {
		System.out.println("Client Application Tvis v0.0.1");
		ConfigurableApplicationContext ctx = SpringApplication.run(CaTvisApplication.class, args);
    }

    @Bean
	public MgqsClient getMgqsClient() {
    	return new MgqsClient();
	}

	@Override
	public void run(String... args) throws Exception {
    	String cmd = args[0];
    	if (cmd.equals("mgqs")) {
			getMgqsClient().startup(args);
		} else {
    		System.out.println("未知命令：" + cmd + "!");
		}
	}
}
