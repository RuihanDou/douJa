package local.begin.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. 缺失的区间
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 *
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 */
public class LeetCode0163Solution {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> rst = new ArrayList<>();
        if(nums.length == 0){
            addResult(rst, lower, upper);
        }
        for(int i = 0; i < nums.length; i++){
            int l, r;
            if(i == 0){
                if(nums[i] - lower > 0){
                    l = lower;
                    r = nums[i] - 1;
                    addResult(rst, l, r);
                }
            } else {
                if(nums[i] - nums[i - 1] > 1){
                    l = nums[i - 1] + 1;
                    r = nums[i] - 1;
                    addResult(rst, l, r);
                }
            }
            if(i == nums.length - 1){
                if(upper - nums[i] > 0){
                    l = nums[i] + 1;
                    r = upper;
                    addResult(rst, l, r);
                }
            }
        }
        return rst;
    }

    private void addResult(List<String> rst, int l, int r) {
        if(l == r){
            rst.add(String.format("%d", l));
        } else {
            rst.add(String.format("%d->%d", l, r));
        }
    }

}
