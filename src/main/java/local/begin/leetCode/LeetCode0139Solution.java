package local.begin.leetCode;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class LeetCode0139Solution {

    public boolean wordBreak1(String s, List<String> wordDict) {

        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        // i 为 子字符串的起始位置 j 为字符末尾位置之后的一位坐标
        // 初始化，填满对角线
        for(int i = 0; i < len; i++){
            dp[i][i] = wordDict.contains(s.substring(i, i+1));
        }

        for(int j = 1; j < len; j++){
            for(int i = j-1; i >= 0; i--){
                if(wordDict.contains(s.substring(i, j+1))){
                    dp[i][j] = true;
                } else {
                    boolean flag = false;
                    for(int k = i; k < j; k++){
                        if(dp[i][k] && dp[k+1][j]){
                            flag = true;break;
                        }
                    }
                    dp[i][j] = flag;
                }
            }
        }
        return dp[0][len-1];
    }

    /**
     * dp[i] 表示字符串s  前i个字符组成的字符串 s[0 .. i - 1]能否被空格拆分成若干个字典中出现的单词
     *
     * 转移方程为 dp[i] = dp[j] && check(s[j .. i-1])
     * 其中 check(s[j .. i - 1]) 表示子串 s[j .. i - 1] 是否出现在字典中
     *
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> wordDictSet = new HashSet(wordDict);

        boolean[] dp = new boolean[s.length() + 1];

        // 空字符串合法
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {

            for (int j = 0; j < i; j++) {

                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }



}
