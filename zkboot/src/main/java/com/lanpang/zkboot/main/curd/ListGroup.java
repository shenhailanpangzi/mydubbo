package com.lanpang.zkboot.main.curd;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @program: mydubbo
 * @description: 列出组成员
 * @author: yanghao
 * @create: 2018-12-20 09:30
 **/
public class ListGroup extends ConnectionWatcher {

    private static final String HOST1="localhost:2181";
    private static final String HOST2="192.168.56.90:2182,192.168.56.90:2183,192.168.56.90:2184";

//    在list()方法中，我们调用了getChildren()方法来检索并打印输出一个znode的子节点列表，调用参数为：该znode的路径和 设为false的观察标志。如果在一znode上设置了观察标志，那么一旦该znode的状态改变，关联的观察(Watcher)会被触发。虽然在这里我 们可以不使用观察，但在查看一个znode的子节点时，也可以设置观察，让应用程序接收到组成员加入、退出和组被删除的有关通知。
    public void list(String groupNmae) throws KeeperException, InterruptedException{
        String path ="/"+groupNmae;
        try {
            List children = zk.getChildren(path, false);
            if(children.isEmpty()){
                System.out.println("Group "+groupNmae+" does not exist \n");
                System.exit(1);
            }
            Iterator it=children.iterator();
            while(it.hasNext()){
                String child=(String)it.next();
                System.err.println(child);
            }
        } catch (KeeperException.NoNodeException e) {
            System.out.println("Group "+groupNmae+" does not exist \n");
            System.exit(1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect(HOST1);
        listGroup.list("");
        listGroup.close();
    }
}