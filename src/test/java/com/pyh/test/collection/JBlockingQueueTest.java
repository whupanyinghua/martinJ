package com.pyh.test.collection;

import com.pyh.collection.JBlockingQueue;

/**
 * 类JBlockingQueueTest的实现描述：
 *
 * @author panyinghua 2020-7-31 17:57
 */
public class JBlockingQueueTest {

    public static void main(String[] args) {
        JBlockingQueue blockingQueue = new JBlockingQueue(5);
        Thread tin = new Thread(()->{
            for(int i=0;i<100;i++) {
                blockingQueue.enqueue(i);
                System.out.println(Thread.currentThread().getName() +" enqueue:"+i+",after enqueue,queue size is "+ blockingQueue.size());
                if(i%10==0) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread tout = new Thread(()->{
            while(true) {
                System.out.println(Thread.currentThread().getName() + " dequeue:" + blockingQueue.dequeue()+",after dequeue,queue size is "+ blockingQueue.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        tin.setName("Thread-enqueue");
        tout.setName("Thread-dequeue");
        tin.start();
        tout.start();


    }
}
