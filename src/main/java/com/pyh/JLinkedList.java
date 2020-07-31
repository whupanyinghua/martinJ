package com.pyh;

/**
 * 类JLinkedList的实现描述：非线程安全版本
 *
 * @author panyinghua 2020-7-29 22:36
 */
public class JLinkedList {
    JNode head = null;
    JNode tail = null;
    int size = 0;

    public void addFirst(Integer value) {
        JNode node = new JNode(value, null);
        if(head != null) {
            node.next = head;
        } else {
            // 添加元素的时候如果head非空，那么当前元素即为首元素、尾元素
            tail = node;
        }
        head = node;
        size++;
    }

    /**
     * 将当前元素添加到队列末尾
     * @param value
     */
    public void add(Integer value) {
        JNode node = new JNode(value, null);
        if(null!= tail) {
            tail.next = node;
        } else {
            // 添加元素的时候如果tail是空的，那么当前元素即为首元素、尾元素
            head = node;
        }
        tail = node;
        size++;
    }

    public boolean isEmpty() {
        return 0==size();
    }

    public Integer removeFirst() {
        if(head == null) {
            return null;
        }

        JNode node = head;
        head = head.next;
        node.next = null;
        size--;
        if(null == head) {
            //移除元素之后如果头元素为空，那么尾元素也是空
            tail = null;
        }
        return node.value;
    }

    public int size() {
        return size;
    }

}
