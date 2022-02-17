package local.begin.leetCode;


/**
 * 688. 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 *
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 *
 *
 *
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 *
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 *
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 *
 *
 *
 * 示例 1：
 *
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 *
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *
 *
 * 提示:
 *
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 */
public class LeetCode0688Solution {

    private static final int[][] dirs = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};

    /**
     *
     * 使用 dp[step][i][j] 表示骑士从棋盘上的点(i, j)出发，走了step步时，仍然保留在棋盘上的概率。
     *
     * 特别的，当点(i, j)不在棋盘上时 dp[step][i][j] = 0
     *        当step = 0 时dp[step][i][j] = 1
     *
     * 一般地，dp[step][i][j] = 0.125 * (sum_(i_n, j_n) dp[step - 1][i_n][j_n])
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for(int step = 0; step <= k; step++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){

                    if(step == 0){
                        dp[step][i][j] = 1;
                    }

                    else {
                        for(int[] dir : dirs){
                            int ni = i + dir[0], nj = j + dir[1];
                            if(ni >= 0 && ni < n && nj >= 0 && nj < n){
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }

                    }


                }
            }
        }
        return dp[k][row][column];
    }

}
