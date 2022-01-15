package local.begin.leetCode;

import local.begin.LeetCodeArray.Solution0035;

/**
 *35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 *
 * 输入: nums = [1], target = 0
 * 输出: 0
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 */
public class LeetCode0035Solution {

    // nums[pos - 1] < target <= nums[pos]
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int rst = nums.length;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // nums[mid] >= target的时候，target有可能为 mid
            if(nums[mid] >= target){
                rst = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return rst;
    }

}
