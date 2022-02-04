package local.begin.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 255. 验证前序遍历序列二叉搜索树
 * 给定一个 无重复元素 的整数数组 preorder ， 如果它是以二叉搜索树的先序遍历排列 ，返回 true 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: preorder = [5,2,1,3,6]
 * 输出: true
 * 示例 2：
 *
 * 输入: preorder = [5,2,6,1,3]
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= preorder.length <= 104
 * 1 <= preorder[i] <= 104
 * preorder 中 无重复元素
 *
 *
 * 进阶：您能否使用恒定的空间复杂度来完成此题？
 */
public class LeetCode0255Solution {

    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        if(preorder.length == 0){
            return true;
        }
        int pre = Integer.MIN_VALUE;
        for(int i = 0; i < preorder.length; i++){
            if(preorder[i] < pre){
                return false;
            }
            while (!stack.isEmpty() && stack.peek() < preorder[i]){
                pre = stack.pop();
            }
            stack.push(preorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,3,1};
        LeetCode0255Solution solution = new LeetCode0255Solution();
        System.out.println(solution.verifyPreorder(nums));
    }

}
