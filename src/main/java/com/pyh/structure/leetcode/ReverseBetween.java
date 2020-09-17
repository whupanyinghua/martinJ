package com.pyh.structure.leetcode;

/**
 * 类ReverseBetween的实现描述：
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-9-17 15:40
 */
public class ReverseBetween {


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==n) return head;

        int step = 0;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode newHead = dummy;
        while(step<m-1) {
            newHead = newHead.next;
            step++;
        }

        ListNode cur = newHead.next;
        ListNode tail = newHead.next;
        newHead.next = null;
        while(step<n) {
            ListNode next = cur.next;
            cur.next = null;
            ListNode tmp = newHead.next;
            newHead.next = cur;
            cur.next = tmp;
            cur = next;
            step++;
        }

        tail.next = cur;

        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(m==n) {
            return head;
        }

        int step = 0;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode newHead = dummy;
        ListNode cur = dummy.next;
        ListNode tail = cur;
        while(null != cur) {
            if(step<m-1) {
                newHead = newHead.next;
                cur = cur.next;
                tail = cur;
                step++;
            } else if(step<n) {
                ListNode next = cur.next;
                cur.next = null;
                ListNode tmp = newHead.next;
                if(tmp!=cur) {
                    newHead.next = cur;
                    cur.next = tmp;
                }
                cur = next;
                step++;
            } else {
                break;
            }
        }
        // 把剩下的不需要反转的节点连接到tail上即可
        if(null != tail) {
            tail.next = cur;
        }

        return dummy.next;
    }

    static class ListNode {
        int val;
        ReverseBetween.ListNode next;

        ListNode() {

        }

        ListNode(int value) {
            this.val = value;
        }
    }
}
