package local.begin.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */

public class LeetCode131Solution {

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {

        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }

        for(int i = n - 1; i >= 0; i--){
            for(int j = i + 1; j < n; j++){
                f[i][j] = (s.charAt(j) == s.charAt(i)) && f[i+1][j-1];
            }
        }
        dfs(s, 0);
        return ret;
    }

    private void dfs(String s, int i) {
        if(i == n){
            ret.add(new ArrayList<>(ans));
            return;
        }

        for(int j = i; j < n; j++){
            if(f[i][j]){
                ans.add(s.substring(i, j+1));
                dfs(s, j+1);
                ans.remove(ans.size() - 1);
            }
        }
    }

}
