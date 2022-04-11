package local.begin.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 */

public class LeetCode0054Solution {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> list = new ArrayList<>();

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return list;
        }

        int rows = matrix.length, columns = matrix[0].length;

        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom){

            for(int column = left; column <= right; column++){
                list.add(matrix[top][column]);
            }
            for(int row = top + 1; row <= bottom; row++){
                list.add(matrix[row][right]);
            }

            if (left < right && top < bottom) {
                for(int column = right - 1; column > left; column --) {
                    list.add(matrix[bottom][column]);
                }
                for(int row = bottom; row > top; row --){
                    list.add(matrix[row][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;

        }

        return list;

    }

}
