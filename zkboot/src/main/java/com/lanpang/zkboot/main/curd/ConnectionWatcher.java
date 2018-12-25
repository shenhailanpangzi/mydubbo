package com.lanpang.zkboot.main.curd;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @program: mydubbo
 * @description:注册组的成员。每个组成员将作为一个程序运行，并且加入到组中。当程序退出时，这个组成员应当从组中被删除。为了实现这一点，我们在ZooKeeper的命名空间中使用短暂znode来代表一个组成员。
 * @author: yanghao
 * @create: 2018-12-20 09:22
 **/
public class ConnectionWatcher implements Watcher {

    private static final int SESSION_TIMEOUT=5000;
    private static final String HOST1="localhost:2181";


    protected ZooKeeper zk;
    CountDownLatch connectedSignal=new CountDownLatch(1);

    public void connect(String host) throws IOException, InterruptedException{
        zk=new ZooKeeper(host, SESSION_TIMEOUT, this);
        connectedSignal.await();
    }

    public void process(WatchedEvent event) {
        if(event.getState()==Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }
    public void close() throws InterruptedException{
        zk.close();
    }
    private void create(String groupName) throws KeeperException, InterruptedException {
        String path="/"+groupName;
        if(zk.exists(path, false)== null){
//       路径：用字符串表示。
//　　　　znode的内容：字节数组，本例中使用空值。
//　　　　访问控制列表：简称ACL，本例中使用了完全开放的ACL，允许任何客户端对znode进行读写。
//　　　　创建znode的类型：有两种类型的znode：短暂的和持久的。
            zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("Created:"+path);
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
//        创建了一个CreateGroup的实例并且调用了这个实例的connect()方法
        ConnectionWatcher connectionWatcher = new ConnectionWatcher();
        connectionWatcher.connect(HOST1);
        connectionWatcher.create("c");
        connectionWatcher.close();
    }
}
