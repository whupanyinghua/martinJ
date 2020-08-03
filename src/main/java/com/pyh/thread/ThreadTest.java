package com.pyh.thread;

/**
 * 类ThreadTest的实现描述：
 * 两个线程交替输出奇数偶数
 *
 * @author panyinghua 2020-7-28 16:05
 */
public class ThreadTest {


    static volatile boolean flag = true;

    private static void test1() {
        Thread t1 = new Thread(() -> {
            int i=0;
            while(true) {
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i = i + 2;
                    flag = false;
                }
            }
        });
        t1.setName("t1");
        Thread t2 = new Thread(() -> {
            int i=1;
            while(true) {
                while (!flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i = i + 2;
                    flag = true;
                }
            }
        });
        t2.setName("t2");

        t1.start();
        t2.start();

    }

    public static void main(String[] args) {
        ThreadTest.test1();
    }
}
