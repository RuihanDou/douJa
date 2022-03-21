package local.begin.leetCode;


import java.util.*;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class LeetCode0020Solution {

    private static final Map<Character, Character> brankets = new HashMap<>();
    static {
        brankets.put(')', '(');
        brankets.put(']', '[');
        brankets.put('}', '{');
    }

    public boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }

        Deque<Character> dp = new LinkedList<>();
        for(char c : s.toCharArray()) {
            if (brankets.values().contains(c)) {
                dp.push(c);
            } else if (brankets.keySet().contains(c)) {
                if (dp.size() == 0 || brankets.get(c) != dp.pop()) {
                    return false;
                }
            } else {
                continue;
            }
        }
        return dp.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode0020Solution solution = new LeetCode0020Solution();
        String input = "(])";
        System.out.println(solution.isValid(input));
    }

}
