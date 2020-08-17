package com.pyh.collection;

/**
 * 类JDNode的实现描述：双向链表节点定义
 *
 * @author panyinghua 2020-8-17 10:38
 */
public class JDNode {

    public Integer key;

    public Integer value;

    /**
     * 前驱节点
     */
    public JDNode prev;
    /**
     * 后驱节点
     */
    public JDNode next;

    public JDNode(Integer value, JDNode prev, JDNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public JDNode(Integer key, Integer value, JDNode prev, JDNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
