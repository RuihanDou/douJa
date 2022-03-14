package local.begin.leetCode;


import local.begin.struct.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * 示例 2：
 *
 * 输入：root = []
 * 输出：0
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点的数目范围是[0, 5 * 104]
 * 0 <= Node.val <= 5 * 104
 * 题目数据保证输入的树是 完全二叉树
 *
 *
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 */
public class LeetCode0222Solution {

    /**
     * 规定根结点位于第0层， 最大层数为h
     *
     * 如果 h 层只有一个节点，那么 节点个数是 2 ^ h
     * 如果 h 层满了，那么节点个数是 2 ^ (h + 1) - 1
     *
     * 二分查找节点个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }

        int level = 0;
        TreeNode node = root;
        while (node.left != null){
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high){
            int mid = (high - low + 1) / 2 + low;
            if(exists(root, level, mid)){
                low = mid;
            }
            else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * 如何判断第 kk 个节点是否存在呢？第0层的第一个节点是01， 第一层两个节点是 10， 11
     * 如果第 k 个节点位于第 h 层，则 k 的二进制表示包含 h+1 位，其中最高位是 1，
     * 其余各位从高到低表示从根节点到第 k 个节点的路径，0 表示移动到左子节点，1 表示移动到右子节点。
     *
     * @param root
     * @param level
     * @param k
     * @return
     */
    private boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0){
            if((bits & k) == 0){
                node = node.left;
            }
            else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }


}
