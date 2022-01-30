package local.begin.leetCode;


/**
 * 172. 阶乘后的零
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= n <= 104
 *
 *
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
public class LeetCode0172Solution {

//    public int trailingZeroes(int n) {
//
//        int zeroCount = 0;
//        for(int i = 5; i <= n; i += 5){
//            int currentFactor = i;
//
//            // 25 中可以提供 2 个 5 zeroCount 在 currentFactor = 25 时， zeroCount增加了2
//            while (currentFactor % 5 == 0){
//                zeroCount++;
//                currentFactor /= 5;
//            }
//        }
//        return zeroCount;
//
//    }

    public int trailingZeroes(int n) {
        int zeroCount = 0;
        while (n > 0){
            // n 每除一次 5，就可以增加 n 个 5
            // 例如
            // 26 / 5 = 5， 26 可以提供 5， 10， 15， 20， 25 这 5个 5
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }

    public static void main(String[] args) {
        int num = 25;
        LeetCode0172Solution solution = new LeetCode0172Solution();
        System.out.println(solution.trailingZeroes(25));
    }


}
