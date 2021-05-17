package com.pyh.structure.leetcode.tree;

/**
 * 类ConstructMaximumBinaryTree的实现描述：构造大堆，其实这个构造方式跟快排的思想很类似，在一个区间中先找到最大的元素，再递归处理左区间、右区间
 *
 * @author panyinghua 2020-9-27 16:43
 */
public class ConstructMaximumBinaryTree {

    public static void main(String[] args) {
        int[] n = {3,2,1,6,0,5};
        ConstructMaximumBinaryTree tree = new ConstructMaximumBinaryTree();
        TreeNode root = tree.constructMaximumBinaryTree(n);
        System.out.println(root);
    }


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(null == nums) {
            return null;
        }

        int len = nums.length;
        if(0==len) {
            return null;
        }

        return buildMaxTree(nums, 0, len-1);

    }

    private TreeNode buildMaxTree(int[] nums, int begin, int end) {
        if(begin>end) {
            // 越界的begin end，则直接返回null，座位递归的终结条件
            return null;
        }
        if(begin==end) {
            // 只有一个节点，那么根节点就是自己，可以无需进入下一层的递归
            return new TreeNode(nums[begin]);
        }

        // 1.先找到当前范围内最大的元素
        int index = begin;
        int maxValue = nums[begin];
        for(int i=begin;i<=end;i++) {
            if(nums[i]>maxValue) {
                maxValue = nums[i];
                index = i;
            }
        }

        // 2.根节点构建
        TreeNode root = new TreeNode(maxValue);
        // 3.递归处理左节点
        root.left = buildMaxTree(nums, begin, index-1);
        // 4.递归处理右节点
        root.right = buildMaxTree(nums, index+1, end);

        // 5.返回当前区间的根节点
        return root;
    }
}
