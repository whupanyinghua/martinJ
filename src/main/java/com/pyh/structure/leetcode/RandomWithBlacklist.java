package com.pyh.structure.leetcode;

import java.util.*;

/**
 * 类RandomWithBlacklist的实现描述：
 * 给定一个输入N,list,表示从[0~N)之前随机获取一个数，并且这个数不在黑名单list中
 *
 * @author panyinghua 2020-9-29 20:50
 */
public class RandomWithBlacklist {

    public static void main(String[] args) {
        int n=5;
        List<Integer> list = Arrays.asList(1,3);

        RandomWithBlacklist randomWithBlacklist = new RandomWithBlacklist(n, list);
        for(int i=0;i<20;i++) {
            System.out.println(randomWithBlacklist.pick());
        }
    }

    private int[] nums;
    private int length;
    private List<Integer> blacklist;

    // 辅助集合
    private Map<Integer, Integer> blacklistMap = new HashMap<>();
    private int valSize = 0;
    private Random random = new Random();


    public RandomWithBlacklist(int n, List<Integer> list) {
        this.nums = new int[n];
        length = n;
        this.blacklist = list;

        valSize = length-list.size();
        // 将黑名单转换成map，方便查找
        for(int black: list) {
            // 映射的值可以随意，我们需要的只是blacklistMap中可以查找到是否黑名单即可
            blacklistMap.put(black, black);
        }

        // 主要想法就是将[0-valSize)区间的元素中如果有黑名单中的元素，则将改元素映射到[valSize,n)中并且不在黑名单中的元素
        // 需要注意的是[valSize,n)中的黑名单映射关系不用管，这些映射关系在上文中我们映射为了他们自己（key、value是相同的值）
        // 最终区间[0-valSize)中的所有黑名单元素都一一对应到区间[valSize,n)中的非黑名单元素
        int last = n-1;
        for(int black: blacklist) {
            if(black>=valSize) {
                // 非[0-valSize)区间，无需映射
                continue ;
            }
            while(blacklistMap.containsKey(last)) {
                last--;
            }
            blacklistMap.put(black, last);
            last--;
        }

    }


    /**
     * 随机返回一个[0,n)之间的数字，但是该数字不能在黑名单blacklist中
     * 所有不在黑名单中的数字出现的概率要相等
     * @return
     */
    public int pick() {
        // 随机生成[0,valSize)之间的任意数字，此时每个数字出现的概率是均等的
        int index = Math.abs(random.nextInt())%valSize;
        // 查看是否命中黑名单，如果命中黑名单，则返回黑名单映射到的非黑名单数字
        int result = blacklistMap.getOrDefault(index, -1);
        // result=-1表示没有命中黑名单可以直接返回index，如果命中黑名单，那么result本身就是黑名单映射到的非黑名单数字
        return -1==result?index:result;
    }
}
