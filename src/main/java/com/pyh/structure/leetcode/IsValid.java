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


    /**
     * 判断字符串是否一个有效的包含()/[]/{}的字符串
     * @param s
     * @return
     */
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

    /**
     * 已知一个字符串s，其中只包含(|),如何插入最少的(|)使之变成合法的字符串，也就是左括号右括号必须一一成对出现
     * 如 "())(" 需要加入一个左括号一个右括号才能标称合法的字符串 "(())()"
     * @param s
     * @return
     */
    public int fill(String s) {
        // 左括号的需求
        int leftNeed = 0;
        // 右括号的需求
        int rightNeed = 0;

        for(char c : s.toCharArray()) {
            if(c=='(') {
                // 当遇到左括号的时候，意味着需要一个右括号
                rightNeed += 1;
            }
            if(c==')') {
                // 当遇到右括号的时候，把rightNeed进行-1操作
                rightNeed -= 1;
                // 如果rightNeed=-1的时候意味着什么
                // 表明此时右括号多了一个，需要插入一个左括号
                if(rightNeed == -1) {
                    leftNeed += 1;
                    // 插入左括号之后，把右括号的需求变为0
                    rightNeed = 0;
                }
            }
        }

        // 左括号的需求+右括号的需求就是最后的结果
        return leftNeed+rightNeed;
    }

    /**
     * 相对于fill1，这里的字符串合法的定义是，一个左括号对应两个右括号
     * (()(( )))) ())
     * (( ))))))) ())
     * @param s
     * @return
     */
    public int fill2(String s) {
        // 左括号的需求
        int leftNeed = 0;
        // 右括号的需求
        int rightNeed = 0;
        // 计算过程中插入左括号或者右括号的计数
        int res = 0;

        for(char c : s.toCharArray()) {
            if(c=='(') {
                // 当遇到左括号的时候，意味着需要两个个右括号
                rightNeed += 2;

                // 难点:
                // rightNeed是奇数表示需要在本次的左括号之前，肯定是需要补充一个右括号，来把之前的某个左括号进行匹配
                if(rightNeed%2==1) {
                    // 插入右括号
                    res++;
                    // 此处需要满足一个左括号两个右括号的规则，需要将右括号的需求-1
                    rightNeed -= 1;
                }
            }
            if(c==')') {
                // 当遇到右括号的时候，把rightNeed进行-1操作
                rightNeed -= 1;
                // 什么时候添加左括号？
                // rightNeed=-1表示右括号太多，需要插入左括号
                if(rightNeed==-1) {
                    res++;
                    // 插入一个左括号之后，因为一个左括号对应两个右括号，所以讲右括号从-1变更为1，与插入的左括号匹配
                    rightNeed = 1;
                }
            }
        }

        // 左括号的需求+右括号的需求就是最后的结果
        return res+rightNeed;
    }
}
