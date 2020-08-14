package com.pyh.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类MultiThreadPrintNumber的实现描述：多个线程打印出给定的一个字符串数字
 *
 * @author panyinghua 2020-8-14 14:45
 */
public class MultiThreadPrintNumber {

    private volatile boolean flag;

    public static void main(String[] args) {
        MultiThreadPrintNumber multiThreadPrintNumber = new MultiThreadPrintNumber();
        String numStr = "23456789354567155";
        System.out.println(numStr);
        multiThreadPrintNumber.printNumber(numStr);
    }

    public void printNumber(String numberString) {
        Lock lock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        // 初始化线程数组
        Condition[] conditions = new Condition[10];
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++) {
            conditions[i] = lock.newCondition();
            threads[i] = new Thread(new NumberRunnable(i,lock,conditions[i], countDownLatch));
            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // 被中断
        }
        for(int i=0;i<numberString.length();i++) {
            int num = Integer.parseInt(numberString.charAt(i)+"");
            lock.lock();
            try {
                // 唤醒对应数字的等待打印数字线程
                conditions[num].signal();
                // 当前线程等待，需要被唤起的打印数字线程打印完成之后唤醒本线程
                conditions[num].await();
            } catch (InterruptedException e) {
                // 中断暂时不管
            } finally {
                lock.unlock();
            }
        }

        // 通过中断来结束数字打印线程
        for (Thread thread : threads) {
            thread.interrupt();
        }

    }
    
    static class NumberRunnable implements Runnable {
        private int number;
        private Lock lock;
        private Condition condition;
        private CountDownLatch countDownLatch;

        public NumberRunnable(int num, Lock lo, Condition con, CountDownLatch cdl) {
            this.number = num;
            this.lock = lo;
            this.condition = con;
            this.countDownLatch = cdl;
        }

        @Override
        public void run() {
            while(true) {
                lock.lock();
                try {
                    countDownLatch.countDown();
                    // 在当前线程关联的condition上等待
                    condition.await();
                    System.out.print(number);
                    // 唤醒在condition上等待的其他线程
                    condition.signal();
                } catch (InterruptedException e) {
                    // 中断用于停止线程
                    break;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
