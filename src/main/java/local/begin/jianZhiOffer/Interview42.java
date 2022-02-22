package local.begin.jianZhiOffer;


/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 */
public class Interview42 {

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return 0;
        }

        // dp[i] 以nums[i]结尾的最大连续子数组的和
        int[] dp = new int[len];
        dp[0] = nums[0];
        int maxAns = dp[0];
        for(int i = 1; i < len; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxAns = Math.max(dp[i], maxAns);
        }
        return maxAns;
    }

}
