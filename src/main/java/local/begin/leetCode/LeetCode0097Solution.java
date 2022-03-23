package local.begin.leetCode;


/**
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 *
 * 提示：
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 *
 *
 * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
 */
public class LeetCode0097Solution {

    /**
     * 不能使用双指针法
     *
     * 使用动态规划
     *
     * dp(i, j)表示 s1 的 前 i 个元素 和 s2 的 前 j 个元素是否能组成 s3 的 前i + j 个元素
     *
     * dp(i, j) = ( dp(i - 1, j) and s1(i - 1) == s3(p) ) or ( dp(i, j - 1) and s2(j - 1) == s3(p) )
     *
     * p = i + j - 1
     *
     * dp(0, 0) = true
     *
     * 因为 dp(i, j) 只与 dp(i - 1, j) 和 dp(i, j - 1) 相关
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if(n + m != t){
            return false;
        }

        boolean[] dp = new boolean[m + 1];

        dp[0] = true;

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= m; j++){
                int p = i + j - 1;
                if(i > 0){
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if(j > 0){
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return dp[m];
    }

}
