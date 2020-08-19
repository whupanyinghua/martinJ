package com.pyh.structure.leetcode.dp;

/**
 * 类CoinChangeTwo的实现描述：
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 *
 *  
 *
 * 示例 1:
 *
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 *
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 *
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-27 17:35
 */
public class CoinChangeTwo {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1,2,5};
        System.out.println(new CoinChangeTwo().change(amount, coins));

        amount = 3;
        coins = new int[]{2};
        System.out.println(new CoinChangeTwo().change(amount, coins));

        amount = 10;
        coins = new int[]{10};
        System.out.println(new CoinChangeTwo().change(amount, coins));
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        // 动态规划解法
        dp(dp, amount, coins);

        return dp[amount];
    }

    /**
     * 动态规划：
     * 考虑状态方程
     * 变量:金额，因为硬币总类是有限的，硬币个数无线，因此硬币不算变量
     * 因为这里的求解是求满足条件的组合数，不太像其他的求极值问题，可以考虑变换下角度去思考动态规划的框架
     * 思考动态规划状态转移方程，思考如何求解该问题的子问题？
     * nums[]表示的是硬币数组，n表示计算的总金额
     * n=dp(i,j) 表示的是在使用前i种硬币金额可以组装成金额j一共有n种解法
     * 那i-1的情况是如何呢？
     * 注意-第i种硬币的值是nums[i-1]
     * dp(i,j) = dp(i-1,j)+dp(i-1,j-nums[i-1])+dp(i-1,j-2*nums[i-1])+dp(i-1,j-3*nums[i-1])+dp(i-1,j-4*nums[i-1])+...+dp(i-1,j-k*nums[i-1])，其中k为j/nums[i-1]的最大正整数
     * 这里这个等式的含义是说，在加入第i个硬币的时候，dp(i,j)的组合应该是由一下结果组成：第i个硬币出现0次+第i个硬币出现1次+第i个硬币出现2次+...+第i个硬币出现k次
     *
     * 数学方式简化方程
     * dp(i,j) = dp(i-1,j)+dp(i-1,j-nums[i-1])+dp(i-1,j-2*nums[i-1])+dp(i-1,j-3*nums[i-1])+dp(i-1,j-4*nums[i-1])+...+dp(i-1,j-k*nums[i-1])， 公式1
     * 令j=j-nums[i-1]代入公式1得
     * dp(i,j-nums[i-1]) = dp(i-1,j-nums[i-1])+dp(i-1,j-2*nums[i-1])+dp(i-1,j-3*nums[i-1])+dp(i-1,j-4*nums[i-1])+dp(i-1,j-5*nums[i-1])+...+dp(i-1,j-k*nums[i-1])+dp(i-1,j-(k+1)*nums[i-1]) 公式2
     * k为j/nums[i-1]的最大正整数，因此j-(k+1)*nums[i-1]<0，因此dp(i-1,j-(k+1)*nums[i-1])=0表示无意义，公式2可以优化为
     *
     * dp(i,j)           = dp(i-1,j)+dp(i-1,j-nums[i-1])+dp(i-1,j-2*nums[i-1])+dp(i-1,j-3*nums[i-1])+dp(i-1,j-4*nums[i-1])+...+dp(i-1,j-k*nums[i-1])， 公式1
     * dp(i,j-nums[i-1]) = dp(i-1,j-nums[i-1])+dp(i-1,j-2*nums[i-1])+dp(i-1,j-3*nums[i-1])+dp(i-1,j-4*nums[i-1])+dp(i-1,j-5*nums[i-1])+...+dp(i-1,j-k*nums[i-1]) 公式2
     *
     * 公式1-公式2可得
     * dp(i,j)-dp(i,j-nums[i-1])=dp(i-1,j) 即
     * dp(i,j)=dp(i,j-nums[i-1]) + dp(i-1,j) -- 状态转移方程
     * 有了状态转移方程，就可以写代码了
     *
     * 二维数组优化成一维数组的套路
     * dp(i,j)=dp(i,j-nums[i-1]) + dp(i-1,j)
     *
     * 只取j这个维度来看，我们使用变量i左外外层循环，在遍历j的时候，要考虑是从左往右还是从右往左遍历
     * 计算dp(i,j)的时候跟本层的dp(i,j-nums[i-1])以及上一层的的dp(i-1,j)相关，
     * 上一层的dp(i-1,j)我们无需考虑，因为在计算本层的j并且还没有覆盖的时候，当前dp[j]中就是上一轮的dp[j]的结果
     * 本层的dp(i,j-nums[i-1])，因为j-nums[i-1]<j，所以为了保证结果的正确性，那么我们在计算dp(i,j)的时候是要确保dp(i,j-nums[i-1])已经计算过的，那就是dp[j-nums[i-1]]要先于dp[j]计算，因此选择的遍历方式是从左往右
     *
     * base: dp[0]=1,表示装出金额为0的解法只有1种，那就是什么硬币都不选
     * @param dp
     * @param amount
     * @param nums
     */
    private void dp(int[] dp, int amount, int[] nums) {
        dp[0] = 1;
        for(int i=1;i<=nums.length;i++) {
            for(int j=1;j<=amount;j++) {
                if(j>=nums[i-1]) {
                    // dp(i,j)=dp(i,j-nums[i-1]) + dp(i-1,j)
                    // dp(j)=dp(j-nums[i-1]) + dp(j) 右侧的dp[j]是上一轮的结果
                    dp[j]+=dp[j-nums[i-1]];
                }
            }
        }
    }


}
