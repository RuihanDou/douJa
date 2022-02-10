package local.begin.leetCode;


/**
 * 650. 只有两个键的键盘
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 *
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。
 * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：3
 * 输出：3
 * 解释：
 * 最初, 只有一个字符 'A'。
 * 第 1 步, 使用 Copy All 操作。
 * 第 2 步, 使用 Paste 操作来获得 'AA'。
 * 第 3 步, 使用 Paste 操作来获得 'AAA'。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= n <= 1000
 */
public class LeetCode0650Solution {
//
//    /**
//     * 动态规划
//     *
//     * 对所有的 j (j 可以整出 i)  f[i] = min(f[j] + (i / j))
//     *                          f[i] = min(f[i / j] + j)
//     *
//     * @param n
//     * @return
//     */
//    public int minSteps(int n) {
//        int[] f = new int[n + 1];
//        for(int i = 2; i <= n; i++){
//            f[i] = Integer.MAX_VALUE;
//            for(int j = 1; j * j <= i; j++){
//                if(i % j == 0){
//                    f[i] = Math.min(f[i], f[j] + i / j);
//                    f[i] = Math.min(f[i], f[i / j] + j);
//                }
//            }
//        }
//        return f[n];
//    }

    public int minSteps(int n) {
        int ans = 0;
        for (int i = 2; i * i <= n; ++i) {
            // 寻找质因数
            while (n % i == 0) {
                n /= i;
                ans += i;
            }
        }
        if (n > 1) {
            ans += n;
        }
        return ans;
    }

}
