package com.pyh.structure.leetcode;

import com.pyh.structure.sort.Sorts;

/**
 * 类MinMeetingRooms的实现描述：会议室问题
 * 假如一天之内有多个会议，每个会议的开始时间结束时间可能都不一样，每个会议都需要单独的一个会议室，也就是说每一个会议期间必须单独占用一个会议室
 * 求解至少需要预定多少个会议室才能满足会议的顺利召开？
 *
 * 参考 https://mp.weixin.qq.com/s/YVqd4J1GVnh25FKk8FUYzA
 * @author panyinghua 2021-7-16 15:58
 */
public class MinMeetingRooms {


    /**
     * 二位数组表示会议的开始时间与结束时间，这里将开始时间结束时间都简化成了整数
     * meetings[i][0]表示第i个会议的开始时间，meetings[i][1]表示第i个会议的结束时间
     * @param meetings
     * @return 返回至少需要预约的会议室个数
     */
    public int minMeetingRooms(int[][] meetings) {

        int meetingNum = meetings.length;
        // begin[i]表示会议i的开始时间
        // end[i]表示会议i的结束时间
        int[] begin = new int[meetingNum];
        int[] end = new int[meetingNum];
        for(int i=0;i<meetingNum;i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }

        // 对开始时间结束时间排序，从小到大
        Sorts.quickSortM(begin);
        Sorts.quickSortM(end);

        // 最终结果
        int result = 0;
        // 扫描过程中的计数器
        int res = 0;
        int i=0;
        int j=0;
        while(i<meetingNum && j<meetingNum) {
            // 精髓点
            // 碰到begin点，则对结果+1
            // 碰到end点，则对结果-1
            if(begin[i]<end[j]) {
                res++;
                i++;
            } else {
                res--;
                j++;
            }
            result = Math.max(result,res);
        }

        return result;
    }
}
