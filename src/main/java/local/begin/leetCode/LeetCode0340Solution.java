package local.begin.leetCode;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 340. 至多包含 K 个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * 示例 2:
 *
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
 */
public class LeetCode0340Solution {

//    /**
//     * 滑动窗口 + 哈希表
//     * @param s
//     * @param k
//     * @return
//     */
//    public int lengthOfLongestSubstringKDistinct(String s, int k) {
//        int n = s.length();
//        if (n*k == 0) return 0;
//
//        // 互动窗口的左右边界指针
//        int left = 0;
//        int right = 0;
//        // 哈希表中，key 是 字符 value 是 它最右边的位置
//        // 在滑动窗口中
//        Map<Character, Integer> hashmap = new HashMap<>();
//
//        int maxLen = 1;
//
//        while (right < n) {
//            // 添加新字符 和 它最右边的位置
//            hashmap.put(s.charAt(right), right++);
//
//            // 当滑动窗口中的字符大于 k 个时
//            if (hashmap.size() == k + 1) {
//                // 删除最左边的字符
//                int delIdx = Collections.min(hashmap.values());
//                hashmap.remove(s.charAt(delIdx));
//                // 做指针右移
//                left = delIdx + 1;
//            }
//
//            maxLen = Math.max(maxLen, right - left);
//        }
//        return maxLen;
//    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n*k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);

        int max_len = 1;

        while (right < n) {
            Character character = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(character))
                hashmap.remove(character);
            hashmap.put(character, right++);

            // slidewindow contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
                hashmap.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }


}
