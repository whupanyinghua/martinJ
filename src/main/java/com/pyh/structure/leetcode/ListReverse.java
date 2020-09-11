package com.pyh.structure.leetcode;

import com.pyh.collection.JNode;

/**
 * 类ListReverse的实现描述：链表反转
 *
 * @author panyinghua 2020-9-11 16:35
 */
public class ListReverse {

    public static void main(String[] args) {
        JNode node1 = new JNode(1, null);
        JNode node2 = new JNode(2, null);
        JNode node3 = new JNode(3, null);
        node1.next = node2;
        node2.next = node3;
        System.out.println("begin reverse,source list is:" + node1);
        ListReverse reverse = new ListReverse();
        JNode newHeadNode = reverse.reverseNew(node1);
        System.out.println("end reverse,target list is: " + newHeadNode);

        JNode sourceList = reverse.reverseNew(newHeadNode);
        System.out.println("begin reverseByGroup,source list is:" + sourceList);
        JNode newHeadNode2 = reverse.reverseByGroup(sourceList, 2);
        System.out.println("end reverseByGroup,target list is: " + newHeadNode2);

    }


    /**
     * 链表反转
     * @param head
     * @return
     */
    public JNode reverse(JNode head) {
        if(null == head || null == head.next) {
            // 空链表或者只有一个头结点，直接返回head即可
            return head;
        }

        JNode pre = null;
        JNode cur = head;
        JNode next = null;

        while(null != cur) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 注意这里需要返回的是pre节点，因为上述while循环中到最后结束的时候cur节点是null节点，pre是上一个节点也就是新列表的头节点
        return pre;
    }


    /**
     * 链表反转
     * @param head
     * @return
     */
    public JNode reverseNew(JNode head) {
        return reverse(head, null);
    }

    /**
     * 翻转list，区间范围[head,tail)，不包括tail节点，如果是要翻转整个链表，tail参数传入null
     * @param head
     * @param tail
     * @return
     */
    public JNode reverse(JNode head, JNode tail) {
        if(null == head || null == head.next) {
            // 空链表或者只有一个头结点，直接返回head即可
            return head;
        }

        JNode pre = null;
        JNode cur = head;
        JNode next = null;

        while(tail != cur) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 注意这里需要返回的是pre节点，因为上述while循环中到最后结束的时候cur节点是null节点，pre是上一个节点也就是新列表的头节点
        return pre;
    }

    /**
     * 按照k个一组对列表进行反转，如果剩下的节点不够k个节点，那么该区间不反转
     * @param head
     * @param k
     * @return
     */
    public JNode reverseByGroup(JNode head, int k) {

        JNode begin = head;
        JNode end = head;
        for(int i=0;i<k;i++) {
            // 注意判断null的顺序，如果把end=end.next放在if前面，那么节点数刚好满足k的时候判断有问题
            if(null == end) {
                // 剩下的区间长度<k，则不需要反转，直接返回原本的头结点head
                return head;
            }
            end = end.next;
        }
        // for循环结束之后，end的位置是下一个需要反转的区间的head节点
        // 反转现在的区间[begin,end)
        JNode newHead = reverse(begin, end);
        JNode nextHead = reverseByGroup(end, k);
        // 把当前链表跟下一轮反转的链表连接起来
        begin.next = nextHead;

        // 返回头结点
        return newHead;
    }
}
