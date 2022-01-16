package local.begin.LeetCodeArray;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class Solution0031 {


    // 4，5，2，6，3，1 -> 4，5，3，1，2，6
    public void nextPermutation(int[] nums) {
        // 下面 的while 循环和其初始化设置 i = nums.length - 2 是为了寻找最右边的一个逆序对
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // nums[i] = 2
        // i >= 1 代表 nums 中存在这么一个逆序对
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // nums[j] = 3
            swap(nums, i, j);
        }
        // 4，5，3，6，2，1 元素3 i位置以后的元素反转
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
