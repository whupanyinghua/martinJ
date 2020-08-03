package com.pyh.collection;

/**
 * 类ListToStack的实现描述：@TODO
 *
 * @author panyinghua 2020-7-29 20:41
 */
public class ListToStack {
    // 1.直接使用java的链表结构，自己实现也比较简单
    //private LinkedList<Integer> outList = new LinkedList<>();
    // 2.自己的简单链表类
    private JLinkedList outList = new JLinkedList();


    public static void main(String[] args) {
        ListToStack stack = new ListToStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pull());
        System.out.println(stack.pull());
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pull());
    }

    /**
     * 入栈
     * @param i
     * @return
     */
    public void push(Integer i) {
        // 一个单链表实现即可，添加元素的时候添加到队列的首位置
        outList.addFirst(i);
    }

    /**
     * 出栈
     * @return
     */
    public Integer pull() {
        // 出栈的时候先从出队列里边从头开始输出元素
        if(!outList.isEmpty()) {
            return outList.removeFirst();
        }
        return null;
    }
}
