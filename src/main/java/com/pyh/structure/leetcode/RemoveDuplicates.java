package com.pyh.structure.leetcode;

import java.util.Arrays;

/**
 * 类RemoveDuplicates的实现描述：
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-19 17:37
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] a = {1,2,2,2,3};
        System.out.println(Arrays.toString(a));
        int len = new RemoveDuplicates().removeDuplicates2(a);
        int[] b = new int[len];
        System.arraycopy(a, 0, b, 0, len);
        System.out.println(Arrays.toString(b));
    }

    /**
     * 元素最大允许重复元素出现2次
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(null == nums) return 0;
        int len = nums.length;
        if(len<=2) return len;
        int i=0,jb=0,je=0;
        while(je<len&&i<len) {
            // 找到第一个与i位置不相等的元素
            while(je<len&&nums[je]==nums[jb]) {
                je++;
            }
            if(je-jb>=2) {
                nums[i]=nums[jb];
                nums[i+1]=nums[jb];
                i=i+2;
            }else {
                nums[i]=nums[jb];
                i=i+1;
            }
            jb=je;

        }
        return i;
    }

    /**
     * 元素最大允许重复元素出现1次
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if(null == nums) return 0;
        int len = nums.length;
        if(len<=1) return len;
        int i=0,jb=0,je=0;
        while(je<len&&i<len) {
            // 找到第一个与i位置不相等的元素
            while(je<len&&nums[je]==nums[i]) {
                je++;
            }
            if(je<len) {
                // 隐藏的一个逻辑是nums[0]元素不用变更
                nums[++i] = nums[je];

            }
        }
        return i+1;
    }
}
