package local.begin.leetCode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 */
public class LeetCode0224Solution {

    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n){
            if(s.charAt(i) == ' '){
                i++;
            }
            else if(s.charAt(i) == '+'){
                sign = ops.peek();
                i++;
            }
            else if(s.charAt(i) == '-'){
                sign = -ops.peek();
                i++;
            }
            else if(s.charAt(i) == '('){
                ops.push(sign);
                i++;
            }
            else if(s.charAt(i) == ')'){
                ops.pop();
                i++;
            }
            else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))){
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LeetCode0224Solution solution = new LeetCode0224Solution();
        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }



}
