package com.lanpang.zkboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ZkbootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ZkbootApplication.class, args);
    }

}

