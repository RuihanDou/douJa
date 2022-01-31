package local.begin.leetCode;


/**
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 *
 *
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 通过次数159,695提交次数220,064
 */
public class LeetCode0238Solution {

//    // 前缀之积 和 后缀之积分别存储
//    public int[] productExceptSelf(int[] nums) {
//        int len = nums.length;
//        int[] leftProduct = new int[len];
//        int[] rightProduct = new int[len];
//        leftProduct[0] = 1;
//        rightProduct[len-1] = 1;
//
//        for(int i = 1; i < len; i++){
//            leftProduct[i] = nums[i-1] * leftProduct[i-1];
//        }
//        for(int j = len - 2; j >= 0; j--){
//            rightProduct[j] = nums[j+1] * rightProduct[j+1];
//        }
//        int[] res = new int[len];
//        for(int k = 0; k < len; k++){
//            res[k] = leftProduct[k] * rightProduct[k];
//        }
//        return res;
//    }

    // 缩减空间
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length, temp = 1;
        int[] res = new int[len];
        res[0] = temp;
        for(int i = 1; i < len; i++){
            temp = temp * nums[i - 1];
            res[i] = temp;
        }
        temp = 1;
        for(int j = len - 2; j >= 0; j--){
            temp = temp * nums[j + 1];
            res[j] = res[j] * temp;
        }
        return res;
    }

}
