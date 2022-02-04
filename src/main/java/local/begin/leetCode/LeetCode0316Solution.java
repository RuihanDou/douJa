package local.begin.leetCode;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class LeetCode0316Solution {

    // 单调栈
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        int[] lastIndex = new int[26];
        for(int i = 0; i< len; i++){
            lastIndex[charArray[i] - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[26];
        for(int i = 0; i < len; i++){

            // 单调栈内出现过的字符，与之位置交换不能使字典序靠前
            if(visited[charArray[i] - 'a']){
                continue;
            }

            // 栈顶元素字典序大于 i 位置上的，并且 栈顶元素在之后还会出现，则移除栈顶，栈顶元素同时被登记为没有访问过
            while (!stack.isEmpty() && stack.peek() > charArray[i] && lastIndex[stack.peek() - 'a'] > i){
                Character top = stack.remove();
                visited[top - 'a'] = false;
            }

            stack.push(charArray[i]);
            visited[charArray[i] - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(char c : stack){
            sb.append(c);
        }
        return sb.reverse().toString();

    }

}
