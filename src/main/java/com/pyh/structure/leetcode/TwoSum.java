package com.pyh.structure.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 类TwoSum的实现描述：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-10 14:13
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }


    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> numsMap = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            numsMap.put(nums[i], i);
        }
        for(int i=0;i<nums.length;i++) {
            // 每个元素只能用一次
            if(null != numsMap.get(target-nums[i]) && i!=numsMap.get(target-nums[i])) {
                result[0] = i;
                result[1] = numsMap.get(target-nums[i]);
                break;
            }
        }
        return result;
    }
}
