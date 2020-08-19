package com.pyh.structure.leetcode.doublept;

import com.pyh.structure.Sorts;

/**
 * 类ThreeSumClosest的实现描述：
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *[1,2,4,8,16,32,64,128]
 * 82
 * @author panyinghua 2020-7-21 16:34
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] a = {1,2,4,8,16,32,64,128};
        int target = 82;
        System.out.println(new ThreeSumClosest().threeSumClosest(a, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        if(null == nums || nums.length==0)
            return 0;
        if(nums.length<=3) {
            int sum = 0;
            for(int i=0;i<nums.length;i++)
                sum+=nums[i];
            return sum;
        }

        // 1.排序 2.双指针大法
        Sorts.quickSort(nums);
        int length = nums.length;
        int sum=nums[0]+nums[1]+nums[2]; // 先初始化一个sum的值，避免下方的比较中使用不当的0值
        int j=0,k=0;
        for(int i=0;i<length-2;i++){
            j=i+1;
            k=length-1;
            while(j<k) {
                int tmp = nums[j] + nums[k] + nums[i];
                if (tmp == target) {
                    return target; // 相等的话直接返回，相等就是最优解
                }
                // 判断当前的和是否更接近于target
                if(Math.abs(target-tmp) <= Math.abs(target-sum)) {
                    sum = tmp;
                }

                if (tmp > target) {
                    // tmp比target的值大，则向左移动指针去找可能存在的更接近target的值
                    k--;
                } else {
                    // tmp比target的值小，则向右移动指针去找可能存在的更接近target的值
                    j++;
                }
            }
        }
        return sum;
    }
}
