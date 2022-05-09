package local.begin.leetCode;


import java.util.ArrayList;
import java.util.List;

/**
 * 305. 岛屿数量 II
 * 给你一个大小为 m x n 的二进制网格 grid 。网格表示一个地图，其中，0 表示水，1 表示陆地。最初，grid 中的所有单元格都是水单元格（即，所有单元格都是 0）。
 *
 * 可以通过执行 addLand 操作，将某个位置的水转换成陆地。给你一个数组 positions ，其中 positions[i] = [ri, ci] 是要执行第 i 次操作的位置 (ri, ci) 。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是将单元格 (ri, ci) 转换为陆地后，地图中岛屿的数量。
 *
 * 岛屿 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。
 *
 *
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * 输出：[1,1,2,3]
 * 解释：
 * 起初，二维网格 grid 被全部注入「水」。（0 代表「水」，1 代表「陆地」）
 * - 操作 #1：addLand(0, 0) 将 grid[0][0] 的水变为陆地。此时存在 1 个岛屿。
 * - 操作 #2：addLand(0, 1) 将 grid[0][1] 的水变为陆地。此时存在 1 个岛屿。
 * - 操作 #3：addLand(1, 2) 将 grid[1][2] 的水变为陆地。此时存在 2 个岛屿。
 * - 操作 #4：addLand(2, 1) 将 grid[2][1] 的水变为陆地。此时存在 3 个岛屿。
 * 示例 2：
 *
 * 输入：m = 1, n = 1, positions = [[0,0]]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= m, n, positions.length <= 104
 * 1 <= m * n <= 104
 * positions[i].length == 2
 * 0 <= ri < m
 * 0 <= ci < n
 *
 *
 * 进阶：你可以设计一个时间复杂度 O(k log(mn)) 的算法解决此问题吗？（其中 k == positions.length）
 */
public class LeetCode0305Solution {

    private final static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    private int rows;
    private int cols;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        this.rows = m;
        this.cols = n;
        UnionFind unionFind = new UnionFind(m * n);
        boolean[] visited = new boolean[m * n];

        List<Integer> res = new ArrayList<>();
        for(int[] position : positions){
            int curI = position[0];
            int curJ = position[1];
            // 并查集 和 访问记录里的 索引 是 i * cols + j = j * rows + i （行序号 * 列数 + 列序号 / 列序号 * 行数 + 行序号）
            int index = curI * cols + curJ;
            if(!visited[index]){
                // 把水反转成陆地
                unionFind.addCount();
                visited[index] = true;
                for (int[] direction : directions){
                    int nextI = curI + direction[0];
                    int nextJ = curJ + direction[1];
                    int nextIndex = nextI * cols + nextJ;
                    // 确保下一个考察的位置 1、在地图里 2、被访问过 3、没有被并查集联通，此时进行union 操作
                    if(inArea(nextI, nextJ) && visited[nextIndex] && !unionFind.isConnected(index, nextIndex)){
                        unionFind.unionElements(index, nextIndex);
                    }
                }
            }
            res.add(unionFind.getCount());
        }

        return res;

    }

    private boolean inArea(int nextI, int nextJ) {
        return nextI >= 0 && nextI < rows && nextJ >= 0 && nextJ < cols;
    }

    /**
     * 并查集要改造能用来 计算 土地合并的数量
     */
    private class UnionFind{

        private int[] parent;

        private int count;

        public UnionFind(int size){
            parent = new int[size];

            for(int i = 0; i < size; i++){
                parent[i] = i;

            }
        }

        public int getSize() {
            return parent.length;
        }

        public int getCount(){
            return count;
        }

        public void addCount(){
            count++;
        }

        // 查找过程，查找元素p所在的集合编号
        // O(h)复杂度， h 为树的高度
        private int find(int p){
            if(p < 0 || p >= parent.length){
                throw new IllegalArgumentException("p is out of bound.");
            }

            while (p != parent[p]){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上

        public void unionElements(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if(pRoot == qRoot){
                return;
            }

            parent[pRoot] = qRoot;
            count--;

        }
    }

}
