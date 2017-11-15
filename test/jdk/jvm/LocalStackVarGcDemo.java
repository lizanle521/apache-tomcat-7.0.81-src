package jdk.jvm;

import org.junit.Test;

/**
 * -XX:+PrintGCDetails
 * Created by Administrator on 2017/11/14.
 */
public class LocalStackVarGcDemo {
    @Test
    public void localvarGc1(){
        byte[] bytes = new byte[6 * 1024 * 1024];
        System.gc();
        // 没有进行垃圾回收 ParOldGen       total 87552K, used 7381K [0x0000000080a00000, 0x0000000085f80000, 0x00000000d5900000)
    }

    @Test
    public void localGc2(){
        //-XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -XX:+UseConcMarkSweepGC
        // -XX:+PrintVMOptions -XX:+PrintCommandLineFlags
        byte[] bytes = new byte[6 * 1024 * 1024];
        bytes = null;
        System.gc();
        // 垃圾回收成功 ParOldGen       total 87552K, used 1237K [0x0000000080a00000, 0x0000000085f80000, 0x00000000d5900000)
    }

    @Test
    public void localGc3(){
        try{
            byte[] bytes = new byte[6 * 1024 * 1024];
        }catch (Exception e){

        }
        System.gc();
        // 垃圾回收失败  ParOldGen       total 87552K, used 7382K [0x0000000080a00000, 0x0000000085f80000, 0x00000000d5900000)
    }

    @Test
    public void localGc4(){
        try{
            byte[] bytes = new byte[6 * 1024 * 1024];
        }catch (Exception e){

        }
        // 垃圾回收成功ParOldGen       total 87552K, used 1239K [0x0000000080a00000, 0x0000000085f80000, 0x00000000d5900000)
        int c = 10;
        System.gc();
    }

    @Test
    public void localGc5(){
        localvarGc1();
        System.gc();
        //垃圾回收成功ParOldGen       total 87552K, used 1240K [0x0000000080a00000, 0x0000000085f80000, 0x00000000d5900000)
    }

    @Test
    public void localvar1(){
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localvar2(){
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }
}
