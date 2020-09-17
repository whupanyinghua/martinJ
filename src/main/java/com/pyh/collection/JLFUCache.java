package com.pyh.collection;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 类JLFUCache的实现描述：基于LinkedHashSet的LFU算法
 * LFU:least frequency used
 *
 * @author panyinghua 2020-8-17 16:11
 */
public class JLFUCache {

    /**
     * key->value(node)的映射，用于get接口中根据key查询对应节点的辅助MAP
     */
    private Map<Integer, JDNode> keyToValMap = new HashMap<>();
    /**
     * key->freq的映射，用于addFreq操作中根据key查询对应节点的当前访问频率
     */
    private Map<Integer, Integer> keyToFreqMap = new HashMap<>();
    /**
     * freq->list(node)的映射，同一个访问频率可以对应多个数据节点
     */
    private Map<Integer, LinkedHashSet<JDNode>> freqToNodeMap = new HashMap<>();


    /**
     * 最大容量
     */
    int cap;

    /**
     * 当前最小使用频率
     */
    int minFreq;

    public JLFUCache(int cap) {
        this.cap = cap;
        this.minFreq = 0;
    }

    /**
     * 从缓存中获取元素，并且针对被获取的元素的访问次数+1
     * @param key
     * @return
     */
    public Integer get(Integer key) {
        // 1.获取key对应的value，不存在则返回空
        JDNode node;
        if((node=keyToValMap.get(key)) == null) {
            return null;
        }
        // 2.更新对应key的访问频率
        addFreq(node);

        // 3.返回对应的value
        return node.value;
    }

    /**
     * 将元素添加到缓存中，如果存储空间不够，先淘汰掉最近使用次数最少的元素，再添加新的元素
     * @param key
     * @param value
     */
    public void put(Integer key, Integer value) {
        JDNode node;
        if((node=keyToValMap.get(key))!=null) {
            // 元素本身存在于缓存中
            // 1.更新元素的value
            node.value = value;
            // 2.更新key对应的访问次数
            addFreq(node);
            return;
        }

        // 新元素
        if(keyToValMap.size()>=cap) {
            // 容量超限，先淘汰最近使用次数最少的元素
            removeLeastFreqUsed();
        }

        addNewNode(key, value);
    }

    /**
     * 添加一个新的元素到cache中，并初始化相关的数据
     * @param key 新元素的key
     * @param value 新元素的value
     */
    private void addNewNode(Integer key, Integer value) {
        // 1.添加新元素
        JDNode node = new JDNode(key, value);
        keyToValMap.put(key, node);
        //freqToNodeMap.putIfAbsent(1, new LinkedHashSet<>());
        //freqToNodeMap.get(1).add(node);
        freqToNodeMap.computeIfAbsent(1, v -> new LinkedHashSet<>()).add(node);
        keyToFreqMap.put(key, 1);
        // 2.添加新元素之后，最少访问频率肯定是1
        this.minFreq = 1;
    }

    /**
     * 移除最近最少使用次数的元素，如果存在多个使用频率相同的元素，那么淘汰时间最早的元素
     */
    private void removeLeastFreqUsed() {
        // 1.找出最少使用频率的set
        LinkedHashSet<JDNode> freqSet = freqToNodeMap.get(this.minFreq);
        // set的头个元素就是最早添加的元素
        JDNode node = freqSet.iterator().next();
        // 2.更新keyToValMap
        keyToValMap.remove(node.key);
        // 3.更新freqToNodeMap
        freqSet.remove(node);
        if(freqSet.isEmpty()) {
            freqToNodeMap.remove(this.minFreq);
            // 如果set为空，是否需要更新minFreq？？？
            // removeLeastFreqUsed方法仅在add方法中被调用，而且被调用到的前提是上一次的add调用一定会把minFreq置为1，就算这里
            // minFreq被更新我们也可以不用管它，因为在下一次removeLeastFreqUsed被调用的时候，minFreq要么是本次add操作中被设置为1，
            // 要么是在addFreq中被更新为正确的值。
            // 而且另外需要说明的是，在addFreq中也会将minFreq更新为正确的值
        }
        // 4.更新keyToFreqMap
        keyToFreqMap.remove(node.key);
    }


    /**
     * 针对被访问的元素进行访问次数+1的操作，注意，该元素一定要存在于cache中
     * @param node 需要增加访问频率的元素
     */
    private void addFreq(JDNode node) {
        Integer freq = keyToFreqMap.get(node.key);
        // 1.先将当前元素从原本的频率set中删除
        LinkedHashSet<JDNode> freqSet = freqToNodeMap.get(freq);
        freqSet.remove(node);
        // 2.将当前元素加到下一个使用频率set中
        //freqToNodeMap.putIfAbsent(freq+1, new LinkedHashSet<>());
        //freqToNodeMap.get(freq+1).add(node);
        freqToNodeMap.computeIfAbsent(freq+1, v->new LinkedHashSet<>()).add(node);
        // 3.处理上一个频率的set列表
        if(freqSet.isEmpty()) {
            // 元素挪走之后，频率set为空，则从freqToNodeMap中删除
            freqToNodeMap.remove(freq);
            // 是否需要更新最少使用频率？当前处理的是最少使用频率的节点，那么在set为空之后，需要将minFreq置为freq+1
            if(freq.intValue()==this.minFreq) {
                this.minFreq = freq+1;
            }
        }
        // 4.更新访问频率
        keyToFreqMap.put(node.key, freq+1);
    }


}
