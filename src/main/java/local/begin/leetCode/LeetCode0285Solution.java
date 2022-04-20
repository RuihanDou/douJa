package local.begin.leetCode;

import local.begin.struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * 285. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 *
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 */
public class LeetCode0285Solution {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        // 情况一 ： p 节点有右节点， 那么其右子树的最左端就是中序后继
        if(p.right != null){
            p = p.right;
            while (p.left != null){
                p = p.left;
            }
            return p;
        }

        // 情况二 ： p节点没有右节点，使用中序遍历
        Deque<TreeNode> stack = new ArrayDeque<>();
        int inOrder = Integer.MIN_VALUE;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            // 非递归中序深度优先遍历
            // 先走到该子树的最左端
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();

            // inOrder 记录的是cur的中序前继的值，如果 inOrder == p.val 证明 cur 是 p 的中序后继，返回cur
            if(inOrder == p.val){
                return cur;
            }

            // 记录此时节点的值到 inOrder
            inOrder = cur.val;
            // 当 cur 是一个子树的最左叶子节点时，cur = cur.right = null 进入下一个循环中
            cur = cur.right;
        }

        return null;
    }

}
