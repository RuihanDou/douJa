package local.begin.leetCode;


/**
 * 651. 4键键盘
 * 假设你有一个特殊的键盘包含下面的按键：
 *
 * A：在屏幕上打印一个 'A'。
 * Ctrl-A：选中整个屏幕。
 * Ctrl-C：复制选中区域到缓冲区。
 * Ctrl-V：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。
 * 现在，你可以 最多 按键 n 次（使用上述四种按键），返回屏幕上最多可以显示 'A' 的个数 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 3
 * 输出: 3
 * 解释:
 * 我们最多可以在屏幕上显示三个'A'通过如下顺序按键：
 * A, A, A
 * 示例 2:
 *
 * 输入: n = 7
 * 输出: 9
 * 解释:
 * 我们最多可以在屏幕上显示九个'A'通过如下顺序按键：
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 *
 *
 * 提示:
 *
 * 1 <= n <= 50
 */
public class LeetCode0651Solution {

    /**
     * 动态规划
     *
     * 按键 N 次 写出 M 个 A
     *
     * 加法 按 1 次 M + 1
     * 乘法 按 k+1 次 M * k (k >= 2)
     *
     *
     * @param n
     * @return
     */
    public int maxA(int n) {
        int[] best = new int[n + 1];
        for(int k = 1; k <= n; k++){
            best[k] = best[k - 1] + 1;
            for(int x = 0; x < k - 1; x++){
                best[k] = Math.max(best[k], best[x] * (k - x - 1));
            }
        }
        return best[n];
    }

//    public int maxA(int N) {
//        int[] best = new int[]{0, 1, 2, 3, 4, 5, 6, 9, 12,
//                16, 20, 27, 36, 48, 64, 81};
//        int q = N > 15 ? (N - 11) / 5 : 0;
//        return best[N - 5*q] << 2 * q;
//    }


}
