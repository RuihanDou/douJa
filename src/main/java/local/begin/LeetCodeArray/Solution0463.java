package local.begin.LeetCodeArray;


/**
 * 463. 岛屿的周长
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 *
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 * 示例 2：
 *
 * 输入：grid = [[1]]
 * 输出：4
 * 示例 3：
 *
 * 输入：grid = [[1,0]]
 * 输出：4
 *
 *
 * 提示：
 *
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 */
public class Solution0463 {

    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {1,0,-1,0};

    // 为了方便，把遍历过的土地 grid[i][j] 设置成 1 以省去记忆矩阵
    public int islandPerimeter(int[][] grid) {
        // 因为 1 <= row, col <= 100 减少对 空的讨论
        int rows = grid.length, cols = grid[0].length;
        int ans = 0;
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    ans += dfs(i, j, grid, rows, cols);
                }
            }
        }
        return ans;
    }

    private int dfs(int x, int y, int[][] grid, int rows, int cols) {
        if(crossArea(x, y, rows, cols) || grid[x][y] == 0){
            return 1;
        }
        if(grid[x][y] == 2){
            return 0;
        }
        grid[x][y] = 2;
        int res = 0;
        for(int k = 0; k < 4; k++){
            int nextX = x + dx[k];
            int nextY = y + dy[k];
            res += dfs(nextX, nextY, grid, rows, cols);
        }
        return res;
    }

    private boolean crossArea(int i, int j, int rows, int cols) {
        return i < 0 || j < 0 || i >= rows | j >= cols;
    }

}
