package local.begin.leetCode;


import java.util.ArrayList;
import java.util.List;

/**
 * 1414. 和为 K 的最少斐波那契数字数目
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 *
 * 斐波那契数字定义为：
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 ， 其中 n > 2 。
 * 数据保证对于给定的 k ，一定能找到可行解。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 7
 * 输出：2
 * 解释：斐波那契数字为：1，1，2，3，5，8，13，……
 * 对于 k = 7 ，我们可以得到 2 + 5 = 7 。
 * 示例 2：
 *
 * 输入：k = 10
 * 输出：2
 * 解释：对于 k = 10 ，我们可以得到 2 + 8 = 10 。
 * 示例 3：
 *
 * 输入：k = 19
 * 输出：3
 * 解释：对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
 *
 *
 * 提示：
 *
 * 1 <= k <= 10^9
 */
public class LeetCode1414Solution {

    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibonacci = new ArrayList<>();
        int first = 1, second = 1;
        fibonacci.add(first);
        fibonacci.add(second);
        while (first + second <= k){
            int third = first + second;
            fibonacci.add(third);
            first = second;
            second = third;
        }

        int ans = 0;
        for(int i = fibonacci.size() - 1; i >= 0 && k > 0; i++){
            int num = fibonacci.get(i);
            if(k >= num){
                k -= num;
                ans++;
            }
        }
        return ans;
    }

}
