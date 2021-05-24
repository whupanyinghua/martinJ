package com.pyh.structure.sort;

import com.pyh.structure.heap.BigRootHeap;

import java.util.Arrays;
import java.util.Random;

/**
 * 类HeapSort的实现描述：堆排序
 * 可以使用大根堆来进行排序，总体思想如下：
 * 1.先把数组a[begin...end]构造成一个大根堆
 * 2.大根堆的根节点就是堆中最大的元素
 * 3.将2中大根堆堆顶元素（最大）与堆最后一个元素互换（此时最大元素就到了end的位置）
 * 4.针对a[begin...end-1]重复执行步骤1到步骤4，直到堆中只剩下一个元素
 *
 * 经过步骤3的交换，此时a[begin...end]已经不满足大根堆的性质了；
 * end位置的元素已经是最大，不需要再进行堆调整，那么我们只需要对a[begin...end-1]处理
 *
 * @author panyinghua 2021-5-24 15:58
 */
public class HeapSort {

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = random.ints(30, 100,1000).toArray();
        // a = new int[] {613, 975, 905, 941, 613};
        System.out.println("source:"+ Arrays.toString(a));

        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(a);
        System.out.println("sorted:"+ Arrays.toString(a));
    }

    public void heapSort(int[] a) {
        heapSort(a, 0, a.length-1);
    }


    private void heapSort(int[] a, int begin, int end) {
        // 1. 构建大根堆
        BigRootHeap heap = new BigRootHeap();
        // 2.自上而下的方式构建大根堆
        heap.buildHeap(a, begin, end);

        // 3.堆顶元素与堆最后一个元素互换，再重复执行
        int k = end; // k的位置表示的是堆的最后一个元素
        while(k>begin) {
            swap(a, begin, k);
            k--;
            // 针对a[begin...end-1]调整
            heap.heapifyWithUpToDown(a, begin, k);
        }

    }

    private void swap(int[] nums, int start, int parent) {
        int tmp = nums[start];
        nums[start] = nums[parent];
        nums[parent] = tmp;
    }
}
