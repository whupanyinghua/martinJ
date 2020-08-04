package com.pyh.structure.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckInclusion {

    public static void main(String[] args) {
        String s1="adc";
        String s2="dcda";
        System.out.println(new CheckInclusion().checkInclusion(s1,s2));
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> s1Map = new HashMap<>();
        for(int i=0;i<s1.length();i++) {
            char c = s1.charAt(i);
            s1Map.put(c, s1Map.getOrDefault(c,0)+1);
        }

        int s1Size = s1Map.size();
        int left = 0, right=0, valid=0;
        int s2Len = s2.length();
        Map<Character, Integer> s2Map = new HashMap<>();
        while(right<s2.length()) {
            char c = s2.charAt(right);
            right++;// 右移right边界
            if(s1Map.containsKey(c)) {
                s2Map.put(c, s2Map.getOrDefault(c,0)+1);
                if(s2Map.get(c).intValue()==s1Map.get(c)) {
                    valid++;
                }
            } else {
                // 当前字符不在s1map中，那代表在之前的所有元素中不能满足条件，右移left边界
                left = right;
                // 清空s2Map与valid，从新的right位置开始往后查找
                s2Map.clear();
                valid=0;
            }

            while(valid==s1Size && left<right) {
                char lc = s2.charAt(left);
                int lcn = s2Map.get(lc).intValue();
                if(lcn>s1Map.get(lc).intValue()) {
                    left++;
                    if(lcn==1) {
                        s2Map.remove(lc);
                    } else {
                        s2Map.put(lc, lcn - 1);
                    }
                } else {
                    break;
                }
            }

            if(valid==s1Size && s1.length()==right-left) {
                return true;
            }
        }

        return false;
    }
}
