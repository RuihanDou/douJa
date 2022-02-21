package local.begin.leetCode;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class LeetCode0076Solution {

    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if(sLen == 0 || tLen == 0 || sLen < tLen){
            return "";
        }

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        // 使用字符频数数组记录
        int[] winFreq = new int[128];
        int[] tFreq = new int[128];

        for (char c : charArrayT){
            tFreq[c]++;
        }

        // 滑动窗口中需要的变量，滑动窗口中包含多少t中的字符，对应的频数超过了不重复计算
        int distance = 0;
        int minLen = sLen + 1;
        int begin = 0;
        // [left, right) right 左边的字符都是看过的且 长度为 right - left
        int left = 0;
        int right = 0;

        while (right < sLen){
            // 如果有边界上的字符不在t中出现，有边界右移
            if(tFreq[charArrayS[right]] == 0){
                right++;
                continue;
            }
            // 此时 charArrayS[right] 的字符一定在 t 中出现， 统计该字符对应的频率并且右移一位，看下一个字符, 维护distance 和 winFreq
            if(winFreq[charArrayS[right]] < tFreq[charArrayS[right]]){
                distance++;
            }
            winFreq[charArrayS[right]]++;
            right++;

            while (distance == tLen){

                if(right - left < minLen){
                    minLen = right - left;
                    begin = left;
                }

                if(tFreq[charArrayS[left]] == 0){
                    left++;
                    continue;
                }

                if(winFreq[charArrayS[left]] == tFreq[charArrayS[left]]){
                    distance--;
                }

                winFreq[charArrayS[left]]--;
                left++;

            }


        }
        if(minLen == sLen + 1){
            return "";
        }
        return s.substring(begin, begin + minLen);
    }

}
