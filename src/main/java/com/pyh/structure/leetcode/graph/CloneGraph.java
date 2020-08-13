package com.pyh.structure.leetcode.graph;

import java.util.*;

/**
 * 类CloneGraph的实现描述：
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author panyinghua 2020-8-13 10:32
 */
public class CloneGraph {

    Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        return cloneGraphDFS(node);
    }

    /**
     * DFS
     * @param node
     * @return
     */
    public Node cloneGraphDFS(Node node) {
        if(null == node) return node;

        // copy当前节点
        Node cpNode;
        if(!visited.containsKey(node)) {
            cpNode = new Node(node.val);
            visited.put(node, cpNode);
        } else {
            //  已访问过，则直接返回，递归的终止条件
            return visited.get(node);
        }

        // 处理邻接节点(深度遍历的方式)
        for(Node adjNode: node.neighbors) {
            cpNode.neighbors.add(cloneGraphDFS(adjNode));
        }

        return cpNode;
    }


    /**
     * BFS
     * @param node
     * @return
     */
    public Node cloneGraphBFS(Node node) {
        if(null == node) return node;

        /**
         * BFS遍历需要的辅助队列
         */
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> bfsvisited = new HashMap<>();

        // 先将首个节点添加到队列
        queue.offer(node);
        bfsvisited.put(node, new Node(node.val));

        while(!queue.isEmpty()) {
            Node oriNode = queue.poll();
            for(Node neighbor: oriNode.neighbors) {
                if(!bfsvisited.containsKey(neighbor)) {
                    // 该临节点没有被访问过，那么复制一个节点，并且放到已访问map与队列中
                    bfsvisited.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                // 更新当前克隆节点的邻接节点
                bfsvisited.get(oriNode).neighbors.add(bfsvisited.get(neighbor));
            }
        }

        return bfsvisited.get(node);

    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int vval) {
            this.val = vval;
            this.neighbors = new ArrayList<>();
        }
    }
}
