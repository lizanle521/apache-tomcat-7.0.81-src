package jdk.lock.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * CLH锁也是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
 * Created by Lizanle on 2017/10/12.
 */
public class CLHLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        myPred = new ThreadLocal<QNode>(){
            @Override
            protected QNode initialValue() {
                return null;
            }
        };
        myNode = new ThreadLocal<QNode>(){
            @Override
            protected QNode initialValue() {
                return new QNode();
            }
        };
    }

    /**
     * 尝试获取锁
     */
    @Override
    public void lock() {
        //1.创建一个的QNode，将其中的locked设置为true表示需要获取锁
        QNode qNode = myNode.get();
        qNode.locked = true;
        //2.线程对tail域调用getAndSet方法，使自己成为队列的尾部，同时获取一个指向前驱节点的引用myPred
        QNode pred = tail.getAndSet(qNode);
        myPred.set(pred);
        //3.该线程就在前趋结点的locked字段上旋转，直到前趋结点释放锁
        while (pred.locked){

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        QNode qNode = myNode.get();
        qNode.locked = false;
        myNode.set(myPred.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    class QNode {
        boolean locked = false;
        QNode next = null;
    }
    @Test
    public void test() {
        CLHLock lock = new CLHLock();
        Thread thread1 = new Thread(new TestCLHLockThread(lock));
        Thread thread2 = new Thread(new TestCLHLockThread(lock));
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(thread1);
        executorService.submit(thread2);
        try {
            executorService.awaitTermination(3000,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private volatile boolean t = true;
    public   class TestCLHLockThread implements Runnable{
        private CLHLock lock;

        public TestCLHLockThread(CLHLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true){
                if(t){
                    lock.lock();
                    t = false;
                    System.out.println("A");
                    lock.unlock();
                }else {
                    lock.lock();
                    t = true;
                    System.out.println("B");
                    lock.unlock();
                }
            }
        }
    }
}
