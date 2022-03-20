package local.begin.leetCode;


/**
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 */
public class LeetCode0007Solution {

    public static int reverseV0(int x) {
        int rst = 0;
        while (x != 0) {
            int test = rst;
            int pop = x % 10;
            x /= 10;
            rst = rst * 10 + pop;
            // 检测是否溢出
            if(rst / 10 != test) {return 0;}
        }
        return rst;
    }

    // 规范化溢出判断
    public static int reverse(int x) {
        int rst = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            //
            //if (rev > INT_MAX/10 || (rev == INT_MAX / 10 && pop > 7)) return 0;
            //if (rev < INT_MIN/10 || (rev == INT_MIN / 10 && pop < -8)) return 0;
            //  因为 Integer.MAX_VALUE 的最后一位是 7 如果 ret == Integer.MAX_VALUE && pop <= 7 没有溢出
            if (rst > Integer.MAX_VALUE / 10 || (rst == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            //  因为 Integer.MAX_VALUE 的最后一位是 8 如果 ret == Integer.MAX_VALUE && pop >= -8 没有溢出
            if (rst < Integer.MIN_VALUE / 10 || (rst == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rst = rst * 10 + pop;
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(LeetCode0007Solution.reverse(-214748364));
    }

}
