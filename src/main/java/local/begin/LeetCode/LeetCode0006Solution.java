package local.begin.LeetCode;



/**
 * 6. Z 字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class LeetCode0006Solution {
    // 建立 numRows 个 Stringbuilder
    public static String convert(String s, int numRows) {
        if(s == null || s.length() == 1 || numRows <= 1) {
            return s;
        }

        StringBuilder[] array = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            array[i] = new StringBuilder();
        }
        int direct = 1;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            array[j].append(s.charAt(i));
            if (j == 0) {direct = 1;}
            if (j == numRows - 1) {direct = -1;}
            j = j + direct;
        }
        StringBuilder rst = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            rst.append(array[k]);
        }
        return rst.toString();
    }

    public static void main(String[] args) {
        String rst = LeetCode0006Solution.convert("LEETCODEISHIRING", 3);
        System.out.println(rst);
    }
}
