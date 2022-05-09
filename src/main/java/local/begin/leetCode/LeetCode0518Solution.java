package local.begin.leetCode;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2：
 *
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 * 示例 3：
 *
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class LeetCode0518Solution {

    /**
     * 动态规划
     * dp[x] 表示金额之和等于x 的硬币组合数，目标是求dp[amount]
     * 动态规划边界是dp[0] = 1 只有当不取任何硬币这一种情况时，金额之和才为0
     *
     * 对于面额为coin的硬币，当 coin <= i <= amount时，如果存在一种硬币组合的金额之和等于 i - coin, 则在该硬币组合中增加一个面额为coin的硬币 即可得到一种金额之和等于i的硬币组合。
     *
     * 遍历 coins，对于其中每一个 coin ：
     *     遍历 i  从 coin 到 amount 将dp[i - coin] 增加到dp[i]
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

}
