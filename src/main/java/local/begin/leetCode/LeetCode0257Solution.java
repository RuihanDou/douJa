package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：["1"]
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 */
public class LeetCode0257Solution {

//    private List<String> res = new ArrayList<>();
//
//    public List<String> binaryTreePaths(TreeNode root) {
//        if(root == null){
//            return res;
//        }
//        String s = String.valueOf(root.val);
//        if(root.left == null && root.right == null){
//            res.add(s);
//            return res;
//        }
//        if(root.left != null){
//            dfs(root.left, s);
//        }
//        if(root.right != null){
//            dfs(root.right, s);
//        }
//        return res;
//    }
//
//    private void dfs(TreeNode node, String str){
//        str = str + "->" + node.val;
//        if(node.left == null && node.right == null){
//            res.add(str);
//            return;
//        }
//        if(node.left != null){
//            dfs(node.left, str);
//        }
//        if(node.right != null){
//            dfs(node.right, str);
//        }
//    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }

    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(root.val);
            if (root.left == null && root.right == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }


}
