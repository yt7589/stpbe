package com.zhuanjingkj.stpbe.dtmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SrDtmpApplication {
    public static void main(String[] args) {
        System.out.println("Deamon Task Management Platform v0.0.1");
        SpringApplication.run(SrDtmpApplication.class, args);
    }
}
