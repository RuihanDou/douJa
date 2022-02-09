package local.begin.leetCode;


/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：
 *
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LeetCode0300Solution {

//    /**
//     * 动态规划 dp[i] 为以第 i 个数字结尾的最长上升子序列长度 nums[i] 必须在子序列中
//     *
//     * 0 <= j < i 且 nums[j] < nums[i] 的 所有 dp[j]中的最大的 +1
//     *
//     * @param nums
//     * @return
//     */
//    public int lengthOfLIS(int[] nums) {
//        if(nums.length == 0){
//            return 0;
//        }
//        int[] dp = new int[nums.length];
//        dp[0] = 1;
//        int maxAns = 1;
//        for(int i = 1; i < nums.length; i++){
//            dp[i] = 1;
//            for(int j = 0; j < i; j++){
//                if(nums[j] < nums[i]){
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//            maxAns = Math.max(maxAns, dp[i]);
//        }
//        return maxAns;
//    }

    // TODO debug 弄明白
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
