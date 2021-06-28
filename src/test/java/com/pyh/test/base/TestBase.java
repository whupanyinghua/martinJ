package com.pyh.test.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * 类TestBase的实现描述：测试类基础类
 *
 * @author panyinghua 2021-6-28 18:14
 */
public class TestBase {

    @Test
    public void test() {

        System.out.println("test junit.");
        Assert.assertTrue("junit don't work.", true);
    }
}
