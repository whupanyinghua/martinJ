package com.pyh.structure;
/**
 * 类DP.java的实现描述：TODO 类实现描述 
 * @author panyinghua 2019年3月4日 上午11:21:42
 */
public class DP {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DP dp = new DP();
        int[] prices = new int[]{2,4,2,3,6};
        int n = 5;
        int w = 9;
        boolean[][] tree = ShoppingSnacks(prices, n, w);
        System.out.println(tree);
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<w+1;j++) {
                System.out.format("%-8s",tree[i][j]);
            }
            System.out.print("\n");
        }
    }
    
    /**
     * 
     * @param prices 各个商品的价格
     * @param n 商品的个数
     * @param w 满减条件（满 199-99元）
     */
    public static boolean[][] ShoppingSnacks(int[] prices,int n,int w){
        boolean[][] tree = new boolean[n][w+1];
        tree[0][0] = true;
        tree[0][prices[0]] = true;
        //动态规划
        for (int i = 1; i < n; i++) {
            // 不购买当前商品
            for(int j = 0;j <=w; j++) {
                //寻找上一个商品决策状态
                if(tree[i-1][j] == true) {
                    tree[i][j] = true;
                }
            }

            // 购买当前商品
            for(int j = 0;j <=w-prices[i]; j++) {
                //寻找上一个商品决策状态
                if(tree[i-1][j] == true) {
                    tree[i][j+prices[i]] = true;
                }
            }
         }
        
        return tree;
    }


}
