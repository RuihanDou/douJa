package local.begin.leetCode;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 *
 * 提示：
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr 按 升序 排列
 * -104 <= arr[i], x <= 104
 */
public class LeetCode0658Solution {

    // arr 是有序的，考虑二分查找法
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = ret.size();
        if(x <= ret.get(0)){
            return ret.subList(0, k);
        }
        else if(x >= ret.get(n-1)){
            return ret.subList(n - k, n);
        }
        else {
            //大于 x 一点点的元素的索引 index
            int index = Collections.binarySearch(ret, x);

            if(index < 0){
                index = -index - 1;
            }
            int low = Math.max(0, index - k - 1), high = Math.min(n - 1, index + k - 1);

            while (high - low > k - 1){
                if((x - ret.get(low)) <= (ret.get(high) - x)){
                    high--;
                }
                else if((x - ret.get(low)) > (ret.get(high) - x)){
                    low++;
                }
                else {
                    throw new RuntimeException("Wrong Case.");
                }
            }

            return ret.subList(low, high+1);

        }
    }

//    public List<Integer> findClosestElements(int[] arr, int k, int x) {
//        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        Collections.sort(ret, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
//        ret = ret.subList(0, k);
//        Collections.sort(ret);
//        return ret;
//    }


}
