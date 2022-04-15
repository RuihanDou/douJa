package local.begin.leetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 164. 最大间距
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 *
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: nums = [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */
public class LeetCode0164Solution {

//    public int maximumGap(int[] nums) {
//        if(nums.length < 2){
//            return 0;
//        }
//        sort(nums, 1000);
//        int maxv = Integer.MIN_VALUE;
//        for(int i = 1; i < nums.length; i++){
//            maxv = Math.max(maxv, nums[i] - nums[i - 1]);
//        }
//        return maxv;
//    }
//
//    private static void sort(int[] arr, int c){
//
//        if(c <= 0)
//            throw new IllegalArgumentException("c must be > 0");
//
//        int maxv = Integer.MIN_VALUE, minv = Integer.MAX_VALUE;
//        for(int e: arr){
//            maxv = Math.max(maxv, e);
//            minv = Math.min(minv, e);
//        }
//
//        int range = maxv - minv + 1; // arr 中的数据范围
//        int B = range / c + (range % c > 0 ? 1 : 0); // 根据数据范围决定桶的个数
//
//        LinkedList<Integer>[] buckets = new LinkedList[B];
//        for(int i = 0; i < B; i ++){
//            buckets[i] = new LinkedList<>();
//        }
//
//        for(int e: arr){
//            buckets[(e - minv) / range].add(e);
//        }
//
//        for(int i = 0; i < B; i ++){
//            Collections.sort(buckets[i]);
//        }
//
//        int index = 0;
//        for(int i = 0; i < B; i ++){
//            for(int e: buckets[i]){
//                arr[index ++] = e;
//            }
//        }
//    }
    // 基数排序
    public int maximumGap0(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        long exp = 1;
        int[] buf = new int[n];
        int maxVal = Arrays.stream(nums).max().getAsInt();

        while (maxVal >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int) exp) % 10;
                cnt[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int) exp) % 10;
                buf[cnt[digit] - 1] = nums[i];
                cnt[digit]--;
            }
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }

        int ret = 0;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }



    // 基于桶的算法
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }


}
