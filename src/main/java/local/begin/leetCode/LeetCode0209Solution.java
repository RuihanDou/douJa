package local.begin.leetCode;


import java.util.Arrays;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class LeetCode0209Solution {

    /**
     * 使用滑动窗口实现的O(n) 时间复杂度的解法
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // l 是窗口的左端
        int l = 0;
        // sum 记录连续字串的和
        int sum = 0;
        // r 是窗口的右端 向后移动
        for(int r = 0; r < n; r++){
            sum += nums[r];
            while (sum >= target){
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return  ans == Integer.MAX_VALUE ? 0 : ans;
    }

//    /**
//     *  使用前缀和 + 二分查找 O(n log(n))
//     *
//     * @param target
//     * @param nums
//     * @return
//     */
//    public int minSubArrayLen(int target, int[] nums) {
//        int n = nums.length;
//        if (n == 0) {
//            return 0;
//        }
//        int ans = Integer.MAX_VALUE;
//        int[] sums = new int[n + 1];
//        // 为了方便计算，令 size = n + 1
//        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
//        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
//        // 以此类推
//        for (int i = 1; i <= n; i++) {
//            sums[i] = sums[i - 1] + nums[i - 1];
//        }
//        for (int i = 1; i <= n; i++) {
//            int t = target + sums[i - 1];
//            int bound = Arrays.binarySearch(sums, t);
//            if (bound < 0) {
//                bound = -bound - 1;
//            }
//            if (bound <= n) {
//                ans = Math.min(ans, bound - (i - 1));
//            }
//        }
//        return ans == Integer.MAX_VALUE ? 0 : ans;
//    }
}
