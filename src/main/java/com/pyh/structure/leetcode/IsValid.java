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
 *给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
 *
 * 从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："())"
 * 输出：1
 * 示例 2：
 *
 * 输入："((("
 * 输出：3
 * 示例 3：
 *
 * 输入："()"
 * 输出：0
 * 示例 4：
 *
 * 输入："()))(("
 * 输出：4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
 *
 * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
 * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
 * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
 *
 * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 *
 * 请你返回让 s 平衡的最少插入次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "(()))"
 * 输出：1
 * 解释：第二个左括号有与之匹配的两个右括号，但是第一个左括号只有一个右括号。我们需要在字符串结尾额外增加一个 ')' 使字符串变成平衡字符串 "(())))" 。
 * 示例 2：
 *
 * 输入：s = "())"
 * 输出：0
 * 解释：字符串已经平衡了。
 * 示例 3：
 *
 * 输入：s = "))())("
 * 输出：3
 * 解释：添加 '(' 去匹配最开头的 '))' ，然后添加 '))' 去匹配最后一个 '(' 。
 * 示例 4：
 *
 * 输入：s = "(((((("
 * 输出：12
 * 解释：添加 12 个 ')' 得到平衡字符串。
 * 示例 5：
 *
 * 输入：s = ")))))))"
 * 输出：5
 * 解释：在字符串开头添加 4 个 '(' 并在结尾添加 1 个 ')' ，字符串变成平衡字符串 "(((())))))))" 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-insertions-to-balance-a-parentheses-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-14 12:13
 */
public class IsValid {


    /**
     * 判断字符串是否一个有效的包含()/[]/{}的字符串
     * https://leetcode-cn.com/problems/valid-parentheses/
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
     * https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
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
     * 相对于minAddToMakeValid，这里的字符串合法的定义是，一个左括号对应两个右括号
     * (()(( )))) ())
     * (( ))))))) ())
     * https://leetcode-cn.com/problems/minimum-insertions-to-balance-a-parentheses-string/
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        // 左括号的需求
        int leftNeed = 0;
        // 右括号的需求
        int rightNeed = 0;
        // 计算过程中插入左括号或者右括号的计数
        // 左括号或者右括号需求数，也就是当前插入括号数（在后边的判断中，左括号右括号都可能在不同的分支进行添加，因此不使用leftNeed变量）
        int res = 0;

        for(char c : s.toCharArray()) {
            if(c=='(') {
                // 当遇到左括号的时候，意味着需要两个个右括号
                rightNeed += 2;

                // 难点:
                // rightNeed是奇数表示需要在本次的左括号之前，肯定是需要补充一个右括号，来把之前的某个左括号进行匹配
                // (rightNeed&1)==1等效于rightNeed%2=1
                if((rightNeed&1)==1) {
                    // 插入右括号,当前插入括号数res+1操作，表示当前总计需要插入的括号的数量
                    res++;
                    // 此处需要满足一个左括号两个右括号的规则，需要将右括号的需求-1
                    // 插入一个右括号之后，因为一个左括号匹配的是两个右括号，其中一个是新插入的右括号，另外一个从右括号的需求数中扣减，那么需要将右括号的需求数-1
                    rightNeed -= 1;
                }
            }
            if(c==')') {
                // 当遇到右括号的时候，把rightNeed进行-1操作
                rightNeed -= 1;
                // 什么时候添加左括号？
                // rightNeed=-1表示右括号太多，需要插入左括号
                // 意味着当前右括号数量是多于左括号的需求数的，那么此时需要插入一个左括号，然后对右括号的需求数进行变更
                if(rightNeed==-1) {
                    // 插入左括号，对插入括号数res+1操作
                    res++;
                    // 插入一个左括号之后，需要两个右括号进行匹配，那么一个是当前的右括号字符，另外一个记到右括号的需求数中，将rightNeed置为1
                    rightNeed = 1;
                }
            }
        }

        // 左括号的需求+右括号的需求就是最后的结果
        return res+rightNeed;
    }
}
