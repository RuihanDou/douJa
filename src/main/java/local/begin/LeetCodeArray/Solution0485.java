package local.begin.LeetCodeArray;

/**
 * 485. 最大连续 1 的个数
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 *
 *
 *
 * 示例：
 *
 * 输入：[1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 *
 *
 * 提示：
 *
 * 输入的数组只包含 0 和 1 。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class Solution0485 {

    public int findMaxConsecutiveOnes(int[] nums) {
        // res 记录连续最长的1的数量， temp记录该连续1串的增长
        int res = 0, temp = 0;
        for(int e : nums){
            if(e == 1){
                temp++;
            } else {
                temp = 0;
            }
            res = Math.max(res, temp);
        }
        return res;
    }

}
