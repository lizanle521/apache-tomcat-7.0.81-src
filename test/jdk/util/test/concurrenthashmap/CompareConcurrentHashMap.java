package jdk.util.test.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class CompareConcurrentHashMap {
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>(40000);


    public static void putPerformance(int index, int num) {
        for (int i = index; i < (num + index) ; i++)
            map.put(String.valueOf(i), i);
    }
    public static void getPerformance2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 400000; i++)
            map.get(String.valueOf(i));
        long end = System.currentTimeMillis();
        System.out.println("get: it costs " + (end - start) + " ms");
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final CountDownLatch cdLatch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    CompareConcurrentHashMap.putPerformance(100000 * finalI, 100000);
                    cdLatch.countDown();
                }
            }).start();
        }
        cdLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("put: it costs " + (end - start) + " ms");
        CompareConcurrentHashMap.getPerformance2();
    }
}
