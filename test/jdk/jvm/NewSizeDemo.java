package jdk.jvm;

import org.junit.Test;

/**
 * Created by Administrator on 2017/11/15.
 */
public class NewSizeDemo {
    // -Xmx20m -Xms20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
    // ParallelGC 新生代最小也要512k
    //-Xmx20m -Xms20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
    // 老年代也用掉了1m，估计是升级 ？
    @Test
    public  void main1() {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1*1024*1024];
        }
    }
   //-Xmx20m -Xms20m -Xmn15m -XX:SurvivorRatio=8-XX:+PrintGCDetails -XX:+UseSerialGC
    //如果是单元测试则会进行一次gc
   //-Xmx20m -Xms20m -XX:NewRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
    // 进行10次GC，因为新生代大小为2m
    public static void main(String[] args) {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1*1024*1024];
        }
    }
}
