package jdk.jvm;

import java.nio.ByteBuffer;

/**
 * 堆外内存 堆内内存比较
 * Created by Administrator on 2017/11/19.
 */
public class AccessDirectBuffer {
    public void directAccess(){
        long c = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);
            }
            byteBuffer.flip();
            for (int j = 0; j < 99; j++) {
                byteBuffer.getInt();
            }
            byteBuffer.clear();
        }
        long e = System.currentTimeMillis();
        System.out.println(e-c);
    }

    public void bufferAccess(){
        long c = System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                byteBuffer.putInt(j);
            }
            byteBuffer.flip();
            for (int j = 0; j < 99; j++) {
                byteBuffer.getInt();
            }
            byteBuffer.clear();
        }
        long e = System.currentTimeMillis();
        System.out.println(e-c);
    }

    public static void main(String[] args) {
        AccessDirectBuffer directBuffer = new AccessDirectBuffer();
        directBuffer.bufferAccess();
        directBuffer.directAccess();

        directBuffer.bufferAccess();
        directBuffer.directAccess();
    }


}
