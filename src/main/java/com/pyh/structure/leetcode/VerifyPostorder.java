package com.pyh.structure.leetcode;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * 知识点：二叉搜索树的特性是  若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
 */
public class VerifyPostorder {

    public static void main(String[] args) {
        int[] a = {4, 8, 6, 12, 16, 14, 10};
        System.out.println(new VerifyPostorder().verifyPostorder(a));
    }

    public boolean verifyPostorder(int[] postorder) {
        int root = postorder[postorder.length-1];// 整棵树的根节点
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;
        return verifyPostorder(postorder, 0, postorder.length-1, minValue, maxValue);
    }

    private boolean verifyPostorder(int[] postorder, int beginIndex, int endIndex, int minValue, int maxValue) {
        // 递归终止条件
        if(beginIndex==endIndex) return true;
        if(beginIndex>endIndex) return true;

        int rightTreeRoot = postorder.length;
        int leftTreeRoot = -1;
        int root = postorder[endIndex];
        for(int i=endIndex-1;i>=beginIndex;i--) {
            if(postorder[i]<root) {
                // 当前i节点是i+1节点的左子树
                leftTreeRoot = i;
                break; // 找到了左子树，右子树肯定也确认了，因为是后序遍历
            } else {
                if(rightTreeRoot==postorder.length) {
                    rightTreeRoot = i;
                }
            }
        }
        if(leftTreeRoot>rightTreeRoot) {
            return false;//左子树的根节点不可能在右子树的根节点之后
        }
        if(rightTreeRoot!=postorder.length && postorder[rightTreeRoot]>maxValue) {
            return false;
        }
        if(leftTreeRoot!= -1 && postorder[leftTreeRoot]<minValue) {
            return false;
        }

        // 找到满足条件的左子树，右子树，那么递归调用
        // 左子树[beginIndex, leftTreeRoot]
        // 右子树[leftTreeRoot+1,rightTreeRoot]
        int beginOfRightTree = leftTreeRoot==-1?beginIndex:leftTreeRoot+1;
        return (leftTreeRoot==-1 || verifyPostorder(postorder, beginIndex, leftTreeRoot, minValue, root)) &&
                (rightTreeRoot==postorder.length || verifyPostorder(postorder,beginOfRightTree,rightTreeRoot, root, maxValue));
    }
}
