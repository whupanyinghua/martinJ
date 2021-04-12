package com.pyh.structure.leetcode.dp;

/**
 * 类LCS的实现描述：Longest Common Subsequence
 * 最长公共子序列的长度
 * @author panyinghua 2021-4-12 19:07
 */
public class LCS {

    public static void main(String[] args) {
        String s1="abcde";
        String s2="ace";
        System.out.println("'" + s1 + "' and '" + s2 + "' LCS is: " + lcs(s1,s2));
    }

    public static int lcs(String s1, String s2) {

        // 考虑定义dp数组，因为是涉及到两个字符串，以及子序列，子序列是不要求所有字符都相邻，可以有间隔
        // dp[i][j] 表示 s1[0...i-1],s2[0...j-1]的最长公共子序列，那么我们如何找到状态转移呢？
        // 比如怎么从dp[i-1][j-1]的值获取到dp[i][j]
        // 假如 s1[i-1]==s2[j-1],那么s1[i]、s2[j]都在LCS结果字符串中，则dp[i][j]=dp[i-1][j-1]+1
        // 假如 s1[i-1]!=s2[j-1],那么s1[i]、s2[j]不可能都在LCS中，即有可能一个在，也可能两个都不在，
        // s1[i-1]不在LCS中可以使用dp[i-1][j]表示
        // s2[j-1]不在LCS中可以使用dp[i][j-1]表示
        // s1[i-1]、s2[j-1]两个都不在LCS中可以使用dp[i-1][j-1]表示
        // 此时直接获取这三个中的最大值即可
        // 即 s1[i-1]!=s2[j-1],那么s1[i-1]、s2[j-1]不可能都在LCS中，则dp[i][j]=max(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
        // 这三个式子其实可以优化下，从表达式中可以看出 dp[i-1][j-1] 肯定小于等于 dp[i-1][j] （为什么呢？注意看dp的定义，dp[i-1][j-1]）相对于dp[i-1][j]来说，j-1比j下标靠前，那么对于dp来说，j-1肯定是比j的LCS要短或者是相等
        // 这三个式子其实可以优化下，从表达式中可以看出 dp[i-1][j-1] 肯定小于等于 dp[i][j-1]
        // dp[i][j]=max(dp[i-1][j],dp[i][j-1])
        // 得出最终的dp状态转移方程：
        // if(s1[i-1]==s2[j-1])
        //    dp[i][j]=dp[i-1][j-1]+1
        // else
        //    dp[i][j]=max(dp[i-1][j],dp[i][j-1])

        // 转换成代码如下
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        // base case
        // dp[...][0]=0的意思是：表示s1跟s2[0...-1]也就是s2没有字符，那么LCS肯定是0
        // dp[0][...]=0的意思是：表示s1[0...-1]跟s2也就是s1没有字符，那么LCS肯定是0
        for(int i=0;i<m;i++) {
            dp[i][0]=0;
        }
        for(int j=0;j<n;j++) {
            dp[0][j]=0;
        }


        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        // dp[m][n]表示的就是s1[0...m-1]、s2[0...n-1]的LCS，也就是s1 s2的LCS咯
        return dp[m][n];
    }
}
