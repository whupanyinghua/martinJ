package com.pyh.guavatest;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 类GuavaListTest的实现描述：@TODO
 *
 * @author panyinghua 2020-12-23 17:58
 */
public class GuavaListTest {
    public static void main(String[] args) {
        List<Integer> result = Lists.newArrayList(new Integer(1),new Integer(2), null);
        System.out.println(result);
    }
}
