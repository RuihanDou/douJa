package local.begin.leetCode;


/**
 * 414. 第三大的数
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 *
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 *
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 */
public class LeetCode0414Solution {

    public int thirdMax(int[] nums) {
        // 因为有不满三个数的情况，所以得允许有数有null存在
        Integer t1 = null, t2 = null, t3 = null;
        for(int e : nums){
            if(t1 == null || e > t1){
                t3 = t2;
                t2 = t1;
                t1 = e;
            } else if(t1 > e && (t2 == null || e > t2)){
                t3 = t2;
                t2 = e;
            } else if(t2 != null && t2 > e && (t3 == null || e > t3)){
                t3 = e;
            }
        }
        return t3 == null ? t1 : t3;
    }

}
