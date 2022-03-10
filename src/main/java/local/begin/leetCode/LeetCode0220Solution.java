package local.begin.leetCode;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 */
public class LeetCode0220Solution {

//    // 滑动窗口 + 有序集合
//    // nums[i] 的影响范围为[x - t, x + t]
//
//    /**
//     * 维护一个大小为 k 的滑动窗口，每次遍历到元素 x 时，
//     * 滑动窗口中包含元素 x 前面的最多 k 个元素，
//     * 我们检查窗口中是否存在元素落在区间[x−t,x+t] 中即可
//     *
//     * @param nums
//     * @param k
//     * @param t
//     * @return
//     */
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        int n = nums.length;
//        TreeSet<Long> set = new TreeSet<>();
//        for (int i = 0; i < n; i++) {
//            // ceiling 返回在这个集合中大于或者等于给定元素的最小元素, ceiling 为 大于或等于 num[i] - t 的最小元素
//            Long ceiling = set.ceiling((long) nums[i] - (long) t);
//            // 所取得的ceiling 是nums[i - k , i] 中 大于 num[i] - t 的 最小元素，只要celling <= nums[i] + t那么就满足
//            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
//                return true;
//            }
//            set.add((long) nums[i]);
//            if (i >= k) {
//                set.remove((long) nums[i - k]);
//            }
//        }
//        return false;
//    }


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        // 桶大小设置为t + 1
        long w = (long) t + 1;
        for(int i = 0; i < n; i++){
            long id = getId(nums[i], w);

            if(map.containsKey(id)){
                return true;
            }

            if(map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w){
                return true;
            }

            if(map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w){
                return true;
            }

            map.put(id, (long) nums[i]);

            if(i >= k){
                map.remove(getId(nums[i - k], w));
            }

        }
        return false;

    }

    private long getId(long x, long w){
        if(x >= 0){
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        LeetCode0220Solution solution = new LeetCode0220Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, 3, 0));
    }

}
