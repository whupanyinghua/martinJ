package com.pyh.structure.leetcode.dp;

/**
 * 类GSS的实现描述：给定一个数组，求数组中最大和的子数组，并返回该和
 *
 * @author panyinghua 2021-4-9 18:02
 */
public class GSS {

    /**
     * 求数组中连续子数组最大和
     * @param nums
     * @return
     */
    public static int findGreatestSumOfSubArray(int[] nums) {
        if(null == nums) return 0;
        if(1 == nums.length) return nums[0];


        int len = nums.length;
        // dp[i]的定义是，以nums[i]为结尾的最大连续子数组之和
        int[] dp = new int[len];
        // base case (初始化时，dp[i]=nums[i])
        for(int i=0;i<len;i++) {
            dp[i]=nums[i];
        }

        for(int i=1;i<len;i++) {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
        }

        int res = dp[0];
        for(int i=0;i<len;i++) {
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
