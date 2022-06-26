package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 *
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 *
 * 输入:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 */
public class LeetCode0662Solution {

//    private class PositionNode{
//        public TreeNode treeNode;
//        public int depth;
//        public int pos;
//
//        public PositionNode(TreeNode treeNode, int depth, int pos){
//            this.treeNode = treeNode;
//            this.depth = depth;
//            this.pos = pos;
//        }
//    }
//
//    /**
//     * 广度优先搜索
//     * 设 二叉树 的层数从0开始
//     * 第i层能容纳的 节点一共是 2^i 个
//     * 第i层上 的位置j的节点的左子节点的 位置是i+1层上的 2*j, 右子节点的位置是 i+1 层上的 2*j + 1
//     *
//     *
//     * @param root
//     * @return
//     */
//    public int widthOfBinaryTree(TreeNode root) {
//
//        if(root == null){
//            return 0;
//        }
//
//        Queue<PositionNode> queue = new LinkedList<>();
//        queue.offer(new PositionNode(root, 0, 0));
//        int width = 0;
//        while (!queue.isEmpty()){
//            int size = queue.size();
//            int l = -1, r = -1;
//            for(int i = 0; i < size; i++){
//                PositionNode pn = queue.poll();
//                if(i == 0){
//                    l = pn.pos;
//                }
//                if(i == size - 1){
//                    r = pn.pos;
//                }
//                if(pn.treeNode.left != null){
//                    queue.offer(new PositionNode(pn.treeNode.left, pn.depth + 1, 2 * pn.pos));
//                }
//                if(pn.treeNode.right != null){
//                    queue.offer(new PositionNode(pn.treeNode.right, pn.depth + 1, 2 * pn.pos + 1));
//                }
//            }
//            width = Math.max(width, r - l + 1);
//        }
//        return width;
//    }

    // 因为 宽度无法设计为dfs递归函数的返回值，使用全局变量标记
    private int ans;
    // 记录每一层的最左边的位置
    private Map<Integer, Integer> leftMostPos;

    /**
     * 使用深度优先遍历
     * 设 二叉树 的层数从0开始
     * 第i层能容纳的 节点一共是 2^i 个
     * 第i层上 的位置j的节点的左子节点的 位置是i+1层上的 2*j, 右子节点的位置是 i+1 层上的 2*j + 1
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        ans = 0;
        leftMostPos = new HashMap<>();
        dfs(root, 0, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth, int pos){
        if(node == null){
            return;
        }
        if(!leftMostPos.containsKey(depth)){
            leftMostPos.put(depth, pos);
        }
        ans = Math.max(ans, pos - leftMostPos.get(depth) + 1);
        dfs(node.left, depth + 1, 2 * pos);
        dfs(node.right, depth + 1, 2 * pos + 1);
    }

}
