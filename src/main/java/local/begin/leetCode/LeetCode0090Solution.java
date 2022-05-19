package local.begin.leetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class LeetCode0090Solution {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }

    public void dfs(boolean choosePre, int cur, int[] nums) {
        // 终止条件
        if (cur == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        // 跳过当前 cur 位置上的 考察下一个位置 cur + 1, 对下一个dfs来说，没有选上次的元素，所以，choosePre = false
        dfs(false, cur + 1, nums);
        // 如果没有选上一个元素，且上一个元素等于这个元素，那么意味着也不用选这一个元素，本次dfs跳过
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        // 选择当前 cur 位置上的元素
        temp.add(nums[cur]);
        // 因为选择了当前的元素，所以对下一个dfs来说，选择了上次的元素，所以，choosePre = true
        dfs(true, cur + 1, nums);
        // 为了回溯方便，temp 移除最后一个元素
        temp.remove(temp.size() - 1);
    }

}
