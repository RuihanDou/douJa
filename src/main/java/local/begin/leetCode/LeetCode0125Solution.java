package local.begin.leetCode;

import local.begin.tools.DebugTools;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 */
public class LeetCode0125Solution {

    public boolean isPalindrome(String s) {

        StringBuilder forward = new StringBuilder();
        int length = s.length();
        for(int i = 0; i < length; i++) {
            char cur = s.charAt(i);
            if(Character.isLetterOrDigit(cur)){
                forward.append(Character.toLowerCase(cur));
            }
        }

        StringBuilder backward = new StringBuilder(forward).reverse();

        return forward.toString().equals(backward.toString());

    }

    public static void main(String[] args) {
        LeetCode0125Solution solution = new LeetCode0125Solution();

        DebugTools.print(solution.isPalindrome("race a car"));
    }

}
