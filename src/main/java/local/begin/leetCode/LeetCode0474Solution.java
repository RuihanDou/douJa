package local.begin.leetCode;


/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
public class LeetCode0474Solution {

//    // 使用动态规划 三维动态规划
//    /**
//     * dp[i][j][k]表示前 i 个字符串中，使用 j 个 0 和 k 个 1 的情况下最多可以得到的字符串数量
//     *
//     * strs 长度为 l dp 的 大小为 l * m * n
//     *
//     * 当没有任何字符串可以使用时，可以得到的字符串数量只能是 0，因此动态规划的边界条件是：
//     * 当 i = 0 时，对任意 0 <= j <= m 和 0 <= k <= n，都有 dp[i][j][k]=0
//     * 当 1 <= i <= l 时， 对于 strs 中的第 i 个字符串(计数从 1 开始) ，先获取其中 0 和 1 的数量 zeros 和 ones
//     *
//     * 在 dp[i][j][k] 时， 0 的容量为 j ; 1 的容量为 k
//     * 如果 j < zeros 或者 k < ones 则不能选该字符串，此时 dp[i][j][k] = dp[i-1][j][k]
//     * 如果 j >= zeros 且 k >= ones 则有选择
//     *             1  不选择该字符串    dp[i][j][k] = dp[i-1][j][k]
//     *             2   选择该字符串     dp[i][j][k] = dp[i-1][j -zeros][k -ones] + 1
//     *    选取上面两个的最大值
//     *
//     * @param strs
//     * @param m
//     * @param n
//     * @return
//     */
//    public int findMaxForm(String[] strs, int m, int n) {
//        int len = strs.length;
//        int[][][] dp = new int[len + 1][m + 1][n + 1];
//        for(int i = 1; i <= len; i++){
//            int[] zeroOnes = getZerosOnes(strs[i - 1]);
//            int zeros = zeroOnes[0], ones = zeroOnes[1];
//
//            for(int j = 0; j <= m; j++){
//                for(int k = 0; k <= n; k++){
//                    dp[i][j][k] = dp[i - 1][j][k];
//                    if( j >= zeros && k >= ones){
//                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j -zeros][k -ones] + 1);
//                    }
//                }
//            }
//        }
//        return dp[len][m][n];
//    }
//
//    private int[] getZerosOnes(String str){
//        int[] zerosOnes = new int[2];
//        int length = str.length();
//        for(int i = 0; i < length; i++){
//            zerosOnes[str.charAt(i) - '0']++;
//        }
//        return zerosOnes;
//    }


    // 因为 上述方法 dp[i][*][*] 仅 和 dp[i - 1][*][*] 相关
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            int[] zerosOnes = getZerosOnes(strs[i]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            // 注意，因为 j - zeros 和 k - ones 小于 j k 所以更新应该从后往前
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeros][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

}
