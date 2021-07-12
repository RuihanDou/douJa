package local.begin.LeetCode;


/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 示例 3：
 *
 * 输入：nums = [1,1]
 * 输出：1
 * 示例 4：
 *
 * 输入：nums = [1,1,2]
 * 输出：1
 *
 *
 * 提示：
 *
 * 2 <= n <= 3 * 104
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以在不修改数组 nums 的情况下解决这个问题吗？
 * 你可以只用常量级 O(1) 的额外空间解决这个问题吗？
 * 你可以设计一个时间复杂度小于 O(n2) 的解决方案吗？
 */
public class LeetCode0287Solution {


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
