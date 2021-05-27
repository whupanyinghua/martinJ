package com.pyh.structure.leetcode;

import com.pyh.structure.sort.Sorts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类NNum的实现描述：在一个数组内，给定一个值，该值等于有数组内的N个元素相加。
 * 给定值Sn=a[i]+a[j]+a[k]+...+a[m]，n表示右边的元素个数为n，求出所有的元素组合，并且元组不能重复
 *
 * @author panyinghua 2020-7-15 10:26
 */
public class NNum {

    public static void main(String[] args) {
        int[] a = {-1,0,1,2,-1,-4};
        NNum nNum = new NNum();
        System.out.println(nNum.getNNum(a, -1, 4));
    }

    public List<List<Integer>> getNNum(int[] a, int nsum, int n) {
        Sorts.quickSort(a);
        return nNum(a, nsum, n, 0);
    }

    /**
     * 在升序排序后的数组中查找n个元素，这n个元素的和等于nsum
     * 想想为什么要排序，是因为排序之后求解的得出的元组比较方便进行去重
     * 如果给定的n为2可以不用排序，直接使用特殊的TwoSum求解方式
     * @param a 升序排序的数组
     * @param nsum
     * @param n
     * @param begin
     * @return
     */
    public List<List<Integer>> nNum(int[] a, int nsum, int n, int begin) {
        List<List<Integer>> result = new ArrayList<>();
        if(n<2 || a.length<n) {
            return result; // 求和的数组长度大于或等于所求元素n个数；n>=2
        }
        if(n > 2) {
            // 如果求解的元组个数要求大于2，则递归求解,并组装结果
            for(int i=begin;i<a.length-2;i++) {
                List<List<Integer>> res = nNum(a, nsum-a[i], n-1, i+1);
                if(res.size()>0) {
                    for(List<Integer> list : res) {
                        list.add(0, a[i]);
                        result.add(list);
                    }
                }
                // 重复的元素仅计算一次即可
                while(i<a.length-1 && a[i]==a[i+1]) {
                    i++;
                }
            }
        } else {
            // 从begin开始的数组中查找两个数之和等于nsum，双指针方式
            int start = begin;
            int end = a.length-1;
            while(start<end) {
                int left = a[start];
                int right = a[end];
                int sum = a[start]+a[end];
                if(sum<nsum) {
                    start++;
                    // 去掉重复的元素
                    while(start<end && a[start]==left) start++;
                } else if(sum>nsum) {
                    end--;
                    // 去掉重复的元素
                    while(start<end && a[end]==right) end--;
                } else {
                    // a1+a2=nsum
                    List<Integer> list = new ArrayList<>();
                    list.add(a[start]);
                    list.add(a[end]);
                    result.add(list);
                    // 去掉重复的元素
                    while(start<end && a[start]==left) start++;
                    while(start<end && a[end]==right) end--;
                }
            }
        }

        return result;
    }


    /**
     * 使用双指针的方式计算两数之和s=a+b
     * @param a
     * @param nsum
     * @param n
     * @param begin
     * @return
     */
    private List<List<Integer>> twoNum(int[] a, int nsum, int n, int begin) {
        List<List<Integer>> result = new ArrayList<>();
        int start = begin;
        int end = a.length-1;
        while(start<end) {
            int left = a[start];
            int right = a[end];
            int sum = a[start]+a[end];
            if(sum<nsum) {
                start++;
                // 去掉重复的元素
                while(start<end && a[start]==left) start++;
            } else if(sum>nsum) {
                end--;
                // 去掉重复的元素
                while(start<end && a[end]==right) end--;
            } else {
                // a1+a2=nsum
                List<Integer> list = new ArrayList<>();
                list.add(a[start]);
                list.add(a[end]);
                result.add(list);
                // 去掉重复的元素
                while(start<end && a[start]==left) start++;
                while(start<end && a[end]==right) end--;
            }
        }

        return result;
    }

    /**
     * 使用map的方式计算两数之和，s=a+b
     * @param a
     * @param nsum
     * @param n
     * @param begin
     * @return
     */
    private List<List<Integer>> spTwoNum(int[] a, int nsum, int n, int begin) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> aMap = new HashMap<>();
        int end = a.length;
        while(begin<a.length) {
            int a1=a[begin];
            if(aMap.containsKey(nsum-a1)) {
                List<Integer> list = new ArrayList<>();
                list.add(nsum-a1); // 因为在外层while循环中aMap是先判断是否存在nsum-a1的元素，如果存在，那么nsum-a1元素肯定是之前的循环中添加的元素，所以nsum-a1肯定小于或者等于a[begin]
                list.add(a1);
                result.add(list);
                aMap.put(a[begin], begin++);
                // 使用过该元素之后，可以将后续重复的元素跳过
                while(begin<a.length && a1==a[begin]) {
                    begin++;
                }
            } else {
                aMap.put(a[begin], begin++);
            }
        }

        return result;
    }
}
