package com.pyh.structure;

/**
 * 类BinarySearch的实现描述：二分查找以及相关变形问题
 *
 * @author panyinghua 2021-5-7 11:00
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,5,7,9,11};
        System.out.println("[binarySearch]key 10 index is: "+binarySearch(nums,10));
        System.out.println("[binarySearch]key 5 index is: "+binarySearch(nums,5));
        System.out.println("[binarySearchLeft]key 5 index is: "+binarySearchLeft(nums,5));
        System.out.println("[binarySearchRight]key 5 index is: "+binarySearchRight(nums,5));
        System.out.println("[binarySearchFirstBigger]key 5 index is: "+binarySearchFirstBigger(nums,5));
        System.out.println("[binarySearchFirstBigger]key 4 index is: "+binarySearchFirstBigger(nums,4));
        System.out.println("[binarySearchLastSmaller]key 5 index is: "+binarySearchLastSmaller(nums,5));
        System.out.println("[binarySearchLastSmaller]key 6 index is: "+binarySearchLastSmaller(nums,6));
    }

    /**
     * 二分查找，查找key是否在数组中，存在则返回数字在数组中的下标,如果不存在，则返回-1
     * @param nums 待查找的数组，注意，数组一定是要排序好的，并且是从小到大排序
     * @param key 要查找的key
     * @return
     */
    public static int binarySearch(int[] nums, int key) {
        int len = nums.length;
        return binarySearchInternal(nums,0,len-1, key);
    }

    public static int binarySearchLeft(int[] nums, int key) {
        int len = nums.length;
        return binarySearchLeftInternal(nums,0,len-1, key);
    }

    public static int binarySearchRight(int[] nums, int key) {
        int len = nums.length;
        return binarySearchRightInternal(nums,0,len-1, key);
    }

    public static int binarySearchFirstBigger(int[] nums, int key) {
        int len = nums.length;
        return binarySearchFirstBiggerInternal(nums,0,len-1, key);
    }

    public static int binarySearchLastSmaller(int[] nums, int key) {
        int len = nums.length;
        return binarySearchLastSmallerInternal(nums,0,len-1, key);
    }

    /**
     * 二分查找，查找key是否在数组中，存在则返回数字在数组中的下标,如果不存在，则返回-1
     * @param nums
     * @param begin
     * @param end
     * @param key
     * @return
     */
    private static int binarySearchInternal(int[] nums, int begin, int end, int key) {
        int result = -1;
        int low = begin;
        int high = end;

        // 注意循环条件为啥是小于等于，考虑到如果当前begin=end的时候，也就是数组范围当前只有一个元素的时候，也需要进入循环进行判断
        while(low<=high) {
            // int mid = (high+low)/2 中high+low有溢出风险，所以可以换个写法，考虑将+号转换称-号的写法 low+(high-low)/2
            // 其实对于后边的除以2，还可以使用移位来运算 low+((high-low)>>1)  (需要注意右移符号的优先级是比+号小的，所以需要加括号)
            int mid = low+(high-low)/2;
            if(nums[mid]==key) {
                return mid;
            } else if(nums[mid]>key) {
                // mid下标的元素大于key，那么key只能在[low...mid-1]的范围内
                high = mid-1;
            } else if(nums[mid]<key) {
                // mid下标的元素小于key，那么key只能在[mid+1,high]的范围内
                low = mid+1;
            }
        }

        return result;
    }

    /**
     * 如果数组中有多个等于key的元素，返回最左边的一个元素的下标
     * @param nums
     * @param begin
     * @param end
     * @param key
     * @return
     */
    private static int binarySearchLeftInternal(int[] nums, int begin, int end, int key) {
        int result = -1;
        int low = begin;
        int high = end;

        while(low<=high) {
            // int mid = (high+low)/2 中high+low有溢出风险，所以可以换个写法，考虑将+号转换称-号的写法 low+(high-low)/2
            // 其实对于后边的除以2，还可以使用移位来运算 low+((high-low)>>1)  (需要注意右移符号的优先级是比+号小的，所以需要加括号)
            int mid = low+(high-low)/2;
            if(nums[mid]==key) {
                // mid下标的元素等于key，那么在[low...mid-1]的范围内再搜索下还有没有其他相等的key（因为要求解的是最左边的等于key的元素）
                // 注意与binarySearchRightInternal的区别，一个是更新low，一个是更新high
                result = mid;
                high = mid-1;
            } else if(nums[mid]>key) {
                // mid下标的元素大于key，那么key只能在[low...mid-1]的范围内
                high = mid-1;
            } else if(nums[mid]<key) {
                // mid下标的元素小于key，那么key只能在[mid+1,high]的范围内
                low = mid+1;
            }
        }

        return result;
    }

    /**
     * 如果数组中有多个等于key的元素，返回最右边的一个元素的下标
     * @param nums
     * @param begin
     * @param end
     * @param key
     * @return
     */
    private static int binarySearchRightInternal(int[] nums, int begin, int end, int key) {
        int result = -1;
        int low = begin;
        int high = end;

        while(low<=high) {
            // int mid = (high+low)/2 中high+low有溢出风险，所以可以换个写法，考虑将+号转换称-号的写法 low+(high-low)/2
            // 其实对于后边的除以2，还可以使用移位来运算 low+((high-low)>>1)  (需要注意右移符号的优先级是比+号小的，所以需要加括号)
            int mid = low+(high-low)/2;
            if(nums[mid]==key) {
                // mid下标的元素等于key，那么在[mid+1...high]的范围内再搜索下还有没有其他相等的key（因为要求解的是最右边的等于key的元素）
                // 注意与binarySearchLeftInternal的区别，一个是更新low，一个是更新high
                result = mid;
                low = mid+1;
            } else if(nums[mid]>key) {
                // mid下标的元素大于key，那么key只能在[low...mid-1]的范围内
                high = mid-1;
            } else if(nums[mid]<key) {
                // mid下标的元素小于key，那么key只能在[mid+1,high]的范围内
                low = mid+1;
            }
        }

        return result;
    }

    /**
     * 数组中第一个大于等于给定值key的元素下标
     * @param nums
     * @param begin
     * @param end
     * @param key
     * @return
     */
    private static int binarySearchFirstBiggerInternal(int[] nums, int begin, int end, int key) {
        int result = -1;
        int low = begin;
        int high = end;

        while(low<=high) {
            // int mid = (high+low)/2 中high+low有溢出风险，所以可以换个写法，考虑将+号转换称-号的写法 low+(high-low)/2
            // 其实对于后边的除以2，还可以使用移位来运算 low+((high-low)>>1)  (需要注意右移符号的优先级是比+号小的，所以需要加括号)
            int mid = low+(high-low)/2;
            if(nums[mid]==key) {
                // mid下标的元素等于key，那么在[low...mid-1]的范围内再搜索下还有没有其他相等的key（因为要求解的是第一个大于等于给定值key的元素，所以在左边区间再搜索下）
                // 需要先记录下现在满足条件的下标，进入下一个循环的时候如果还满足条件，下一个循环中的下标是比当前下标更小，会用新值覆盖，如果下一轮循环没有符合条件的值，那么当前结果就是最终结果
                result = mid;
                high = mid-1;
            } else if(nums[mid]>key) {
                // mid下标的元素大于key，那么在[low...mid-1]的范围内再搜索下还有没有其他相等的key（因为要求解的是第一个大于等于给定值key的元素，所以在左边区间再搜索下）
                // 需要先记录下现在满足条件的下标，进入下一个循环的时候如果还满足条件，下一个循环中的下标是比当前下标更小，会用新值覆盖，如果下一轮循环没有符合条件的值，那么当前结果就是最终结果
                result = mid;
                high = mid-1;
            } else if(nums[mid]<key) {
                // mid下标的元素小于key，那么只能在[mid+1,high]的范围内
                low = mid+1;
            }
        }

        return result;
    }

    /**
     * 数组中最后一个小于等于给定值key的元素下标
     * @param nums
     * @param begin
     * @param end
     * @param key
     * @return
     */
    private static int binarySearchLastSmallerInternal(int[] nums, int begin, int end, int key) {
        int result = -1;
        int low = begin;
        int high = end;

        while(low<=high) {
            // int mid = (high+low)/2 中high+low有溢出风险，所以可以换个写法，考虑将+号转换称-号的写法 low+(high-low)/2
            // 其实对于后边的除以2，还可以使用移位来运算 low+((high-low)>>1)  (需要注意右移符号的优先级是比+号小的，所以需要加括号)
            int mid = low+(high-low)/2;
            if(nums[mid]==key) {
                // mid下标的元素等于key，那么在右边区间[mid+1...high]的范围内再搜索下还有没有其他相等的key（因为要求解的是最后一个小于等于给定值key的元素，所以在右边区间再搜索下）
                // 需要先记录下现在满足条件的下标，进入下一个循环的时候如果还满足条件，下一个循环中的下标是比当前下标更大，会用新值覆盖，如果下一轮循环没有符合条件的值，那么当前结果就是最终结果
                result = mid;
                low = mid+1;
            } else if(nums[mid]>key) {
                // mid下标的元素大于key，那么在左边区间[low...mid-1]的范围内才能搜索到满足条件的元素
                high = mid-1;
            } else if(nums[mid]<key) {
                // mid下标的元素小于key，那么在右边区间[mid+1...high]的范围内再搜索下还有没有其他相等的key（因为要求解的是最后一个小于等于给定值key的元素，所以在右边区间再搜索下）
                // 需要先记录下现在满足条件的下标，进入下一个循环的时候如果还满足条件，下一个循环中的下标是比当前下标更大，会用新值覆盖，如果下一轮循环没有符合条件的值，那么当前结果就是最终结果
                result = mid;
                low = mid+1;
            }
        }

        return result;
    }
}
