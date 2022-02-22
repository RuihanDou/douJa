package local.begin.leetCode;


import java.util.*;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LeetCode0003Solution {

    /**
     * 使用滑动窗口解决
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        // 记录窗口中出现的字符
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 初始化窗口的右边界，结果暂存量
        int r = - 1, ans = 0;
        //
        for(int l = 0; l < n; l++){
            // 因为循环伊始，左指针就向右移动一格，移除的字符对应从出现的字符中移除
            if(l != 0){
                occ.remove(s.charAt(l));
            }
            // 右移 右边界 因为右移向右多看一格，所以 l < n - 1，向右移动得保证子串中没有重复字符
            while (r < n - 1 && !occ.contains(s.charAt(r + 1))){
                occ.add(s.charAt(r + 1));
                r++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

}
