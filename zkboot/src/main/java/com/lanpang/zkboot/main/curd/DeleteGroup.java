package com.lanpang.zkboot.main.curd;

/**
 * @program: mydubbo
 * @description:删除组
 * 如果所提供的版本号与znode的版本号一致，ZooKeeper会删除这个znode。这是一种乐观的加锁机制，使客户端能够检测出对znode的修改冲突。通过将版本号设置为-1，可以绕过这个版本检测机制，不管znode的版本号是什么而直接将其删除。ZooKeeper不支持递归的删除操作，因此在删除父节点之前必须先删除子节点。
 * @author: yanghao
 * @create: 2018-12-20 09:34
 **/
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DeleteGroup extends ConnectionWatcher{

    private static final String HOST1="192.168.56.90:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";

    public void delete(String groupName) throws InterruptedException, KeeperException{
        String path="/"+groupName;
        List children;
        try {
            children = zk.getChildren(path, false);
            Iterator it=children.iterator();
            while(it.hasNext()){
                zk.delete(path+"/"+(String)it.next(), -1);
            }

            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.println("Group "+groupName+" does not exist \n");
            System.exit(1);
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect(HOST1);
        deleteGroup.delete("a");
        deleteGroup.close();
    }
}
