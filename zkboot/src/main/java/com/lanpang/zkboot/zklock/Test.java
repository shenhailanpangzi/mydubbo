package com.lanpang.zkboot.zklock;

/**
 * @program: mydubbo
 * @description: 测试
 * @author: yanghao
 * @create: 2018-12-25 10:55
 **/
public class Test {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {

        Runnable runnable = () -> {
            DistributedLock lock = null;
            try {
                lock = new DistributedLock("127.0.0.1:2181", "test1");
                lock.lock();
                secskill();
                System.out.println(Thread.currentThread().getName() + "正在运行");
            } finally {
                if (lock != null) {
                    lock.unlock();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
