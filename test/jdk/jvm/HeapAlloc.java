package jdk.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * -Xmx20m -Xms5m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 * Created by Administrator on 2017/11/15.
 */
public class HeapAlloc {
    public static void main(String[] args) {
        getHeapInfo();
        byte[] bytes = new byte[1 * 1024 * 1024];
        // 一开始在新生代分配
        System.out.println("分配1M");
        getHeapInfo();
        bytes = new byte[4*1024*1024];
        // 新生代不满足条件了，进行了一次fullGC 就分配在老年代
        System.out.println("分配4M");
        getHeapInfo();
    }

    public static void getHeapInfo(){

        System.out.println("maxMemory"+Runtime.getRuntime().maxMemory()/1000/1000);
        System.out.println("freeMemory"+Runtime.getRuntime().freeMemory()/1000/1000);
        System.out.println("totalMemory"+Runtime.getRuntime().totalMemory()/1000/1000);

    }
}
