package com.pyh.structure.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 类MergeSort的实现描述：归并排序
 *
 * @author panyinghua 2021-5-20 14:56
 */
public class MergeSort {

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = random.ints(30, 100,1000).toArray();
        System.out.println("source:"+ Arrays.toString(a));

        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(a);
        System.out.println("sorted:"+ Arrays.toString(a));
    }

    /**
     * 对数组a进行排序
     * @param a
     */
    public void mergeSort(int[] a) {
        mergeSort(a, 0, a.length-1);
    }


    public void mergeSort(int[] a, int low, int high) {
        // 0.递归的中止条件就是当前要处理的数组只有一个元素
        if(low>=high) {
            return ;
        }

        // 1.先将当前数组进行对半划分
        int mid = (low+high)/2;
        mergeSort(a, low, mid);
        mergeSort(a, mid+1, high);
        // 2.合并排序好的数组
        merge(a,low,mid,mid+1,high);
    }

    /**
     * 将两个有序数组进行合并
     * @param a
     * @param alow
     * @param ahigh
     * @param blow
     * @param bhigh
     */
    private void merge(int[] a, int alow, int ahigh, int blow, int bhigh) {
        int[] tmp = new int[ahigh-alow+1+bhigh-blow+1];
        int k = 0;
        int i=alow;
        int j=blow;
        while(i<=ahigh&&j<=bhigh) {
            // a[i]<=a[j]的时候，优先将a[i]放入到排序数组中，保证了排序算法的稳定性
            if(a[i]<=a[j]) {
                tmp[k]=a[i];
                i++;
            } else {
                tmp[k]=a[j];
                j++;
            }
            k++;
        }
        // while循环结束之后，i、j其中有一个已经到了结束的下标，还有一个可能没到，把没到下标的剩下的数字都填充到tmp数组中
        // 这两个中肯定是只有一个是还没有结束的，互斥
        if(i<=ahigh) {
            // 1.i还没有结束
            while(i<=ahigh) {
                tmp[k++]=a[i++];
            }
        } else {
            // 2.j还没有结束
            while(j<=bhigh) {
                tmp[k++]=a[j++];
            }
        }

        // 将有序的元素复制到tmp数组之后，再将数组tmp回写到数组a中
        int kk=alow;
        for(int tmpi=0;tmpi<tmp.length;tmpi++) {
            a[kk++] = tmp[tmpi];
        }
    }
}
