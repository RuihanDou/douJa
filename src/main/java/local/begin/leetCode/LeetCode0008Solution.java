package local.begin.leetCode;


/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 示例 1:
 *
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 * 示例 5:
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode0008Solution {


    public static int myAtoi(String str) {
        // 结果
        int rst = 0;
        char[] charArray = str.toCharArray();
        // 正负标记
        int zf = 1;
        // 处理字符位置
        int i = 0;
        // 字符串长度
        int length = charArray.length;

        // 处理数字前缀
        while (i < length) {
            // 空字符出现，直接处理之后字符
            if (' ' == charArray[i]) {
                i++;
                continue;
            }
            // 前面出现‘+’正常处理之后的字符
            else if ('+' == charArray[i]) {
                i++;
                break;
            }
            // 前面出现‘-’ 正负标记置负
            else if ('-' == charArray[i]) {
                i++;
                zf = -1;
                break;
            }
            // 第一个字符不是 数字字符，直接返回 0
            else if (charArray[i] < '0' || charArray[i] > '9') {
                return 0;
            }
            // 没有发生以上情况，直接break 开始处理数字
            else {
                break;
            }
        }
        // 如果处理完前缀直接就把str处理完了，返回0
        if (length == i) {
            return 0;
        }
        // 开始正式处理数字
        while (i < length) {
            // 如果后缀出现非数字字符串，直接返回前面数字部分
            if (charArray[i] < '0' || charArray[i] > '9') {
                return rst;
            }
            // 因为要防止溢出，所以在溢出检测前得把正负标记带上
            int pop = zf * (charArray[i] - '0');
            // 防止溢出
            //  因为 Integer.MAX_VALUE 的最后一位是 7 如果 ret == Integer.MAX_VALUE && pop <= 7 没有溢出
            if (rst > Integer.MAX_VALUE / 10 || (rst == Integer.MAX_VALUE / 10 && pop > 7)) {
                return Integer.MAX_VALUE;
            }
            //  因为 Integer.MAX_VALUE 的最后一位是 8 如果 ret == Integer.MAX_VALUE && pop >= -8 没有溢出
            if (rst < Integer.MIN_VALUE / 10 || (rst == Integer.MIN_VALUE / 10 && pop < -8)) {
                return Integer.MIN_VALUE;
            }
            rst = 10 * rst + pop;
            i++;
        }

        return rst;
    }

    public static void main(String[] args) {
        String str = "-91283472332";
        int num = LeetCode0008Solution.myAtoi(str);
        System.out.println(num);
    }
}
