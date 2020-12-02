package com.zhuanjingkj.stpbe.ca_tvis;

public class CaTvisApplication {
	public final static String MODE_MGQS = "mgqs"; // 图搜系统客户端

    public static void main(String[] args) {
		System.out.println("Client Application Tvis v0.0.1");
		String mode = args[0];
		ITvisClient client = null;
		if (mode.equals("mgqs")) {
			client = new MgqsClient();
			client.startup(args);
		}
    }
}
