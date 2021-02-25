package local.begin.LeetCode;


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
public class LeetCode003Solution {

    /**
     * 暴力遍历法
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV0(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s,i, j)){
                    ans = Math.max(j - i, ans);
                }
            }
        }
        return ans;
    }

    /**
     * 滑动窗口法，窗口是一个HashSet
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV1(String s) {
        int ans = 0;
        int n = s.length();
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();

        while(i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans , set.size());
            }
            else {
                set.remove(s.charAt(i));
                i++;
            }
        }

        return ans;
    }

    /**
     * 如果s[j] 在[i, j)范围内有与 j'重复的字符，我们不需要逐渐增加i。我们可以直接跳过[i，j]
     *  范围内的所有元素，并将i变为 j' + 1
     *  +1
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV2(String s) {
        int ans = 0, n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            ans = Math.max(j - i + 1, ans);
            map.put(s.charAt(j), j);
        }
        return ans;
    }

    /**
     * 把添加的map换成数组
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringV3(String s) {
        int n = s.length(), ans = 0;
        // 初始化出来index每一位都是0
        int[] index = new int[256];

        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)] + 1, i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j;
        }

        return ans;
    }

    private static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            char ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        int num = LeetCode003Solution.lengthOfLongestSubstringV3("pwwkew");
        System.out.println(num);
    }


}
