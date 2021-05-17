package com.pyh.structure.leetcode.tree;

/**
 * 类BTreeBuilder的实现描述：提供了根据两种遍历方式来构造二叉树的思路
 * 1.根据前序遍历、中序遍历构造二叉树
 * 2、根据中序遍历、后续遍历构造二叉树
 *
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * @author panyinghua 2020-9-28 19:00
 */
public class BTreeBuilder {

    /**
     * 根据前序遍历、中序遍历构造出二叉树
     * @param preOrders
     * @param inOrders
     * @return
     */
    private TreeNode buildTree(int[] preOrders, int[] inOrders) {
        return buildTreeInternal(preOrders, 0, preOrders.length-1, inOrders, 0, inOrders.length-1);
    }

    private TreeNode buildTreeInternal(int[] preOrders, int preBegin, int preEnd, int[] inOrders, int inBegin, int inEnd) {
        // base case
        if(preBegin>preEnd) {
            // 前序遍历与中序遍历的终止递归条件，只需要保留一个即可
            return null;
        }

        // 查找根节点，并确认根节点在中序遍历中的位置index
        int rootValue = preOrders[preBegin];

        int index = -1;
        for(int i=inBegin;i<=inEnd;i++) {
            if(rootValue == inOrders[i]) {
                index = i;
                break;
            }
        }

        // 生成根节点
        TreeNode root = new TreeNode(rootValue);
        int leftSize = index-inBegin;
        // 递归左子树
        root.left = buildTreeInternal(preOrders, preBegin+1,preBegin+leftSize, inOrders,inBegin,index-1);
        // 递归右子树
        root.right = buildTreeInternal(preOrders,preBegin+leftSize+1, preEnd, inOrders,index+1,inEnd);

        return root;
    }

    /**
     * 从中序遍历、后续遍历构造二叉树
     * @param inOrders
     * @param postOrders
     * @return
     */
    public TreeNode buildTree2(int[] inOrders, int[] postOrders) {
        return buildTreeInternal2(inOrders, 0, inOrders.length-1, postOrders, 0, postOrders.length-1);
    }

    private TreeNode buildTreeInternal2(int[] inOrders, int inBegin, int inEnd, int[] postOrders, int postBegin, int postEnd) {
        // base case
        if(postBegin>postEnd) {
            // 前序遍历与中序遍历的终止递归条件，只需要保留一个即可
            return null;
        }
        // 查找根节点，并确认根节点在中序遍历中的位置index
        int rootValue = postOrders[postEnd];
        int index = -1;
        for(int i=inBegin;i<=inEnd;i++) {
            if(rootValue == inOrders[i]) {
                index = i;
                break;
            }
        }

        // 生成根节点
        TreeNode root = new TreeNode(rootValue);
        // 左子树的大小
        int leftSize = index-inBegin;
        // 递归左子树
        root.left = buildTreeInternal2(inOrders,inBegin,index-1,postOrders,postBegin,postBegin+leftSize-1);
        // 递归右子树
        root.right = buildTreeInternal2(inOrders,index+1,inEnd,postOrders,postBegin+leftSize,postEnd-1);

        return root;
    }
}
