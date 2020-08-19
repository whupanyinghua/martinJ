package com.pyh.structure.leetcode.doublept;

/**
 * 类RemoveNthFromEnd的实现描述：
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：给定的 n 保证是有效的。
 *
 * @author panyinghua 2020-7-20 15:56
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode tail = new ListNode(2);
        head.next = tail;
        System.out.println(new RemoveNthFromEnd().removeNthFromEndGF(head, 2).val);
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n<=0 || head==null) return head;
        if(head.next==null && n==1) return null;

        ListNode tar = head;
        ListNode ne = head;

        int step = 0;
        while(ne.next != null) {
            if(step<n) {
                step++;
            } else {
                tar = tar.next;
            }
            ne = ne.next;
        }
        // while循环遍历完成之后，ne指针指向最后一个元素，tar指针指向倒数n+1个元素,因为n肯定大于0，那么tar.next肯定不为空
        if(step<n && tar == head) {
            // tar没移动完成，但是ne已经到了最后一个节点，特殊情况特殊处理，对应需要删除的是原本的头结点
            ListNode nhead = head.next;
            head.next = null;
            head = nhead;
        } else {
            ListNode nne = tar.next;
            tar.next = nne.next;
            nne.next = null;
        }

        return head;
    }

    /**
     * 官方解法
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndGF(ListNode head, int n) {
        // 隐藏节点的加入简直是太棒了！
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int value) {
            this.val = value;
        }
    }
}
