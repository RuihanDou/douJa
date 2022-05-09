package local.begin.leetCode;


import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 示例 2:
 *
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class LeetCode0611Solution {

    public int triangleNumber(int[] nums){

        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;

        // nums[i] 为第一条边
        for (int i = 0; i < n; i++) {
            // nums[k + 1] 为第三条边，最大 所以，满足三角形的条件是 nums[k + 1] < nums[i] + nums[j]
            int k = i;
            // nums[j] 为第二条边
            for (int j = i + 1; j < n; j++) {
                // 确定第三条边的位置
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                // 此时 k 满足 k < n 且 nums[k] < nums[i] + nums[j]
                // 且 k 为当前 i he j 情况下的最大右指针
                ans += Math.max(k - j, 0);
            }
        }
        return ans;

    }

}
