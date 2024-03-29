package local.begin.leetCode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */
public class LeetCode0032Solution {


    /**
     * 动态规划
     *
     * 定义 dp[i] 是 以下标 i 字符结尾的最长有效括号序列长度
     *
     * 如果 s[i] = ')' , s[i - 1] = '(' 那么形如 “...()” 的有效序列
     *      此时，dp[i] = dp[i - 2] + 2
     *
     * 如果 s[i] = ')' , s[i - 1] = ')' 那么形如 “...))” 的有效序列 如果 这个时候有 s[i - dp[i - 1] - 1] = '('
     *      此时，dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2
     *
     *
     * @param s
     * @return
     */
//    public int longestValidParentheses(String s) {
//        int maxAns = 0;
//        int len = s.length();
//        int[] dp = new int[len];
//        for(int i = 0; i < s.length(); i++){
//            if(s.charAt(i) == ')'){
//                // 两种状态递推公式
//                if(s.charAt(i - 1) == '('){
//                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
//                }
//                else if(i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
//                    dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0)) + 2;
//                }
//                maxAns = Math.max(dp[i], maxAns);
//            }
//        }
//        return maxAns;
//    }

    /**
     * 我们始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
     *
     * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
     * 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     *
     * 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     * 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     * 我们从前往后遍历字符串并更新答案即可。
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxAns = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }
            else { // s.charAt(i) == ')'
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }
                else {
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }

}
