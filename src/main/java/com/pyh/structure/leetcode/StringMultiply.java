package com.pyh.structure.leetcode;

/**
 * 类StringMultiply的实现描述：
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-13 18:03
 */
public class StringMultiply {

    public static void main(String[] args) {
        System.out.println(new StringMultiply().multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        if(num1.charAt(0)=='0' || num2.charAt(0)=='0') {
            return new String("0");
        }
        if(num1.length()<=num2.length()) {
            return multiplyInternal(num1, num2);
        } else {
            return multiplyInternal(num2,num1);
        }
    }


    public String multiplyInternal(String num1, String num2) {
        // num1的长度小于或者等于num2的长度
        // 目标的最大长度等于num1+num2的长度之和
        int len1=num1.length();
        int len2=num2.length();
        char[] dest = new char[len1+len2];
        int k = 0;//保存进位的数值
        int endIndex=len1+len2-1;//保存目标字符数组的位置
        while(endIndex>=0) {
            dest[endIndex--] = '0';
        }
        int beginIndex = 0;
        endIndex=len1+len2-1;
        for (int j=len1-1; j>=0; j--) {
            int cur = endIndex;
            for(int i=len2-1;i>=0;i--) {
                int tmp = (num1.charAt(j)-48)*(num2.charAt(i)-48)+(dest[cur]-48)+k;
                dest[cur--] = (char) (tmp%10+48);
                k = tmp / 10;
            }
            while(k>0) {
                int tmp = (char)(k+(dest[cur]-48));
                dest[cur--] = (char) (tmp%10+48);
                k = tmp / 10;
            }
            // 重置下一轮循环的进位以及下一轮循环的beginIndex,endIndex
            k=0;
            beginIndex = cur+1;
            endIndex--;
        }


        // 处理最后的进位
        char[] result ;
        if(0==beginIndex) {
            result = dest;
        } else {
            result = new char[len1+len2-1-beginIndex+1];
            System.arraycopy(dest, beginIndex, result, 0, result.length);
        }

        return new String(result);

    }
}
