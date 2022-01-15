package local.begin.LeetCodeArray;

import java.util.Random;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 进阶：
 *
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class Solution0169 {

//

//    // O(log(n)) 分治
//
//    private int countInRange(int[] nums, int num, int lo, int hi){
//        int count = 0;
//        for(int i = lo; i <= hi; i++){
//            if(nums[i] == num){
//                count++;
//            }
//        }
//        return count;
//    }
//
//    private int majorityElementRec(int[] nums, int lo, int hi){
//        // nums 长度为1 num[0]就是答案
//        if (lo == hi) {
//            return nums[lo];
//        }
//
//        // nums 分成两部分
//        int mid = (hi - lo) / 2 + lo;
//        int left = majorityElementRec(nums, lo, mid);
//        int right = majorityElementRec(nums, mid + 1, hi);
//
//        // 两部分众数 是一样的，返回一个
//        if (left == right) {
//            return left;
//        }
//
//        // 两部分众数不一样，那比较一下
//        int leftCount = countInRange(nums, left, lo, hi);
//        int rightCount = countInRange(nums, right, lo, hi);
//
//        return leftCount > rightCount ? left : right;
//    }
//
//    public int majorityElement(int[] nums) {
//        return majorityElementRec(nums, 0, nums.length - 1);
//    }

    // O(n) 复杂度，Boyer-Moore 投票算法
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for(int num : nums){
            if(count == 0){
                candidate = count;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return (int)candidate;
    }

}
