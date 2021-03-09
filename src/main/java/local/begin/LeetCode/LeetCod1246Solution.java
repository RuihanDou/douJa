package local.begin.LeetCode;


/**
 * 1246. 删除回文子数组
 * 给你一个整数数组 arr，每一次操作你都可以选择并删除它的一个 回文 子数组 arr[i], arr[i+1], ..., arr[j]（ i <= j）。
 *
 * 注意，每当你删除掉一个子数组，右侧元素都会自行向前移动填补空位。
 *
 * 请你计算并返回从数组中删除所有数字所需的最少操作次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：arr = [1,3,4,1,5]
 * 输出：3
 * 解释：先删除 [4]，然后删除 [1,3,1]，最后再删除 [5]。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 20
 */
public class LeetCod1246Solution {

    public int minimumMoves(int[] arr) {

        int len = arr.length;
        int[][] dp = new int[len][len];

        if(null == arr || arr.length == 0){
            return 0;
        }

        // 初始化，完成长度为1或者2的情况
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;
        }
        for(int i = 0; i < len - 1; i++){
            dp[i][i+1] = arr[i] == arr[i+1] ? 1 : 2;
        }

        // 以i为开始点，j为结束点
        for(int j = 2; j < len; j ++){

            for(int i = j - 2; i >= 0; i--){

                // 记录最小值的中间变量；
                int min = len;

                // 回文的情况,arr[i...j]首位两端元素相等，那么其值应该等于arr[i+1...j-1]
                if(arr[i] == arr[j]){
                    min = dp[i+1][j-1];
                }

                // arr[i] 与 arr[j] 不相等，需遍历其所有二分子段
                // k 为arr[i...j]中间可能的切点
                for(int k = i; k < j; k++){
                    min = Math.min(min, dp[i][k] + dp[k+1][j]);
                }

                dp[i][j] = min;
            }

        }

        return dp[0][len-1];
    }

}
