package com.pyh.collection.base;

/**
 * 类JDNode的实现描述：双向链表节点定义
 *
 * @author panyinghua 2020-8-17 10:38
 */
public class JDNode<K,V> {

    public K key;

    public V value;

    /**
     * 前驱节点
     */
    public JDNode prev;
    /**
     * 后驱节点
     */
    public JDNode next;

    public JDNode(V value) {
        this(value, null, null);
    }

    public JDNode(K key, V value) {
        this(key, value, null, null);
    }

    public JDNode(V value, JDNode prev, JDNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public JDNode(K key, V value, JDNode prev, JDNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
