package local.begin.leetCode;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */
public class LeetCode0448Solution {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        // key 是 nums 里的数值， value 是是否出现的标记
        Set<Integer> set = new HashSet<>();
        int max = nums.length;
        for(int e : nums){
            set.add(e);
        }
        List<Integer> rst = new ArrayList<>();
        for(int i = 1; i <= max; i++){
            if(!set.contains(i)){
                rst.add(i);
            }
        }
        return rst;
    }

}
