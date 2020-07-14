package com.pyh.structure.leetcode;

/**
 * 类MaxArea的实现描述：
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-7-14 14:28
 */
public class MaxArea {
    public static void main(String[] args) {
        int[] a={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
    }

    /**
     * 思路：其实就是找到两个数距离最远，并且差值最近
     * 1.暴力方式：双层for循环
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int result = 0;
        for(int i=0;i<height.length-1;i++) {
            for(int j=i+1;j<height.length;j++) {
                int tmp = calcArea(i,height[i],j,height[j]);
                if(tmp > result) {
                    result = tmp;
                }
            }
        }
        return result;
    }

    /**
     * 双指针思路
     *
     * @param height
     * @return
     */
    public static int maxAreaNew(int[] height) {
        int begin = 0,end=height.length-1;
        // 先计算首尾元素之间的面积，然后从两头开始往中间查找
        int result = 0;
        while(begin<end) {
            int tmp = calcArea(begin, height[begin], end, height[end]);
            if(tmp>result) {
                result=tmp;
            }
            // 移动比较小的边界
            if(height[begin]<=height[end]) {
                begin++;
            } else {
                end--;
            }
        }
        return result;
    }

    private static int calcArea(int i, int hi, int j, int hj) {
        return (j-i)*Math.min(hi,hj);
    }
}
