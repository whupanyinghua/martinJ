package com.pyh.collection;

/**
 * 类MLinkedList的实现描述：普通单向链表，非线程安全版本
 *
 * @author panyinghua 2021-4-29 19:15
 */
public class MLinkedList<E> {
    MNode<E> head = null;
    MNode<E> tail = null;
    int size = 0;

    public void addFirst(E value) {
        MNode<E> node = new MNode<E>(value, null);
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
    public void add(E value) {
        MNode<E> node = new MNode<E>(value, null);
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

    public E removeFirst() {
        if(head == null) {
            return null;
        }

        MNode<E> node = head;
        head = head.next;
        node.next = null;
        size--;
        if(null == head) {
            //移除元素之后如果头元素为空，那么尾元素也是空
            tail = null;
        }
        return node.value;
    }

    public E get(int index) {
        if(index<0 || index>size-1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int ii = index;
        MNode<E> item = head;
        while(ii>0 && item!=null) {
            item = item.next;
            ii--;
        }

        if(item==null || ii>0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return item.value;
    }

    public int size() {
        return size;
    }
}
