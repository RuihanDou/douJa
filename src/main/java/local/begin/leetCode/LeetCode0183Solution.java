package local.begin.leetCode;

import java.util.Arrays;

/**
 * 阿里第一次面试的题
 *
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class LeetCode0183Solution {

    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;

        // 循环不变量，left 永远为不为0的最后一个元素
        while (right < n){
            if(nums[right] != 0){
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        LeetCode0183Solution solution = new LeetCode0183Solution();
        solution.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

}
