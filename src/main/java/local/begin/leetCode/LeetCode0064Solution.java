package local.begin.leetCode;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class LeetCode0064Solution {

    // 使用动态规划
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        //定义 dp[i][j] 为 grid[i][j] 到达 grid[m][n] 的最短路径
        int[] dp = new int[n];
        // dp[i][j] = grid[i][j] + min(dp[i+1][j], dp[i][j+1])
        // 由于dp 第i行 只依赖于最多 第i+1行 和 本行的数值，所以只保留一行
        // 初始化，先获取 第 m - 1 行的 dp
        int cur = 0;
        for(int j = n - 1; j >= 0; j--){
            cur += grid[m-1][j];
            dp[j] = cur;
        }
        // 从 m - 2 行 开始 到 第 0 行， 按行更新dp
        for(int i = m - 2; i >= 0; i--){
            // 先获取 该行 的最后一列的数值
            dp[n - 1] = dp[n - 1] + grid[i][n-1];
            for(int j = n - 2; j >= 0; j--){
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];

    }

}
