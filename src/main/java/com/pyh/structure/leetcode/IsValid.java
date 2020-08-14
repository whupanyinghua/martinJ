package com.pyh.structure.leetcode;

import java.util.Stack;

/**
 * 类IsValid的实现描述：
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-14 12:13
 */
public class IsValid {

    public boolean isValid(String s) {
        if(null == s) return false;
        int length = s.length();
        if(length<=0) return true;
        if(length%2!=0) return false;
        int i=0;
        Stack<Character> stack = new Stack();
        while(i<length) {
            char cur = s.charAt(i);
            if(cur=='(' || cur=='[' || cur=='{') {
                // 左括号则往下走
                stack.push(cur);
            } else {
                // 右括号考虑匹配
                if(stack.empty()) {
                    // 当前栈为空，直接返回失败
                    return false;
                }
                char pre = stack.pop();
                if((pre=='('&&cur==')') || (pre=='['&&cur==']') || (pre=='{'&&cur=='}')) {
                    // 可以匹配括号
                } else {
                    // 匹配失败，直接返回false
                    return false;
                }
            }
            i++;
        }
        if(!stack.empty()) {
            return false;
        }

        return true;
    }
}
