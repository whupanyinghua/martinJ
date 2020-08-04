package com.pyh.structure.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 类MinWindow的实现描述：
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 *  
 *
 * 示例：
 *
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 *  
 *
 * 提示：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-3 16:02
 */
public class MinWindow {

    /**
     * 滑动窗口解法思路概括：
     * 1、我们在字符串S中使双指针中的左右指针技巧，初始化left=right=0，把索引左闭右开区间[left,right)称为一个「窗口」。
     * 2、我们先不断地增加right指针扩大窗口[left,right)，直到窗口中的字符串符合要求（包含了T中的所有字符）。
     * 3、此时，我们停止增加right，转而不断增加left指针缩小窗口[left,right)，直到窗口中的字符串不再符合要求（不包含T中的所有字符了）。
     * 同时，每次增加left，我们都要更新一轮结果。
     * 4、重复第2和第3步，直到right到达字符串S的尽头。
     * 在这个解法中只要思考一下几个问题，按照问题的解法填充
     * 1、当移动right扩大窗口，即加入字符时，应该更新哪些数据？
     * 2、什么条件下，窗口应该暂停扩大，开始移动left缩小窗口？
     * 3、当移动left缩小窗口，即移出字符时，应该更新哪些数据？
     * 4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>();
        for(int i=0;i<t.length();i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i),0)+1);
        }
        int tsize = tmap.size();
        int left=0;
        int right=0;

        Map<Character, Integer> smap = new HashMap<>();
        int validSize = 0;
        int subBegin = 0; // 所求子串的起始位置
        int subLen = Integer.MAX_VALUE;//所求子串的长度
        // 滑动窗口解法，遵循左开右闭规则
        while(right<s.length()) {
            // 当前字符加入窗口
            char c = s.charAt(right);
            if(tmap.containsKey(c)) {
                smap.put(c, smap.getOrDefault(c,0)+1);
                if(smap.get(c).intValue() == tmap.get(c).intValue()) {
                    // 在当前字符串中找到了满足t字符串中c字符的条件，则将validSize+1
                    validSize++;
                }
            }
            right++;// 窗口右边界右移

            // 考虑移动左边边界
            while(validSize==tsize) {
                // 此时表示窗口已经满足了条件，更新下子串的结果
                if(right-left < subLen) {
                    subLen = right-left;
                    subBegin = left;
                }

                // 当前将要移出窗口的字符
                char lc = s.charAt(left);
                if(tmap.containsKey(lc)) {
                    // lcn不可能出现获取不到值的情况，因为下方对key进行移除是完全按照字符统计的个数来进行
                    int lcn = smap.get(lc).intValue();
                    if(lcn == tmap.get(lc).intValue()) {
                        // 当前字符移出之后，不再满足条件，此处需要注意的是，如果该字符串存在多个，smap中存储的数值是比tmap中的大，所以只能在等于的时候进行一次validSize--操作即可
                        validSize--;
                    }
                    if(lcn>1) {
                        smap.put(lc, lcn-1);
                    } else if(lcn==1) {
                        smap.remove(lc);
                    }
                }
                left++;// 窗口左边界右移
            }


        }

        return subLen == Integer.MAX_VALUE? "" : s.substring(subBegin, subBegin+subLen);
    }
}
