package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 * 示例 2:
 *
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 *
 *
 * 提示:
 *
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
 */
public class LeetCode0106Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        int postLen = postorder.length;

        if(inLen != postLen){
            throw new RuntimeException("Incorrect input data");
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inLen; i++){
            map.put(inorder[i], i);
        }

        return buildTree(postorder, 0, postLen - 1, map, 0, inLen - 1);

    }

    private TreeNode buildTree(int[] postorder, int postLeft, int postRight, Map<Integer, Integer> map, int inLeft, int inRight){

        if(postLeft > postRight || inLeft > inRight){
            return null;
        }

        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);

        root.left = buildTree(postorder, postLeft, postLeft + pIndex - inLeft - 1, map, inLeft, pIndex - 1);
        root.right = buildTree(postorder, postRight - inRight + pIndex, postRight - 1, map, pIndex + 1, inRight);
        return root;
    }


}
