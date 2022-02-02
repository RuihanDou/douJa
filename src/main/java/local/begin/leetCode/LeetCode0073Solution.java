package local.begin.leetCode;

/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 */
public class LeetCode0073Solution {

//    public void setZeroes(int[][] matrix) {
//        int m = matrix.length, n = matrix[0].length;
//        // 标记第一列 和 第一行 是否有 0
//        boolean flagCol0 = false, flagRow0 = false;
//        for (int i = 0; i < m; i++) {
//            if (matrix[i][0] == 0) {
//                flagCol0 = true;
//            }
//        }
//        for (int j = 0; j < n; j++) {
//            if (matrix[0][j] == 0) {
//                flagRow0 = true;
//            }
//        }
//
//        // 当一个元素为0时， 把它所在的 列头 和 行首 都置为0
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (matrix[i][j] == 0) {
//                    matrix[i][0] = matrix[0][j] = 0;
//                }
//            }
//        }
//
//        // 使用标记置零
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//
//        // 如果第一列有0，把第一列置零
//        if (flagCol0) {
//            for (int i = 0; i < m; i++) {
//                matrix[i][0] = 0;
//            }
//        }
//        // 如果第一行有0，把第一行置零
//        if (flagRow0) {
//            for (int j = 0; j < n; j++) {
//                matrix[0][j] = 0;
//            }
//        }
//    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 只标记第一列是否有0
        boolean flagCol0 = false;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
            // 从该列第二个元素 开始遍历
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 从最后一行往前遍历， 为了防止第一行 有 被置零的元素 从前往后遍历行导致 之后无法判断列是否标记0
        for (int i = m - 1; i >= 0; i--) {
            // 从第二列往后遍历
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }


}
