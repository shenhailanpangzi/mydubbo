package com.lanpang.dubboapiprovider.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.lanpang.dubboapisamples.service.ConsumerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {

    @Reference(
            version = "${dubbo.consumer.version}"
    )
    private ConsumerService consumerService;

    @RequestMapping("/consmerhello/{name}")
    public String sayHello1(@PathVariable String name) {
        return consumerService.consmerhello(name);
    }
}
