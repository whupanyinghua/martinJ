package com.pyh.structure.leetcode;

/**
 * 类MinFallingPathSum的实现描述：
 * leetcode 931
 *
 * @author panyinghua 2021-2-24 19:51
 */
public class MinFallingPathSum {

    public static int minFallingPathSum(int[][] matrix) {
        // 二维数组的行数
        int n = matrix.length;
        int maxJ = matrix[0].length;
        int res = Integer.MAX_VALUE;

        // 备忘录模式
        memo = new int[n][maxJ];
        for(int i=0;i<n;i++) {
            for(int j=0;j<maxJ;j++) {
                memo[i][j] = Integer.MAX_VALUE;
            }
        }

        // 结果集取最后一行n-1的所有列中最小的值
        for(int j=0;j<maxJ;j++) {
            res = Math.min(res, dp(matrix,n-1,j));
        }



        return res;
    }

    /**
     * dp(int[][] matrix, int i, int j)函数的定义是指从matrix[0][..]到matrix[i][j]的最小路径
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    public static int dp(int[][] matrix, int i, int j) {
        // 边界条件限制
        if(i<0 || j<0 || i>=matrix.length || j>=matrix[0].length) {
            // 边界条件返回Integer.MAX_VALUE是保证在min的求值中不影响结果取值
            return Integer.MAX_VALUE;
        }

        // base
        if(i==0) {
            return matrix[0][j];
        }

        // 备忘录
        if(memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }

        int res = matrix[i][j] + min(dp(matrix,i-1,j-1),dp(matrix,i-1,j),dp(matrix,i-1,j+1));

        // 备忘录
        memo[i][j] = res;

        return res;

    }

    private static int min(int dp, int dp1, int dp2) {
        return Math.min(dp, Math.min(dp1, dp2));
    }

    private static int[][] memo = null;
}
