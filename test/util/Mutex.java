package util;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 排他锁的实现，一次只能一个线程获取到锁。
 * Created by Administrator on 2017/9/24.
 */
public class Mutex implements Lock,Serializable {
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    // 内部类 自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否处于占用状态

        /**
         * CANCELLED，值为1，表示当前的线程被取消；
         SIGNAL，值为-1，表示当前节点的后继节点包含的线程需要运行，也就是unpark；
         CONDITION，值为-2，表示当前节点在等待condition，也就是在condition队列中；
         PROPAGATE，值为-3，表示当前场景下后续的acquireShared能够得以执行；
         值为0，表示当前节点在sync队列中，等待着获取锁。
         * @return
         */
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 当状态为0的时候获取锁
         * @param accquires
         * @return
         */
        public boolean tryAcquire(int accquires){
            assert accquires == 1; // 否则是没有被使用
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        // 释放锁，将状态设置为0
        protected boolean tryRelease(int releases){
            assert releases == 1;//否则不使用
            if(getState() == 0) throw new IllegalArgumentException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个condition，每个condition都包含了一个condition队列
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    // 锁定当前线程
    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition(){
        return sync.newCondition();
    }

    // 判断当前线程是否阻塞了
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }


    /**
     * 尝试获取锁
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock(long timeout,TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(timeout));
    }


}
