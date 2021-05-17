package com.pyh.structure.leetcode.tree;

/**
 * 类InvertTree的实现描述：
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
