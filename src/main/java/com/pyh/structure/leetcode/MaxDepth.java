package com.pyh.structure.leetcode;

/**
 * 类MaxDepth的实现描述：
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
 * 两个题目是一样的
 *
 * @author panyinghua 2020-7-28 10:55
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if(null == root) return 0; // 若当前节点为空，则高度是0
        // 递归左子树的高度、右子树的高度，返回中间较大的值然后+1即为当前树的最大高度
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}
