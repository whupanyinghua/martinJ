package com.pyh.structure.leetcode.dp;

/**
 * 类CoinChange的实现描述：
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-28 15:10
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0; // 其他的数组位置要不要初始化都没关系，默认的数组出来就是0
        for(int i=1;i<amount+1;i++) {
            dp[i] = Integer.MAX_VALUE-1; // 初始化成Integer.MAX_VALUE-1是因为后边在求解dp数组的时候会有一个+1的操作
        }
        dp(dp, coins, amount);

        return dp[amount]!=Integer.MAX_VALUE-1?dp[amount]:-1;
    }

    /**
     * 动态规划
     * 状态方程：变量-金额，硬币类别
     * nums[i]数组表示硬币类别数组，j表示组成的金额
     * 第一种思路：
     * dp(i,j) 表示由前i种硬币组成j的最小硬币个数，那么考虑子问题是否是独立问题
     * dp(i,j) = min(dp(i-1,j)，dp(i-1,j-nums[0])+1,dp(i-1,j-nums[1])+1,...,dp(i-1,j-nums[i-1])+1),其中i属于硬币种类数组
     * 观察上述方程，元素只跟i-1上一层计算相关，因此可以优化成一维数组
     * dp[j] = min(dp[j],dp(j-nums[0])+1,dp(j-nums[1])+1,...,dp(j-nums[i-1])+1)
     * 因为j-nums[i-1]<j，因此可以从左往右遍历
     * base:dp[0]=0表示组装成0的金额硬币组合数为0
     * @param dp
     * @param coins
     * @param amount
     */
    private void dp(int[] dp, int[] coins, int amount) {
        for(int i=1;i<dp.length;i++) {
            for(int coin: coins) {
                if(i>=coin) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1); // 这里采取的是min运算，那么在初始化dp数组的时候，就需要将dp初始化成比较大的数字，预防影响到dp min操作结果
                }
            }
        }
    }
}
