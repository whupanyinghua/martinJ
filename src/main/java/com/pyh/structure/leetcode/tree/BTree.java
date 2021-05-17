package com.pyh.structure.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 类BTree的实现描述：二叉树相关
 *
 * @author panyinghua 2020-7-8 16:35
 */
public class BTree {

    public static void main(String[] args) {
        TreeNode[] preOrders = TreeNode.generateNodes(new int[]{3,9,20,15,7});
        TreeNode[] inOrders = TreeNode.generateNodes(new int[]{9,3,15,20,7});
        TreeNode rootNode = buildTreeFromDLRLDR(preOrders, inOrders);
        System.out.println(rootNode);
    }


    /**
     * 从前序遍历、中序遍历中重新构建二叉树
     * @param preOrders 前序遍历
     * @param inOrders 中序遍历
     * @return 构建的二叉树的根节点
     */
    public static TreeNode buildTreeFromDLRLDR(TreeNode[] preOrders, TreeNode[] inOrders) {
        TreeNode rootNode = null;

        // 知识点：
        // 前序遍历： 先根节点，然后左节点，再右节点，其中任意节点的子树节点均满足该规则
        // 中序遍历： 先左节点，然后根节点，再右节点，其中任意节点的子树节点均满足该规则
        // 将中序遍历的数组转换成Map，key为节点本身，value是节点在数组中的位置，方便后续查找
        Map<TreeNode, Integer> inOrderMap = new HashMap<>();
        int index=0;
        for(TreeNode node : inOrders) {
            inOrderMap.put(node, index++);
        }

        // 开始根节点index为0
        int rootNodePreIndex = 0;
        rootNode = preOrders[rootNodePreIndex];
        // 当前根节点在中序遍历数组中的位置
        int rootNodeInIndex = inOrderMap.get(rootNode);
        // 左子树，边界(中序遍历)为[0,rootNodeInIndex-1]
        int leftTreeBeginIndex = 0;
        int leftTreeEndIndex = rootNodeInIndex-1;
        // 右子树，边界(中序遍历)是[rootNodeInIndex+1,inOrders.length-1]
        int rightTreeBeginIndex = rootNodeInIndex+1;
        int rightTreeEndIndex = inOrders.length-1;

        // 递归处理
        //buildTree(preOrders, inOrders, rootNode, rootNodePreIndex, rootNodeInIndex, leftTreeBeginIndex, leftTreeEndIndex, rightTreeBeginIndex, rightTreeEndIndex, inOrderMap);
        buildTreeNew(preOrders, inOrders, rootNodePreIndex, rootNodeInIndex, leftTreeBeginIndex, rightTreeEndIndex, inOrderMap);

        return rootNode;
    }

    /**
     * 根据前序遍历和中序遍历构建出对应的二叉树
     * @param preOrders 前序遍历
     * @param inOrders 中序遍历
     * @param parentRootNode 当前根节点
     * @param prnPreIndex 当前根节点在前序遍历中的位置
     * @param prnInIndex 当前根节点在中序遍历中的位置
     * @param leftNodeInBeginIndex 左子树的左边界
     * @param leftNodeInEndIndex 左子树的右边界
     * @param rightNodeInBeginIndex 右子树的左边界
     * @param rightNodeInEndIndex 右子树的右边界
     * @param inOrderMap 中序遍历转换成的MAP，key为节点自身，value为节点在中序遍历数组中的位置
     */
    private static void buildTree(TreeNode[] preOrders, TreeNode[] inOrders, TreeNode parentRootNode,
                          int prnPreIndex, int prnInIndex,
                          int leftNodeInBeginIndex, int leftNodeInEndIndex,
                          int rightNodeInBeginIndex, int rightNodeInEndIndex,
                          Map<TreeNode, Integer> inOrderMap) {

        int leftTreeBeginIndex = leftNodeInBeginIndex;
        int leftTreeEndIndex = leftNodeInEndIndex;
        // 递归处理左子树
        if(leftTreeEndIndex>=leftTreeBeginIndex) {
            // 根据前序遍历规则，则此时左子树的根节点为 prnPreIndex+1
            int leftRootNodePreIndex = prnPreIndex+1;
            TreeNode leftTreeNode = preOrders[leftRootNodePreIndex];
            parentRootNode.left = leftTreeNode;
            int leftTreeNodeInIndex = inOrderMap.get(leftTreeNode);
            buildTree(preOrders, inOrders, leftTreeNode, leftRootNodePreIndex, leftTreeNodeInIndex,
                    leftTreeBeginIndex, leftTreeNodeInIndex-1,
                    leftTreeNodeInIndex+1,prnInIndex-1,
                    inOrderMap);
        }
        // 递归处理右子树
        int rightTreeBeginIndex = rightNodeInBeginIndex;
        int rightTreeEndIndex = rightNodeInEndIndex;
        if(rightTreeEndIndex>=rightTreeBeginIndex) {
            // 根据前序遍历规则，此时右子树的跟节点为 父根节点的前序位置+左子树的长度+1 = prnPreIndex+leftTree.length+1
            int rightRootNodePreIndex = prnPreIndex+(leftTreeEndIndex-leftTreeBeginIndex+1)+1;
            TreeNode rightTreeNode = preOrders[rightRootNodePreIndex];
            parentRootNode.right = rightTreeNode;
            int rightTreeNodeInIndex = inOrderMap.get(rightTreeNode);
            buildTree(preOrders, inOrders, rightTreeNode, rightRootNodePreIndex, rightTreeNodeInIndex,
                    rightTreeBeginIndex, rightTreeNodeInIndex-1,
                    rightTreeNodeInIndex+1, rightTreeEndIndex,
                    inOrderMap);
        }

    }


    /**
     * 根据前序遍历和中序遍历构建出对应的二叉树
     * @param preOrders 前序遍历
     * @param inOrders 中序遍历
     * @param prnPreIndex 当前根节点在前序遍历中的位置
     * @param prnInIndex 当前根节点在中序遍历中的位置
     * @param leftNodeInBeginIndex 左子树的左边界
     * @param rightNodeInEndIndex 右子树的右边界
     * @param inOrderMap 中序遍历转换成的MAP，key为节点自身，value为节点在中序遍历数组中的位置
     */
    private static void buildTreeNew(TreeNode[] preOrders, TreeNode[] inOrders,
                                 int prnPreIndex, int prnInIndex,
                                 int leftNodeInBeginIndex,
                                 int rightNodeInEndIndex,
                                 Map<TreeNode, Integer> inOrderMap) {

        TreeNode curRootNode = preOrders[prnPreIndex];
        int leftTreeBeginIndex = leftNodeInBeginIndex;
        int leftTreeEndIndex = prnInIndex-1;
        // 递归处理左子树
        if(leftTreeEndIndex>=leftTreeBeginIndex) {
            // 根据前序遍历规则，则此时左子树的根节点为 prnPreIndex+1
            TreeNode leftTreeNode = preOrders[prnPreIndex+1];
            curRootNode.left = leftTreeNode;
            int leftTreeNodeInIndex = inOrderMap.get(leftTreeNode);
            buildTreeNew(preOrders, inOrders,
                    prnPreIndex+1, leftTreeNodeInIndex,
                    leftTreeBeginIndex,
                    leftTreeEndIndex,
                    inOrderMap);
        }
        // 递归处理右子树
        int rightTreeBeginIndex = prnInIndex+1;
        int rightTreeEndIndex = rightNodeInEndIndex;
        if(rightTreeEndIndex>=rightTreeBeginIndex) {
            // 根据前序遍历规则，此时右子树的跟节点为 父根节点的前序位置+左子树的长度+1 = prnPreIndex+leftTree.length+1
            int rightRootNodePreIndex = prnPreIndex+(leftTreeEndIndex-leftTreeBeginIndex+1)+1;
            TreeNode rightTreeNode = preOrders[rightRootNodePreIndex];
            curRootNode.right = rightTreeNode;
            int rightTreeNodeInIndex = inOrderMap.get(rightTreeNode);
            buildTreeNew(preOrders, inOrders,
                    rightRootNodePreIndex, rightTreeNodeInIndex,
                    rightTreeBeginIndex,
                    rightTreeEndIndex,
                    inOrderMap);
        }

    }



}
