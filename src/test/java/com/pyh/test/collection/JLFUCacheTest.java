package com.pyh.test.collection;

import com.pyh.collection.JLFUCache;

/**
 * 类JLFUCacheTest的实现描述：JLFUCache测试类
 *
 * @author panyinghua 2020-8-17 18:22
 */
public class JLFUCacheTest {
    public static void main(String[] args) {
        JLFUCache lfu = new JLFUCache(3);

        lfu.put(1,1);
        lfu.put(2,2);
        lfu.put(3,3);
        lfu.put(4,4);
        System.out.println("val1:"+lfu.get(1));
        System.out.println("val2:"+lfu.get(2));
        System.out.println("val3:"+lfu.get(3));


        System.out.println("val2:"+lfu.get(2));
        System.out.println("val2:"+lfu.get(2));
        lfu.put(5,5);
        System.out.println("val3:"+lfu.get(3));
        System.out.println("val4:"+lfu.get(4));
        System.out.println("val5:"+lfu.get(5));


    }
}
