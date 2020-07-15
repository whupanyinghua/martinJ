package com.pyh.structure;

import java.util.Arrays;
import java.util.Random;

/**
 * 类Sorts的实现描述：排序算法
 *
 * @author panyinghua 2020-5-8 13:53
 */
public class Sorts {
    public static void main(String[] args) {
        Random random = new Random();
        int[] a = random.ints(30, 100,1000).toArray();
        System.out.println("source:"+Arrays.toString(a));
        quickSort(a);
        System.out.println("sorted:"+Arrays.toString(a));
        // quickSort(null);
    }

    /**
     * 快速排序
     * @param a
     */
    public static void quickSort(int[] a) {
        if(null == a || a.length<=1)
            return;

        int length = a.length;
        int begin = 0;
        par(a, begin, length-1);
    }

    private static void par(int[] a, int low, int high) {
        if(low==high)
            return ;

        int start = low;
        int end = high;
        int key = a[start]; // 取首个元素为key
        while(start<end) {
            while(start<end && a[end]>=key)
                end--;  // 从右往左找到第一个比key小的元素，该循环结束时end对应的元素为比key小的元素
            while(start<end && a[start]<=key)
                start++;  // 从左往右找到第一个比key大的元素，该循环结束时start对应的元素为比key大的元素
            if(start!=end) {
                // 找到了可以交换的元素，start end元素互换
                int tmp = a[start];
                a[start] = a[end];
                a[end]=tmp;
            } else {
                // start end下标一样大的时候,此时的外部循环可以结束了，可以把此时的start或者end的元素与key互换
                // 也可以放到最外层while循环的外面
                /*int tmp = a[start];
                a[low] = tmp;
                a[start] = key;*/
            }
        }
        // 循环结束之后把key放到合适的位置
        a[low] = a[start];
        a[start] = key;

        // 循环结束之后，此时的key已经处在合适的位置，在key的新位置左边的元素都比key小，在key的新位置右边的元素都比key大
        // 再递归对左边部分、右边部分进行par操作
        // 注意死循环，需要加判断，因为par的开头只判断了low==high才结束递归
        if(start>low)
            par(a, low, start - 1);
        if(end<high)
            par(a, end + 1, high);
    }
}
