package com.pyh.structure.leetcode;

/**
 * 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Flatten {
    public void flatten(TreeNode root) {
        flattenInternal(root);
    }

    private TreeNode flattenInternal(TreeNode root) {
        if(null == root) return null;
        TreeNode leftTail = flattenInternal(root.left);
        TreeNode rightTail = flattenInternal(root.right);
        if(leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return rightTail!=null?rightTail:(leftTail!=null?leftTail:root);
    }
}
