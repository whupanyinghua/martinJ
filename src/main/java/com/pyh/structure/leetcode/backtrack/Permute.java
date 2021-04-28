package com.pyh.structure.leetcode.backtrack;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * 类Permute的实现描述：排列
 * 输入一个不包含重复数字的数组 nums，返回这些数字的全部排列
 * 比如说输入数组 [1,2,3]，输出结果应该如下，顺序无所谓，不能有重复：
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 * @author panyinghua 2021-4-28 18:15
 */
public class Permute {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = Lists.newArrayList();

        List<Integer> track = Lists.newArrayList();
        int n = nums.length;
        // 所求解的每一个排列的元素个数为数组的长度n
        backtrack(nums,n,res,track);

        return res;
    }

    private static void backtrack(int[] nums, int n, List<List<Integer>> res, List<Integer> track) {
        // base case
        // n表示的是排列的元素个数，如果当前路径track中的元素个数达到了排列的个数，则添加到结果集，并返回，无需再往后递归了
        if(n==track.size()) {
            res.add(Lists.newArrayList(track));
            return ;
        }

        // 回溯算法三步走大法，1.选择 2.计算更新路径 3.撤销选择
        int len = nums.length;
        for(int i=0;i<len;i++) {
            // 这里需要留意的是，因为要求解的是排列，而且 是原始数组中不存在重复的元素，所以这里需要过滤下当前路径下可以选择的节点
            // 如果当前元素已经在当前路径track中，说明这个元素已经被选择过，那么直接进入到下一个元素的判断
            // 这里有一个扩展问题，如果初始数组中有重复元素，这里应该如何处理？？？
            if(track.contains(nums[i])) {
                continue;
            }
            // 1.选择
            track.add(nums[i]);
            // 2.计算，更新当前路径track
            backtrack(nums, n, res, track);
            // 3.撤销选择
            track.remove(track.size()-1);
        }
    }
}
