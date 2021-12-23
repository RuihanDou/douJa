package local.begin.leetCode;


import java.util.HashSet;

/**
 * 695. 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *              [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *              [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *              [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *              [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *              [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * 示例 2：
 *
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 */
public class LeetCode0695Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private int[][] grid;

    private boolean[][] visted;

    public int maxAreaOfIsland(int[][] grid) {

        if(grid == null) {
            return 0;
        }

        R = grid.length;
        if(R == 0){
            return 0;
        }
        C = grid[0].length;
        if(C == 0){
            return 0;
        }

        this.grid = grid;

        int res = 0;
        visted = new boolean[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(!visted[i][j] && grid[i][j] == 1){
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int x, int y) {
        visted[x][y] = true;
        int res = 1;

        for(int d = 0; d < 4; d++){
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visted[nextx][nexty] && grid[nextx][nexty] == 1){
                res += dfs(nextx, nexty);
            }
        }

        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
