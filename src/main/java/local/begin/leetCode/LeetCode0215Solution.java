package local.begin.leetCode;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/***
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class LeetCode0215Solution {
    public int findKthLargest(int[] nums, int k) {
        Random rnd = new Random();
//        return selectK(nums, 0, nums.length - 1, nums.length - k, rnd);
        return selectK(nums, nums.length - k, rnd);
    }

    private int selectK(int[] arr, int k, Random rnd){
        int l = 0, r = arr.length - 1;
        while (l <= r){
            int p = partition(arr, l, r, rnd);

            if(p == k){
                return arr[p];
            }

            if(k < p){
                r = p - 1;
            } else {
                l = p + 1;
            }
        }
        throw new RuntimeException("No Solution");
    }

    private int selectKR(int[] arr, int l, int r, int k, Random rnd){

        int p = partition(arr, l, r, rnd);

        if(k == p) {
            return arr[p];
        }

        if(k < p){
            return selectKR(arr, l, p - 1, k, rnd);
        }

        return selectKR(arr, p + 1, r, k, rnd);
    }

    private int partition(int[] arr, int l, int r, Random rnd){

        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1, j = r;
        while(true){

            while(i <= j && arr[i] < arr[l]){
                i ++;
            }
            while(j >= i && arr[j] > arr[l]){
                j --;
            }
            if(i >= j){
                break;
            }
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j){

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 最小优先队列 return o1.compareTo(o2);
                // 最大优先队列 return o2.compareTo(o1);
                return o1.compareTo(o2);
            }
        });

        for(int i = 0; i < k; i++){
            pq.add(nums[i]);
        }

        for(int i = k; i < nums.length; i++){
            if(!pq.isEmpty() && nums[i] > pq.peek()){
                pq.remove();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}
