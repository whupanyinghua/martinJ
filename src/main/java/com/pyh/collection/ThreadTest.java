package com.pyh.collection;

import java.util.concurrent.CountDownLatch;

/**
 * 类ThreadTest的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 20:09
 */
public class ThreadTest {
    static Thread[] threads = new Thread[10];
    static final Object[] locks = new Object[10];
    static volatile boolean flag = true;
    static volatile boolean exit = false;
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) {


        for(int i=0;i<10;i++) {
            Object lock = new Object();
            locks[i] = lock;
            int finalI = i;
            Thread t = new Thread(()-> {
                while(!exit) {
                    synchronized (lock){
                        try {
                            countDownLatch.countDown();
                            lock.wait();
                            System.out.print(finalI);
                            lock.notify();
                        } catch (InterruptedException e) {
                            // 中断暂时不管，这里无需处理中断的情况
                            // 方便线程外部通过中断来结束线程
                            break;
                        }
                    }
                }
            });
            //t.setDaemon(true);
            threads[i]=t;
            t.start();
        }
        //确保上述线程数组所有线程都启动好
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // 中断暂时不管，这里无需处理中断的情况
        }
        String source = "3382019835830";
        for(int i=0;i<source.length();i++) {
            flag = true;
            int currentPrintNum = Integer.parseInt(source.charAt(i)+"");
            Object lock = locks[currentPrintNum];
            synchronized(lock) {
                lock.notify();

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // 中断暂时不管，这里无需处理中断的情况
                }
            }
        }

        // 停止数字打印线程
        exit = true;
        for(Thread thread: threads) {
            // 通过中断来结束数字打印
            thread.interrupt();
        }

    }
}
