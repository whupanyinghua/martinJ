package com.pyh.structure.leetcode.tree;

/**
 * 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Flatten {
    public void flatten(TreeNode root) {
        flattenInternal(root);
    }

    /**
     * flattenInternal函数的定义是，将root节点下的左右子树拉平形成一颗链表，并且返回链表的尾节点
     * @param root
     * @return
     */
    private TreeNode flattenInternal(TreeNode root) {
        if(null == root) return null;
        // 将左子树拉平成链表
        TreeNode leftTail = flattenInternal(root.left);
        // 将右子树拉平成链表
        TreeNode rightTail = flattenInternal(root.right);
        // 将左子树放到root节点的right位置上，并且将原来的右子树拼接到原来左子树的后边
        if(leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        // 难点在于如何理解，本次函数返回的尾节点的定义
        // 尾节点首先从右子树的尾节点，如果右子树尾节点为空，那么取左子树的尾节点，如果左子树尾节点为空，那么取根节点本身
        return rightTail!=null?rightTail:(leftTail!=null?leftTail:root);
    }


    /**
     * 函数的定义是将root根节点的左子树拉平成链表，将右子树拉平成链表，并且将右子树拼接到左子树后边，然后将左子树变为root节点的右子树
     * @param root
     */
    private void flattenInternal2(TreeNode root) {
        // base case
        if(null == root) {
            return ;
        }

        // 左子树拉平成链表
        flattenInternal2(root.left);
        // 右子树拉平成链表
        flattenInternal2(root.right);

        // 后续遍历
        // 将左子树变为root的右子树，将原右子树接到当前右子树后边
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        // 找到原左子树的最后一个节点，这个理有一个诀窍是从root节点开始找而不是从left节点开始找，是因为left节点可能是空
        TreeNode lt = root;
        while(lt.right!=null) {
            lt = lt.right;
        }
        lt.right = right;
    }


}
