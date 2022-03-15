package local.begin.leetCode;


import java.util.Arrays;

/**
 * 498. 对角线遍历
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 *
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 */
public class LeetCode0498Solution {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        int row = 0, col = 0;
        int direction = 1, r = 0;
        while (row < n && col < m){
            res[r] = mat[row][col];
            int newRow = row + (direction == 1 ? -1 : 1);
            int newCol = col + (direction == 1 ? 1 : -1);
            if(newRow < 0 || newRow == n || newCol < 0 || newCol == m){
                if(direction == 1){
                    row += (col == m - 1 ? 1 : 0);
                    col += (col < m - 1 ? 1 : 0);
                }
                else {
                    col += (row == n - 1 ? 1 : 0);
                    row += (row < n - 1 ? 1 : 0);
                }
                direction = 1 - direction;
            }
            else {
                row = newRow;
                col = newCol;
            }
            r++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        LeetCode0498Solution solution = new LeetCode0498Solution();
        int[] res = solution.findDiagonalOrder(mat);
        System.out.println(Arrays.toString(res));
    }

}
