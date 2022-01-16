package local.begin.LeetCodeArray;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 350. 两个数组的交集 II
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Solution0350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // 经过上面的操作，可以确保num1的数据两小于或等于num2
        // map key 为数值， value 为出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums1){
            if(!map.containsKey(num)){
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int num : nums2){
            if(map.containsKey(num)){
                list.add(num);
                int count = map.get(num);
                if(count > 1){
                    map.put(num, count - 1);
                } else {
                    map.remove(num);
                }
            }
        }
        int[] rst = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            rst[i] = list.get(i);
        }
        return rst;
    }

}
