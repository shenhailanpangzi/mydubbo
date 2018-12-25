package com.lanpang.zkboot.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.zookeeper.Watcher;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mydubbo
 * @description: 使用rest接口访问zk
 * @author: yanghao
 * @create: 2018-12-18 11:27
 **/
@RestController
public class RestZkController {

    @GetMapping(value = "/zkGet")
    public String zkGet(){
        System.out.println("进入：zkGet");
        Watcher watcher= new Watcher(){
            public void process(WatchedEvent event) {
                System.out.println("receive event："+event);
            }
        };

        String value = null;
        try {
            final ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181", 999999, watcher);
            final byte[] data = zookeeper.getData("/dubbo", watcher, null);
            value = new String(data);
            zookeeper.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "获取 node_1 节点值为 [" + value + "]";
    }
}
