package local.begin.leetCode;

import local.begin.struct.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class LeetCode0230Solution {

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            k--;
            if(k == 0){
                break;
            }
            cur = cur.right;
        }

        return cur.val;
    }

}
