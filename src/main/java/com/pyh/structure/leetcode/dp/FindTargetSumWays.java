package com.pyh.structure.leetcode.dp;

/**
 * 类FindTargetSumWays的实现描述：
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号+和-。对于数组中的任意一个整数，你都可以从+或-中选择一个符号添加在前面。
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
 * 可以参见 KnapsackProblem的背包问题变体：kp01_ziji方法
 *
 * @author panyinghua 2020-7-23 14:33
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        int[] a = {7,9,3,8,0,2,4,8,3,9};
        int s = 1;
        System.out.println(new FindTargetSumWays().findTargetSumWays(a, s));
    }


    /**
     * 动态规划：
     * 可以将问题转化为：
     * 将数组分为A，B两块，A是+号的一组，B是-号的一组，sum是整个数组所有的数据和,那么有如下关系
     * SA-SB=target
     * SA+SB=sum
     * 推导出2SA=target+sum，即SA=(target+sum)/2
     * 那么可以将问题转化为：在数组中求一个集合A，满足sum(A)=(target+sum)/2，这个就是很明显的背包问题了。
     * 动态规划解法：
     * dp(i,j)=k表示前i个元素中组装出j容量的解法有k种，那么可以推导
     * 分两种可能，
     * 1，第i个元素不装进背包，那么有dp(i-1,j)
     * 1，第i个元素装进背包，那么有dp(i-1,j-nums[i-1])，其中nums[i-1]就是第i个元素的值
     * 得出
     * dp(i,j) = dp(i-1,j) + dp(i-1,j-nums[i-1])
     *
     * 需要创建一个dp[nums.length+1][(target+sum)/2+1]的二维数组来保存状态
     *
     * 优化技巧：
     * 观察推导公式，发现计算dp(i,j)的时候只跟上一层dp(i-1,j)与上一层dp(i-1,j-nums[i-1]) 状态相关，
     * 其实是可以将二位数组优化为一维数组dp[j]，在计算dp[j]的时候，还没有更新dp[j]回去的情况下dp[j]保存的就是上一轮即i-1的结果，
     * 此时考虑dp[j]是从左到右，还是从右到左进行计算，我们考虑在动态规划推导公式中，计算dp(j)涉及上一层dp(j-nums[i-1])中，
     * j-nums[i-1]<=j，那么我们就需要从右往左计算，在计算dp(j)的时候dp(j-nums[i-1])已经还是上一轮循环的结果。
     *
     * dp[0]=1表示组装出结果是0的集合只有一个，那就是一个元素都不选
     * @param nums
     * @param S
     * @return
     */
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
