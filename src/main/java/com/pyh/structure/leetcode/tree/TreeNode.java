package com.pyh.structure.leetcode.tree;

/**
 * 类TreeNode的实现描述：二叉树节点结构
 *
 * @author panyinghua 2020-7-8 16:37
 */
public class TreeNode {
    public int value; // 节点的值
    public TreeNode left; // 左子节点
    public TreeNode right; // 右子节点

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof TreeNode) {
            return this.value == ((TreeNode)obj).value;
        }

        return false;
    }

    @Override
    public String toString() {
        return "node->value:"+this.value;
    }

    public static TreeNode[] generateNodes(int[] values) {
        TreeNode[] nodes = new TreeNode[values.length];
        for(int i=0;i<values.length;i++) {
            nodes[i] = new TreeNode(values[i]);
        }
        return nodes;
    }
}
