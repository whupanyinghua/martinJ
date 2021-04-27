package com.pyh.structure.leetcode.backtrack;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 类Combine的实现描述：组合
 * 输入两个数字 n, k，算法输出 [1..n] 中 k 个数字的所有组合。
 * 比如输入 n = 4, k = 2，输出如下结果，顺序无所谓，但是不能包含重复（按照组合的定义，[1,2] 和 [2,1] 也算重复）：
 * [
 *  [1,2],
 *  [1,3],
 *  [1,4],
 *  [2,3],
 *  [2,4],
 *  [3,4]
 * ]
 *
 *
 * @author panyinghua 2021-4-27 18:04
 */
public class Combine {

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = Lists.newArrayList();

        // 路径
        List<Integer> track = Lists.newArrayList();
        backtrack(n,k,1,res,track);

        return res;
    }

    private static void backtrack(int n, int k, int start, List<List<Integer>> res, List<Integer> track) {
        // base case
        // 思考如何结束递归，或者说是如何添加结果到结果集？
        // k表示的是需要添加到结果集中的track中的元素个数，如果track路径中的元素个数达到了k那么将当前track添加到结果集res中
        if(track.size()==k) {
            // java语言的特性，需要复制一份track中的元素到一个新的列表中，因为整个递归使用的track是同一个对象
            res.add(Lists.newArrayList(track));
            // 注意与Subsets中子集的区别，因为这里求解的只有k个元素的路径，所以如果当前路径中的元素达到k之后，直接return，无需再往下执行。
            return ;
        }

        // 为了防止结果重复，我们在选择元素的时候严格按照顺序来，并且以start为起始节点
        for(int i=start;i<=n;i++) {
            // 选择
            track.add(i);
            // 计算（挑选可以选择的路径）
            backtrack(n,k,i+1,res,track);
            // 撤销选择 (移除最后一个元素，没有使用linkedList，所以直接使用下标移除法)
            track.remove(track.size()-1);
        }
    }
}
