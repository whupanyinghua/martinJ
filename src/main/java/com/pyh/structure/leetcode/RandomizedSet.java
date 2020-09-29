package com.pyh.structure.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 类RondomizedSet的实现描述：设计一个支持在 平均时间复杂度O(1)的数据结构
 *
 * @author panyinghua 2020-9-29 20:29
 */
public class RandomizedSet {

    private int[] nums = new int[10000];
    // size表示当前nums数组中具有元素的个数
    private int size = 0;
    private Map<Integer, Integer> valToIndexMap = new HashMap<>();

    // 随机种子
    private Random random = new Random();


    public boolean insert(int val) {
        if(valToIndexMap.containsKey(val)) {
            return false;
        }

        // 在当前元素末尾添加元素
        nums[size] = val;
        valToIndexMap.put(val, size);
        size++;

        return true;
    }

    public boolean remove(int val) {
        int index = valToIndexMap.getOrDefault(val, -1);
        if(-1 == index) {
            // 待删除的元素不在数组总
            return false;
        }

        // 将待删除的元素（index下标）与当前数组的最后一个元素进行交换，再删除最后一个元素(可以直接将最后一个元素置为0表示删除)
        valToIndexMap.put(nums[size-1], index);
        valToIndexMap.remove(val);
        nums[index] = nums[size-1];
        nums[size-1] = 0;

        size--;

        return true;
    }

    public int getRandom() {
        // 随机数对size取模作为取数的下标，达到随机获取元素的目的
        return nums[random.nextInt()%size];
    }
}
