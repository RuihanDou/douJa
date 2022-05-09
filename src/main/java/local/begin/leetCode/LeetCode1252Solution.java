package local.begin.leetCode;

/**
 * 1252. 奇数值单元格的数目
 * 给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
 *
 * 另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 *
 * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
 *
 * ri 行上的所有单元格，加 1 。
 * ci 列上的所有单元格，加 1 。
 * 给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：m = 2, n = 3, indices = [[0,1],[1,1]]
 * 输出：6
 * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
 * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
 * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
 * 示例 2：
 *
 *
 *
 * 输入：m = 2, n = 2, indices = [[1,1],[0,0]]
 * 输出：0
 * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 50
 * 1 <= indices.length <= 100
 * 0 <= ri < m
 * 0 <= ci < n
 *
 *
 * 进阶：你可以设计一个时间复杂度为 O(n + m + indices.length) 且仅用 O(n + m) 额外空间的算法来解决此问题吗？
 */
public class LeetCode1252Solution {

    /**
     * 直接用某一行，某一列的操作的次数的奇偶性来判断。
     * 就是先建立两数组来判断某行和某列分别被操作（被加1）了奇数次还是偶数次（奇数次就是1，偶数次0），
     * 在分别统计共有多少行（假设是h）和共有多少列（假设是l）操作了奇数次。
     * 注意，如果只是相加结果h * m+l * n的话，首先多算了行列交汇处的数字，
     * 因为那里的数字每次操作是进行了两次加1，另外在上述相加的式子里面也是重复计算了，
     * 所以最后要减去两倍重复计算，也就是减去h*l的两倍。
     *
     * @param n
     * @param m
     * @param indices
     * @return
     */
    public int oddCells(int n, int m, int[][] indices) {
        int rows[]=new int[n];
        int cols[]=new int[m];
        for(int i=0;i<indices.length;i++){
            rows[indices[i][0]]++;
            rows[indices[i][0]]%=2;
            cols[indices[i][1]]++;
            cols[indices[i][1]]%=2;
        }
        int r=0;
        int c=0;
        for(int i=0;i<n;i++){
            r+=rows[i];
        }
        for(int i=0;i<m;i++){
            c+=cols[i];
        }
        return r*m+c*n-2*r*c;
    }

}
