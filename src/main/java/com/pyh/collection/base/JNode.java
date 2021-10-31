package com.pyh.collection.base;

/**
 * 类JNode的实现描述：列表节点
 *
 * @author panyinghua 2020-7-31 15:32
 */
public class JNode<E> {
    public E value;
    public JNode next;

    public JNode(E value, JNode next) {
        this.value = value;
        this.next = next;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(value);
        JNode node = this.next;
        while(null != node) {
            builder.append(",").append(node.value);
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }
}
