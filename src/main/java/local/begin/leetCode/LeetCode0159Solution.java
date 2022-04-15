package local.begin.leetCode;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 159. 至多包含两个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 *
 * 示例 1:
 *
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 *
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 */
public class LeetCode0159Solution {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if(n < 3){
            return n;
        }

        int l = 0, r = 0;
        Map<Character, Integer> map = new HashMap<>();

        int longest = 2;

        // 窗口右界没有超过时
        while (r < n){

            if(map.size() < 3){
                map.put(s.charAt(r), r++);
            }
            // 滑动窗口包含有三个字符时
            if(map.size() == 3){
                // 删除掉最左边的字符
                int delIndex = Collections.min(map.values());
                map.remove(s.charAt(delIndex));
                // 窗口左界右移
                l = delIndex + 1;
            }

            longest = Math.max(longest, r - l);

        }

        return longest;

    }

}
