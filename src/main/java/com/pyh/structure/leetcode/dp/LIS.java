package com.pyh.structure.leetcode.dp;

import java.util.Arrays;

/**
 * 类LIS的实现描述：最长递增子序列，注意是子序列，不是子数组，元素之间可以不用连续
 *
 * @author panyinghua 2021-4-8 19:54
 */
public class LIS {

    public static void main(String[] args) {
        int[] nums = {1,4,3,4,2};
        System.out.println("nums is: " + Arrays.toString(nums));
        System.out.println("LIS is: " + lenOfLIS(nums));
    }

    /**
     * 求出数组中最长的递增子序列
     * @param nums
     * @return
     */
    public static int lenOfLIS(int[] nums) {
        // 定义dp数组
        // dp[i] 表示的是以当前元素nums[i]结尾的最长递增子序列的长度
        // dp[i]如何求值？
        // 从[0~i)开始遍历，假设遍历的坐标是j，那么如果nums[j]<nums[i]，那么dp[i]就可以在dp[j]的基础上+1，因为是遍历取值的，
        // 每次碰到可以取值的时候都需要跟原来的dp[i]进行比对取较大值
        // 即 if(nums[j]<nums[i]) 则 dp[i] = max(dp[i],dp[j]+1)

        // base case
        // 因为最长递增子序列可以只由当前一个元素构成，所以可以把整个dp数组都初始化为1
        int len = nums.length;
        int[] dp = new int[len];
        for(int i=0;i<len;i++) {
            dp[i] = 1;
        }

        // 开始处理dp数组
        for(int i=1;i<len;i++) {
            for(int j=0;j<i;j++) {
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        // 返回dp数组中最大的值即可
        int res = 0;
        for(int i=0;i<len;i++) {
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
