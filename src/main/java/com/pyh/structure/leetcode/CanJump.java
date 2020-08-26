package com.pyh.structure.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 类CanJump的实现描述：
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-21 16:29
 */
public class CanJump {

    public static void main(String[] args) {
        CanJump jump = new CanJump();
        /*int[] a = {2,3,1,1,0,4};
        //System.out.println("数组："+ Arrays.toString(a)+"的跳到最后一个元素需要最短步数是："+jump.canJump2WithMemo(a));
        int[] b = {2,3,1,1,4};
        System.out.println("数组："+ Arrays.toString(b)+"的跳到最后一个元素需要最短步数是："+jump.canJump2WithMemo(b));
        System.out.println("end");*/

        int[] nums = new int[25000];
        int j = 1;
        for(int i=0;i<25000;i++) {
            nums[i] = j++;
        }
        long begin = System.currentTimeMillis();
        System.out.println("跳到最后一个元素需要最短步数是："+jump.canJump2WithMemo(nums));
        long end = System.currentTimeMillis();
        System.out.println("总共花费:"+(end-begin)+" ms");

    }

    /**
     * 查找是否可以跳到最后一个元素的位置
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;

        // 思路：1.使用step记录当前可以跳到的最大的位置，如果step的值大于等于len-1则表示可以跳到最后一个位置
        // 2.那么我们需要计算的就是在循环中更新跳的最远的步数，循环中，当前元素可以到达最远的位置是 当前位置i+nums[i]，我们用max(step,i+nums[i])来更新step的值
        // 循环的终结条件有两个，1) step>=len-1,表示可以到达最后一个元素 2)计算出来的step刚好等于当前位置i，并且nums[i]=0，意味着无法再往下跳了，则此时可以直接返回false
        int step = 0;
        for(int i=0;i<len;i++) {
            step = max(step, i+nums[i]);
            if(step>=len-1) {
                // 找到一个可以抵达的跳法，直接返回
                return true;
            }
            if(step==i && 0==nums[i]) {
                // 最大的step与当前步骤能取到的step相等，并且当前可以跳跃的步数是0，那么不可能往下走，此时的结果就是不可能到达最后一个元素
                return false;
            }
        }

        return false;
    }

    /**
     * 查找跳到最后一个位置的最近路径长度，如果没有则返回-1
     * @param nums
     * @return
     */
    public int canJump2(int[] nums) {
        int len = nums.length;
        // 暴力破解
        return step(nums, 0, len-1);

    }

    private int step(int[] nums, int begin, int end) {
        if(begin==end) return 0;
        if(nums[begin]==0) return -1;

        // 初始化为最大长度，如果这个值没有被更新过，那么证明没有找到更小的路径，也就是没有路径
        int minStep = end-begin+1;
        for(int i=1;i<=nums[begin];i++) {
            int nextStep = step(nums, begin+i, end);
            if(nextStep==-1) {
                // 表示当前往前跳i步之后没法抵达目的地，则不用加到结果集的更新中

            } else {
                minStep = min(minStep, nextStep + 1);
            }
        }

        if(minStep==end-begin+1) {
            return -1;
        }

        return minStep;
    }

    /**
     * 查找跳到最后一个位置的最近路径长度，如果没有则返回-1，使用备忘录
     * @param nums
     * @return
     */
    public int canJump2WithMemo(int[] nums) {
        int len = nums.length;
        // 暴力破解，带有备忘录的方式
        return stepWithMemo(nums, 0, len-1);

    }

    /**
     * 观察发现step方法中循环调用的step肯定是存在重复计算，那么可以通过备忘录的方法去掉重复计算的步骤
     * 因为我们采取的计算方式中，end一直都是数组的最后一个位置，所以可以使用begin元素作为备忘录map的key
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    private int stepWithMemo(int[] nums, int begin, int end) {
        if(begin>=end) return 0;
        if(nums[begin]==0) return -1;

        // 1.先尝试从备忘录中获取结果
        Integer memoVal = memo.get(begin);
        if(null != memoVal) return memoVal;

        // 初始化为最大长度+1，如果这个值没有被更新过，那么证明没有找到更小的路径，也就是没有路径
        int minStep = end-begin+1;
        for(int i=nums[begin];i>=1;i--) {
            if(i+begin>end) {
                continue;
            }
            int nextStep = stepWithMemo(nums, begin+i, end);
            if(nextStep==-1) {
                // 表示当前往前跳i步之后没法抵达目的地，则不用加到结果集的更新中

            } else {
                minStep = min(minStep, nextStep + 1);
            }
        }

        if(minStep==end-begin+1) {
            minStep = -1;
        }

        // 将计算结果放到备忘录memo中
        memo.put(begin, minStep);

        return minStep;
    }


    private Map<Integer, Integer> memo = new HashMap<>();


    private int min(int num1, int num2) {
        return num1>=num2?num2:num1;
    }

    private int max(int num1, int num2) {
        return num1>=num2?num1:num2;
    }
}
