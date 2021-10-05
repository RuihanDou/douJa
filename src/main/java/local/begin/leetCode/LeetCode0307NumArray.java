package local.begin.leetCode;


import local.begin.dataStructureAlgorithm.dataStruct.SegmentTree;

import java.util.Arrays;

/**
 * 307. 区域和检索 - 数组可修改
 * 给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
 *
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值更新为 val
 * int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] + nums[left + 1], ..., nums[right]）
 *
 *
 * 示例：
 *
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 最多调用 3 * 104 次 update 和 sumRange 方法
 */
public class LeetCode0307NumArray {

    /**
     * 使用线段树解决
     */

//    private SegmentTree<Integer> segTree;
//
//    public LeetCode0307NumArray(int[] nums) {
//        if(nums.length > 0){
//            Integer[] data = new Integer[nums.length];
//            for(int i = 0; i < nums.length; i++){
//                data[i] = nums[i];
//            }
//            segTree = new SegmentTree<>(data, Integer::sum);
//        }
//    }
//
//    public void update(int index, int val) {
//        if(segTree == null){
//            throw new IllegalArgumentException("Segment Tree is null");
//        }
//        segTree.set(index, val);
//    }
//
//    public int sumRange(int left, int right) {
//        if(segTree == null){
//            throw new IllegalArgumentException("Segment Tree is null");
//        }
//        return segTree.query(left, right);
//    }

    /**
     * 使用SQRT分解解决
     */
    private int[] data, blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    public LeetCode0307NumArray(int[] nums) {
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

    public void update(int i, int val) {
        if(i < 0 || i >= N){
            return;
        }
        int b = i / B;
        blocks[b] -= data[i];
        blocks[b] += val;

        data[i] = val;
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
