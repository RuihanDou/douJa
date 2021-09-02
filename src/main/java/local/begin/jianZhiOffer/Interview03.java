package local.begin.jianZhiOffer;


/**
 * 面试题03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 */


public class Interview03 {

    public int findRepeatNumber(int[] nums) {
        int length = nums.length;

        if (nums.length < 1) {
            throw new IllegalArgumentException("illegal input");
        }

        for(int i = 0; i < length; i++) {
            if(nums[i] < 0 || nums[i] > length - 1) {
                throw  new IllegalArgumentException("illegal input");
            }
        }

        // 重要条件： 数组中的数都在 0 ~ length - 1的范围内
        // 当 rank 和 值 不相等时，把该rank上的值放到 rank == 值 的rank上
        // 取那个重复的值：nums[i] == nums[nums[i]]
        for(int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if(nums[i] == nums[nums[i]]) {
                    return nums[i];
                }

                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }

        throw new IllegalArgumentException("none match");
    }

    private int countRange(int[] nums, int length, int start, int end) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if(nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0};
        Interview03 solution = new Interview03();
        int du = solution.findRepeatNumber(nums);
        System.out.println(du);
    }

}
