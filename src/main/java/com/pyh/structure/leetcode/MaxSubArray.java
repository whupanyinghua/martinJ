package com.pyh.structure.leetcode;

/**
 * 类MaxSubArray的实现描述：
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *  
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-22 11:22
 */
public class MaxSubArray {

    /**
     * 如果在i之前所有子数组的最大值记为sum[i]，包含a[i]的最大值记为sum2[i]，此处肯定有sum[i]>=sum2[i]
     * 那么考虑i+1的情况
     *
     * 分两种情况
     * a[i+1]>=0
     * sum[i+1] = max(sum[i],a[i+1],sum2[i]+a[i+1])
     * sum2[i+1] = max(sum2[i]+a[i+1],a[i+1])
     *
     * a[i+1]<0
     * 此时a[i+1]对于结果没影响？
     * sum[i+1] = max(sum[i],a[i+1])
     * sum2[i+1] = max(sum2[i]+a[i+1],a[i+1])
     *
     * 总结发现，sum[i+1]在a[i+1]<0条件下 max(sum[i],a[i+1])等价于max(sum[i],a[i+1],sum2[i]+a[i+1])
     *
     * 那规律如下：
     * sum[i+1] = max(sum[i],a[i+1],sum2[i]+a[i+1])
     * sum2[i+1] = max(sum2[i]+a[i+1],a[i+1])
     *
     * 另sum[0]=a[0],sum1[0]=a[0],循环一个数组即可求解
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(null == nums) return 0;
        if(nums.length==1) return nums[0];


        int s1 = nums[0],s2=nums[0];
        int result = s1;
        for(int i=1;i<nums.length;i++){
            s1 = max(max(s1,nums[i]), s2+nums[i]);
            s2 = max(s2+nums[i],nums[i]);
            if(s1>result) {
                result = s1;
            }
        }
        return result;
    }

    public int max(int a1, int a2) {
        if(a1>a2) {
            return a1;
        } else {
            return a2;
        }
    }

    /**
     * 动态规划解析：
     * 状态定义： 设动态规划列表 dp ，dp[i] 代表以元素 nums[i] 为结尾的连续子数组最大和。
     *
     * 为何定义最大和 dp[i] 中必须包含元素 nums[i] ：保证 dp[i] 递推到 dp[i+1] 的正确性；如果不包含 nums[i] ，递推时则不满足题目的 连续子数组 要求。
     * 转移方程： 若 dp[i-1] ≤0 ，说明 dp[i - 1]对 dp[i]产生负贡献，即 dp[i-1] + nums[i] 还不如 nums[i]本身大。
     *
     * 当 dp[i - 1] > 0 时：执行 dp[i] = dp[i-1] + nums[i]；
     * 当 dp[i - 1] ≤0 时：执行 dp[i] = nums[i]；
     * 初始状态： dp[0] = nums[0]，即以 nums[0] 结尾的连续子数组最大和为 nums[0]。
     *
     * 返回值： 返回 dp 列表中的最大值，代表全局最大值。
     *
     * @param nums
     * @return
     */
    public int maxSubArrayGF(int[] nums) {
        if(null == nums) return 0;
        if(nums.length==1) return nums[0];

        int result = nums[0],tmp = nums[0];
        for(int i=1;i<nums.length;i++) {
            tmp = Math.max(tmp+nums[i], nums[i]);
            result = Math.max(result, tmp);
        }

        return result;
    }
}
