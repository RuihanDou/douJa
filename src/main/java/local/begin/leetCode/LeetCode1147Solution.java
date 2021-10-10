package local.begin.leetCode;

/**
 * 1147. 段式回文
 * 段式回文 其实与 一般回文 类似，只不过是最小的单位是 一段字符 而不是 单个字母。
 *
 * 举个例子，对于一般回文 "abcba" 是回文，而 "volvo" 不是，但如果我们把 "volvo" 分为 "vo"、"l"、"vo" 三段，则可以认为 “(vo)(l)(vo)” 是段式回文（分为 3 段）。
 *
 *
 *
 * 给你一个字符串 text，在确保它满足段式回文的前提下，请你返回 段 的 最大数量 k。
 *
 * 如果段的最大数量为 k，那么存在满足以下条件的 a_1, a_2, ..., a_k：
 *
 * 每个 a_i 都是一个非空字符串；
 * 将这些字符串首位相连的结果 a_1 + a_2 + ... + a_k 和原始字符串 text 相同；
 * 对于所有1 <= i <= k，都有 a_i = a_{k+1 - i}。
 *
 *
 * 示例 1：
 *
 * 输入：text = "ghiabcdefhelloadamhelloabcdefghi"
 * 输出：7
 * 解释：我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
 * 示例 2：
 *
 * 输入：text = "merchant"
 * 输出：1
 * 解释：我们可以把字符串拆分成 "(merchant)"。
 * 示例 3：
 *
 * 输入：text = "antaprezatepzapreanta"
 * 输出：11
 * 解释：我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
 * 示例 4：
 *
 * 输入：text = "aaa"
 * 输出：3
 * 解释：我们可以把字符串拆分成 "(a)(a)(a)"。
 *
 *
 * 提示：
 *
 * text 仅由小写英文字符组成。
 * 1 <= text.length <= 1000
 */
public class LeetCode1147Solution {

    private long MOD = (long)(1e9 + 7);
    private long[] pow26;

    /**
     * 贪心 + 字符串哈希 解决
     *
     * (a + b) % M == (a % M + b % M) % M
     * (a * b) % M == (a % M * b % M) % M
     *
     * @param text
     * @return
     */
    public int longestDecomposition(String text) {

        // pow26[i] = 26 ^ i;
        pow26 = new long[text.length()];
        pow26[0] = 1;
        for(int i = 1; i < text.length(); i++){
            pow26[i] = pow26[i-1] * 26 % MOD;
        }

        return solve(text, 0, text.length() - 1);
    }


    // s[left, right]
    private int solve(String s, int left, int right){
        if(left > right){
            return 0;
        }

        long prehash = 0, posthash = 0;
        for(int i = left, j = right; i < j; i++, j--){
            prehash = (prehash * 26 + (s.charAt(i) - 'a')) % MOD;
            posthash = ((s.charAt(j) - 'a') * (pow26[right - j]) + posthash) % MOD;
            // s[left, i] == s[j, right]
            if(prehash == posthash && equals(s, left, i, j, right)){
                return 2 + solve(s, i + 1, j - 1);
            }
        }

        return 1;
    }

    // s[l1, r1] == s[l2, r2]
    private boolean equals(String s, int l1, int r1, int l2, int r2) {
        for(int i = l1, j = l2; i <= r1 && j <= r2; i++, j++){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }

    /**
     * 暴力解决O(n^2)
     * @param text
     * @return
     */
//    public int longestDecomposition(String text) {
//        return solve(text, 0, text.length() - 1);
//    }
//
//    // s[left, right]
//    private int solve(String s, int left, int right){
//        if(left > right){
//            return 0;
//        }
//
//        for(int i = left, j = right; i < j; i++, j--){
//            // s[left, i] == s[j, right]
//            if(equals(s, left, i, j, right)){
//                return 2 + solve(s, i + 1, j - 1);
//            }
//        }
//
//        return 1;
//    }
//
//    // s[l1, r1] == s[l2, r2]
//    private boolean equals(String s, int l1, int r1, int l2, int r2) {
//        for(int i = l1, j = l2; i <= r1 && j <= r2; i++, j++){
//            if(s.charAt(i) != s.charAt(j)){
//                return false;
//            }
//        }
//        return true;
//    }

}
