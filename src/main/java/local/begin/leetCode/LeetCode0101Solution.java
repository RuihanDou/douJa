package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class LeetCode0101Solution {


//    // dfs
//    public boolean isSymmetric(TreeNode root) {
//        if(root == null){
//            return true;
//        }
//        return isMirror(root.left, root.right);
//    }
//
//    private boolean isMirror(TreeNode t1, TreeNode t2){
//        if(t1 == null && t2 == null){
//            return true;
//        }
//        if(t1 == null || t2 == null){
//            return false;
//        }
//        return t1.val == t2.val && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
//    }

    // BFS
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode t1, TreeNode t2){
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.offer(t1);
        q2.offer(t2);

        while (!q1.isEmpty() && !q2.isEmpty()){
            TreeNode s1 = q1.poll(), s2 = q2.poll();
            if(s1 == null && s2 == null){
                continue;
            }
            if(s1 == null || s2 == null || s1.val != s2.val){
                return false;
            }

            q1.offer(s1.left);
            q2.offer(s2.right);
            q1.offer(s1.right);
            q2.offer(s2.left);
        }
        return true;
    }

}
