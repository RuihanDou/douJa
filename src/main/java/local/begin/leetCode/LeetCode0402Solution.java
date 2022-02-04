package local.begin.leetCode;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 402. 移掉 K 位数字
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 *
 *
 * 示例 1 ：
 *
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 *
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 ：
 *
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 *
 *
 * 提示：
 *
 * 1 <= k <= num.length <= 105
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 */
public class LeetCode0402Solution {

    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        int len = num.length();
        for(int i = 0; i < len; i++){
            char digit = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit){
                stack.pollLast();
                k--;
            }
            stack.offerLast(digit);
        }

        // 没有移除够 k 个，把栈顶多余的字符移除
        for(int i = 0; i < k; i++){
            stack.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean headZero = true;
        while (!stack.isEmpty()){
            char digit = stack.pollFirst();
            if(headZero && digit == '0'){
                continue;
            }
            headZero = false;
            sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }


    public static void main(String[] args) {
        LeetCode0402Solution solution = new LeetCode0402Solution();
        System.out.println(solution.removeKdigits("1432219", 3));
    }


}
