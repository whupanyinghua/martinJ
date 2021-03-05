package com.pyh.structure.leetcode.backtrack;

/**
 * 类CanPartitionKSubsets的实现描述：
 * 给你输入一个数组nums和一个正整数k，请你判断nums是否能够被平分为元素和相同的k个子集。
 *
 * @author panyinghua 2021-3-5 15:44
 */
public class CanPartitionKSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {


        return false;
    }


    public boolean backtrack(int[] nums, int k, int start,boolean[] use, int sum, int target) {
        // base case
        if(k==0) {
            // 判断是否所有的桶都符合
            // k=0 表示桶都装完了，而且能进入这里，表示上一层是经过了sum&target的判断是相等的，
            return true;
        }

        if(sum==target) {
            // 当前桶已经装满，从下一桶开始
            // k-1表示桶的数目-1，start=0要从位置0开始重新进行数字的选择，因为之前的循环里边可能有的元素被跳过了，需要从头开始选，sum=0表示新的桶，那求和也从0开始
            return backtrack(nums, k-1, 0, use, 0, target);
        }

        // 回溯算法框架
        // 1.做选择
        // 2.计算
        // 3.撤销选择
        for(int i=start;i<nums.length;i++) {
            if(use[i]) {
                continue;
            }
            if(sum+nums[i]>target) {
                continue;// 当前元素装不到k号桶
            }
            // 当前元素装入K号桶
            sum += nums[i];
            use[i] = true;
            if(backtrack(nums, k, i+1, use, sum, target)) {
                return true;
            }
            // 怎么撤销选择？
            sum -= nums[i];
            use[i] = false;
        }


        return false;
    }
}
