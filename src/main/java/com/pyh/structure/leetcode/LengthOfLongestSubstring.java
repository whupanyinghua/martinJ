package com.pyh.structure.leetcode;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 类LengthOfLongestSubstring的实现描述：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-4 15:24
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "aaaaaaa";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left=0;
        int right=0;
        int maxRes = 0;
        int currentRes = 0;
        Map<Character, Integer> smap = new HashMap<>();

        while(right < len) {
            char rc = s.charAt(right);
            right++;
            // 扩大滑动窗口
            if(!smap.containsKey(rc)) {
                smap.put(rc, 1);
                currentRes++; //
                if(currentRes>maxRes) {
                    maxRes = currentRes;
                }
            } else {
                // rc字符重复了，
                smap.put(rc, smap.get(rc)+1);
            }

            // 缩小滑动窗口
            while(right-left>currentRes) {
                char lc = s.charAt(left);
                left++;
                int lcn = smap.getOrDefault(lc,0);
                if(lcn>1) {
                    smap.put(lc, lcn-1);
                } else if(lcn==1) {
                    currentRes--;
                    smap.remove(lc);
                }
            }

        }


        return maxRes;
    }
}
