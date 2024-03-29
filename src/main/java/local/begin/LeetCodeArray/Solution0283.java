package local.begin.LeetCodeArray;

/**
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
public class Solution0283 {

    public void moveZeros(int[] nums){
        int n = nums.length, left = 0, right = 0;
        // 确定循环不变体
        // left 和 right 都从0 开始，当 left == right 的时候，left 和 right 互换不会影响数组
        // 规定循环不变体 为 [0, left] 为非0数，不论如何
        // right 每次循环都向后移动一步
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

}
