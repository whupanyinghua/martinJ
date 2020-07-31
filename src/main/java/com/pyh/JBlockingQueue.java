package com.pyh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类JBlockingQueue的实现描述：阻塞队列
 * 未处理被中断的情况
 *
 * @author panyinghua 2020-7-31 15:25
 */
public class JBlockingQueue {
    // 记录的是队列允许的最大长度，如果没有设置，那么默认就是无界(Integer.MAX_VALUE)
    private int maxSize = Integer.MAX_VALUE;

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private JLinkedList list = new JLinkedList();

    public JBlockingQueue(int maxQueueSize) {
        this.maxSize = maxQueueSize;
    }

    public void enqueue(Integer val) {
        try {
            // 队列是满的，此时入列方法需要阻塞，并且通知出列方法可以出列元素了，如果有线程在等待出列的话
            // 非空条件通知，唤醒在非空条件下等待的线程
            lock.lock();
            if(list.size()==maxSize) {
                while (true) {
                    try {
                        // 在非满条件下等待
                        notFull.await();
                        break; //等待状态解除之后直接跳出while循环
                    } catch (InterruptedException e) {
                        // 中断之后循环等待
                    }
                }
            }
            // 队列没满，直接进行入列操作
            list.add(val);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }



    }

    public Integer dequeue() {
        try {
            // 列表是空的，则出列方法需要阻塞，并且通知入列方法此时可以入列元素，如果有线程在等待入列的话
            lock.lock();
            if(list.size==0) {
                while (true) {
                    try {
                        notEmpty.await();
                        break;//等待状态解除之后直接跳出while循环
                    } catch (InterruptedException e) {
                        // 中断之后循环等待
                    }
                }
            }
            // 队列不是空的，直接进行出列操作
            Integer val = list.removeFirst();
            notFull.signal();
            return val;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return list.size();
    }

}
