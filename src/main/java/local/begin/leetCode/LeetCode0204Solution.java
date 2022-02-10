package local.begin.leetCode;


import java.util.Arrays;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= n <= 5 * 106
 */
public class LeetCode0204Solution {

//    public int countPrimes(int n) {
//
//        if(n < 2){
//            return 0;
//        }
//        // mark 从 下标 2 开始，false 表示是质数，true表示不是质数
//        boolean[] mark = new boolean[n];
//        int ans = 0;
//        for(int i = 2; i < n; i++){
//            if(!mark[i]){
//                ans++;
//            }
//            int j = 2;
//            while (j * i < n){
//                mark[i * j] = true;
//                j++;
//            }
//        }
//
//        return ans;
//    }

    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                ans += 1;
                // Eratosthenes 筛法
                // 到数字 i 开始，i * i 以前的不用考虑，j 的前进步长也是 i
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = true;
                    }
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        LeetCode0204Solution solution = new LeetCode0204Solution();
        System.out.println(solution.countPrimes(10));
    }

}
