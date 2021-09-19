package local.begin.jianZhiOffer;

import java.util.*;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class Interview40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0){
            return new int[0];
        }
        Random rnd = new Random();
        selectK(arr, 0, arr.length - 1, k - 1, rnd);
        return Arrays.copyOf(arr, k);
    }

    private int selectK(int[] arr, int l, int r, int k, Random rnd){
        int p = partition(arr, l, r, rnd);
        if(k == p){
            return arr[p];
        }
        if(k < p){
            return selectK(arr, l, p - 1, k, rnd);
        }
        return selectK(arr, p + 1, r, k, rnd);
    }

    private void swap(int[] arr, int i, int j){

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private int partition(int[] arr, int l, int r, Random rnd){

        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1 ... i-1] <= v; arr[j+1 ... r] >= v
        int i = l + 1, j =r;
        while (true){
            while (i <= j && arr[i] < arr[l]){
                i++;
            }
            while (j >= i && arr[j] > arr[l]){
                j--;
            }
            if(i >= j){
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    public int[] getLeastNumbers1(int[] arr, int k) {

//        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                // 最小优先队列 return o1.compareTo(o2);
//                // 最大优先队列 return o2.compareTo(o1);
//                return o2.compareTo(o1);
//            }
//        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < k; i++){
            pq.add(arr[i]);
        }

        for(int i = k; i < arr.length; i++){
            if(!pq.isEmpty() && arr[i] < pq.peek()){
                pq.remove();
                pq.add(arr[i]);
            }
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = pq.remove();
        }
        return res;
    }

    public static void main(String[] args) {
        Interview40 solution = new Interview40();
        System.out.println(solution.getLeastNumbers(new int[]{3,2,1}, 2));
    }
}
