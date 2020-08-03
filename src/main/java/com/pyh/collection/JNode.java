package com.pyh.collection;

/**
 * 类JNode的实现描述：列表节点
 *
 * @author panyinghua 2020-7-31 15:32
 */
public class JNode {
    int value;
    JNode next;

    public JNode(int value, JNode next) {
        this.value = value;
        this.next = next;
    }
}
