package local.begin.LeetCode;

import local.begin.Tools.DebugTools;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 *
 *
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 300
 * -231 <= nums[i] <= 231 - 1
 */

public class LeetCode041Solution {

    public int firstMissingPositive(int[] nums) {

        int len = nums.length;
        for(int i = 0; i < len; i++) {
            if(nums[i] <= 0){
                nums[i] = len + 1;
            }
        }

        for(int i = 0; i < len; i++){
            int num = Math.abs(nums[i]);
            if(num <= len) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for(int i = 0; i < len; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return len + 1;
    }


    public static void main(String[] args) {

        int nums[] = {1,2,0};
        int nums1[] = {3,4,-1,1};
        int nums2[] = {7,8,9,11,12};

        LeetCode041Solution solution = new LeetCode041Solution();
        int rst = solution.firstMissingPositive(nums1);
        DebugTools.print(rst);
    }

}
