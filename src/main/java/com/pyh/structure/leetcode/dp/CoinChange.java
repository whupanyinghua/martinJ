package com.pyh.structure.leetcode.dp;

/**
 * 类CoinChange的实现描述：
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
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
        System.out.println(CoinChange.coinChangeNew(coins, amount));

        int[] coins2 = {1,2,5};
        int amount2 = 11;
        System.out.println(new CoinChange().coinChange(coins2, amount2));
        System.out.println(CoinChange.coinChangeNew(coins2, amount2));
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
     * 注意，这里的隐藏条件是硬币的个数是无限的，所以非常类似 完全背包问题
     * 第一种思路：
     * dp(i,j) 表示由前i种硬币组成j的最小硬币个数，那么考虑子问题是否是独立问题
     * dp(i,j) = min(dp(i-1,j)，dp(i-1,j-nums[0])+1,dp(i-1,j-nums[1])+1,...,dp(i-1,j-nums[i-1])+1),其中i属于硬币种类数组
     * 注意，这里还隐藏了一个条件，就是min条件的计算中，要求j-nums[i-1]>=0，也就需要进入min计算，要求前i-1中组装出来的金额与最终金额j相比必须能够容纳下nums[i]
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
                    // 这里采取的是min运算，那么在初始化dp数组的时候，就需要将dp初始化成比较大的数字，预防影响到dp min操作结果
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
    }

    /**
     * 第二种思路
     * 注意，我们的i是从下标1开始来推导公式的，对于硬币数组，那对应的数组下标就是0开始，所以是i-1
     * dp[i][j] 表示的是使用前i种硬币凑成总金额j所需的最少硬币个数
     * 不用第i种面值的硬币 --  dp[i][j] = dp[i-1][j]
     * 使用第i种面值的硬币 --  dp[i][j] = dp[i][j-coins[i-1]]+1 ，因为使用了面额，就是放入了一个硬币，所以硬币个数+1
     * 因为是需要求解的凑成金额所需最少的硬币个数，所以这里使用min的方式 dp[i][j] = min(dp[i-1][j],dp[i][j-coins[i-1]]+1)
     * 那么最终我们需要的结果就是dp[coins.length][amount]
     * 接下来就处理base case，以及一些边界问题
     */

    public static int coinChangeNew(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len+1][amount+1];

        // base case
        // 基于上述的dp数组定义
        // dp[0][...] 表示的是使用前0中硬币凑成金额，因为没有硬币，所以可以认为是 Integer.MAX-1
        // (思考这里为啥不直接使用-1，是因为在状态方程的转换过程中使用了min方法取值，初始化为-1会影响到正确的取值逻辑，
        // 所以可以初始化为一个较大的数字 Integer.MAX-1，因为这里边的式子里边还有一个+1操作，所以设置为最大值-1即可) ，就是题目要求的没法凑成金额
        // dp[...][0] 表示的是使用前...种硬币凑成金额0，那么可以肯定的是不使用任何硬币，就可以凑出金额是0的方法，就是使用硬币个数是0
        for(int j=0;j<=amount;j++) {
            dp[0][j] = Integer.MAX_VALUE-1;
        }
        for(int i=0;i<=len;i++) {
            dp[i][0] = 0;
        }

        for(int i=1;i<=len;i++) {
            for(int j=1;j<=amount;j++) {
                if(j>=coins[i-1]) {
                    // 不选择或者选择第i种面额，取两者中的比较小的
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]]+1);
                } else {
                    // j的值比第i中面额小，那么不能取第i中面额，此时结果继承自 dp[i-1][j]
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[len][amount]==Integer.MAX_VALUE-1?-1:dp[len][amount];
    }
}
