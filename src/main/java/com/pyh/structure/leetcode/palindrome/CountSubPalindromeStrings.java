package com.pyh.structure.leetcode.palindrome;

/**
 * 类CountSubPalindromeStrings的实现描述：
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-19 14:43
 */
public class CountSubPalindromeStrings {

    public int countSubstrings(String s) {
        if(null == s) return 0;
        int len = s.length();
        if(len<=1) return 1;
        int result = 0;
        for(int i=0;i<len;i++) {
            for(int j=i;j<len;j++) {
                if(isPalindrome(s, i, j)) {
                    result++;
                }
            }
        }

        return result;
    }

    public boolean isPalindrome(String s, int begin, int end) {
        if(begin==end) return true;

        int left = begin;
        int right = end;
        while(left<=right) {
            if(s.charAt(left)!=s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
