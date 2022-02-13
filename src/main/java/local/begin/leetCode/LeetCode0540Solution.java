package local.begin.leetCode;


/**
 * 540. 有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 */
public class LeetCode0540Solution {


    /**
     *
     *  当 mid 在 target 左边时，
     *  mid 为奇数， nums[mid - 1] = nums[mid]
     *  mid 为偶数， nums[mid + 1] = nums[mid]
     *  即 mid < target 时 nums[mid ^ 1] = nums[mid]
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high){
            int mid = low + (high - low) / 2;
            if(nums[mid ^ 1] == nums[mid]){
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return nums[low];
    }

}
