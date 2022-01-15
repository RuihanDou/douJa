package local.begin.LeetCodeArray;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 *
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 *
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *
 *
 * 提示:
 *
 * 0 <= rowIndex <= 33
 *
 *
 * 进阶：
 *
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 */
public class Solution0119 {

//    public List<Integer> getRow(int rowIndex) {
//        List<Integer> pre = new ArrayList<>();
//        for(int i = 0; i <= rowIndex; i++){
//            List<Integer> cur = new ArrayList<>();
//            for(int j = 0; j <= i; j++){
//                if(j == 0 || j == i){
//                    cur.add(1);
//                } else {
//                    cur.add(pre.get(j - 1) + pre.get(j));
//                }
//            }
//            pre = cur;
//        }
//        return pre;
//    }


    // 杨辉三角第i行第j个数字（i,j都从0算起）为 ( C(j, i) )
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }


}
