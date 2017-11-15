package jdk.jvm;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Lizanle on 2017/11/15.
 */
public class OOMEDemo {
    @Test
    public void test(){
        //  -Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\\a.hprof
        ArrayList<OOMEDemo> arrayList = new ArrayList<OOMEDemo>(1000000);
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(new OOMEDemo());
        }
    }
}
