package local.begin.JianZhiOffer;

import local.begin.DataStructureAlgorithm.Alogo.InsertionSort;

import java.util.Arrays;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 */
public class Interview51 {

    private int res = 0;

    public int reversePairs(int[] nums) {
        res = 0;
        int[] temp = Arrays.copyOf(nums, nums.length);
        sort(nums, 0, nums.length - 1, temp);
        return res;
    }

    // 归并排序
    private void sort(int[] arr, int l, int r, int[] temp){

        if(r <= l){
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);
        sort(arr, mid + 1, r, temp);
        if(arr[mid] > arr[mid + 1]){
            merge(arr, l, mid, r, temp);
        }
    }

    // 合并有序区间 arr[l, mid] 和 arr[mid+1, r]
    private void merge(int[] arr, int l, int mid, int r, int[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                res += mid + 1 - i;
                j++;
            }
        }
    }

}
