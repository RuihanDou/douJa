package local.begin.leetCode;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 767. 重构字符串
 * 给定一个字符串 s ，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *
 * 返回 s 的任意可能的重新排列。若不可行，返回空字符串 "" 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "aab"
 * 输出: "aba"
 * 示例 2:
 *
 * 输入: s = "aaab"
 * 输出: ""
 *
 *
 * 提示:
 *
 * 1 <= s.length <= 500
 * s 只包含小写字母
 */
public class LeetCode0767Solution {

//    /**
//     * 基于最大堆的贪心
//     *
//     * @param s
//     * @return
//     */
//    public String reorganizeString(String s) {
//        if (s.length() < 2) {
//            return s;
//        }
//        int[] counts = new int[26];
//        int maxCount = 0;
//        int length = s.length();
//        for (int i = 0; i < length; i++) {
//            char c = s.charAt(i);
//            counts[c - 'a']++;
//            maxCount = Math.max(maxCount, counts[c - 'a']);
//        }
//        if (maxCount > (length + 1) / 2) {
//            return "";
//        }
//        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
//            @Override
//            public int compare(Character letter1, Character letter2) {
//                return counts[letter2 - 'a'] - counts[letter1 - 'a'];
//            }
//        });
//        for (char c = 'a'; c <= 'z'; c++) {
//            if (counts[c - 'a'] > 0) {
//                queue.offer(c);
//            }
//        }
//        StringBuffer sb = new StringBuffer();
//        while (queue.size() > 1) {
//            char letter1 = queue.poll();
//            char letter2 = queue.poll();
//            sb.append(letter1);
//            sb.append(letter2);
//            int index1 = letter1 - 'a', index2 = letter2 - 'a';
//            counts[index1]--;
//            counts[index2]--;
//            if (counts[index1] > 0) {
//                queue.offer(letter1);
//            }
//            if (counts[index2] > 0) {
//                queue.offer(letter2);
//            }
//        }
//        if (queue.size() > 0) {
//            sb.append(queue.poll());
//        }
//        return sb.toString();
//    }

    /**
     * 基于计数的贪心
     * @param s
     * @return
     */
    public String reorganizeString(String s) {
        if (s.length() < 2) {
            return s;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }

}
