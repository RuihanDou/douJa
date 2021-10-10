package local.begin.leetCode;

/**
 * 1392. 最长快乐前缀
 * 「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
 *
 * 给你一个字符串 s，请你返回它的 最长快乐前缀。
 *
 * 如果不存在满足题意的前缀，则返回一个空字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "level"
 * 输出："l"
 * 解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
 * 示例 2：
 *
 * 输入：s = "ababab"
 * 输出："abab"
 * 解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
 * 示例 3：
 *
 * 输入：s = "leetcodeleet"
 * 输出："leet"
 * 示例 4：
 *
 * 输入：s = "a"
 * 输出：""
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 只含有小写英文字母
 */
public class LeetCode1392Solution {

    private long MOD = (long)(1e9 + 7);

    public String longestPrefix(String s) {

        // pow26[i] = 26 ^ i;
        long[] pow26 = new long[s.length()];
        pow26[0] = 1;
        for(int i = 1; i < s.length(); i++){
            pow26[i] = pow26[i - 1] * 26 % MOD;
        }

        // prehash[i] : hash(s[0...i)
        long[] prehash = new long[s.length()];
        prehash[0] = s.charAt(0) - 'a';
        for(int i = 1; i < s.length(); i++){
            prehash[i] = (prehash[i - 1] * 26 + (s.charAt(i) - 'a')) % MOD;
        }
        // posthash[i] : hash(s[i...s.length - 1])
        long[] posthash = new long[s.length()];
        posthash[s.length() - 1] = s.charAt(s.length() - 1) - 'a';
        for(int i = s.length() - 2; i >= 0; i--){
            posthash[i] = ((s.charAt(i) - 'a') * pow26[s.length() - i - 1] + posthash[i + 1]) % MOD;
        }
                // s[0 ... len-1] ~ s[s.length - len ... s.length - 1]
        for(int len = s.length() - 1; len >= 1; len--){
            if(prehash[len - 1] == posthash[s.length() - len] &&
                    equals(s, 0, len - 1, s.length() - len, s.length() - 1)){
                return s.substring(0, len);
            }
        }
        return "";
    }

    /**
     * 暴力解法
     * @param s
     * @return
     */
//    public String longestPrefix(String s) {
//
//        // s[0 ... len-1] ~ s[s.length - len ... s.length - 1]
//        for(int len = s.length() - 1; len >= 1; len--){
//            if(equals(s, 0, len - 1, s.length() - len, s.length() - 1)){
//                return s.substring(0, len);
//            }
//        }
//        return "";
//
//    }
//

    // s[l1, r1] == s[l2, r2]
    private boolean equals(String s, int l1, int r1, int l2, int r2) {
        for(int i = l1, j = l2; i <= r1 && j <= r2; i++, j++){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }

}
