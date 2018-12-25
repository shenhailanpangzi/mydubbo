package com.lanpang.dubboapiprovider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@DubboComponentScan(basePackages = "com.lanpang.dubboapiprovider.controller")
public class DubboApiProviderApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(DubboApiProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

    }

}

