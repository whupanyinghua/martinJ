package com.pyh.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Map<Integer, Integer> test = new HashMap<Integer, Integer>();
        test.put(new Integer(1), new Integer(1));
        test.put(new Integer(2), new Integer(2));
        test.put(new Integer(3), new Integer(3));
        
        Set<Map.Entry<Integer, Integer>> ss = test.entrySet();
        for(Map.Entry<Integer, Integer> s :ss) {
            System.out.println(s);
        }
    }

}
