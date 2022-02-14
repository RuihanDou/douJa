package local.begin.leetCode;

public class LeetCode0034Solution {

    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1, -1};
        }
        int l = searchBegin(nums, target);
        if(nums[l] != target){
            return new int[]{-1, -1};
        }
        int r = searchEnd(nums, target);
        return new int[]{l, r};
    }


    // 在[l, r] 中寻找target左界
    public int searchBegin(int[] nums, int target){
        int l = 0, r = nums.length - 1;
        while (l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < target){
                l = mid + 1;
            }
            else { // nums[mid] >= target
                r = mid;
            }
        }
        return l;
    }

    // 在[l, r] 中寻找target右界
    public int searchEnd(int[] nums, int target){
        int l = 0, r = nums.length - 1;
        while (l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] > target){
                r = mid - 1;
            }
            else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        LeetCode0034Solution solution = new LeetCode0034Solution();
        int[] nums = {5,7,7,8,8,10};
        System.out.println(solution.searchBegin(nums, 10));
    }

}
