package local.begin.leetCode;

public class LeetCode0031Solution {

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len <= 1){
            return;
        }
        boolean descend = true;
        for(int i = 0; i < len - 1; i++){
            if(nums[i] < nums[i + 1]){
                descend = false;
            }
        }


    }

}
