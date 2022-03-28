package local.begin.leetCode;


import local.begin.struct.TreeNode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class LeetCode0543Solution {

    private int globalMax = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return globalMax - 1;
    }

    /**
     *  dfs 有两个过程，一个是线上返回经过本节点的最深路径，这个值 作为dfs的返回
     *
     *                 另一个是更新 globalMax 由于 globalMax 和 返回值要分别返回
     *
     *
     * @param node
     * @return
     */
    private int dfs(TreeNode node){

        if(node == null){
            return 0;
        }

        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);

        globalMax = Math.max(globalMax, leftDepth + rightDepth + 1);

        return Math.max(leftDepth, rightDepth) + 1;

    }

}
