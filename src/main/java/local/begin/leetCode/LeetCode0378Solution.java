package local.begin.leetCode;


/**
 * 378. 有序矩阵中第 K 小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * 示例 2：
 *
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 *
 *
 * 提示：
 *
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n2
 */
public class LeetCode0378Solution {

    // matrix 顺序规整，应该使用二分查找法
    public int kthSmallest(int[][] matrix, int k) {
        //  二分查找初始化处理
        int n = matrix.length;
        int l = matrix[0][0];
        int r = matrix[n-1][n-1];

        while (l < r){
            int mid = l + (r - l) / 2;
            if(check(matrix, mid, k, n)){
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l;

    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1, j = 0;
        int num = 0;
        while (i >= 0 && j < n){
            // 如果 matrix[i][j] <= mid， 则将 当前列  不大于 mid 的数的个数 i + 1 加到 num 中, ij 向右移动
            if(matrix[i][j] <= mid){
                num += i + 1;
                j++;
            }
            // 如果 matrix[i][j] > mid, ij 向上移动
            else {
                i--;
            }
        }
        return num >= k;
    }

}
