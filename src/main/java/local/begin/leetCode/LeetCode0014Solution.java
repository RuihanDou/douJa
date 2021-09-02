package local.begin.leetCode;


import java.util.*;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class LeetCode0014Solution {

    public String longestCommonPrefixV0(String[] strs) {
        int len = strs.length;
        int[] stringLengths = new int[len];
        if (len < 1) {
            return "";
        }
        for(int i = 0; i < len; i++){
            stringLengths[i] = strs[i].length();
        }

        int minLen = Arrays.stream(stringLengths).min().getAsInt();
        StringBuilder rst = new StringBuilder();

        for(int i = 0; i < minLen; i++) {
            Set<Character> set = new HashSet<>();
            for(int k = 0; k < len; k++) {
                set.add(strs[k].charAt(i));
            }
            if(set.size() != 1) {
                break;
            }
            set.forEach(ele -> rst.append(ele));
        }
        return rst.toString();

    }

    public String longestCommonPrefixV1(String[] strs){

        int strLen = strs.length;

        if(strLen < 1) {
            return "";
        } else if (strLen == 1) {
            return strs[0];
        } else {
            String rst = strs[0];
            int i = 1;
            while (i < strLen){
                rst = twoCompare(rst, strs[i]);
                i++;
            }
            return rst;
        }
    }

    private String twoCompare(String str1, String str2) {
        int i = 0;
        StringBuilder rst = new StringBuilder();
        while (i < str1.length() && i < str2.length()) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            } else {
                rst.append(str1.charAt(i));
            }
            i++;
        }
        return rst.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"cca","aba"};
        LeetCode0014Solution solution = new LeetCode0014Solution();

        String rst = solution.longestCommonPrefixV1(strs);

        System.out.println(rst);
    }
}
