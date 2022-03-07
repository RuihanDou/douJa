package local.begin.leetCode;


/**
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 *
 * 输入: num = -7
 * 输出: "-10"
 *
 *
 * 提示：
 *
 * -107 <= num <= 107
 */
public class LeetCode0504Solution {

    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        boolean neg = num < 0;
        num = Math.abs(num);
        StringBuffer digits = new StringBuffer();
        while (num > 0){
            digits.append(num % 7);
            num /= 7;
        }
        if(neg){
            digits.append('-');
        }
        return digits.reverse().toString();
    }

}
