package local.begin.LeetCode;

public class LeetCode287Solution {


    // 快慢指针 n
    public int findDuplicate(int[] nums) {

        int tortoise = nums[0];
        int hare = nums[0];

        do{
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        int ptr1 = nums[0];
        int ptr2 = tortoise;

        while (ptr1 != ptr2){
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    // 二分搜索 nlog(n)
    public int findDuplicate1(int[] nums){

        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;

        while (l <= r){

            int mid = l + (r - l) / 2;
            int cnt = 0;

            // 统计小于等于当前元素的个数
            for(int i = 0; i < n; i++){
                cnt += nums[i] <= mid ? 1 : 0;
            }

            //
            if(cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }

        return ans;
    }


}
