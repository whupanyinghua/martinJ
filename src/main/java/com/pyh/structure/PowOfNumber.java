package com.pyh.structure;

/**
 * 类PowOfNumber的实现描述：关于数字的2^N算法
 *
 * @author panyinghua 2020-4-29 9:53
 */
public class PowOfNumber {
    public static void main(String[] args) {
        int testNumber = 1000;

        System.out.println(String.format("minUpPowOf-距离%d之上最近的2^N的数字是%d",testNumber, minUpPowOf(testNumber)));
        System.out.println(String.format("tableSizeFor-距离%d之上最近的2^N的数字是%d",testNumber, tableSizeFor(testNumber)));
    }

    /**
     * 返回距离给定参数最近的2^N数字
     * @param testNumber 给定数字
     * @return
     */
    private static int minUpPowOf(int testNumber) {
        if(testNumber==0)
            return 1;
        if(testNumber==1) {
            return 1;
        }
        // -1是为了传入参数是2^N的时候返回2^N本身
        testNumber = testNumber-1;
        // 将最高1之后所有位数全部置为1
        testNumber = testNumber | (testNumber>>>1);
        testNumber = testNumber | (testNumber>>>2);
        testNumber = testNumber | (testNumber>>>4);
        testNumber = testNumber | (testNumber>>>8);
        testNumber = testNumber | (testNumber>>>16);
        return testNumber+1;
    }

    /**
     * 该方法来自于ConcurrentHashMap
     * @param c
     * @return
     */
    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }


    class test {

    }
}
