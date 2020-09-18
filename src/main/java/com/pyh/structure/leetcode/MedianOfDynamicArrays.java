package com.pyh.structure.leetcode;

import java.util.PriorityQueue;

/**
 * 类MedianOfDanamicArrays的实现描述：在一个不停输入的可变数组中快速找到中位数
 *
 * @author panyinghua 2020-9-18 17:41
 */
public class MedianOfDynamicArrays {

    // small、large两个堆存储各一半的元素，如果元素总数为奇数，则small比large多存储1个元素
    // 注意，small中的所有元素都小于或等于large中的任意元素
    // 大根堆格式，存储整个数组中比较小的一半元素，比较小的堆中默认存储 N/2+1个元素
    private PriorityQueue<Integer> small = new PriorityQueue<>((a,b) -> b-a);
    // 小根堆格式，存储整个数组中比较大的一半元素，比较大的堆中默认存储N/2个元素
    private PriorityQueue<Integer> large = new PriorityQueue<>();


    public static void main(String[] args) {
        MedianOfDynamicArrays median = new MedianOfDynamicArrays();
        System.out.println("median " + median.findMedian());
        median.addNum(1);
        System.out.println("median " + median.findMedian());
        median.addNum(3);
        System.out.println("median " + median.findMedian());
        median.addNum(2);
        System.out.println("median " + median.findMedian());
        median.addNum(4);
        System.out.println("median " + median.findMedian());
    }


    /**
     * 往数组中加一个元素
     * @param num
     */
    public void addNum(int num) {
        // 思路就是在添加元素的时候，到底是往small中添加，还是往large中添加元素
        if(small.size()<=large.size()) {
            // small堆中的元素个数小于或者等于large堆，则往small堆中添加元素
            // 注意，因为添加元素会破坏small、large的元素约定，所以在添加元素的时候需要特殊处理下
            // 往small中添加元素num的时候，需要num与large中最小的元素进行比较，如果num<=min(large)，那么把num加入到small中
            // 否则，将min(large)从large中取出并且加入small，然后将num加入到large中
            //int minLarge = large.peek(); // 当small与large中都没有元素的时候，large.peek()会异常，因此if价格判断small.size()==0
            if(small.size()==0 || num<=large.peek()) {
                small.offer(num);
            } else {
                large.poll();
                large.offer(num);
                small.offer(large.peek());
            }
        } else {
            // small堆中的元素个数大于large堆，则往large堆中添加元素，处理规则与往small堆中添加元素类似
            // 往large中添加元素num的时候，需要num与small中最大的元素进行比较，如果num>max(small)，那么把num加入到large中
            // 否则，将max(small)从small中取出并且加入large，然后将num加入到small中
            int maxSmall = small.peek();
            if(num>maxSmall) {
                large.offer(num);
            } else {
                small.poll();
                small.offer(num);
                large.offer(maxSmall);
            }
        }

    }

    /**
     * 找出当前已经存在的数字中的中位数
     * @return
     */
    public double findMedian() {
        if(small.size()==0) {
            return 0;
        }

        // small中元素比较多，则直接取出队列的首个元素即为中位数
        if(small.size()>large.size()) {
            return small.peek();
        }

        return (small.peek()+large.peek())/2.0;
    }
}
