package com.pyh.structure.leetcode;

/**
 * 类IntReverse的实现描述：
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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

    /**
     * 其实不需要特别区分正数负数，取模，除法都可以正常区分正数负数
     * @param x
     * @return
     */
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
