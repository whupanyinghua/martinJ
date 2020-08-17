package com.pyh.collection;

/**
 * 类JDLinkedList的实现描述：双向链表
 *
 * @author panyinghua 2020-8-17 10:40
 */
public class JDLinkedList {

    private JDNode head;
    private JDNode tail;

    private int size;

    public JDLinkedList() {
        // 创建头尾虚拟节点，方便后续的添加、删除操作
        head = new JDNode(0,null, null);
        tail = new JDNode(0,null, null);
        head.next = tail;
        tail.prev = head;
        size=0;
    }


    /**
     * 添加头节点
     * @param node
     */
    public void addFirst(JDNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

        size++;
    }

    /**
     * 添加尾节点
     * @param node
     */
    public void addLast(JDNode node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;

        size++;
    }

    /**
     * 删除链表头部元素
     * @return
     */
    public JDNode removeFirst() {
        if(size==0) {
            return null;
        }

        JDNode firstNode = head.next;
        firstNode.next.prev = head;
        head.next = firstNode.next;

        firstNode.prev = null;
        firstNode.next = null;
        size--;
        return firstNode;
    }

    /**
     * 删除链表尾部元素
     * @return
     */
    public JDNode removeLast() {
        if(size==0) {
            return null;
        }

        JDNode lastNode = tail.prev;
        lastNode.prev.next = tail;
        tail.prev = lastNode.prev;

        lastNode.prev = null;
        lastNode.next = null;

        size--;
        return lastNode;
    }

    /**
     * 移除链表中指定的元素，注意，该元素肯定存在于链表中
     * @param node
     */
    public void removeNode(JDNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

        node.prev = null;
        node.next = null;
    }

    public int size() {
        return size;
    }
}
