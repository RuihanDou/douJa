package local.begin.leetCode;

import local.begin.struct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 95. 不同的二叉搜索树 II
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 */
public class LeetCode0095Solution {

    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end){
        List<TreeNode> allTrees = new LinkedList<>();

        if(start > end){
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行的根节点
        for(int i = start; i <= end; i++){
            // 获取所有左子树 和 右子树合集
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 拼接
            for(TreeNode left : leftTrees){
                for (TreeNode right : rightTrees){
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    cur.right =right;
                    allTrees.add(cur);
                }
            }
        }
        return allTrees;
    }

}
