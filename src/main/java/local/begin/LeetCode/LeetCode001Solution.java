package local.begin.LeetCode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

public class LeetCode001Solution {

    /**
     * 最多一次循环就可以有结果
     * 利用了map，循环一遍
     * 这种方法返回的是第二个数排在最前的一组
     * @param nums
     * @param target
     * @return  返回的是 nums 里的序号 而非数字本身
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> targetSupple = new TreeMap<>();
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (targetSupple.containsKey(num)) {
                if (targetSupple.get(num) != i) {
                    return new int[] {targetSupple.get(num), i};
                }
            }
            targetSupple.put(target - num, i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 使用两次循环，第一次用map记录数组，第二次找到里面的余数supple并做返回操作
     * 这种方法返回的是第一个数排在最前的一组
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumV1(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new TreeMap<>();
        for(int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++) {
            int supple = target - nums[i];
            if (numsMap.containsKey(supple) && numsMap.get(supple) != i) {
                return new int[] {i, numsMap.get(supple)};
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSumV0(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j<  nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5,6,7,8,9};
        int taget = 14;

        int result[] = LeetCode001Solution.twoSum(nums, taget);
        System.out.println(Arrays.toString(result));
    }

}
