package local.begin.leetCode;


import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 */
public class LeetCode0120Solution {

    /**
     * 动态规划
     *
     * dp[i][j] 表示从三角形定走到位置 i,j 的最小路径和
     * 那么  一般的  dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j]
     *      特别地   dp[i][j] = dp[i - 1][0] + triangle[i][j]   j = 0 时
     *              dp[i][j] = dp[i - 1][i - 1] + triangle[i][j]  j = i 时
     *              dp[0][0] = triangle[0][0]
     *
     *
     * 由于dp[i]行 只依靠 dp[i - 1]这一行，所以
     * 只用一行数组 dp
     *
     * 那么  dp[j] = min(dp[j - 1], dp[j]) + triangle[i][j]
     * 每行从后开始遍历
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for(int i = 1; i < n; i++){
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for(int j = i - 1; j > 0; j--){
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }
        int minTotal = dp[0];
        for(int i = 1; i < n; i++){
            minTotal = Math.min(minTotal, dp[i]);
        }
        return minTotal;
    }

}
