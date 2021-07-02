package com.pyh.structure.leetcode.dp;

/**
 * 类KnapsackProblem的实现描述：背包算法相关
 *
 * @author panyinghua 2021-4-19 19:06
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        System.out.println(kp01(new int[]{2, 1, 3},new int[]{4,2,3},4));
        System.out.println(kp01_ziji(new int[]{1,5,11,5}));
        System.out.println(kp01_ziji(new int[]{1,5,11}));
        System.out.println(kp01_ziji(new int[]{1,5,11,6}));
        System.out.println(kp01_ziji(new int[]{1,5,11,9}));
        System.out.println(kpall(new int[]{1,2,5},5));
    }




    /**
     *  0-1背包算法
     *  有一个背包的容量cap，有一组物品，物品有重量与价值两个属性，重量用wt数组表示，价值用val数组表示
     *  0-1背包就是在每个物品最多只能装一次的情况，问在在超过背包容量cap的情况下能装下的最大价值
     * @param wt
     * @param val
     * @param cap
     * @return
     */
    public static int kp01(int[] wt, int[] val, int cap) {
        // dp[i][j]表示使用前i种物品占用空间为j的时候所能装出的最大价值
        // 子问题 dp[i-1][j-1]表示使用前i-1种物品占用空间为j-1的时候所能装出的最大价值
        // 注意，数组中下标是从0开始的，而我们这里使用的i是从1开始，所以一会儿计算重量、价值的时候数组的下标需要对应的-1操作
        // 怎么从子问题的解推导出父问题的解呢？
        // 【经典】从第i-1种物品到底i种物品的时候，对于第i种物品我们可以有两种选择，一种是装入背包，另一种是不装入背包，然后取其中的较大值即可
        // dp[i][j] = max(dp[i-1][j-val[i-1]]+wt[i-1],dp[i-1][j])
        // 装入背包的情况：dp[i-1][j-val[i-1]]+wt[i-1]，dp[i-1][j-val[i-1]]表示前i-1种物品装出j-val[i-1]的容量背包，然后再加上第i种物品的价值wt[i-1]
        // 不装入背包的情况：dp[i-1][j] 表示前i-1种物品装入j的容量背包
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

    /**
     * 0-1背包的变体。
     * 给定一个只包含正整数的非空数组，是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 例如 数组[1,5,11,5] 可以分为两个子集[1,5,1] [11]，这两个子集的元素和都为11
     * 可以将上述问题转化为如下： 另sum为所有数组元素之和，那么是否存在从数组中挑选一些元素，使得元素之和为 sum/2，也就是挑选元素装满背包容量为sum/2的挑法是否存在
     * @return
     */
    public static boolean kp01_ziji(int[] nums) {

        int n = nums.length;
        int sum = 0;
        for(int i=0;i<n;i++) {
            sum+=nums[i];
        }
        int cap = sum/2;
        // 如果不能整除，那么肯定是不能分为两个相同和的子集
        if((cap << 1) !=sum) {
            return false;
        }

        boolean[][] dp = new boolean[n+1][cap+1];

        // dp[i][j]表示能否使用前i个元素装满容量j的背包，dp中的元素都是boolean变量
        // 第i个物品要不要选择装入背包的思考
        // 不选择不装入：dp[i][j] = dp[i-1][j] 那就是直接用前i-1种物品装成容量为j的背包的结果
        // 选择装入：  dp[i][j] = dp[i-1][j-nums[i-1]] 直接继承dp[i-1][j-nums[i-1]]的结果，
        // dp[i-1][j-nums[i-1]]表示如果前i-1个物品装j-nums[i-1]的重量，然后再装入第i个物品，也就是nums[i-1]，
        // 如果dp[i-1][j-nums[i-1]]能装满，那现在装入第i个物品，肯定也可以装满,
        // 如果dp[i-1][j-nums[i-1]]装不满，那现在装入第i个物品，肯定是装不满的
        // 因为求解的是能否有一种方式可以装满背包，所以我们对这两个选择直接采取 "求或" 的做法，只要有一种选择可以成功，那就认为是成功的
        // 注意，普通背包问题这里一般是 取较大值或较小值，完全背包则是取和
        // 得出dp的转移方程
        // dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j]
        // 跟其他背包一样的套路处理下标越界的问题 j-nums[i-1]
        // if j>nums[i-1]
        //     dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j]
        // else
        //     dp[i][j] = dp[i-1][j]
        //
        // base case
        // dp[0][...] 表示能否使用前0个元素构成所求解的值，没有物品可以选，那显然是不可能存在解法
        // dp[...][0] 表示能否使用所有元素中的任意元素构成值为0的背包，显然不选择任何一个元素就是解法

        for(int j=0;j<cap;j++) {
            dp[0][j] = false;
        }
        for(int i=0;i<=n;i++) {
            dp[i][0] = true;
        }


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=cap;j++) {
                if(j>=nums[i-1]) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][cap];
    }

    /**
     * 完全背包问题，就是物品的个数无限，0-1背包中单种物品只有一个，在完全背包里边单种物品的个数无限
     * 题目也变为：使用i种物品可以在容量为j的背包里边，一共有多少种装法可以把背包装满？
     * 注意，完全背包问题里边没有价值这个维度的变量了。
     * @param wt
     * @param cap
     * @return
     */
    public static int kpall(int[] wt, int cap) {

        // dp[i][j]表示使用前i种物品把容量j装满的方法数
        // 如何从子问题的解推导出父问题的解呢？
        // 考虑在第i种物品的时候，我们可以有两种选择，装入背包，不装入背包
        // 不装入背包：此时子问题dp[i-1][j]表示使用前i-1种物品装满j容量的背包的方法数，就是父问题dp[i][j]的解，即dp[i][j]=dp[i-1][j]
        // 装入背包：这个时候意味着是使用第i种物品，则 子问题 dp[i][j-wt[i-1]]的基础上（i从1开始，数组下标从0开始，所以这里有i-1表示物品的重量），再加入一个i物品，就可以装满背包j容量，
        // 即 dp[i][j]=dp[i][j-wt[i-1]]，这里可以这么理解，我在使用第i种物品的情况下，装出了j-wt[i-1]容量的背包，然后再往背包里装入物品i，不就是dp[i][j]的方法数了嘛
        // 因为求解的是一共有多少种装法，所以这里的式子就是 不装入背包+装入背包 这两个之和，注意与0-1背包中的区别！
        // dp[i][j]=dp[i-1][j]+dp[i][j-wt[i-1]]
        // 这个式子里边有索引的越界问题，j-wt[i-1]，那么我们在遍历j的时候，需要做下判断
        // j>=wt[i-1]  则走正常的式子 dp[i][j]=dp[i-1][j]+dp[i][j-wt[i-1]]
        // j<wt[i-1] 则表示当前容量装不下物品i，那么结果直接从子问题的解继承过来，即 dp[i][j]=dp[i-1][j]
        // 再考虑base case
        // dp[0][...] 表示使用前0种物品来组装背包，没有物品可以选择，那么装法肯定是 0
        // dp[...][0] 表示使用前任意多种物品来组装容量为0的背包背包，那么一个物品都不选就是解法，也就是 1
        // 特别需要注意的是 dp[0][0]=1 ,这个表示的是，使用前0种物品装容量为0的背包的解法，虽然没有物品可以选择，但是背包容量有是0，不需要选择物品，所以不选择物品是唯一的解

        int n = wt.length;
        int[][] dp = new int[n+1][cap+1];

        // base case
        for(int j=0;j<=cap;j++) {
            dp[0][j]=0;
        }
        for(int i=0;i<=n;i++) {
            dp[i][0]=1;
        }


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=cap;j++) {
                if(j>=wt[i-1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - wt[i - 1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][cap];
    }
}
