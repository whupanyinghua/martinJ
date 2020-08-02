package com.pyh.thread;

/**
 * 两个线程交替打印奇数、偶数
 */
public class OddEvenThread {
    static volatile int num = 1;

    public static void main(String[] args) {
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
}
