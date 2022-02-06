package local.begin.leetCode;


/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
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
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class LeetCode0044Solution {


    /**
     * 动态规划  字符串 s 字符串 p
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     *
     * dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
     *
     *   如果 s[i] 和 p[j] 都是字母， dp[i][j] = (s[i] == p[j]) && dp[i - 1][j - 1]
     *   如果 p[j] 是 ? ，dp[i][j] = dp[i - 1][j - 1]
     *   如果 p[j] 是 * ，那么分两种情况，如果不使用 * 则 dp[i][j] = dp[i][j - 1]
     *                                如果使用 * 则 dp[i][j] = dp[i - 1][j]
     *                      所以，dp[i][j] = dp[i][j - 1] || dp[i - 1][j]
     *
     *   合并讨论
     *
     *      s[i] == p[j] || p[j] == '?' 则 dp[i][j] = dp[i - 1][j - 1]
     *      p[j] 是 '*' 则 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]
     *      其他情况 false
     *
     *      dp[0][0] = true
     *      当 i != 0 时， dp[i][0] = false
     *      当 j != 0 时， dp[0][j] 需要分类讨论 当前 p 的 前 j 都是 * 时，dp[0][j] = true 其他情况都是 false
     * @param s
     * @param p
     * @return
     */
//    public boolean isMatch(String s, String p) {
//        int sLen = s.length(), pLen = p.length();
//        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
//        dp[0][0] = true;
//        // 第 一 列 除了 dp[0][0] 是 true 其他都是false
//        // 初始化 第一行
//        for(int j = 1; j <= pLen; j++){
//            if(p.charAt(j - 1) == '*'){
//                dp[0][j] = true;
//            } else {
//                break;
//            }
//        }
//
//        for(int i = 1; i <= sLen; i++){
//            for(int j = 1; j <= pLen; j++){
//                if(p.charAt(j - 1) == '*'){
//                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//                }
//                else if(p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)){
//                    dp[i][j] = dp[i - 1][j - 1];
//                }
//            }
//        }
//        return dp[sLen][pLen];
//    }

    /**
     * 贪心
     */
    public boolean isMatch(String s, String p) {
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }

        if (pRight == 0) {
            return sRight == 0;
        }

        int sIndex = 0, pIndex = 0;
        int sRecord = -1, pRecord = -1;

        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }

        return allStars(p, pIndex, pRight);
    }

    public boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

}
