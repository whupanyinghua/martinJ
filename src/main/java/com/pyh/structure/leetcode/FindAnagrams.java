package com.pyh.structure.leetcode;

import java.util.*;

/**
 * 类FindAnagrams的实现描述：
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-4 18:57
 */
public class FindAnagrams {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p="abc";
        System.out.println(new FindAnagrams().findAnagrams(s,p));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        Map<Character, Integer> needs = new HashMap<>();
        for(int i=0;i<p.length();i++) {
            needs.put(p.charAt(i), needs.getOrDefault(p.charAt(i),0)+1);
        }

        int plen = p.length();
        int left=0;
        int right=0;
        int valid=0;
        int needSize = needs.size();
        Map<Character, Integer> smap = new HashMap<>();
        while(right<s.length()) {
            // 扩大窗口
            char rc = s.charAt(right);
            right++;
            // 更新窗口中的数据
            if(needs.containsKey(rc)) {
                smap.put(rc, smap.getOrDefault(rc,0)+1);
                if(smap.get(rc).intValue()==needs.get(rc).intValue()) {
                    valid++;
                }
            }

            // 缩小窗口
            // 因为是寻找字母异位的子串，子串，那么找出来的子串的长度必须是等于p的长度的，所以此处取right-left>=plen做为缩小窗口的时机
            while(right-left>=plen) {
                if(valid==needSize) {
                    res.add(left);
                }
                char lc = s.charAt(left);
                left++;
                // 更新窗口中的数据
                if(needs.containsKey(lc)) {
                    int lcn = smap.getOrDefault(lc, 0);
                    if(lcn==needs.get(lc).intValue()) {
                        valid--;
                    }
                    if(lcn>1) {
                        smap.put(lc, lcn - 1);
                    } else if(lcn==1) {
                        smap.remove(lc);
                    }
                }
            }
        }

        return res;
    }
}
