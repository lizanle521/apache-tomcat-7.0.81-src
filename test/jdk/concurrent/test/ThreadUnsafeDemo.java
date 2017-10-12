package jdk.concurrent.test;


/**
 * 对成员变量 t进行并发修改，
 * 读取到的值就有可能是一个被修改坏了的值
 * 将t改成volatile可以避免这种情况
 * Created by Lizanle on 2017/10/12.
 */
public class ThreadUnsafeDemo {
    private static volatile long t = 0L;

    public static class WriterThread implements Runnable {
        private long z = 0;

        public WriterThread(long z) {
            this.z = z;
        }

        @Override
        public void run() {
            while(true){
                ThreadUnsafeDemo.t = this.z;
                Thread.yield();
            }
        }
    }

    public static class ReadThread implements Runnable {
        @Override
        public void run() {
            while (true){
                long temp = ThreadUnsafeDemo.t;
                if(temp != 333L && temp != 111L && temp != -999L && temp != 444L){
                    System.out.println(temp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new WriterThread(333L)).start();
        new Thread(new WriterThread(111L)).start();
        new Thread(new WriterThread(-999L)).start();
        new Thread(new WriterThread(444L)).start();
        new Thread(new ReadThread()).start();
    }
}
