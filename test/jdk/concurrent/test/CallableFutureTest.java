package jdk.concurrent.test;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by Lizanle on 2017/10/10.
 */
public class CallableFutureTest {
    @Test
    public void callableFutureTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Integer> task = new Task();
        Future<Integer> submit = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        System.out.println("主线程正在运行");
        try {
            System.out.println("task运行结果"+submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void callableFutureTest1(){
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class Task implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 10000; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
