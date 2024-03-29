package local.begin.LeetCodeArray;


import java.util.Arrays;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 */
public class Solution0066 {

    public int[] plusOne(int[] digits) {
        int len = digits.length, carry = 1;
        for(int i = len - 1; i >= 0; i--){
            int tempCarry = carry;
            carry = (digits[i] + carry) / 10;
            digits[i] = (digits[i] + tempCarry) % 10;
        }
        if(carry == 0){
            return digits;
        } else {
            int[] rst = new int[len + 1];
            rst[0] = carry;
            for(int i = 0; i < len; i++){
                rst[i + 1] = digits[i];
            }
            return rst;
        }
    }

    public static void main(String[] args) {
        int[] digits = new int[]{1,2,3};
        Solution0066 solution = new Solution0066();
        int[] rst = solution.plusOne(digits);
        System.out.println(Arrays.toString(rst));
    }

}
