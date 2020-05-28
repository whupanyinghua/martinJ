package com.pyh.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.collect.Lists;


public class ArrayListTest {

    public static void main(String[] args) {
        //System.out.println(minRunLength(70));
        exList();
    }
    
    public static void randomList(){
        for(int i=0;i<10000;i++) {
            List<Integer> arr = generateRandomLists();
            System.out.println(i+"--->"+arr);
            arr.sort((Integer i1, Integer i2)->{
                if (i1.intValue() < i2.intValue()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            System.out.println(i+"--->"+arr);
        }
    }
    
    public static List<Integer> generateRandomLists() {
        int listSize = RandomUtils.nextInt(5, 50);
        List<Integer> arr = Lists.newArrayListWithCapacity(listSize);
        for(int i=0;i<listSize;i++) {
            arr.add(RandomUtils.nextInt(1,13));
        }
        
        return arr;
    }
    
    public static void exList() {
        // 6, 5, 7, 9, 4, 6, 10, 4,
        List<Integer> arr = Lists.newArrayList(6, 5, 7, 9, 4, 6, 10, 4, 8, 3, 12, 2, 2, 3, 7, 5, 9, 12, 9, 12, 12, 10, 1, 8, 11, 11, 12, 4, 11, 7, 10, 4, 2, 4, 11, 5, 6, 10);
        System.out.println("--->"+arr);
        arr.sort((Integer i1, Integer i2)->{
            if (i1.intValue() < i2.intValue()) {
                return -1;
            }/* else if(i1.intValue() == i2.intValue()) {
                return 0;
            } */else {
                return 1;
            }
        });
        System.out.println("--->"+arr);
    }

    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    private static final int MIN_MERGE = 32;

}
