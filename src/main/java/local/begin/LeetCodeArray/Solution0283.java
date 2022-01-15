package local.begin.LeetCodeArray;

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
