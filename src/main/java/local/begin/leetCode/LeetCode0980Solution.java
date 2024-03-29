package local.begin.leetCode;


/**
 * 980. 不同路径 III
 * 在二维网格 grid 上，有 4 种类型的方格：
 *
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 *
 * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * 输出：2
 * 解释：我们有以下两条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 示例 2：
 *
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * 输出：4
 * 解释：我们有以下四条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * 示例 3：
 *
 * 输入：[[0,1],[2,0]]
 * 输出：0
 * 解释：
 * 没有一条路能完全穿过每一个空的方格一次。
 * 请注意，起始和结束方格可以位于网格中的任意位置。
 *
 *
 * 提示：
 *
 * 1 <= grid.length * grid[0].length <= 20
 */
public class LeetCode0980Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int R, C;
    private boolean[][] visited;
    private int start, end;

    public int uniquePathsIII(int[][] grid) {

        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        visited = new boolean[R][C];
        int left = R * C;

        for(int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                if(grid[i][j] == 1){
                    start = i * C + j;
                    grid[i][j] = 0;
                } else if(grid[i][j] == 2){
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if(grid[i][j] == -1){
                    left--;
                }
            }
        }

        return dfs(start, left);

    }

    private int dfs(int v, int left) {
        int x = v / C, y = v % C;
        visited[x][y] = true;
        left--;

        if(left == 0 && v == end){
            visited[x][y] = false;
            return 1;
        }

        int res = 0;
        for (int d = 0; d < 4; d++){
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && grid[nextx][nexty] == 0 && !visited[nextx][nexty]){
                res += dfs(nextx * C + nexty, left);
            }
        }
        visited[x][y] = false;
        return res;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

//    private int ans;
//    private int[][] grid;
//    int R, C;
//    int tr, tc, target;
//    int[] dr = {0, -1, 0, 1};
//    int[] dc = {1, 0, -1, 0};
//    Integer[][][] memo;
//
//    public int uniquePathsIII(int[][] grid){
//        this.grid = grid;
//        R = grid.length;
//        C = grid[0].length;
//        target = 0;
//
//        int sr = 0, sc = 0;
//        for(int r = 0; r < R; r++){
//            for(int c = 0; c < C; c++){
//                if(grid[r][c] % 2 == 0){
//                    target |= code(r, c);
//                }
//                if(grid[r][c] == 1){
//                    sr = r;
//                    sc = c;
//                } else if(grid[r][c] == 2){
//                    tr = r;
//                    tc = c;
//                }
//            }
//        }
//        memo = new Integer[R][C][1 << (R*C)];
//        return dp(sr, sc, target);
//    }
//
//    /**
//     * 返回 从 r, c 开始行走， 还没遍历的无障碍方格集合todo 的 路径数量
//     * @param r
//     * @param c
//     * @param todo 状态压缩后的 没有遍历的无障碍方格
//     * @return
//     */
//    private int dp(int r, int c, int todo) {
//        if (memo[r][c][todo] != null){
//            return memo[r][c][todo];
//        }
//
//        if(r == tr && c == tc){
//            return todo == 0 ? 1 : 0;
//        }
//
//        int ans = 0;
//        for(int k = 0; k < 4; k++){
//            int nr = r + dr[k];
//            int nc = c + dc[k];
//            if(inArea(nr, nc)){
//                if((todo & code(nr, nc)) != 0){
//                    ans += dp(nr, nc, todo ^ code(nr, nc));
//                }
//            }
//        }
//        memo[r][c][todo] = ans;
//        return ans;
//    }
//
//    public int code(int r, int c) {
//        return 1 << (r * C + c);
//    }
//
//    private boolean inArea(int r, int c){
//        return  r >= 0 && r < R && c >= 0 && c < C;
//    }

}
