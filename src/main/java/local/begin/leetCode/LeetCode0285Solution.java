package local.begin.leetCode;

import local.begin.struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 *
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
