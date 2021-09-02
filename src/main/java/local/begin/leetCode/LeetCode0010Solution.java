package local.begin.leetCode;


import java.util.Arrays;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class LeetCode0010Solution {

    // p 是带符号的匹配 ； s 是全字母目标
    // 方法1：回溯法
    public boolean isMatchV0(String s, String p) {
        // 如果p字符串为空，那么返回s字符串是否也为空的结果
        if(p.isEmpty()){
            return s.isEmpty();
        }
        //判断s和p的首字符是否匹配，注意要先判断s不为空,这是现在字符串的第一位
        boolean headMatched = !s.isEmpty() && (s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //如果p的下一个元素是* 则分别对两种情况做判断
            // 情况一 是 当前位出现为 0 次；
            boolean condition1 = isMatchV0(s, p.substring(2));
            // 情况二是 当前位出现多次；这时需要headmatch为true
            boolean condition2 = headMatched && isMatchV0(s.substring(1), p);

            return  condition1 || condition2;
        }else if (headMatched) {
            // 否则，如果s和p的首字符相等
            return isMatchV0(s.substring(1), p.substring(1));
        }else {
            return false;
        }
    }

    public boolean isMatchV1(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] table = new boolean[sLen + 1][pLen + 1];
        // 如果p字符串为空，那么返回s字符串是否也为空的结果
        if(p.isEmpty()){
            return s.isEmpty();
        }

        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                //处理第0行 , 即s为空时
                if(i == 0) {
                    if (j == 0) {
                        table[i][j] = true;
                    } else {
                        if (j > 1 && table[i][j - 2] && p.charAt(j-1) == '*') {
                            table[i][j] = true;
                        } else {
                            table[i][j] = false;
                        }
                    }
                }
                else {
                  if (j == 0) {
                      table[i][j] = false;
                  } else if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j) == '.') {
                      table[i][j] = table[i -  1][j - 1];
                  } else if (p.charAt(j-1) == '*') {
                      // s = "baab" p = "bc*aab"的情况
                      if(p.charAt(j-2) != s.charAt(i-1)) {
                          table[i][j] = table[i][j - 2];
                      } else if (p.charAt(j-2) == s.charAt(i-2) || p.charAt(j-2) == '.') {

                      }
                  }
                }
            }
        }
        System.out.println(Arrays.deepToString(table));
        return table[sLen][pLen];
    }

    public static void main(String[] args) {
        LeetCode0010Solution leet = new LeetCode0010Solution();
        boolean res = leet.isMatchV1("aabcd", "a*bcd");
        System.out.println(res);
    }

}
