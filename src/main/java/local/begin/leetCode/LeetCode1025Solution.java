package local.begin.leetCode;


/**
 * 1025. 除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字 n 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < n 且 n % x == 0 。
 * 用 n - x 替换黑板上的数字 n 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 true 。假设两个玩家都以最佳状态参与游戏。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *
 *
 * 提示：
 *
 * 1 <= n <= 1000
 */
public class LeetCode1025Solution {

    /**
     * n=1 的时候，区间 (0,1) 中没有整数是 n 的因数，所以此时 Alice 败。
     * n = 2 的时候，Alice 只能拿 1，n 变成 11，Bob 无法继续操作，故 Alice 胜。
     * n = 3 的时候，Alice 只能拿 1，n 变成 22，根据 n=2 的结论，我们知道此时 Bob 会获胜，Alice 败。
     * n = 4 的时候，Alice 能拿 1 或 2，如果 Alice 拿 11，根据 n = 3n=3 的结论，Bob 会失败，Alice 会获胜。
     * n = 5 的时候，Alice 只能拿 1，根据 n=4 的结论，Alice 会失败。

     *
     *
     * @param n
     * @return
     */
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }

}
