package com.pyh.structure.leetcode.tree;

/**
 * 类TrieTree的实现描述：trie树，也称作前缀树
 * 我们这里只实现一个包含有[a~z]之间所有的字符的trie树，也就是前提是需要查询的只有小写字母
 *
 * @author panyinghua 2021-6-1 16:52
 */
public class TrieTree {

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        String[] ss = new String[]{"abc","ttt","bc","martin","bc","a"};
        for(String s : ss) {
            tree.add(s);
        }

        String f1 = "abc";
        System.out.println("find source '" + f1 + "', result is " + tree.find(f1));
        f1="ttt";
        System.out.println("find source '" + f1 + "', result is " + tree.find(f1));
        f1="bc";
        System.out.println("find source '" + f1 + "', result is " + tree.find(f1));
        f1="sss";
        System.out.println("find source '" + f1 + "', result is " + tree.find(f1));
    }




    // 根节点的val不存储任何数据，直接初始化好
    private TrieNode root;

    public TrieTree() {
        // 根节点的val不存储任何数据，直接初始化好，存储一个空白字符
        root = new TrieNode(' ');
    }


    /**
     * 将字符串source添加到trie树中
     * @param source
     */
    public void add(String source) {
        add(source.toCharArray());
    }

    /**
     * 将字符数组source添加到trie树中
     * @param source
     */
    public void add(char[] source) {
        // node指针指向跟节点
        TrieNode node = root;
        for(char c : source) {
            if(node.children[c-'a']==null) {
                node.children[c-'a'] = new TrieNode(c);
            }
            // node指针更新
            node = node.children[c-'a'];
        }
        // for循环之后，把node指针，也就是当前添加的字符数组中最后一个字符所在的位置进行标记，代表当前字符是当前添加字符串的结束
        // 后边的查找操作会使用到这个辅助字段，来判断是否是结束字符
        node.endFlag = true;
    }


    /**
     * 查找当前trie树中是否存在 source 单词
     * @param source
     * @return
     */
    public boolean find(String source) {
        return find(source.toCharArray());
    }

    /**
     * 查找当前trie树中是否存在要查找的字符数组source
     * @param source
     * @return
     */
    public boolean find(char[] source) {
        // node指针指向根节点
        TrieNode node = root;
        for(char c : source) {
            if(node.children[c-'a']==null) {
                // 不匹配，直接返回false
                return false;
            }
            // node指针更新
            node = node.children[c-'a'];
        }
        // 查找到最后一个元素，则判断最后一个node对应的标记endFlag是否为true，为true则表示是一个结束字符串，否则当前节点只是历史添加的某个字符串的前缀
        return node.endFlag;
    }
    
    
    
    
    
    
    
    static class TrieNode {
        // 当前节点的字符
        private char value;
        // 子节点，这里因为我们限制了只有a~z的26个小写字符，所以子节点直接使用一个长度为26的数组来存储，数组的下标对应子节点的asc码
        // 比如其中一个子节点是a，那么就在childen[0]存储子节点，如果是b，则是children[1]，按顺序存储
        private TrieNode[] children = new TrieNode[26];

        private boolean endFlag = false;

        public TrieNode(char val) {
            this.value = val;
        }
    }
}
