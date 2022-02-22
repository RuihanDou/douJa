package local.begin.leetCode;


/**
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 */
public class LeetCode0009Solution {

    public boolean isPalindrome(int x) {
        // 负数不是回文数，如果个位是0，最高位无法是0，所以也不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        // 只有一位数，是回文数
        else if (x >= 0 && x <= 9) {
            return true;
        }
        // 计算翻转的数字
        int revert = 0;
        while (x != 0) {
            revert += revert * 10 + x % 10;
            x /= 10;
            // x == revert 是偶数位的情况， x / 10 == revert 是奇数位的情况，一旦出现就可以确定是回文数
            if (x == revert || x / 10 == revert) {
                return true;
            }
        }
        return false;
    }

}
