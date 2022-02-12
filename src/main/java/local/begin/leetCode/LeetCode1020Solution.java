package local.begin.leetCode;


/**
 * 1020. 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 */
public class LeetCode1020Solution {

    private static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    private int m, n;
    private boolean[][] visited;

    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        // 把濒临边界的陆地都找出来，在visited里标记出来
        for(int i = 0; i < m; i++){
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        for(int j = 0; j < n; j++){
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        int enclaves = 0;
        for(int i = 1; i < m - 1; i++){
            for(int j = 1; j < n - 1; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    private void dfs(int[][] grid, int row, int col){
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0 || visited[row][col]){
            return;
        }
        visited[row][col] = true;
        for(int[] dir : dirs){
            dfs(grid, row + dir[0], col + dir[1]);
        }
    }

}
