package local.begin.leetCode;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 *
 * 进阶：
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class LeetCode0075Solution {
    /**
     * 使用 partition 解决
     */
//    public void sortColors(int[] nums) {
//        // nums[0 ... zero] == 0; nums[zero + 1, i] == 1; nums[two, n - 1] == 2
//        int zero = -1, i = 0, two = nums.length;
//        while (i < two){
//
//            if(nums[i] == 0){
//                zero++;
//                swap(nums, zero, i);
//                i++;
//            }
//
//            else if(nums[i] == 2){
//                two--;
//                swap(nums, i, two);
//            }
//
//            else {
//                i++; // num[i] == 1
//            }
//        }
//    }
//
//    private void swap(int[] nums, int i, int j){
//        int t = nums[i];
//        nums[i] = nums[j];
//        nums[j] = t;
//    }

    /**
     * 使用 计数排序 解决
     */

//    public void sortColors(int[] nums) {
//
//        int[] cnt = new int[3];
//        for(int num : nums){
//            cnt[num]++;
//        }
//
//        for(int i = 0; i < cnt[0]; i++){
//            nums[i] = 0;
//        }
//
//        for(int i = cnt[0]; i < cnt[0] + cnt[1]; i++){
//            nums[i] = 1;
//        }
//
//        for(int i = cnt[0] + cnt[1]; i < cnt[0] + cnt[1] + cnt[2]; i++){
//            nums[i] = 2;
//        }
//    }

    public void sortColors(int[] nums) {
        // 处理元素取值范围是[0, R)的计数排序
        int R = 3;

        int[] cnt = new int[3];
        for(int num : nums){
            cnt[num]++;
        }

        // [index[i], index[i + 1]) 的值为 i
        int[] index = new int[R + 1];
        for(int i = 0; i < R; i++){
            index[i + 1] = index[i] + cnt[i];
        }

        for(int i = 0; i + 1 < index.length; i++){
            // [index[i], index[i + 1]) 的 值为 i
            for(int j = index[i]; j < index[i + 1]; j++){
                nums[j] = i;
            }
        }
    }

}
