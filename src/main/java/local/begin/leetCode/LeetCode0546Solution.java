package local.begin.leetCode;


/**
 * 546. 移除盒子
 * 给出一些不同颜色的盒子 boxes ，盒子的颜色由不同的正数表示。
 *
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 个积分。
 *
 * 返回 你能获得的最大积分和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 * 示例 2：
 *
 * 输入：boxes = [1,1,1]
 * 输出：9
 * 示例 3：
 *
 * 输入：boxes = [1]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 */
public class LeetCode0546Solution {

    private int[][][] dp;

    /**
     * 用 f(l,r,k) 表示移除区间 [l,r] 的元素 a_l, a_(l + 1), a_(l + 2), ... , a_r 加上该区间右边等于 a_r 的 k 个元素组成序列的最大积分
     * 例如序列 {6,3,6,5,6,7,6,6,8,6}，l=0，r=4，那么 f(l,r,3) 对应的元素就是 {[6_,3_,6_,5_,6_],7,6_,6_,8,6_} 中标记下划线的部分。
     *
     * a. 先删除后面的 4 个 6，再删除前面这个区间 获得分数为 f(0, 3, 0) + 4^2
     *   [6_0, 3_1, 6_2, 5_3, 6_], 7 , 6_, 6_, 8, 6_
     *
     * b. 先删除一个单独的 index = 3 的 5， 得到分数 f(3, 3, 0), 问题变成删除index区间 [0, 2], 分数是 f(0,2,4) 这样获得的总分数是 f(0,2,4) + f(3,3,0)
     *   [[6, 3, 6], [5], 6_], 7, 6_, 6_, 8, 6_
     *
     * c. 删除 index = 1, 2, 3 得到分数 f(1,3,0), 之后再删除[0,0]区间 和 后面和 index = 1位置上相等的 4 个数得到 f(0,0,4) 这样获得的总分数是 f(0,0,4) + f(1,3,0)
     *   [6], [3,5,6], 6, 7, ,6, 6, 8, 6
     *
     *
     *   f(l,r,k) = max{f(l,r - 1,0) + (k + 1)^2,  max{i = l -> r - 1}{[f(l, i, k + 1) + f(i + 1, r - 1, 0)] * e(a_i == a_r)}}
     *
     *   其中 e(a_i == a_r) 当 a_i == a_r 时 为 1，其余为 0
     *
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        int length = boxes.length;
        dp = new int[length][length][length];
        return calculatePoints(boxes, 0, length - 1, 0);
    }

    public int calculatePoints(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] == 0) {
            dp[l][r][k] = calculatePoints(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, l, i, k + 1) + calculatePoints(boxes, i + 1, r - 1, 0));
                }
            }
        }
        return dp[l][r][k];
    }

}
