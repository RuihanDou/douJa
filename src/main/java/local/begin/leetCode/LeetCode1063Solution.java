package local.begin.leetCode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 1063. 有效子数组的数目
 * 给定一个整数数组 nums ，返回满足下面条件的 非空、连续 子数组的数目：
 *
 * 子数组 是数组的 连续 部分。
 * 子数组最左边的元素不大于子数组中的其他元素 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,4,2,5,3]
 * 输出：11
 * 解释：有 11 个有效子数组，分别是：[1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：3
 * 解释：有 3 个有效子数组，分别是：[3],[2],[1] 。
 * 示例 3：
 *
 * 输入：nums = [2,2,2]
 * 输出：6
 * 解释：有 6 个有效子数组，分别为是：[2],[2],[2],[2,2],[2,2],[2,2,2] 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 105
 */
public class LeetCode1063Solution {

    /**
     * 使用单调栈
     *
     * Todo : 把过程模拟出来
     *
     *
     * @param nums
     * @return
     */
    public int validSubarrays(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0, len = nums.length;

        for (int i = 0; i < len; i++){

            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i]){
                ans += i - stack.pollLast();
            }
            stack.offerLast(i);
        }
        // 栈中剩余
        while (!stack.isEmpty()){
            ans += len - stack.pollLast();
        }

        return ans;
    }

}
