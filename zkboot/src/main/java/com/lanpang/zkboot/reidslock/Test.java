package com.lanpang.zkboot.reidslock;

/**
 * @program: mydubbo
 * @description: 模拟线程进行秒杀服务
 * @author: yanghao
 * @create: 2018-12-25 10:32
 **/
public class Test {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}