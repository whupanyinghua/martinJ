package com.pyh.thread;

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

        Thread oddThread = new Thread(()->{
            while(true && num<maxNum) {
                synchronized(lock) {
                    // 奇数线程先输出
                    System.out.println("奇数线程："+num++);
                    lock.notify();
                    if(num==maxNum) {
                        break; // 奇数输出到maxNum-1之后就不要等待了，方便程序退出
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"奇数线程");
        Thread evenThread = new Thread(()->{
            while(true && num<maxNum) {
                synchronized(lock) {
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 偶数线程后输出
                    System.out.println("偶数线程："+num++);
                }
            }
        },"偶数线程");

        evenThread.start();
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
