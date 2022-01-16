package local.begin.LeetCodeArray;


import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Solution0349 {

    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();
        for(int num1 : nums1){
            set.add(num1);
        }
        List<Integer> list = new ArrayList<>();
        for(int num2 : nums2){
            if(set.contains(num2)){
                list.add(num2);
                set.remove(num2);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

}
