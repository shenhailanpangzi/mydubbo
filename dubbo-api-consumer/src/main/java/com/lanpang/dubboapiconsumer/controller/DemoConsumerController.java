package com.lanpang.dubboapiconsumer.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.lanpang.dubboapisamples.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {

    @Reference
    private DemoService demoService;

    @RequestMapping("/sayHello1/{name}")
    public String sayHello1(@PathVariable String name) {
        return demoService.sayHello1(name);
    }
    @RequestMapping("/sayHello2/{name}")
    public String sayHello2(@PathVariable String name) {
        return demoService.sayHello2(name);
    }
}
