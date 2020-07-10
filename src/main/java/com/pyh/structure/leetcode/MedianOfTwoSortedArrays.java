package com.pyh.structure.leetcode;

/**
 * 类MedianOfTwoSortedArrays的实现描述：在两个升序数组中找中位数
 *
 * @author panyinghua 2020-7-9 18:03
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] a = {1,3};
        int[] b = {2,4,5,6};
        System.out.println("中位数为"+findMedianOfTwoSortedArrays(a,b));
    }


    /**
     * 在两个升序数组中找中位数
     * @param nums1 数组1，此处规定数组1的长度小于等于数组2的长度
     * @param nums2 数组2
     * @return
     */
    public static double findMedianOfTwoSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length) {
            return findMedianOfTwoSortedArrays(nums2, nums1);
        }


        /**
         * 假定nums1的长度为m，nums2的长度为n, m<=n
         * 将两个数组分别按照i,j进行中位数进行划分,左边部分的值都小于或等于右边部分的值
         *  left_part    ||     right_part
         * nums1[0~i-1]  ||    nums1[i~m-1]
         * nums2[0~j-1]  ||    nums2[j~n-1]
         * 那么则有如下规则
         * 1.len(left_part)=len(right_part) --m+n为偶数  len(left_part)=len(right_part)+1 --m+n为奇数
         * 2.max(left_part)<=min(right_part)
         * 那么此时的中位数为
         * median=(max(left_part)+min(right_part))/2 --m+n为偶数
         * median=max(left_part) --m+n为奇数
         * 那么要满足上诉条件，我们可以推导如下信息
         * 左边的元素个数=右边的元素个数
         * 偶数：i+j=m-i+n-j 即 j=(m+n)/2 - i  奇数： i+j=m-i+n-j+1 即j=(m+n+1)/2-i，两个等式等价于 j=(m+n+1)/2-i
         * nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i] ，这两个等式经过数学证明（哈哈哈，此处不写想法，期待后续自己能证明）等价于
         * 两个等式等价于 nums1[i-1]<=nums2[j]，其中 j=(m+n+1)/2-i
         * 接下来的问题就是在nums1中找到最大的i使上述等式成立即可
         *
         */
        int m=nums1.length,n=nums2.length;
        int medianLeft=0,medianRight=0;
        int left = 0;
        int right = m; // 因为基于数学方法允许边界m数学存在，所以此处right直接取m，而不是m-1
        while(left<=right) {
            int i = (left+right)/2;
            int j=(m+n+1)/2-i;
            // 边界条件的考虑，假设 num1[]
            int num1i   = i==m?Integer.MAX_VALUE:nums1[i];
            int num1i_1 = i==0?Integer.MIN_VALUE:nums1[i-1];
            int num2j   = j==n?Integer.MAX_VALUE:nums2[j];
            int num2j_1 = j==0?Integer.MIN_VALUE:nums2[j-1];

            if(num1i_1 <= num2j) {
                medianLeft = Math.max(num1i_1, num2j_1);
                medianRight = Math.min(num1i, num2j);
                // 当前位置满足之前推导的公式，但是不代表此时是最大的i，需要再往下找找试试，二分查找右边元素即边界left设置为i+1
                left = i+1;
            } else {
                right = i-1;
            }
        }

        return (m+n)%2==0?((medianLeft+medianRight)/2.0):medianLeft;
    }

}
