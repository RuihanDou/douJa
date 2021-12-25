package local.begin.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 *
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * 示例：
 *
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 * 提示：
 *
 * board 是一个如上所述的 2 x 3 的数组.
 * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 */
public class LeetCode0773Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int slidingPuzzle(int[][] board) {

        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        String initalState = boardToString(board);
        if(initalState.equals("123450")){
            return 0;
        }

        // BFS
        queue.add(initalState);
        visited.put(initalState, 0);

        while (!queue.isEmpty()){
            String cur = queue.remove();
            ArrayList<String> nexts = getNexts(cur);

            for(String next : nexts){
                if(!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if(next.equals("123450")){
                        return visited.get(next);
                    }
                }
            }
        }

        return -1;
    }

    private ArrayList<String> getNexts(String s) {

        int[][] cur = stringToBoard(s);
        int zero;
        for(zero = 0; zero < 6; zero++){
            if(cur[zero / 3][zero % 3] == 0){
                break;
            }
        }

        ArrayList<String> res = new ArrayList<>();
        int zx = zero / 3, zy = zero % 3;
        for(int d = 0; d < 4; d++){
            int nextx = zx + dirs[d][0], nexty = zy + dirs[d][1];
            if(inArea(nextx, nexty)){
                swap(cur, zx, zy, nextx, nexty);
                res.add(boardToString(cur));
                swap(cur, zx, zy, nextx, nexty);
            }
        }
        return res;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < 2 && y >= 0 && y < 3;
    }

    private String boardToString(int[][] board){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[][] stringToBoard(String s){
        int[][] board = new int[2][3];
        for(int i = 0; i < 6; i++){
            board[i / 3][i % 3] = s.charAt(i) - '0';
        }
        return board;
    }

}
