package local.begin.leetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 757. 设置交集大小至少为2
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 *
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 *
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 */
public class LeetCode0757Solution {

    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        int res = 0;
        int m = 2;

        // 先给区间排序
        Arrays.sort(intervals, (a, b) -> {
            if(a[0] == b[0]){
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        List<Integer>[] temp = new List[n];
        for(int i = 0; i < n; i++){
            temp[i] = new ArrayList<>();
        }

        for(int i = n - 1; i >= 0; i--){
            for(int j = intervals[i][0], k = temp[i].size(); k < m; j++, k++){
                res++;
                help(intervals, temp, i - 1, j);
            }
        }
        return res;
    }

    private void help(int[][] intervals, List<Integer>[] temp, int pos, int num) {
        for(int i = pos; i >= 0; i--){
            if(intervals[i][1] < num){
                break;
            }
            temp[i].add(num);
        }
    }

}
