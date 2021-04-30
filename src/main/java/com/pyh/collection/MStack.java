package com.pyh.collection;

/**
 * 类DStack的实现描述：栈
 *
 * @author panyinghua 2021-4-29 19:09
 */
public class MStack<E> {


    private MLinkedList<E> items = new MLinkedList<>();
    private int count;
    
    
    public void push(E item) {
        if(null == item) {
            return ;
        }

        // 入栈添加到队首，这样的话出栈的时候也从队首出
        items.addFirst(item);
        count++;
    }

    public E pull() {
        E item=null;
        item = items.removeFirst();
        if(item != null) {
            count--;
        }
        return item;
    }

    public E peek() {
        if(count==0) {
            return null;
        }

        return items.get(0);
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return count;
    }

}
