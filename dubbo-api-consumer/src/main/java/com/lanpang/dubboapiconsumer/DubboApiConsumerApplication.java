package com.lanpang.dubboapiconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(basePackages = "com.lanpang.dubboapiconsumer.service")
public class DubboApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboApiConsumerApplication.class, args);
    }

}

