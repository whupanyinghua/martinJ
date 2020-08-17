package com.pyh.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 类JLRU的实现描述：基于JDLinkedList、MAP的LRU算法
 *
 * @author panyinghua 2020-8-17 11:07
 */
public class JLRUCache {

    /**
     * 双向链表记录节点被使用的时间顺序，我们这里规定尾节点是最近使用，头节点是最久使用
     */
    private JDLinkedList cache = new JDLinkedList();
    /**
     * 辅助Map用于快速定位元素
     */
    private Map<Integer, JDNode> map = new HashMap<>();
    /**
     * 最大容量
     */
    private int cap;

    /**
     * 构造函数中传入cache允许的最大容量
     * @param cap
     */
    public JLRUCache(int cap) {
        this.cap = cap;
    }

    /**
     * 获取缓存中key对应的元素，并将节点置为最近使用
     * @param key
     * @return
     */
    public Integer get(int key) {
        JDNode node;
        if((node=map.get(key))==null) {
            return null;
        }

        // 将当前节点置为最近使用
        makeNodeRecently(node);

        return node.value;
    }




    /**
     * 添加元素到缓存中，如果空间不足，则淘汰掉最近最久使用的元素，然后将当前元素添加到缓存，并且将当前元素置为最近使用
     * @param key
     * @param value
     */
    public void add(int key, int value) {
        if(map.containsKey(key)) {
            // 如果本身对应的key在缓存中，两种做法：1.先删除，再添加；2.直接替换。此处无需判断缓存的大小
            // 1.删除对应的key
            deleteKey(key);
            // 2.添加元素，并置为最近使用
            addNodeRecently(key, value);
        } else {
            // 1.容量过大，先删除最近最久使用的元素
            if (cache.size() >= cap) {
                removeLeastRecentlyNode();
            }
            // 2.将当前元素添加到缓存，并且置为最近使用
            addNodeRecently(key, value);
        }
    }


    /**
     * 将当前元素置为最近使用
     * @param key
     */
    private void makeNodeRecently(int key) {
        JDNode node = map.get(key);
        if(null != node) {
            makeNodeRecently(node);
        }
    }

    /**
     * 将当前元素置为最近使用
     * @param node
     */
    private void makeNodeRecently(JDNode node) {
        // 1.先从链表中移除
        cache.removeNode(node);
        // 2.在添加到链表的尾部，表示最近使用
        cache.addLast(node);
    }

    /**
     * 将一个新节点添加到缓存，并且置为最近使用，注意在使用本方法前，已经判断缓存中有足够的空间进行添加
     * @param key
     * @param value
     */
    private void addNodeRecently(int key, int value) {
        JDNode node = new JDNode(key, value, null, null);
        // 1.添加到链表中
        cache.addLast(node);
        // 2.添加到map中
        map.put(key, node);
    }

    private void addNodeRecently(JDNode node) {
        if(null != node) {
            // 1.添加到链表中
            cache.addLast(node);
            // 2.添加到map中
            map.put(node.key, node);
        }
    }

    /**
     * 移除最久使用的元素
     */
    private void removeLeastRecentlyNode() {
        // 1.从链表中删除头部元素
        JDNode node = cache.removeFirst();
        // 2.从map中删除对应的映射
        map.remove(node.key);
    }

    /**
     * 从缓存中删除一个key
     * @param key
     */
    private void deleteKey(int key) {
        JDNode node = map.get(key);
        // 1.从链表中删除
        cache.removeNode(node);
        // 2.从map中删除
        map.remove(key);
    }

}
