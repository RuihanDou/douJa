package local.begin.leetCode;


/**
 * 357. 统计各位数字都不同的数字个数
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：1
 *
 *
 * 提示：
 *
 * 0 <= n <= 8
 */
public class LeetCode0357Solution {

    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 10;
        }
        // 当位数 n 有 2 <= n <= 10 时，哥哥位置数字不同的数 x 的个数为 {（ 9 * A( [d - 1] / 9 )） + 小于 n 位数的 所有countNumbersWithUniqueDigits }
        int res = 10, cur = 9;
        // 注意 上界 是 i < n - 1
        for(int i = 0; i < n - 1; i++){
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }

}
