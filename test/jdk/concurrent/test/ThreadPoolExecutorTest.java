package jdk.concurrent.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/26.
 */
public class ThreadPoolExecutorTest {
    public static class Mytask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+"--"+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testFixedThreadPoolExecutor(){
        Mytask mytask = new Mytask();
        // 线程池只有5个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(mytask);
        }
        /**
         * 后边必须停顿一下，不然的就只能5个线程执行一次
         */
        try {
            executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    @Test
    public void testCachedThreadPoolExecutor(){
        Mytask mytask = new Mytask();
        // 线程池会根据实际情况调整
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(mytask);
        }
        System.out.println("------------------");
        for (int i = 0; i < 10; i++) {
            executorService.submit(mytask);
        }
        executorService.shutdown();
    }

    @Test
    public void testSingleThreadPoolExecutor(){
        Mytask mytask = new Mytask();
        // 只有一个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.submit(mytask);
        }
        try {
            executorService.awaitTermination(20,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    @Test
    public void testSingleThreadScheduleExecutor(){
        Mytask mytask = new Mytask();
        // 只有一个线程
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // 固定的周期进行。如果执行时间大于周期，那么就会下一个任务就会马上执行
       executorService.scheduleAtFixedRate(mytask,0,2,TimeUnit.SECONDS);
        try {
            executorService.awaitTermination(20,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
