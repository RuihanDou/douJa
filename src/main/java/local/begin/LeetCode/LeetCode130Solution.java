package local.begin.LeetCode;


import java.util.List;

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
public class LeetCode130Solution {

    public boolean wordBreak(String s, List<String> wordDict) {

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

}
