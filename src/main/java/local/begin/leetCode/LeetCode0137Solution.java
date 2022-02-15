package local.begin.leetCode;


/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class LeetCode0137Solution {

//    /**
//     * 设计一个三状态转移
//     *
//     *  00 -> 01 -> 10 -> 00
//     *
//     * 真值表
//     * ai,bi               xi              新 ai,bi
//     * 00                  0                00
//     * 00                  1                01
//     * 01                  0                01
//     * 01                  1                10
//     * 10                  0                10
//     * 10                  1                00
//     *
//     * 状态转移逻辑方程
//     *       a_(i+1) = ai'bixi + aibi'xi'
//     *       b_(i+1) = ai'bi'xi + ai'bixi' = ai'(bi xor xi)
//     *
//     * @param nums
//     * @return
//     */
//    public int singleNumber(int[] nums) {
//        int a = 0, b = 0;
//        for(int num : nums){
//            int aN = (~a & b & num) | (a & ~b & ~num);
//            int bN = ~a & (b ^ num);
//            a = aN;
//            b = bN;
//        }
//        return b;
//    }


    /**
     * 更新真值表，利用已经求出的 新b 求新a
     *
     * 真值表
     *
     *       ai    新bi     xi         新 ai
     *        0      0       0            0
     *        0      1       1            0
     *        0      1       0            0
     *        0      0       1            1
     *        1      0       0            1
     *        1      0       1            0
     *
     *    a_(i+1) = ai'b_(i+1)'xi + aib_(i+1)'xi' = b_(i+1)'(ai xor xi)
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for(int num : nums){
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }

}
