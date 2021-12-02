package local.begin.leetCode;

import local.begin.dataStructureAlgorithm.dataStruct.BST;
import local.begin.struct.TreeNode;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
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
 * 输出：[1,2]
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
public class LeetCode0144Solution {

    private List<Integer> preOrderList = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return this.preOrderList;
    }

    private void preOrder(TreeNode node){
        if(node != null){
            preOrderList.add(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void preOrderNR(TreeNode node){
        if(node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            preOrderList.add(cur.val);

            if(cur.right != null){
                stack.push(cur.right);
            }

            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

}
