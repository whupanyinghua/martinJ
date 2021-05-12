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
        //quickSort(a);
        quickSortM(a);
        System.out.println("sorted:"+Arrays.toString(a));
        // quickSort(null);
    }

    private static void quickSortM(int[] a) {
        int len = a.length;
        quickSortInternal(a,0,len-1);
    }

    private static void quickSortInternal(int[] a, int low, int high) {
        if(low<high) {
            int mid = partition(a, low, high);
            quickSortInternal(a, low, mid-1);
            quickSortInternal(a, mid+1,high);
        }
    }

    private static int partition(int[] a, int begin, int end) {
        int key = a[begin];

        int low=begin;
        int high=end;
        // 1.思考，要不要等号 (如果需要等号，while循环里边判断的都没有等号的范围)
        // 2.思考，为什么key使用数组中最左边的值的时候，我们需要先从右边开始查找比key小的元素，不能从左边开始找比key大的元素吗？
        while(low<high) {
            while(low<high && a[high]>=key) {
                high--;
            }
            while(low<high && a[low]<=key) {
                low++;
            }
            if(low<high) {
                swap(a, low, high);
            }
        }
        //  注意在从左往右查找的时候，是找到笔key小的元素才互换，所以现在a[begin]还是key的值
        // 此时索引i的对应值有两种情况
        // 1.索引i的对应值是一个比key小（i>begin）
        // 2.索引i的对应值应该就等于key (i=begin)
        // 交换 i begin的值
        swap(a, begin, low);
        // 位置i就是key此时的位置
        return low;
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
        if(low>=high)
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

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
