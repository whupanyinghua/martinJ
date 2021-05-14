package com.pyh.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类LinkedhashMapTest的实现描述：@TODO
 *
 * @author panyinghua 2021-5-14 15:34
 */
public class LinkedhashMapTest {

    public static void main(String[] args) {
        HashMap<Integer, Integer> m = new LinkedHashMap<Integer, Integer>(10,0.75f,true);
        m.put(3,11);
        m.put(1,12);
        m.put(5,23);
        m.put(2,22);

        m.put(3,26);
        m.get(5);

        for(Map.Entry e : m.entrySet()) {
            System.out.println(e.getKey());
        }
    }
}
