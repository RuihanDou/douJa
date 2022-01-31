package local.begin.leetCode;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class LeetCode0072Solution {

//    // 使用递归 -- 自顶向下 （注意会超时，只是为了理清思路）
//    public int minDistance(String word1, String word2) {
//        int len1 = word1.length();
//        int len2 = word2.length();
//        if(len1 == 0 || len2 == 0){
//            return Math.max(len1, len2);
//        }
//
//        if(word1.charAt(len1 - 1) == word2.charAt(len2 - 1)){
//            return minDistance(word1.substring(0, len1 - 1), word2.substring(0 ,len2 - 1));
//        }
//
//        return  1 + Math.min(
//          Math.min(minDistance(word1, word2.substring(0 ,len2 - 1)),
//                  minDistance(word1.substring(0, len1 - 1), word2)),
//                minDistance(word1.substring(0, len1 - 1), word2.substring(0 ,len2 - 1))
//        );
//    }

    // 动态规划 -- 自底向上

    /**
     *  word1[0~i] 变成 word2[0~j] 需要的编辑距离 记录到dp[i][j];
     *
     *  如果 word[i] == word[j], dp[i][j] = dp[i-1][j-1]
     *  否者， dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][ j - 1])
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int len1 = word1.length(), len2 = word2.length();

        // 讨论边界情况，如果有字符串为空
        if(len1 * len2 == 0){
            return len1 + len2;
        }

        // 定义动态规划数组
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }

        // 进行状态更新
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                int left = dp[i - 1][j];
                int down = dp[i][j - 1];
                int leftDown = dp[i - 1][j - 1];

                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(left, down), leftDown) + 1;
                }
            }
        }

        return dp[len1][len2];

    }



}
