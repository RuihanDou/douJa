package local.begin.leetCode;

import java.util.Stack;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 *
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 *
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 *
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *
 *
 * 提示：
 *
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class LeetCode0085Solution {


    /**
     *                                [[0,0,0,0,0]
     *      [["1","0","1","0","0"],    [1,0,1,0,0],
     *       ["1","0","1","1","1"],    [1,0,1,2,3],
     *       ["1","1","1","1","1"],    [1,2,3,4,5],
     *       ["1","0","0","1","0"]]    [1,0,0,1,0],
     *                                 [0,0,0,0,0]]
     *       把每一行累积起来，再让每一列像求柱状图最大面积一样
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {

        int m = matrix.length, n = matrix[0].length;
        int[][] left = new int[m+2][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    left[i+1][j] = (j == 0 ? 0 : left[i+1][j - 1]) + 1;
                }
            }
        }
        m += 2;

        int ret = 0;
        for(int j = 0; j < n; j++){
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for(int i = 1; i < m; i++){
                while (left[i][j] < left[stack.peek()][j]){
                    int height = left[stack.pop()][j];
                    while (left[stack.peek()][j] == height){
                        stack.pop();
                    }
                    int width = i - stack.peek() - 1;
                    ret = Math.max(ret, width * height);
                }
                stack.push(i);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'},{'1', '0', '1', '1', '1'},{'1', '1', '1', '1', '1'},{'1', '0', '0', '1', '0'}};
        LeetCode0085Solution solution = new LeetCode0085Solution();
        System.out.println(solution.maximalRectangle(matrix));
    }

}
