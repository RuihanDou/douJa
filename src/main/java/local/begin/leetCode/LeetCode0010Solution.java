package local.begin.leetCode;


import java.util.Arrays;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class LeetCode0010Solution {

    /**
     * 状态转移方程
     *
     * 当 p[j] != * 时，
     *        s[i] == p[j] 或 p[j] = '.'   dp[i][j] = dp[i - 1][j - 1]
     *        其他情况                      dp[i][j] = false
     *
     * 当 p[j] == * 时。
     *         s[i] == p[j]                dp[i][j] = dp[i - 1][j] || dp[i][j - 2]
     *         其他情况                      dp[i][j] = dp[i][j - 2]
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;

        for(int i = 0; i <= sLen; i++){
            for(int j = 1; j <= pLen; j++){
                if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 2];
                    if(matches(s, p, i, j - 1)){
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
                else {
                    if(matches(s, p, i, j)){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    private boolean matches(String s, String p, int i, int j){
        if(i == 0){
            return false;
        }
        if(p.charAt(j - 1) == '.'){
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }



    public static void main(String[] args) {
        LeetCode0010Solution leet = new LeetCode0010Solution();
        boolean res = leet.isMatch("aabcd", "a*bcd");
        System.out.println(res);
    }

}
