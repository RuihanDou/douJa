package local.begin.leetCode;


/**
 * 485. 最大连续 1 的个数
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * 示例 2:
 *
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1.
 */
public class LeetCode0485Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutive = 0, count = 0;
        for(int e : nums){
            if(e == 1){
                count++;
            } else {
                count = 0;
            }
            maxConsecutive = Math.max(maxConsecutive, count);
        }
        return maxConsecutive;
    }

}
