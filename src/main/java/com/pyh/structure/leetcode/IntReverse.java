package com.pyh.structure.leetcode;

/**
 * 类IntReverse的实现描述：@TODO
 *
 * @author panyinghua 2020-8-5 19:16
 */
public class IntReverse {

    public static void main(String[] args) {
        int a = -2147483412;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(new IntReverse().reverse(a));
    }

    public int reverse(int x) {
        int target = 0;
        while(x!=0) {
            int modNum = x%10;
            x = x/10;
            // 计算本轮之前，先使用上一轮的结果判断是否会溢出
            // 处理正数溢出,7是最大正整数最后一位，Integer.MAX_VALUE=2147483647
            if(target>Integer.MAX_VALUE/10 || (target==Integer.MAX_VALUE/10&&modNum>7)) {
                return 0;
            }
            // 处理负数溢出，8是最小负整数的最后一位，Integer.MIN_VALUE=-2147483648
            if(target<Integer.MIN_VALUE/10 || (target==Integer.MIN_VALUE/10&&modNum<-8)) {
                return 0;
            }
            target = target*10+modNum;

        }

        return target;
    }
}
