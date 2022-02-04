package local.begin.leetCode;

/**
 *321. 拼接最大数
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 *
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 *
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 *
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * 通过次数28,337提交次数66,894
 */
public class LeetCode0321Solution {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int m = nums1.length, n = nums2.length;
        int[] maxSequence = new int[k];

        // start 和 end 控制从 num1 he nums2分别取多大的子序列
        // 如果 k > n 在 nums1 中至少取 k - n 个数的子序列
        // 如果 k > m 在 nums1 中至多取 k 个数的子序列
        // num1 中 取 i 个数的子序列的区间范围为 max(0, k - n) ~ min(k, m)
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for(int i = start; i <= end; i++){
            int[] subSequence1 = maxSequence(nums1, i);
            int[] subSequence2 = maxSequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subSequence1, subSequence2);
            if(compare(curMaxSubsequence, 0, maxSequence, 0) > 0){
                System.arraycopy(curMaxSubsequence, 0, maxSequence, 0, k);
            }
        }
        return maxSequence;
    }

    private int[] merge(int[] sub1, int[] sub2) {
        int l1 = sub1.length, l2 = sub2.length;
        if(l1 == 0){
            return sub2;
        }
        if(l2 == 0){
            return sub1;
        }
        int len = l1 + l2;
        int[] merged = new int[len];
        int i1 = 0, i2 = 0;
        for(int i = 0; i < len; i++){
            if(compare(sub1, i1, sub2, i2) > 0){
                merged[i] = sub1[i1++];
            } else {
                merged[i] = sub2[i2++];
            }
        }
        return merged;
    }

    private int compare(int[] sub1, int i1, int[] sub2, int i2) {
        int l1 = sub1.length, l2 = sub2.length;
        while (i1 < l1 && i2 < l2){
            int diff = sub1[i1] - sub2[i2];
            if(diff != 0){
                return diff;
            }
            i1++;
            i2++;
        }
        return (l1 - i1) - (l2 - i2);
    }

    private int[] maxSequence(int[] nums, int k) {
        int len = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = len - k;

        for (int i = 0; i < len; i++){
            int num = nums[i];
            // 栈非空, 栈顶元素小于当前遍历到的元素，且还有剩余元素可取
            while (top >= 0 && stack[top] < num && remain > 0){
                top--;
                remain--;
            }
            if(top < k-1){
                stack[++top] = num;
            } else {
                remain--;
            }
        }

        return stack;

    }


}
