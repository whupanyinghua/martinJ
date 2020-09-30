package com.pyh.structure.leetcode.tree;

/**
 * 类ConnectNode的实现描述：
 * leetcode: 116. 填充二叉树节点的右侧指针
 *
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author panyinghua 2020-9-23 19:27
 */
public class ConnectNode {


    public Node connect(Node root) {
        // 错误思路
        /*if(null == root || null == root.left) {
            return null;
        }

        // 连接左子树
        connect(root.left);
        // 连接右子树
        connect(root.right);
        // 连接left与right
        root.left.next = root.right;*/

        if(null == root) {
            return null;
        }
        connectNew(root.left, root.right);

        return root;
    }

    /**
     * 函数的定义是将left与right两个节点连接起来
     * @param node1
     * @param node2
     * @return
     */
    public void connectNew(Node node1, Node node2) {
        // base case
        if(null == node1 || null == node2) {
            return ;
        }

        // 连接node1与node2
        node1.next = node2;
        // 连接node1.left node1.right
        connectNew(node1.left, node1.right);
        // 连接node2.left node2.right
        connectNew(node2.left, node2.right);
        // 连接node1.right node2.left(跨越父节点的两个相邻节点)
        connectNew(node1.right, node2.left);
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }
}
