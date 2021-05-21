package com.pyh.structure.heap;

/**
 * 类SmallRootHeap的实现描述：小根堆
 * 根节点小于等于子节点
 *
 * @author panyinghua 2021-5-21 16:32
 */
public class SmallRootHeap {
    private int count;
    private int capacity;
    private int[] nums;

    public SmallRootHeap(int cap) {
        count=0;
        capacity = cap;
        nums = new int[cap];
    }

    public void add(int num) {
        if(count<capacity) {
            // 此时数组内元素还没有达到容量，可以视为直接添加元素
            nums[count]=num;
            count++;
            // 调整堆
            heapifyWithDownToUp(nums,count,count);

        } else if(num>nums[0]) {
            // 判断当前元素num是否可以加到到堆中
            // 当前要添加的元素比小根堆堆顶的元素还要大，那么直接将堆顶元素替换为当前元素，再调整小根堆
            nums[0] = num;
            count++;
            // 调整堆
            heapifyWithUpToDown(nums, 0, count);
        } else {
            // 当前元素比小根堆中所有元素都小，无需添加
        }
    }

    /**
     * 删除小根堆中最小的元素，也就是删除堆顶元素了
     */
    public void deleteMin() {
        if(count==0) {
            // 没有元素，无需任何操作
            return ;
        } else if(count==1) {
            // 只有一个元素，注意，这里不能直接用后边多个元素的替换方式，因为最后一个元素也就是第一个元素
            // 直接把第一个元素赋值为0，然后对计数进行-1操作，也就是此时count=0
            nums[0]=0;
            count--;
            return ;
        }

        // 将最后一个元素赋值到第一个元素，然后调整堆，达到删除堆的第一个元素的目的
        nums[0] = nums[count-1];
        count--;

        heapifyWithUpToDown(nums,0,count);

    }

    public int[] getNums() {
        return nums;
    }

    /**
     * 从下往上调整小根堆
     * @param nums
     * @param start 要调整的元素的位置
     * @param cap 数组的大小
     */
    private void heapifyWithDownToUp(int[] nums, int start, int cap) {

        int parent = 0;
        int begin = start;
        while(begin>0) {
            parent = (begin-1)/2;
            if(nums[begin]<nums[parent]) {
                swap(nums, begin, parent);  // 当前节点比父节点小，则交换节点的值；如果是等于则不用互换了，因为根节点小于等于子节点是满足小根堆的性质
            }
            begin = parent;
        }
    }

    /**
     * 自上往下调整小跟堆
     * @param nums
     * @param start 要调整的原始的起始位置
     * @param end 结束的位置，一般就是数组的大小
     */
    private void heapifyWithUpToDown(int[] nums, int start, int end) {
        int begin = start;
        while(true) {
            // 记住本次循环中最大的节点所在的位置
            int maxPos = begin;
            if(2*begin+1<=end && nums[2*begin+1]<nums[begin]) {
                // 左子节点
                maxPos = 2*begin+1;
            }
            if(2*begin+2<=end && nums[2*begin+2]<nums[begin]) {
                // 右子节点
                maxPos = 2*begin+2;
            }

            if(maxPos==begin) {
                // 没有交换，则表示当前树已经满足，可以直接终止循环
                break;
            }
            swap(nums, begin, maxPos);
            begin = maxPos;
            // 此处可以增加判断，如果begin已经到了最后的元素的位置end，则无需进行循环，
            // 或者可以递归进入到下一层循环，因为begin已经达到end，则下次循环之后肯定进入 if(maxPos==begin) 的判断
        }
    }

    private void swap(int[] nums, int start, int parent) {
        int tmp = nums[start];
        nums[start] = nums[parent];
        nums[parent] = tmp;
    }

}
