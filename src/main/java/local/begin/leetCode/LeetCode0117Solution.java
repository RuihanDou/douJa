package local.begin.leetCode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 *
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 *
 *
 * 提示：
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class LeetCode0117Solution {
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

//    /**
//     * 层序遍历的方法，每一层 前一个的 next 指向后一个
//     * 空间负责O(n)
//     * @param root
//     * @return
//     */
//    public Node connect(Node root) {
//        if (root == null){
//            return root;
//        }
//
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while (!queue.isEmpty()){
//            int n = queue.size();
//            Node last = null;
//            for(int i = 1; i <= n; i++){
//                Node f = queue.poll();
//                if(f.left != null){
//                    queue.offer(f.left);
//                }
//                if(f.right != null){
//                    queue.offer(f.right);
//                }
//                if(i != 1){
//                    last.next = f;
//                }
//                last = f;
//            }
//        }
//        return root;
//    }

    // 维护该行最后一个位置 last 和下一行的其实位置 nextStart
    Node last = null, nextStart = null;

    public Node connect(Node root){
        if(root == null){
            return root;
        }
        Node start = root;
        while (start != null){
            last = null;
            nextStart = null;
            for(Node p = start; p != null; p = p.next){
                if(p.left != null){
                    handle(p.left);
                }
                if(p.right != null){
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }

}
