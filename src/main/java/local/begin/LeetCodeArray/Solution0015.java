package local.begin.LeetCodeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Solution0015 {

    public List<List<Integer>> threeSum(int[] nums) {

        // 获取 从小到大 排列的 数组 nums

        // 方便三元组不重复
        // 分开（正数） 和 （负数和零）根据题意第一层循环只需要遍历 （负数和零）的部分
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> rst = new ArrayList<>();
        if(len < 3){
            return rst;
        }

        // 注意耗时
        for(int k = 0; k < nums.length - 2; k++){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                // 为了防止 [0, 0, 0] 先左右移动而无法加入结果集中
                // 先算 sum 然后分条件讨论
                int sum = nums[k] + nums[i] + nums[j];
                // 所有的while循环都只为了跳过重复的元素
                // 这么写是为了容易理解
                // 为缩减耗时应该写出
//                if(sum < 0){
//                    while(i < j && nums[i] == nums[++i]);
//                } else if (sum > 0) {
//                    while(i < j && nums[j] == nums[--j]);
//                } else {
//                    rst.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
//                    while(i < j && nums[i] == nums[++i]);
//                    while(i < j && nums[j] == nums[--j]);
//                }
                if(sum < 0){
                    while(i < j && nums[i] == nums[i + 1]){
                        i++;
                    }
                } else if (sum > 0) {
                    while(i < j && nums[j] == nums[j - 1]){
                        j--;
                    }
                } else {
                    rst.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i < j && nums[i] == nums[i + 1]){
                        i++;
                    }
                    while(i < j && nums[j] == nums[j - 1]){
                        j--;
                    }
                }
            }
        }
        return rst;

    }

    public static void main(String[] args) {
        Solution0015 solution0015 = new Solution0015();
        int[] nums = new int[]{0,0,0};
        List<List<Integer>> rst = solution0015.threeSum(nums);
        System.out.println(rst);
    }

}
