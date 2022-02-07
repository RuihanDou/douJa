package local.begin.leetCode;


/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class LeetCode0045Solution {

    // 使用贪心，每次
    public int jump(int[] nums) {
        int len = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < len - 1; i++){
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // i == 0 时，以下条件可以命中，证明要往前走一步
            // 之后的几步 nums[2]... 如果无法超过 maxPosition，那么 maxPosittion不会更新
            // 下次命中 i == end 时，也许不是 第 i 个位置使得 跳到 maxPosition，但是 在 i 之前的某个位置（或者i）一定能让step增加一步跳到 maxPosition的位置
            if(i == end){
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,1,1,4};
        LeetCode0045Solution solution = new LeetCode0045Solution();
        System.out.println(solution.jump(nums));
    }

}
