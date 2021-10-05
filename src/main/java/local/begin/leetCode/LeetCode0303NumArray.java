package local.begin.leetCode;


import local.begin.dataStructureAlgorithm.algoInterface.Map;
import local.begin.dataStructureAlgorithm.dataStruct.SegmentTree;

import java.util.Arrays;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 *
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 *
 * 示例：
 *
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 */
public class LeetCode0303NumArray {


/**
 *  使用 线段树、presum方法解决
 */
//    private int[] sum; // sum[i]存储前i个元素的和， sum[0] = 0;
//    // sum[i] 存储的是 num[0 ... i-1]的和
//
//    public LeetCode0303NumArray (int[] nums) {
//        sum = new int[nums.length + 1];
//        sum[0] = 0;
//        for(int i = 1; i < sum.length; i++){
//            sum[i] = sum[i - 1] + nums[i - 1];
//        }
//    }
//
//    public int sumRange(int left, int right) {
//        return sum[right + 1] - sum[left];
//    }
//
//    private SegmentTree<Integer> segTree;
//
//    public LeetCode0303NumArray(int[] nums){
//        if(nums.length > 0){
//            Integer[] data = new Integer[nums.length];
//            for(int i = 0; i < nums.length; i++){
//                data[i] = nums[i];
//            }
//            segTree = new SegmentTree<>(data, Integer::sum);
//        }
//    }
//
//    public int sumRange(int left, int right) {
//        if(segTree == null){
//            throw new IllegalArgumentException("Segment Tree is null");
//        }
//        return segTree.query(left, right);
//    }


    /**
     * 使用SQRT分解的方式解决
     */

    private int[] data, blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    public LeetCode0303NumArray(int[] nums) {
        N = nums.length;
        if(N == 0){
            return;
        }

        B = (int)Math.sqrt(N);
        Bn = N / B + (N % B > 0 ? 1 : 0);

        data = Arrays.copyOf(nums, N);

        blocks = new int[Bn];
        for(int i = 0; i < N; i++){
            blocks[i / B] += nums[i];
        }
    }

    public int sumRange(int x, int y) {

        if(x < 0 || x >=N || y < 0 || y >= N || x > y){
            return 0;
        }

        int bstart = x / B, bend = y / B;

        int res = 0;

        if(bstart == bend || bstart + 1 == bend){
            for(int i = x; i <= y; i++){
                res += data[i];
            }
            return res;
        }

        for(int i = x; i < (bstart + 1) * B; i++){
            res += data[i];
        }

        for(int b = bstart + 1; b < bend; b++){
            res += blocks[b];
        }

        for(int i = bend * B; i <= y; i++){
            res += data[i];
        }

        return res;
    }


}
