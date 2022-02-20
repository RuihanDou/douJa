package local.begin.leetCode;


/**
 * 717. 1比特与2比特字符
 * 有两种特殊字符：
 *
 * 第一种字符可以用一个比特 0 来表示
 * 第二种字符可以用两个比特(10 或 11)来表示、
 * 给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * 示例 2:
 *
 * 输入: bits = [1, 1, 1, 0]
 * 输出: false
 * 解释: 唯一的编码方式是两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 *
 *
 * 提示:
 *
 * 1 <= bits.length <= 1000
 * bits[i] == 0 or 1
 */
public class LeetCode0717Solution {

    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1){
            // 如果 bit[i] = 1, 要跳过一位
            // 如果 bit[i] = 0, 跳到下一位
            i += bits[i] + 1;
        }
        return i == n - 1;
    }



}
