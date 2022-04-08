package local.begin.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class LeetCode0046Solution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        for(int num : nums){
            output.add(num);
        }

        int n = nums.length;
        backTrace(n, output, res, 0);

        return res;
    }

    private void backTrace(int n, List<Integer> output, List<List<Integer>> res, int first) {

        // 结束情况，output填满了
        if(first == n){
            res.add(new ArrayList<>(output));
        }

        for(int i = first; i < n; i++){
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续填下一个数
            backTrace(n, output, res, first + 1);
            // 撤销上一个操作
            Collections.swap(output, first, i);
        }
    }

}
