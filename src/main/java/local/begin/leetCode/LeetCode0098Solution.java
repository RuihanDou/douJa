package local.begin.leetCode;

import local.begin.tools.DebugTools;

public class LeetCode0098Solution {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {

        return recursion(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    private boolean recursion(TreeNode node, long lower, long upper){
        if(node == null) {
            return true;
        }

        if(node.val <= lower || node.val >= upper){
            return false;
        }

        return recursion(node.left, lower, node.val) && recursion(node.right, node.val, upper);

    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);

        LeetCode0098Solution solution = new LeetCode0098Solution();

        DebugTools.print(solution.isValidBST(root));
    }

}
