package local.begin.leetCode;


import local.begin.struct.TreeNode;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 *
 * 通过次数119,986提交次数202,472
 */
public class LeetCode0404Solution {

    public int sumOfLeftLeaves(TreeNode root) {
        return root == null ? 0 : dfs(root);
    }

    private int dfs(TreeNode node){
        int ans = 0;
        if(node.left != null){
            ans += isLeaf(node.left) ? node.left.val : dfs(node.left);
        }
        if(node.right != null){
            ans += dfs(node.right);
        }
        return ans;
    }

    private boolean isLeaf(TreeNode node){
        return node.left == null && node.right == null;
    }

}
