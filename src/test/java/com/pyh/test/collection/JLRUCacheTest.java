package com.pyh.test.collection;

import com.pyh.collection.JLRUCache;

/**
 * 类JLRUCacheTest的实现描述：JLRUCache测试类
 *
 * @author panyinghua 2020-8-17 14:37
 */
public class JLRUCacheTest {

    public static void main(String[] args) {
        JLRUCache cache = new JLRUCache(3);

        cache.add(1,1);
        cache.add(2,2);
        cache.add(3,3);
        cache.add(4,4);
        Integer val1 = cache.get(1);
        System.out.println("val1:"+val1);
        Integer val2 = cache.get(2);
        System.out.println("val2:"+val2);
        cache.add(5,5);
        System.out.println("end");
    }
}
