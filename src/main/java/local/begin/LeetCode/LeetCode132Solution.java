package local.begin.LeetCode;

import java.util.Arrays;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 *
 * 返回符合要求的 最少分割次数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 *
 * 输入：s = "ab"
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class LeetCode132Solution {

    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }

}
