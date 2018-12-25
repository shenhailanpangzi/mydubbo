package com.lanpang.zkboot.main.curd;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @program: mydubbo
 * @description:为组名为/zoo的组创建一个znode。
 * @author: yanghao
 * @create: 2018-12-20 09:10
 **/
public class CreateGroup implements Watcher {

    private static final int SESSION_TIMEOUT=5000;
    private static final String HOST1="localhost:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";



    private ZooKeeper zk;
    private CountDownLatch connectedSignal=new CountDownLatch(1);
//    客 户端已经与ZooKeeper建立连接后，Watcher的process()方法会被调用，参数是一个表示该连接的事件
    public void process(WatchedEvent event) {
        if(event.getState()==Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }
    private void close() throws InterruptedException {
        zk.close();
    }
}
//JoinGroup的代码与CreateGroup非常相似，在它的join()方法中，创建短暂znode，作为组znode的子节点，然后通过 休眠来模拟正在做某种工作，直到该进程被强行终止。接着，你会看到随着进程终止，这个短暂znode被ZooKeeper删除。
class JoinGroup extends ConnectionWatcher{

    private static final String HOST1="localhost:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";

    public void join(String groupName,String memberName) throws KeeperException, InterruptedException{
        String path="/"+groupName+"/"+memberName;
        String createdPath=zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created:"+createdPath);
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect(HOST1);
        joinGroup.join("a", "aa");
        Thread.sleep(Long.MAX_VALUE);
    }


}