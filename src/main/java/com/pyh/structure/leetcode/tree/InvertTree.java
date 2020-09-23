package com.pyh.structure.leetcode.tree;

import com.pyh.structure.leetcode.TreeNode;

/**
 * 类InvertTree的实现描述：@TODO
 *
 * @author panyinghua 2020-9-23 19:23
 */
public class InvertTree {


    /**
     * 镜像反转二叉树
     * @param root
     */
    public void invertTree(TreeNode root) {
        // base case
        if(null == root) {
            return ;
        }

        // 翻转左子树
        invertTree(root.left);
        // 翻转右子树
        invertTree(root.right);

        // 后续遍历的位置
        // 将当前左子树、右子树互换，达到翻转数的目的
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
    }
}
