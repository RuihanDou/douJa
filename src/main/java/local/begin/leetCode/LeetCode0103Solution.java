package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 */
public class LeetCode0103Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if(root == null){
            return res;
        }

        deque.addLast(root);
        int levelNum = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; i++){
                boolean leftToRight = levelNum % 2 == 0;
                TreeNode cur = leftToRight ? deque.removeFirst() : deque.removeLast();
                level.add(cur.val);
                if(leftToRight){
                    if(cur.left != null){
                        deque.addLast(cur.left);
                    }
                    if(cur.right != null){
                        deque.addLast(cur.right);
                    }
                }
                else {
                    if(cur.right != null){
                        deque.addFirst(cur.right);
                    }
                    if(cur.left != null){
                        deque.addFirst(cur.left);
                    }
                }
            }
            res.add(level);
            levelNum++;
        }

        return res;
    }

}
