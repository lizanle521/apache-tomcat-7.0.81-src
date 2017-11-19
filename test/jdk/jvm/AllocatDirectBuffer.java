package jdk.jvm;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2017/11/19.
 */
public class AllocatDirectBuffer {
    public void directAlloc(){
        long c = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1000);
        }
        long e = System.currentTimeMillis();
        System.out.println(e-c);
    }

    public void bufferAlloc(){
        long c = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            ByteBuffer allocate = ByteBuffer.allocate(1000);
        }
        long e = System.currentTimeMillis();
        System.out.println(e-c);
    }

    public static void main(String[] args) {
        AllocatDirectBuffer buffer = new AllocatDirectBuffer();
        buffer.bufferAlloc();
        buffer.directAlloc();

        buffer.bufferAlloc();
        buffer.directAlloc();
    }
}
