package local.begin.leetCode;

import local.begin.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class LeetCode0199Solution {


//    // 层序遍历
//    public List<Integer> rightSideView(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        if(null == root){
//            return res;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()){
//            int size = queue.size();
//            for (int i = 0; i < size; i++){
//                TreeNode node = queue.poll();
//                if(node.left != null){
//                    queue.offer(node.left);
//                }
//                if(node.right != null){
//                    queue.offer(node.right);
//                }
//                if(i == size - 1){
//                    res.add(node.val);
//                }
//            }
//        }
//        return res;
//    }

    // DFS
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    // 控制递归顺序，先右后左
    private void dfs(TreeNode node, int depth){
        if(node == null){
            return;
        }
        if(depth == res.size()){
            res.add(node.val);
        }
        depth++;
        dfs(node.right, depth);
        dfs(node.left, depth);
    }

}
