package local.begin.leetCode;


/**
 * 69. x 的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 *
 * 提示：
 *
 * 0 <= x <= 231 - 1
 */
public class LeetCode0069Solution {

    /**
     * 使用牛顿迭代法求
     *
     * f(x) = x ^ 2 - C
     * 在 xi 点的斜率为 f`(xi) = 2 * xi
     *
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x == 0){
            return 0;
        }
        // 初始点 (x0, y) = (C, C) // C = X = xi ^ 2
        double C = x, x0 = x;
        while (true){
            double xi = 0.5 * (x0 + C / x0);
            if(Math.abs(x0 - xi) < 1e-7){
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }

//    public int mySqrt(int x) {
//        if (x == 0) {
//            return 0;
//        }
//        int ans = (int) Math.exp(0.5 * Math.log(x));
//        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
//    }

}
