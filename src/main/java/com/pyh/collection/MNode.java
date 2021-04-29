package com.pyh.collection;

/**
 * 类MNode的实现描述：
 *
 * @author panyinghua 2021-4-29 19:13
 */
public class MNode<E> {
    public E value;
    public MNode<E> next;

    public MNode(E value, MNode<E> next) {
        this.value = value;
        this.next = next;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(value);
        MNode<E> node = this.next;
        while(null != node) {
            builder.append(",").append(node.value);
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }
}
