package local.begin.LeetCode;


/**
 * 4. 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class LeetCode0004Solution {

    public static double findMedianSortedArraysV0(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int l = l1 + l2;
        int s = l % 2;
        int r = l / 2;
        int[] all = new int[r + 1];
        int i = 0, j = 0, k = 0;
        while (k < all.length) {
            if (j >= l2 || i < l1 && nums1[i] <= nums2[j] ) {
                all[k++] = nums1[i++];
            } else {
                all[k++] = nums2[j++];
            }
        }
        if (s == 1) {
            return (double) all[r];
        }
        else {
            return (all[r] + all[r - 1]) / 2.0;
        }
    }

    public static void main(String[] args) {
        int [] n1 = {2};
        int [] n2 = {};
        double middle = LeetCode0004Solution.findMedianSortedArraysV0(n1, n2);

        System.out.println(middle);
    }
}
