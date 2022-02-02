package local.begin.leetCode;


/**
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 *
 * 提示：
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class LeetCode0079Solution {

    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private char[][] board;
    private boolean[][] visited;
    private int rows, cols;
    private String word;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if(check(i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param i    横坐标
     * @param j    纵坐标
     * @param k    单词字符位置
     * @return
     */
    private boolean check(int i, int j, int k){
        if(board[i][j] != word.charAt(k)){
            return false;
        }
        else if(k == word.length() - 1){
            return true;
        }

        visited[i][j] = true;
        boolean rst = false;
        for(int[] dir : directions){
            int nextI = i + dir[0], nextJ = j + dir[1];
            if(validateLocation(nextI, nextJ) && !visited[nextI][nextJ]){
                if(check(nextI, nextJ, k + 1)){
                    rst = true;
                }
            }
        }
        visited[i][j] = false;
        return rst;
    }

    private boolean validateLocation(int i, int j){
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public static void main(String[] args) {
        char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        String word = "AAB";
        LeetCode0079Solution solution = new LeetCode0079Solution();
        System.out.println(solution.exist(board, word));
    }

}
