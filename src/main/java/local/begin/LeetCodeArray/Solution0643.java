package local.begin.LeetCodeArray;

public class Solution0643 {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;

        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        int maxSum = sum;
        for(int i = k; i < n; i++){
            sum = sum + nums[i] - nums[i - k];
            maxSum = Math.max(sum, maxSum);
        }
        return 1.0 * maxSum / k;
    }

}
