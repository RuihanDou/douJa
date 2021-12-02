package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class LeetCode0094Solution {

    private List<Integer> inOrderList = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        inOrder(root);
        return inOrderList;
    }

    private void inOrder(TreeNode node){
        if(node != null){
            inOrder(node.left);
            inOrderList.add(node.val);
            inOrder(node.right);
        }
    }

    private void inOrderNR(TreeNode node){
        if(node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node;

        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()){
                cur = stack.pop();
                inOrderList.add(cur.val);
                cur = cur.right;
            }
        }
    }

}
