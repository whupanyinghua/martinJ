package com.pyh.structure.leetcode.doublept;

/**
 * 类Water的实现描述：水滴问题
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author panyinghua 2020-8-31 17:26
 */
public class WaterTrap {

    public static void main(String[] args) {
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        WaterTrap water = new WaterTrap();
        System.out.println(water.maxWater(a));
        System.out.println(water.maxWater2(a));
        System.out.println(water.trap(a));
    }


    /**
     * 双指针大法！
     * @param nums
     * @return
     */
    public int trap(int[] nums) {
        int len = nums.length;
        if(len<=1) return 0;

        int res = 0;

        int left = 0;
        int right = len-1;
        // lmax
        int lmax = nums[0];
        int rmax = nums[len-1];
        while(left<right) {
            // lmax表示0~left位置中最大的元素
            lmax = max(lmax, nums[left]);
            // rmax表示从right到len-1位置中最大的元素
            rmax = max(rmax, nums[right]);

            if(lmax<rmax) {
                // lmax<rmax，此时lmax肯定是左边[0,left]位置中最大的元素，但是rmax是右边[right,len-1]位置中最大的元素，但是不一定是[left+1,len]中最大的元素
                // 但是不要紧，因为我们求解的算法的思路就是取lmax，rmax中的最小值与当前位置的高度差，此处lmax<rmax，rmax不是[left+1,len]最大元素不影响结果
                res += lmax-nums[left];
                left++;
            } else {
                // 与上述if分支的原由一样
                // rmax是右边[right,len-1]位置中最大的元素的最大元素,lmax是[0,left]中的最大元素，但是不一定是[0,right-1]中最大的元素
                // 此时分支里边 rmax<=lmax,lmax不是[0,right-1]中最大的元素也不影响结果
                res += rmax-nums[right];
                right--;
            }
        }

        return res;
    }

    /**
     * 暴力破解
     * @param nums
     * @return
     */
    public int maxWater(int[] nums) {
        int len = nums.length;
        if(len<=1) return 0;

        int res = 0;
        for(int i=1;i<len-1;i++) {
            // 在位置i可以装的谁取决于左边最大高度lmax，右边最大高度rmax中的最小值，
            // 当前位置i装水的容量是 min(lmax,rmax)-hi，hi表示当前置为的高度
            int tmp = min(lmax(nums, 0, i-1),rmax(nums, i+1,len-1));
            if(tmp>nums[i]) {
                res += tmp-nums[i];
            }
        }


        return res;
    }


    /**
     * 暴力破解+优化
     * @param nums
     * @return
     */
    public int maxWater2(int[] nums) {
        int len = nums.length;
        if(len<=1) return 0;

        int lmax = nums[0];
        int rmax = nums[len-1];
        int rmaxIndex = len-1;

        int res = 0;
        for(int i=1;i<len-1;i++) {
            // lmax保存的是从0~i-1中的最大值
            lmax = max(lmax, nums[i-1]);
            // 如何高效求解rmax？rmax可以不用是右边最大的值
            if(rmax<lmax || i==rmaxIndex) {
                rmaxIndex = i + 1;
                rmax = nums[rmaxIndex];
                while (rmax < lmax && rmaxIndex < len) {
                    // 只要找到一个大于lmax的，就可以中断while循环了，因为此时的min(lmax,rmax)的结果肯定是lmax的值，而lmax肯定是左边最大的数字
                    rmax = max(rmax, nums[rmaxIndex]);
                    rmaxIndex++;
                }
            }

            // 在位置i可以装的谁取决于左边最大高度lmax，右边最大高度rmax中的最小值，
            // 当前位置i装水的容量是 min(lmax,rmax)-hi，hi表示当前置为的高度
            int tmp = min(lmax,rmax);
            // 更新结果集
            if(tmp>nums[i]) {
                res += tmp-nums[i];
            }
        }


        return res;
    }



    private int min(int a, int b) {
        return a>=b?b:a;
    }

    private int max(int a, int b) {
        return a>=b?a:b;
    }

    private int lmax(int[] nums, int begin, int end) {
        return maxs(nums, begin, end);
    }

    private int rmax(int[] nums, int begin, int end) {
        return maxs(nums, begin, end);
    }

    private int maxs(int[] nums, int begin, int end) {
        int j=begin;
        int res = nums[begin];
        while(j<=end) {
            if(nums[j]>res) {
                res = nums[j];
            }
            j++;
        }

        return res;
    }
}
