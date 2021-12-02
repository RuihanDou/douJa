package local.begin.leetCode;


import local.begin.dataStructureAlgorithm.dataStruct.BST;
import local.begin.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class LeetCode0145Solution {

    private List<Integer> postOrderList = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        return postOrderList;
    }

    private void postOrder(TreeNode node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            postOrderList.add(node.val);
        }
    }

    private void postOrderNR(TreeNode node){
        if(node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode cur = node;
        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(cur.right == null || cur.right == prev){
                postOrderList.add(cur.val);
                prev = cur;
                cur = null;
            } else {
                stack.push(cur);
                cur = cur.right;
            }
        }
    }

}
