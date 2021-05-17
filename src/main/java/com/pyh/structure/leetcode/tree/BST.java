package com.pyh.structure.leetcode.tree;

/**
 * 类BST的实现描述：二叉搜索树 （Binary search tree）
 * 1.针对节点不重复的状况
 * 2.树中的任意一个节点均满足：二叉树的左节点都比当前节点小，二叉树的右节点都比当前节点大
 *
 * @author panyinghua 2021-5-17 16:32
 */
public class BST {

    /**
     * 树的根节点
     */
    private TreeNode root = null;

    /**
     * 查找节点
     * @param node
     * @return
     */
    public TreeNode search(TreeNode node) {
        TreeNode treeNode = root;
        while(null != treeNode) {
            if (node.value == treeNode.value) {
                return treeNode;
            } else if(node.value > treeNode.value) {
                // 要查找的值大于当前节点，那么就在右子树中继续查找
                treeNode = treeNode.right;
            } else {
                // 要查找的值小于当前节点，那么就在左子树中继续查找
                treeNode = treeNode.left;
            }
        }

        return null;
    }

    /**
     * 新增节点，可以思考，新增的节点肯定是在当前树里边的叶子节点的位置
     * @param node
     */
    public void add(TreeNode node) {
        if(null == root) {
            root = node;
            return ;
        }

        TreeNode pnode = root;
        // 找到要插入的位置的父节点
        while(null != pnode) {
            if(node.value < pnode.value && null != pnode.left) {
                // 插入节点的值比当前节点小，那么要插入的节点应该位于当前节点的左子树部分
                pnode = pnode.left;
            } else if (node.value > pnode.value && null != pnode.right) {
                // 插入节点的值比当前节点大，那么要插入的节点应该位于当前节点的右子树部分
                pnode = pnode.right;
            } else {
                // 重复的节点，抛异常
            }
        }
        // 插入节点node(再次判断下是插入到左子节点还是右子节点)
        if(node.value < pnode.value) {
            pnode.left = node;
        } else {
            pnode.right = node;
        }

    }

    /**
     * 删除节点
     * 分情况：
     * 1.要删除的节点本身后续只有左节点-只需要将删除节点的左节点拼接到删除节点之前的位置即可
     * 2.要删除的节点本身后续只有右节点-只需要将删除节点的左节点拼接到删除节点之前的位置即可
     * 3.要删除的节点本身后续有左右节点-第三种情况比较复杂
     * 分步考虑：
     * 3.1 找到删除节点的右子树中最小的值（按照二叉搜索树的规则，这个值比根节点也就是要删除的节点的值大，并且比要删除的节点的左子树中任意节点都大）
     * 3.2 将3.1中找到的节点值替换到删除节点的位置，还是可以满足二叉搜索树的性质
     * 3.3 删掉3.1中找到的节点，此时3，1这个节点因为是原本二叉树中右子树最小的值，那么这个节点肯定是没有了左子节点的，删除规则比较简单，可以直接使用步骤2
     * @param node
     */
    public void delete(TreeNode node) {

        // 要删除节点的父节点
        TreeNode ppnode = null;
        // 指向待删除节点
        TreeNode pnode = root;
        
        while(null != pnode && node.value != pnode.value) {
            ppnode = pnode;
            if(node.value > pnode.value) {
                pnode = pnode.right;
            } else {
                pnode = pnode.left;
            }
        }

        // pnode为null，表示当前树中不存在要删除的这个节点
        if(null == pnode) {
            return ;
        }
        
        // 场景3，要删除的节点有左右两个子节点
        if(null != pnode.left && null != pnode.right) {
            TreeNode minppnode = pnode;
            TreeNode minpnode = pnode.right;
            while(null != minpnode.left) {
                minppnode = minpnode;
                minpnode = minpnode.left;
            }

            // 循环结束之后，minpnode肯定是之前右子树中最小的节点，而minppnode则是minpnode的父节点
            // 将minpnode的value替换到pnode的位置
            pnode.value = minpnode.value;
            // 将要删除的位置挪到minpnode位置处
            pnode = minpnode;
            ppnode = minppnode;
        }

        // 场景3经过处理之后，已经跟场景1，场景2可以共用删除逻辑
        // 此时的pnode肯定不会有两个子节点了
        // 将要删除的节点pnode的子节点拼接到pnode原本的位置
        // 有一个特殊状况需要处理，如果此处ppnode是null，表示的是什么意思呢？表示的是删除的是跟节点，并且没有经过场景3的节点交换操作
        // 注释.
        TreeNode child = null != pnode.left?pnode.left:pnode.right;
        if(null == ppnode) {
            root = child;
        } else if(ppnode.left == pnode) {
            // pnode原本在父节点ppnode的左子节点位置
            ppnode.left = child;
        } else {
            // pnode原本在父节点ppnode的右子节点位置
            ppnode.right = child;
        }

    }
}
