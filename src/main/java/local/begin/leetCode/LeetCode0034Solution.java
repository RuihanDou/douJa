package local.begin.leetCode;


/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class LeetCode0034Solution {

    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1, -1};
        }
        int l = searchBegin(nums, target);
        if(nums[l] != target){
            return new int[]{-1, -1};
        }
        int r = searchEnd(nums, target);
        return new int[]{l, r};
    }


    // 在[l, r] 中寻找target左界
    public int searchBegin(int[] nums, int target){
        int l = 0, r = nums.length - 1;
        while (l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < target){
                l = mid + 1;
            }
            else { // nums[mid] >= target
                r = mid;
            }
        }
        return l;
    }

    // 在[l, r] 中寻找target右界
    public int searchEnd(int[] nums, int target){
        int l = 0, r = nums.length - 1;
        while (l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] > target){
                r = mid - 1;
            }
            else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        LeetCode0034Solution solution = new LeetCode0034Solution();
        int[] nums = {5,7,7,8,8,10};
        System.out.println(solution.searchBegin(nums, 10));
    }

}
