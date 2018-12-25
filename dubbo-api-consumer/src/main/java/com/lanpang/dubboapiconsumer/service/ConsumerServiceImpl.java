package com.lanpang.dubboapiconsumer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lanpang.dubboapisamples.service.ConsumerService;
import org.springframework.stereotype.Component;

/**
 * @program: mydubbo
 * @description:
 * @author: yanghao
 * @create: 2018-12-19 15:01
 **/
@Service(
        version = "${dubbo.consumer.version}",
        interfaceClass = ConsumerService.class
)
@Component
public class ConsumerServiceImpl  implements ConsumerService {

    @Override
    public String consmerhello(String name) {
        return "hello:"+name;
    }
}
