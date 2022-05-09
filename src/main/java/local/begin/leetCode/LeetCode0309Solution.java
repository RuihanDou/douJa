package local.begin.leetCode;


/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 *
 * 输入: prices = [1]
 * 输出: 0
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class LeetCode0309Solution {

    /**
     * 动态规划
     *
     * dp[i] 表示第 i 天结束后的最大收益
     * 但是分成了三种情况：
     * dp[i][0] 记录 目前持有一支股票对应的 最大收益
     * dp[i][1] 记录 目前不持有任何股票，且处于冷冻期中，对应的最大收益
     * dp[i][2] 记录 目前不持有任何股票，不处于冷冻期中，对应的最大收益
     *
     * 第 0 天 dp[0][0] = -prices[0] dp[0][1] = 0 dp[0][2] = 0
     *
     * 每次更新有这三种情况
     * dp[i][0] = max ( dp[i-1][0] , dp[i-1][2] - prices[i] )
     * dp[i][1] = dp[i-1][0] + prices[i]
     * dp[i][2] = max ( dp[i-1][1] , dp[i-1][2] )
     *
     * 到最后 取 dp[n - 1][0], dp[n - 1][1], dp[n - 1][2] 中间最大的
     * 最后一天手上持有股票没有意义，所以dp[n-1][0]不用参加比较
     *
     * 因为dp[i]的更新只跟dp[i - 1]相关，所以可以压缩空间
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }
        int dp0 = -prices[0], dp1 = 0, dp2 = 0;
        for (int i = 1; i < prices.length; ++i) {
            int new0 = Math.max(dp0, dp2 - prices[i]);
            int new1 = dp0 + prices[i];
            int new2 = Math.max(dp1, dp2);
            dp0 = new0;
            dp1 = new1;
            dp2 = new2;
        }

        return Math.max(dp1, dp2);

    }

}
