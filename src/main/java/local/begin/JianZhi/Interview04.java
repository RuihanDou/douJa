package local.begin.JianZhi;


import java.util.Arrays;

/**
 * 面试题04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 */
public class Interview04 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        if(rows < 1){
            return false;
        }
        int coloumns = matrix[0].length;
        if(coloumns < 1) {
            return false;
        } else
        if (target < matrix[0][0] || target > matrix[rows - 1][ coloumns - 1]) {
            return false;
        }
        int row = 0;
        int coloumn = coloumns - 1;
        while (row < rows && coloumn >= 0) {
            if(matrix[row][coloumn] == target) {
                return true;
            } else if (matrix[row][coloumn] > target) {
                coloumn--;
            } else {
                row++;
            }

        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] array2D = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30},{19,22,24,27,31}};
        int[][] array2D = {{-5}};

        Interview04 interview = new Interview04();
        boolean res = interview.findNumberIn2DArray(array2D, -5);
        System.out.println(res);

    }

}
