package com.pyh.thread;

/**
 * 类TestSync的实现描述：@TODO
 *
 * @author panyinghua 2021-3-29 18:08
 */
public class TestSync implements Runnable {
    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(500); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread.sleep(250); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync tt = new TestSync();
        Thread t = new Thread(tt);  //1
        t.start(); //2
        //Thread.sleep(250); //xx
        tt.m2(); //3
        System.out.println("main thread b=" + tt.b); //4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
