package com.pyh.structure.leetcode.backtrack;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * 类Subsets的实现描述：输入一个不包含重复数字的数组，要求算法输出这些数字的所有子集。
 * 比如输入 nums = [1,2,3]，你的算法应输出 8 个子集，包含空集和本身，顺序可以不同：
 * [ [],[1],[2],[3],[1,3],[2,3],[1,2],[1,2,3] ]
 *
 * @author panyinghua 2021-4-26 18:03
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        // 回溯算法要考虑好递归的参数，这个是写好回溯算法的一个很关键的点
        // 回溯算法的框架
        // 1.选择
        // 2.计算
        // 3.撤销选择
        //
        //
        //
        //
        List<List<Integer>> res = Lists.newArrayList();

        List<Integer> track = Lists.newArrayList();
        backtrack(nums, 0, res, track);

        return res;
    }

    private static void backtrack(int[] nums, int start, List<List<Integer>> res, List<Integer> track) {
        // base case
        // 因为是可以包含空集合，所以其实在开始遍历的时候，就可以先把当前路径加入结果集中，模式就像二叉树的前序遍历
        // 考虑到递归调用的backtrack函数中有for循环的边界判断，所以basecase里边无需再校验边界
        // java语言的特性，需要复制一份track中的元素到一个新的列表中，因为整个递归使用的track是同一个对象
        res.add(Lists.newArrayList(track));

        // 为什么这里有for循环，可以思考下降题目中的nums数组画成一个二叉树，一个节点下边可以选择的路径肯定是在节点本身之后的元素，也就是节点start之后的nums元素
        for(int i=start;i<nums.length;i++) {
            // 选择
            track.add(nums[i]);
            // 计算，更新路径等
            backtrack(nums, i + 1, res, track);
            // 撤销选择 (撤销掉最后一个加入的元素)
            track.remove(track.size()-1);
        }
    }
}
