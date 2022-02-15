package local.begin.leetCode;


import java.util.*;

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 */
public class LeetCode0051Solution {

//    /**
//     * 第一种解法，基于集合的回溯
//     * @param n
//     * @return
//     */
//    public List<List<String>> solveNQueens(int n) {
//        List<List<String>> solutions = new ArrayList<>();
//        int[] queens = new int[n];
//        Arrays.fill(queens, -1);
//        Set<Integer> columns = new HashSet<>();
//        Set<Integer> diagonals1 = new HashSet<>();
//        Set<Integer> diagonals2 = new HashSet<>();
//        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
//        return solutions;
//    }
//
//    private void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
//
//        if(row == n){
//            List<String> board = generateBoard(queens, n);
//            solutions.add(board);
//        }
//
//        else {
//            for(int i = 0; i < n; i++){
//                if(columns.contains(i)){
//                    continue;
//                }
//                int diagonal1 = row - i;
//                if(diagonals1.contains(diagonal1)){
//                    continue;
//                }
//                int diagonal2 = row + i;
//                if(diagonals2.contains(diagonal2)){
//                    continue;
//                }
//                queens[row] = i;
//                columns.add(i);
//                diagonals1.add(diagonal1);
//                diagonals2.add(diagonal2);
//                backtrack(solutions, queens, n, row + 1, columns,diagonals1, diagonals2);
//                queens[row] = -1;
//                columns.remove(i);
//                diagonals1.remove(diagonal1);
//                diagonals2.remove(diagonal2);
//            }
//        }
//
//    }
//
    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++){
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    /**
     * 基于位运算，状态压缩的回溯
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    private void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {

        if(row == n){
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        }

        else {
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0){
                // x & (−x) 可以获得 x 的二进制表示中的最低位的 1 的位置
                int position = availablePositions & (-availablePositions);
                // x & (x−1) 可以将 x 的二进制表示中的最低位的 1 置成 0 方便下一次while循环
                availablePositions= availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
                queens[row] = -1;
            }
        }

    }


}
