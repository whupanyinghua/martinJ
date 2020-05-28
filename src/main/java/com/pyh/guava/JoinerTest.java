package com.pyh.guava;

import com.google.common.base.Joiner;

/**
 * 类JoinerTest的实现描述：@TODO
 *
 * @author panyinghua 2020-4-14 10:23
 */
public class JoinerTest {

    public static void main(String[] args) {
        String testJoin = Joiner.on("").join("String1", "String2");
        System.out.println("testJoin is "+testJoin);
    }
}
