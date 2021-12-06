package local.begin.leetCode;


import java.util.Arrays;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class LeetCode0416Solution {

    /**
     * dp[i][j] 表示下表为[0 , i]这个区间里的所有整数，在他们当中能否选出一些数，使得这些数之和恰好为整数j
     *  0 <= j <= sum/2
     *
     *  状态转移方程
     *      1.不选择 num[i] : dp[i][j] = dp[i-1][j]
     *      2.选择 num[i]:
     *          a. num[i] == j, dp[i][j] = true ;
     *          b. num[i] < j, dp[i][j] = dp[i-1][j-num[i]] ;
     *   初始化 : dp[i][j] = false
     *   输出: dp[len - 1][sum / 2]
     *
     *
     * @param nums
     * @return
     */

    public boolean canPartition0(int[] nums) {
        int len = nums.length;

        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        // 如果sum是奇数，直接返回 false
        if((sum & 1) == 1){
            return false;
        }
        // 转化为0-1背包问题，能否完成 nums 中选取某些数，使其和为 target
        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];
        //如果 nums[0] 不越界（不大于目标），则初始行 j == num[0]
        if(nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 续填表格
        for(int i = 1; i < len; i++){
            for (int j = 0; j <= target; j++){
                // 初始新的一行，先copy上一行的值，代表不选择 num[i] 见状态转移方程 1
                dp[i][j] = dp[i-1][j];
                // 选择nums[i] 第一种情况 ： 见状态转移方程2.a
                if(nums[i] == j){
                    dp[i][j] = true;
                    continue;
                }
                // 选择nums[i] 第二种情况 ： 见状态转移方程2.b
                if(nums[i] < j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
            // 提前终止，从i行起，dp[i][j]为true 则该列以后元素dp[i+n][j]都为true
            if(dp[i][target]){
                return true;
            }
        }
        return dp[len-1][target];
    }

    public boolean canPartition1(int[] nums) {
        int len = nums.length;

        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        // 如果sum是奇数，直接返回 false
        if((sum & 1) == 1){
            return false;
        }
        // 转化为0-1背包问题，能否完成 nums 中选取某些数，使其和为 target
        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];
        //如果 nums[0] 不越界（不大于目标），则初始行 j == num[0]
        // 不选择第一个元素，那么dp[0][0] = true成立
        dp[0][0] = true;
        if(nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 续填表格
        for(int i = 1; i < len; i++){
            for (int j = 0; j <= target; j++){
                // 初始新的一行，先copy上一行的值，代表不选择 num[i] 见状态转移方程 1
                dp[i][j] = dp[i-1][j];
                // 选择nums[i] 第一种情况 ： 见状态转移方程2.a
                // 当 num[i] == j 时，dp[?][j-nums[i]] = dp[?][0], 要求 dp[?][0] = true
                // 只需 初始化 dp[0][0] = true

                // 选择nums[i] 第二种情况 ： 见状态转移方程2.b
                if(nums[i] < j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
            // 提前终止，从i行起，dp[i][j]为true 则该列以后元素dp[i+n][j]都为true
            if(dp[i][target]){
                return true;
            }
        }
        return dp[len-1][target];
    }

    public boolean canPartition(int[] nums) {
        int len = nums.length;

        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        // 如果sum是奇数，直接返回 false
        if((sum & 1) == 1){
            return false;
        }
        // 转化为0-1背包问题，能否完成 nums 中选取某些数，使其和为 target
        int target = sum / 2;
        // 因为 dp[i][j] 的值 只依赖 其 上面一行同位置 或 左位置 的值，则 dp初始化后 从右向左更新 只需保留一行
        boolean[] dp = new boolean[target + 1];
        //如果 nums[0] 不越界（不大于目标），则初始行 j == num[0]
        // 不选择第一个元素，那么dp[0][0] = true成立
        dp[0] = true;
        if(nums[0] <= target) {
            dp[nums[0]] = true;
        }
        // 续填表格
        for(int i = 1; i < len; i++){
            // dp 需要从右向左填写
            for (int j = target; j >= 0; j--){
                // 初始新的一行，先copy上一行的值，代表不选择 num[i] 见状态转移方程 1
                // 这一步骤因为dp只保留一行，所以省略
                // dp[i][j] = dp[i-1][j];
                // 选择nums[i] 第一种情况 ： 见状态转移方程2.a
                // 当 num[i] == j 时，dp[?][j-nums[i]] = dp[?][0], 要求 dp[?][0] = true
                // 只需 初始化 dp[0][0] = true
                // 选择nums[i] 第二种情况 ： 见状态转移方程2.b
                if(nums[i] < j){
                    dp[j] = dp[j] || dp[j-nums[i]];
                }
            }
            // 提前终止，从i行起，dp[i][j]为true 则该列以后元素dp[i+n][j]都为true
            if(dp[target]){
                return true;
            }
        }
        return dp[target];
    }

}
