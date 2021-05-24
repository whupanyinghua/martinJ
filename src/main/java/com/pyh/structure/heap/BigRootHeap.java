package com.pyh.structure.heap;

/**
 * 类BigRootHeap的实现描述：大根堆
 * 根节点大于等于子节点
 *
 * @author panyinghua 2021-5-21 16:31
 */
public class BigRootHeap {
    private int count;
    private int capacity;
    private int[] nums;

    public BigRootHeap() {

    }

    public BigRootHeap(int cap) {
        count=0;
        capacity = cap;
        nums = new int[cap];
    }

    /**
     * 把数组a[begin...end]构建成一个大根堆
     * 有两种方式：
     * 1.自上往下调整堆的方式
     * 2.自下往上调整堆的方式 (这种方式参见add方法)
     * @param a
     */
    public void buildHeap(int[] a, int begin, int end) {
        count = end-begin+1;
        capacity = count;
        nums = a;

        // 使用1.自上往下的构造方式
        // 1.1 首先我们要先找到最后一个叶子节点，以及叶子节点对应的父节点
        int lastl = end; // 最后一个叶子节点就是数组的最后一个元数
        int lastp = (lastl-1)/2; // 最后䘝叶子节点对应的父节点
        // 1.2 那么可以推导出，从lastp+1到lastl都是叶子节点（为什么？数组表示数的结构中，树肯定都是满二叉树，结合满二叉树的定义来看，除了叶子节点那层，树的所有层次节点都是满的）
        // 1.3 那么我们只需要针对所有的非叶子节点进行倒着遍历，调整堆的结构即可。叶子节点不需要我们操作
        for(int i=lastp;i>=begin;i--) {
            // 经过自上往下的调整之后，以i为根节点的子树都是满足大根堆的性质了
            heapifyWithUpToDown(a, i, lastl);
        }
        // 1.4 for循环执行结束之后，整个数组就都是大根堆了哈

    }

    /**
     * 与 小根堆 相比较，大根堆中保存的元素为历史添加中最小的一批数
     * @param num
     */
    public void add(int num) {
        if(count<capacity) {
            // 此时数组内元素还没有达到容量，可以视为直接添加元素
            nums[count]=num;
            count++;
            // 调整堆
            heapifyWithDownToUp(nums,count,count);

        } else if(num<nums[0]) {
            // 判断当前元素num是否可以加到到堆中
            // 当前要添加的元素比大根堆堆顶的元素还要小，那么直接将堆顶元素替换为当前元素，再调整大根堆
            nums[0] = num;
            count++;
            // 调整堆
            heapifyWithUpToDown(nums, 0, count);
        } else {
            // 当前元素比小根堆中所有元素都大，无需添加
        }
    }

    /**
     * 删除大根堆中最大的元素，也就是删除堆顶元素了
     */
    public void deleteMax() {
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
    public void heapifyWithDownToUp(int[] nums, int start, int cap) {

        int parent = 0;
        int begin = start;
        while(begin>0) {
            parent = (begin-1)/2;
            if(nums[begin]>nums[parent]) {
                swap(nums, begin, parent);  // 当前节点比父节点大，则交换节点的值；如果是等于则不用互换了，因为根节点大于等于子节点是满足大根堆的性质
            }
            begin = parent;
        }
    }

    /**
     * 自上往下调整大跟堆
     * @param nums
     * @param start 要调整的原始的起始位置
     * @param end 结束的位置，一般就是数组的大小
     */
    public void heapifyWithUpToDown(int[] nums, int start, int end) {
        int begin = start;
        while(true) {
            // 记住本次循环中最大的节点所在的位置
            int maxPos = begin;
            if(2*begin+1<=end && nums[2*begin+1]>nums[begin]) {
                // 左子节点
                maxPos = 2*begin+1;
            }
            if(2*begin+2<=end && nums[2*begin+2]>nums[maxPos]) {
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
