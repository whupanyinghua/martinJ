package com.pyh.structure.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 类OpenLock的实现描述：打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-6 16:02
 */
public class OpenLock {

    public static void main(String[] args) {
        String[] strs = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(new OpenLock().openLock(strs, target));
    }


    /**
     * BFS的解题思路
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        // 队列，存储的是当前层节点
        Queue<String> queue = new LinkedList<>();
        // 存储的是已经访问过的节点，使用set方便计算节点是否访问过
        Set<String> visited = new HashSet<>();

        // 存储不能经过的节点
        Set<String> death = new HashSet<>();
        for(String str : deadends) {
            death.add(str);
        }

        // 初始节点
        queue.offer("0000");
        visited.add("0000");
        int period = 0;
        while(!queue.isEmpty()) {
            // 首先在本次while循环中需要把上一次队列中的所有元素都取出来，因此这里需要内置for循环执行size此的poll操作
            int size = queue.size();
            for(int i=0;i<size;i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return period;
                }
                // 增加一个死亡节点的判断，如果当前队列中取出的是死亡节点，则直接遍历队列中的下一个节点
                if (death.contains(cur)) {
                    continue;
                }
                // 将cur的邻居元素都加到queue队列中
                // 转盘数字可以往上转，也可以往下转
                for (int j = 0; j < 4; j++) {
                    String on = moveOn(cur, j);
                    if (!visited.contains(on)) {
                        // 没访问过再加入队列
                        queue.offer(on);
                        visited.add(on);
                    }
                    String down = moveDown(cur, j);
                    if (!visited.contains(down)) {
                        // 没访问过的元素再加入队列
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 路径更新,注意这里是把路径更新放在循环的最后，是因为在初始化的收，我们没有预先更新路径
            // 在取完上一层元素之后，并且添加了上一层元素的所有周围元素到队列中再更新路径数据，这样方便与在下次循环的时候，如果有节点
            // 达到了终点，那么可以直接返回路径，无需对路径做任何处理
            period++;
        }

        return -1;
    }

    /**
     * 数字往上转
     * @param current
     * @param position
     * @return
     */
    private String moveOn(String current, int position) {
        char[] cs = current.toCharArray();
        char c = cs[position];
        if(c=='9') {
            cs[position] = '0';
        } else {
            cs[position] = (char) (c+1);
        }
        return new String(cs);
    }

    /**
     * 数字往下转
     * @param current
     * @param position
     * @return
     */
    private String moveDown(String current, int position) {
        char[] cs = current.toCharArray();
        char c = cs[position];
        if(c=='0') {
            cs[position] = '9';
        } else {
            cs[position] = (char) (c-1);
        }
        return new String(cs);
    }
}
