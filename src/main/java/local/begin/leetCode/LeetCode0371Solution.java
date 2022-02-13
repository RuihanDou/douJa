package local.begin.leetCode;


/**
 * 371. 两整数之和
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ​​​​​​​，计算并返回两整数之和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 2
 * 输出：3
 * 示例 2：
 *
 * 输入：a = 2, b = 3
 * 输出：5
 *
 *
 * 提示：
 *
 * -1000 <= a, b <= 1000
 */
public class LeetCode0371Solution {

    /**
     * 根据题意考虑二进制计算
     *  0 + 0 = 0
     *  0 + 1 = 1
     *  1 + 0 = 1
     *  1 + 1 = 0
     *
     *  进位只有 1 + 1 的时候有进位， 进位向左移动一位
     *
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        while (b != 0){
            int carry = (a & b) << 1;
            a = a ^ b;
            // 只要有进位，进位依然要加进结果里
            b = carry;
        }
        return a;
    }

}
