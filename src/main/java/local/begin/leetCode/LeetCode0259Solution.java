package local.begin.leetCode;


import java.util.Arrays;

/**
 * 259. 较小的三数之和
 * 给定一个长度为 n 的整数数组和一个目标值 target ，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。
 *
 *
 *
 * 示例 1：
 *
 * 输入: nums = [-2,0,1,3], target = 2
 * 输出: 2
 * 解释: 因为一共有两个三元组满足累加和小于 2:
 *      [-2,0,1]
 *      [-2,0,3]
 * 示例 2：
 *
 * 输入: nums = [], target = 0
 * 输出: 0
 * 示例 3：
 *
 * 输入: nums = [0], target = 0
 * 输出: 0
 *
 *
 * 提示:
 *
 * n == nums.length
 * 0 <= n <= 3500
 * -100 <= nums[i] <= 100
 * -100 <= target <= 100
 */
public class LeetCode0259Solution {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length - 2; i++){
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target){
        int sum = 0;
        int left = startIndex, right = nums.length - 1;
        while (left < right){
            if(nums[left] + nums[right] < target){
                sum += right - left;
                left++;
            }
            else {
                right--;
            }
        }
        return sum;
    }

}
