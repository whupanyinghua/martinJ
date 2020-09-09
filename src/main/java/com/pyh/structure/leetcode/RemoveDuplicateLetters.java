package com.pyh.structure.leetcode;

import java.util.Stack;

/**
 * 类RemoveDuplicateLetter的实现描述：
 * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "bcabc"
 * 输出: "abc"
 * 示例 2:
 *
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-9-9 19:03
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        String source = "bcabc";
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("bcac"));
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
    }

    public String removeDuplicateLetters(String source) {
        // 栈保存非重复的字符
        Stack<Character> stack = new Stack<>();
        // ascii码，直接使用256长度的数组保存当前字符出现的次数
        int[] let = new int[256];
        boolean[] in = new boolean[256];
        int len = source.length();
        for(int i=0;i<len;i++) {
            // 数组初始化默认是0
            let[source.charAt(i)]++;
        }

        for(int i=0;i<len;i++) {
            char c = source.charAt(i);
            // 遍历到当前的字符，先将let数组中对应字符的个数进行-1操作
            let[c]--;
            if(in[c]){
                // 字符在栈中，则无需任何处理
                continue;
            }
            char d;
            while(!stack.empty() && (d=stack.peek())>c) {
                // 栈顶元素大于当前元素
                if(let[d]>0) {
                    // 后边还有相同的栈顶元素，则可以将栈顶元素pop掉，并且将对应的in位置置为false
                    stack.pop();
                    in[d]=false;
                } else {
                    // 后边没有栈顶的元素了，则栈顶元素不能pop，另外字符c只能在当前栈顶元素后入栈，也就是字符c出现在栈顶元素的后边
                    break;
                }
            }

            // 将当前元素入栈
            stack.push(c);
            in[c] = true;
        }

        // 此时栈中的元素是字典逆序的，排序较小的元素再栈底
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.reverse().toString();
    }
}
