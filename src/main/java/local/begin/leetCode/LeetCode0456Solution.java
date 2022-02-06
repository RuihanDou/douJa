package local.begin.leetCode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 456. 132 模式
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 *
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 *
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 2 * 105
 * -109 <= nums[i] <= 109
 */
public class LeetCode0456Solution {

    public boolean find132pattern(int[] nums) {

        int n = nums.length;
        Deque<Integer> cadidateK = new LinkedList<>();
        cadidateK.push(nums[n - 1]);
        // maxK为可以作为 nums[k]的最大值 // 因为一开始
        int maxK = Integer.MIN_VALUE;

        // 求 nums 中 的索引 i < j < k 存在关系 nums[i] < nums[k] < nums[j]
        // 从右向左 枚举 i, nums[i] < nums[j]
        // 使用单调栈 cadidateK 维护 nums[k]
        for(int i = n - 2; i >= 0; i--){

            // 判断是否满足条件 nums[i] < nums[k] 找到了一组情况（nums[k] < nums[j]）
            // nums[k] 只能从 candidateK中取出
            // 如果nums[k] 不是Integer.MIN_VALUE， 则candidate中一定存在着一个可以作为nums[j]
            if(nums[i] < maxK){
                return true;
            }
            // 判断 nums[i] 是否可以作为 nums[j]
            // 如果 nums[i] 大于 栈顶元素，（那么 nums[i] 就作为 nums[j]（下面的if条件操作））, 原来的栈顶元素就作为 nums[k]
            while (!cadidateK.isEmpty() && nums[i] > cadidateK.peek()){
                maxK = cadidateK.pop();
            }
            //  如果 nums[i] 大于 nums[k] 那么 nums[i] 作为 nums[j] 的候选元素加入单调栈
            //    如果没有命中上面的while：说明 cadidate 为空 或者 nums[i] <= num[j]已经满足 此时 nums[i] > nums[k]  那它可以作为 nums[j] 的另一个备选
            if(nums[i] > maxK){
                cadidateK.push(nums[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 3, 2, 0};
        LeetCode0456Solution solution = new LeetCode0456Solution();
        System.out.println(solution.find132pattern(nums));
    }

}
