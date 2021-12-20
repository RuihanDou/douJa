package local.begin.leetCode;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * todo : 使用图的算法解决本问题
 *
 * 1162. 地图分析
 * 你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。
 *
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 * 如果网格上只有陆地或者海洋，请返回 -1。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 *
 *
 *
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
 *
 *
 * 提示：
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 不是 0 就是 1
 * 通过次数39,187提交次数83,289
 */
public class LeetCode1162Solution {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private int n;
    private int[][] grid;

    public int maxDistance(int[][] grid) {
        this.n = grid.length;
        this.grid = grid;
        int ans = -1;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    ans = Math.max(ans, findNearestLand(i, j));
                }
            }
        }
        return ans;
    }

    public int findNearestLand(int x, int y) {
        boolean[][] vis = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        vis[x][y] = true;

        while (!queue.isEmpty()){
            int[] f = queue.poll();
            for(int i = 0; i < 4; i++){
                int nx = f[0] + dx[i], ny = f[1] + dy[i];
                if(!(nx >= 0 && nx < n && ny >=0 && ny < n)){
                    continue;
                }
                if(!vis[nx][ny]){
                    queue.offer(new int[]{nx, ny, f[2] + 1});
                    vis[nx][ny] = true;
                    if (grid[nx][ny] == 1){
                        return f[2] + 1;
                    }
                }
            }
        }
        return -1;
    }

}
