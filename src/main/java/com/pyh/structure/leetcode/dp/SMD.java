package com.pyh.structure.leetcode.dp;

/**
 * 类SMD的实现描述：最短编辑距离
 * 给定两个字符串s1，s2，求解从s1转换成s2锁使用的最少操作数
 * 其中可以对一个字符进行如下三个操作
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 * @author panyinghua 2021-4-15 19:24
 */
public class SMD {

    public static int smd(String s1, String s2) {
        // dp[i][j] 表示的是 s1[...i-1] s2[...j-1]的最小编辑距离
        // 思考如何从子问题推导出dp[i][j]
        // 当前字符 s1[i-1]  s2[j-1]字符，
        // 假设 s1[i-1]==s2[j-1] 那么当前无需任何操作，已经符合需求，操作次数=0，直接把i j往前移动即可 dp[i][j]=dp[i-1][j-1]
        // 假设 s1[i-1]!=s2[j-1] 那么可以采取三个操作
        // 1.s1中插入一个字符匹配s2的字符，这个时候，s1的i下标i无需移动，s2的j往前移动一位，
        // 插入的字符跟s2当前字符匹配了，那子问题dp[i][j-1]表示的是s1中前i个字符跟s2的钱j-1个字符比较，即 dp[i][j]=dp[i][j-1]+1，插入字符属于一次操作
        // 2.s1中删除当前字符，这个时候，s1的i下标i往前移动一位，s2的j无需移动，
        // 子问题dp[i-1][j]是s1前i-1个字符跟s2前j个字符比较，即 dp[i][j]=dp[i-1][j]+1，删除字符属于一次操作
        // 3.s1 中替换当前字符为s2对应的字符字符，这个时候，s1的i下标i往前移动一位，s2的j往前移动一位，
        // 子问题dp[i-1][j-1]是s1前i-1个字符跟s2前j-1个字符比较， 即 dp[i][j]=dp[i-1][j-1]+1，插入字符属于一次操作
        // 那么得出dp[i][j]的状态转移方程
        // if(s1[i-1]==s2[j-1])
        //     dp[i][j]=dp[i-1][j-1]
        // else
        //     dp[i][j]=min(dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1]+1)
        // 那么我们求解的结果就是 dp[s1.length][s2.length]
        // 再思考base case 怎么初始化dp数组
        // dp[0][...] 表示 s1[0...-1] 跟s2 的最小编辑距离，s1没有字符，那么编辑距离就等于s2此时的比较长度
        // dp[...][0] 表示 s1 跟s2[0...-1] 的最小编辑距离，s2没有字符，那么编辑距离就等于s1此时的比较长度

        int m=s1.length();
        int n=s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
            dp[i][0]=i;
        }
        for(int j=0;j<=n;j++) {
            dp[0][j]=j;
        }

        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1]+1);
                }
            }
        }
        
        return dp[m][n];
    }

    public static int min(int a,int b,int c) {
        return Math.min(Math.min(a,b),c);
    }
}
