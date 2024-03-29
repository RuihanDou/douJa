package local.begin.leetCode;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 */
public class LeetCode0503Solution {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++){
            // 小于nums[i % n] 当前位置的都弹出，并且栈中存的索引的下一个更大元素就是nums[i % n]
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]){
                ret[stack.pop()] = nums[i % n];
            }
            //  栈顶 nums[stack.peek()] >= nums[i % n]，证明没找到 nums[stack.peek()] 的下一个最大元素，所以 入栈 i % n
            stack.push(i % n);
        }
        return ret;
    }

}
