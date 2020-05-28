package com.pyh.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 类MapTest的实现描述：@TODO
 *
 * @author panyinghua 2020-5-26 11:33
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        System.out.println(testMap);

        // 这个是啥意思？
        testMap.forEach(testMap::put);
        System.out.println(testMap);

        // 可以复制一个map
        Map<String, String> testMap2 = new HashMap<>();
        testMap.forEach(testMap2::put);
        System.out.println(testMap2);
    }
}
