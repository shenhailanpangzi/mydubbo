package com.lanpang.zkboot.main.curd;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @program: mydubbo
 * @description:
 * @author: yanghao
 * @create: 2018-12-19 11:16
 **/
public class ZooKeeperTest {
    public static void main(String[] args) throws Exception{
        ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
        System.out.println("=========创建节点===========");
        if(zk.exists("/test", false) == null)
        {
            zk.create("/test", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("=============查看节点是否安装成功===============");
        System.out.println(new String(zk.getData("/test", false, null)));

        System.out.println("=========修改节点的数据==========");
        zk.setData("/test", "zNode2".getBytes(), -1);
        System.out.println("========查看修改的节点是否成功=========");
        System.out.println(new String(zk.getData("/test", false, null)));

        System.out.println("=======删除节点==========");
        zk.delete("/test", -1);
        System.out.println("==========查看节点是否被删除============");
        System.out.println("节点状态：" + zk.exists("/test", false));
        zk.close();
    }
}
