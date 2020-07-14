package com.pyh.structure.leetcode;

import java.util.*;

/**
 * 类ThreeNum的实现描述：
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-10 17:54
 */
public class ThreeNum {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        //int[] a = {0,0,0,0,0,0,1,2,3};
        int[] a = {-1,0,1,0};
        List<List<Integer>> result = threeSum(a);
        System.out.println(result);
        System.out.println("cost " + (System.currentTimeMillis()-begin) +"ms");
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        // 思路：
        // 1.遍历数组，当前元素a=a[i]，那么可以满足a+b+c=0则b+c=-1=-a[i]
        // 2.在数组中（不包括当前元素a[i]），找出两数和为-a[i]的两个数
        // 3.对a[i],满足条件2中的数组进行组合记为所求a+b+c

        // 辅助查询工具，因为元素可能重复，所以采用list标记元素k锁出现的所有位置
        Map<Integer, List<Integer>> numsMap = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            List<Integer> indexs = numsMap.get(nums[i]);
            if(null == indexs) {
                indexs = new ArrayList<>();
                numsMap.put(nums[i], indexs);
            }
            indexs.add(i);
        }

        Map<Integer, List<Integer>> twoNumMap = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            if(twoNumMap.containsKey(nums[i])) {
                continue;
            }
            findTwoNum(nums, nums[i], i, numsMap, twoNumMap);
        }

        //  从twoNumMap输出结果
        List<List<Integer>> result = new ArrayList<>();
        for(int i: twoNumMap.keySet()) {
            List<Integer> twoNumIndexs = twoNumMap.get(i);
            if(null != twoNumIndexs) {
                int a=i;
                for(int k=0;k<twoNumIndexs.size();k=k+2) {
                    int b = nums[twoNumIndexs.get(k)];
                    int c = nums[twoNumIndexs.get(k+1)];
                    if (a==b && b==c && c==0) {
                        continue; // 为0的情况单独处理
                    }
                    List<Integer> oneResult = genThreeNum(a, b, c);
                    result.add(oneResult);
                }
            }
        }
        if(null != numsMap.get(0) && numsMap.get(0).size()>=3) {
            List<Integer> zero = new ArrayList<>();
            zero.add(0);
            zero.add(0);
            zero.add(0);
            result.add(zero);
        }
        // 如何去重？对于重复元素
        // 对元组中的元素进行排序，再去重

        return result;
    }




    /**
     * 在列表中找两数之和=twoNumTarget的两个数的下标
     * @param nums
     * @param k
     * @param indexOfKey
     * @param numsMap
     * @param twoNumMap
     */
    private static void findTwoNum(int[] nums, int k, int indexOfKey, Map<Integer, List<Integer>> numsMap, Map<Integer, List<Integer>> twoNumMap) {

        List<Integer> twoNumOfKey = twoNumMap.get(k);
        if(null != twoNumOfKey) {
            return ; // 如果这个数字已经存在Map中，则证明k有重复元素，并且之前已经查找过-k=b+c
        }

        Map<Integer, Integer> secondNumMap = new HashMap<>();

        // 在数组中寻找和为-k的两个数，并且这两个数不包括k
        twoNumOfKey = new ArrayList<>();
        for(int i=indexOfKey+1;i<nums.length;i++) {
            // 因为结果要求三元组不能重复，所以此处排除掉indexOfKey
            List<Integer> indexj = numsMap.get(-k-nums[i]); // -k=nums[i]+j,find j
            if(null != indexj) {
                for(Integer index : indexj) {
                    // 按照下标升序加入list，避免重复元组
                    if(index>i && !(twoNumMap.containsKey(nums[i])||twoNumMap.containsKey(nums[index]))) {
                        if(secondNumMap.containsKey(nums[index]) || secondNumMap.containsKey(nums[i])) {
                            continue;
                        }
                        twoNumOfKey.add(i);
                        twoNumOfKey.add(index);
                        secondNumMap.put(nums[i], 1);
                        break;
                    }
                }
            }
        }
        if(twoNumOfKey.size()>0) {
            twoNumMap.put(k, twoNumOfKey);
        }

    }


    private static void process(List<List<Integer>> result) {
        for(List<Integer> list : result) {

        }
    }

    private static List<Integer> genThreeNum(int a, int b, int c) {
        List<Integer> oneResult = new ArrayList<>();
        if(a>b) {
            if(b>c) {
                oneResult.add(c);
                oneResult.add(b);
                oneResult.add(a);
            } else {
                oneResult.add(b);
                if(c>a) {
                    oneResult.add(a);
                    oneResult.add(c);
                } else {
                    oneResult.add(c);
                    oneResult.add(a);
                }
            }
        } else {// b>a
            if(a>c) {
                oneResult.add(c);
                oneResult.add(a);
                oneResult.add(b);
            } else {// c>a
                oneResult.add(a);
                if(b>c) {
                    oneResult.add(c);
                    oneResult.add(b);
                } else {
                    oneResult.add(b);
                    oneResult.add(c);
                }
            }
        }

        return oneResult;
    }
}
