package com.pyh.structure.leetcode;

/**
 * 类FindErrorNums的实现描述：@TODO
 *
 * @author panyinghua 2020-9-16 19:31
 */
public class FindErrorNums {


    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        int dup = -1;
        int missing = -1;

        //  映射关系：nums[i]-1 -> index
        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(nums[i])-1;
            if(nums[index]<0) {
                // 如果检测到nums[index]这个位置已经是负数，那么表示当前索引index对应的元素是重复访问，那么nums[i]就是重复元素
                dup = nums[i];
            } else {
                // 首次访问，将对应的元素置为对应的负数
                nums[index] *= -1;
            }
        }

        // 找到缺失的元素
        // 没有被访问过的index就是缺失元素，没有被访问过的index，就是index对应的元素大于0
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>0) {
                //  映射关系：nums[i]-1 -> index，则missing=index+1
                // 映射关系是通过nums[i]-1的方式来访问元素，那么在元素大于0的这个位置，对应的索引是i，则对应的缺失的数字是i+1
                missing = i+1;
            }
        }

        result[0] = dup;
        result[1] = missing;
        return result;
    }
}
