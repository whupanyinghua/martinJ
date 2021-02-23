package com.pyh.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 两个线程交替打印奇数、偶数
 * 输出1 2 3 4 5 ...  99 100
 */
public class OddEvenThread {
    static volatile int num = 1;

    public static void main(String[] args) {
        printOddEven();
        // printOddEven2();
    }

    /**
     * 使用Object的wait、notify方法实现两个线程交替打印奇数、偶数
     * 输出1 2 3 4 5 ...  99 100
     */
    public static void printOddEven() {
        int maxNum = 100;// 只输出100以内的数字
        Object lock = new Object();
        CountDownLatch latch = new CountDownLatch(1);

        // 奇数线程
        Thread oddThread = new Thread(()->{
            while(true && num<maxNum) {
                synchronized(lock) {
                    // 奇数线程先输出
                    System.out.println("奇数线程："+num++);
                    // 唤起偶数线程
                    lock.notify();
                    if(num==maxNum) {
                        break; // 奇数输出到maxNum-1之后就不要等待了，方便程序退出
                    }
                    try {
                        // 等待偶数线程唤醒自己
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"奇数线程");

        // 偶数线程
        Thread evenThread = new Thread(()->{
            latch.countDown();
            while(true && num<maxNum) {
                synchronized(lock) {
                    try {
                        // 等待奇数线程唤醒自己
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 偶数线程后输出
                    System.out.println("偶数线程："+num++);
                    // 唤起奇数线程
                    lock.notify();
                }
            }
        },"偶数线程");

        // 启动顺序有影响，程序不够完美
        // 1.保证偶数线程先于奇数线程启动，然后偶数线程等待奇数线程先输出数字之后唤起偶数线程
        // 2.先启动偶数线程
        evenThread.start();
        // 3.等待偶数线程启动（通过CountDownLatch的方式），偶数线程对latch进行countDown操作之后，latch的await方法从阻塞中返回
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 4.启动奇数线程
        oddThread.start();

    }

    /**
     * 使用volatile 开关变量实现两个线程交替打印奇数、偶数
     */
    public static void printOddEven2() {
        int maxNum = 100;// 只输出100以内的数字
        Thread t1 = new Thread(() -> {
            int i=1;
            while(true && i<maxNum) {
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i = i + 2;
                    flag = false;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int i=2;
            while(true && i<=maxNum) {
                while (!flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i = i + 2;
                    flag = true;
                }
            }
        });

        t1.setName("奇数线程");
        t2.setName("偶数线程");

        t1.start();
        t2.start();
    }

    static volatile boolean flag = true;
}
