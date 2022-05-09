package local.begin.leetCode;

import java.util.Arrays;

/**
 * 280. 摆动排序
 * 给你一个的整数数组 nums, 将该数组重新排序后使 nums[0] <= nums[1] >= nums[2] <= nums[3]...
 *
 * 输入数组总是有一个有效的答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入：nums = [3,5,2,1,6,4]
 * 输出：[3,5,1,6,2,4]
 * 解释：[1,6,2,5,3,4]也是有效的答案
 * 示例 2:
 *
 * 输入：nums = [6,6,5,6,3,8]
 * 输出：[6,6,5,6,3,8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 104
 * 输入的 nums 保证至少有一个答案。
 *
 *
 *
 * 进阶：你能在 O(n) 时间复杂度下解决这个问题吗？
 */
public class LeetCode0280Solution {

//    /**
//     * 排序
//     * @param nums
//     */
//    public void wiggleSort(int[] nums) {
//        Arrays.sort(nums);
//        for(int i = 1; i < nums.length - 1; i += 2){
//            swap(nums, i, i + 1);
//        }
//    }
//
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

//    /**
//     * 两个两个元素比较
//     *
//     * 一个flag控制是比大还是比肖，如果不满足flag则进行交换
//     *
//     * @param nums
//     */
//    public void wiggleSort(int[] nums) {
//        boolean less = true;
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (less) {
//                if (nums[i] > nums[i + 1]) {
//                    swap(nums, i, i + 1);
//                }
//            } else {
//                if (nums[i] < nums[i + 1]) {
//                    swap(nums, i, i + 1);
//                }
//            }
//            less = !less;
//        }
//    }

//    /**
//     * 上述代码合并逻辑
//     */
//    public void wiggleSort(int[] nums) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (((i % 2 == 0) && nums[i] > nums[i + 1])
//                    || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
//                swap(nums, i, i + 1);
//            }
//        }
//    }

    /**
     * 把上述代码中if的逻辑再合并 但是实际上 比上面代码空间开销大
     */
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                swap(nums, i, i + 1);
            }
        }
    }

}
