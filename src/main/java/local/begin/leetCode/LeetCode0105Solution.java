package local.begin.leetCode;


import local.begin.struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 *
 *
 *
 * 示例 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * 提示:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 */
public class LeetCode0105Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;

        if(preLen != inLen){
            throw new RuntimeException("Incorrect input data.");
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++){
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);

    }

    /**
     *  前序遍历序列 preLeft preRight 之间可以拆成 3 部分
     *
     *  preLeft 根节点
     *  preLeft + 1 到 pIndex - inLeft + preLeft 为根节点的左子树
     *  pIndex - inLeft + preLeft + 1 到 preRight 为根节点的右子树
     *
     * 中序遍历序列 inLeft inRight 之间可以拆成 3 部分
     *
     * inLeft 到 pIndex - 1 为根节点的左子树
     * pIndex 根节点
     * pIndex + 1 到 inRight 为根节点的右子树
     *
     * @param preOrder 前序遍历序列
     * @param preLeft 前序遍历序列子区间的左边界 闭区间
     * @param preRight 前序遍历序列子区间的有边界 闭区间
     * @param map    在中序遍历序列里 数值与其下标的对应关系
     * @param inLeft   中序遍历序列子区间的左边界 闭区间
     * @param inRight  中序遍历序列子区间的有边界 闭区间
     * @return
     */
    private TreeNode buildTree(int[] preOrder, int preLeft, int preRight, Map<Integer, Integer> map, int inLeft, int inRight){
        if(preLeft > preRight || inLeft > inRight){
            return null;
        }

        int rootVal = preOrder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);

        root.left = buildTree(preOrder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1);
        root.right = buildTree(preOrder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
        return root;
    }

}
