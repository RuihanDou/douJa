package local.begin.leetCode;

public class LeetCode0153Solution {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high){
            int mid = low + (high - low) >> 1;
            if(nums[mid] < nums[high]){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

}
