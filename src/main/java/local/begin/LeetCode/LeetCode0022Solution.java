package local.begin.LeetCode;

import java.util.*;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 */

public class LeetCode0022Solution {

    public List<String> generateParenthesis(int n){
        List<String> rst = new ArrayList<>();
        recursion(rst, new StringBuilder(), 0, 0, n);
        return rst;
    }

    private void recursion(List<String> list, StringBuilder cur, int l, int r, int max){

        // 递归成功且结束
        if(cur.length() == max * 2){
            list.add(cur.toString());
            return;
        }

        // 左括号数量只要小于最大值就可以添加
        if (l < max){
            cur.append('(');
            recursion(list, cur, l+1, r, max);
            cur.deleteCharAt(cur.length() - 1);
        }

        // 右括号必须数量小于左括号才可以添加
        if (r < max && r < l){
            cur.append(')');
            recursion(list, cur, l, r+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode0022Solution solution = new LeetCode0022Solution();
        System.out.println(solution.generateParenthesis(3));
    }

}
