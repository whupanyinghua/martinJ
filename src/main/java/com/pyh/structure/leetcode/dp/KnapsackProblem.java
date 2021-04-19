package com.pyh.structure.leetcode.dp;

/**
 * 类KnapsackProblem的实现描述：背包算法相关
 *
 * @author panyinghua 2021-4-19 19:06
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        System.out.println(kp01(new int[]{2, 1, 3},new int[]{4,2,3},4));
    }




    /**
     *  0-1背包算法
     *  有一个背包的容量cap，有一组物品，物品有重量与价值两个属性，重量用wt数组表示，价值用values数组表示
     *  0-1背包就是在每个物品最多只能装一次的情况，问在在超过背包容量cap的情况下能装下的最大价值
     * @param wt
     * @param val
     * @param cap
     * @return
     */
    public static int kp01(int[] wt, int[] val, int cap) {
        // dp[i][j]表示使用前i中物品占用空间为j的时候所能装出的最大价值
        // 子问题 dp[i-1][j-1]表示使用前i-1种物品占用空间为j-1的时候所能装出的最大价值
        // 注意，数组中下标是从0开始的，而我们这里使用的i是从1开始，所以一会儿计算重量、价值的时候数组的下标需要对应的-1操作
        // 怎么从子问题的解推导出父问题的解呢？
        // 【经典】从第i-1中物品到底i中物品的时候，对于第i中物品我们可以有两种选择，一种是装入背包，另一种是不装入背包，然后取其中的较大值即可
        // dp[i][j] = max(dp[i-1][j-val[i-1]]+wt[i-1],dp[i-1][j])
        // 装入背包的情况：dp[i-1][j-val[i-1]]+wt[i-1]，dp[i-1][j-val[i-1]]表示前i-1中物品装出j-val[i-1]的容量背包，然后再加上第i中物品的价值wt[i-1]
        // 不装入背包的情况：dp[i-1][j] 表示前i-1中物品装入j的容量背包
        // 注意在这个式子中有一个j-val[i-1]的j表示的容量下标，那么我们需要针对这个情况做处理
        // 假如当前要求解的j小于第i种物品重量wt[i-1]，那么背包是装不下物品i的，此时直接继承子问题的解(dp[i-1][j])即可，表示的是物品i不装入背包
        // 因此可以整理得出公式
        // if j>= wt[i-1]
        //     dp[i][j] = max(dp[i-1][j-wt[i-1]]+val[i-1],dp[i-1][j])
        // else
        //     dp[i][j] = dp[i-1][j]

        // base case
        // dp[0][...]表示使用前0个物品来组装背包，没有物品，那组装出来的价值肯定都是0
        // dp[...][0]表示使用前任意多个物品来组装背包是容量为0的情况，那么肯定是装不了任何东西，价值也都是0

        int n = val.length;
        int[][] dp = new int[n+1][cap+1];
        // base case
        for(int i=0;i<=n;i++) {
            dp[i][0]=0;
        }
        for(int j=0;j<=cap;j++) {
            dp[0][j]=0;
        }


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=cap;j++) {
                if(j>wt[i-1]) {
                    dp[i][j]=Math.max(dp[i-1][j-wt[i-1]]+val[i-1], dp[i-1][j]);
                } else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        return dp[n][cap];
    }
}
