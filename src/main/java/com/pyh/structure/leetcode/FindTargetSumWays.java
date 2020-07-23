package com.pyh.structure.leetcode;

/**
 * 类FindTargetSumWays的实现描述：
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-23 14:33
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        int[] a = {7,9,3,8,0,2,4,8,3,9};
        int s = 1;
        System.out.println(new FindTargetSumWays().findTargetSumWays(a, s));
    }


    public int findTargetSumWays(int[] nums, int S) {
        if(null == nums || nums.length==0) return 0;
        if(1==nums.length) return nums[0]==Math.abs(S)?1:0;

        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
        }

        // 目标和（绝对值）大于数组总和，肯定没有满足条件的解
        if(Math.abs(S)>sum) return 0;
        // 如果S+sum没法整除2，意味着没有满足条件的解
        if((S+sum)%2!=0) return 0;

        int dpl = (S+sum)/2;
        int[] dp = new int[dpl+1];
        dp[0] = 1;
        for(int i=1;i<=nums.length;i++) {
            for (int j = dpl; j >= 0; j--) {
                if(j>=nums[i-1]) {
                    dp[j] = dp[j] + dp[j - nums[i-1]];
                }
            }
        }

        return dp[dpl];
    }

}
