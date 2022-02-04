package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 *
 *
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *
 *
 * 提示：
 *
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 *
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？
 */
public class LeetCode0099Solution {

    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    public void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }



//    // 使用 Morris 中序遍历
//    public void recoverTree(TreeNode root) {
//
//        TreeNode x = null, y = null, pred = null, predecessor = null;
//
//        while (root != null){
//            if(root.left != null){
//                // 先求predecessor: 当前root节点向左走一步，然后一直走到最右端
//                predecessor = root.left;
//                while (predecessor.right != null && predecessor.right != root){
//                    predecessor = predecessor.right;
//                }
//
//                // 让predecessor的右指针指向 root，继续遍历左子树
//                if(predecessor.right == null){
//                    predecessor.right = root;
//                    root = root.left;
//                }
//
//                // 以上条件过后，证明左子树遍历完成，断开连接
//                else {
//                    if(pred != null && root.val < pred.val){
//                        y = root;
//                        if(x == null){
//                            x = pred;
//                        }
//                    }
//                    pred = root;
//                    predecessor.right = null;
//                    root = root.right;
//                }
//            }
//
//            // 如果没有左孩子，直接访问右孩子
//            else {
//                if(pred != null && root.val < pred.val){
//                    y = root;
//                    if(x == null){
//                        x = pred;
//                    }
//                }
//                pred = root;
//                root = root.right;
//            }
//        }
//        swap(x, y);
//    }
//
//    private void swap(TreeNode x, TreeNode y) {
//        int tmp = x.val;
//        x.val = y.val;
//        y.val = tmp;
//    }

}
