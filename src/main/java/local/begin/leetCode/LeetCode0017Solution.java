package local.begin.leetCode;


import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class LeetCode0017Solution {

    private static Map<Character, List<Character>> phone = new HashMap<>();
    static {
        phone.put('2', new ArrayList<Character>(Arrays.asList('a', 'b', 'c')));
        phone.put('3', new ArrayList<Character>(Arrays.asList('d', 'e', 'f')));
        phone.put('4', new ArrayList<Character>(Arrays.asList('g', 'h', 'i')));
        phone.put('5', new ArrayList<Character>(Arrays.asList('j', 'k', 'l')));
        phone.put('6', new ArrayList<Character>(Arrays.asList('m', 'n', 'o')));
        phone.put('7', new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's')));
        phone.put('8', new ArrayList<Character>(Arrays.asList('t', 'u', 'v')));
        phone.put('9', new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z')));

    }

    public List<String> letterCombinations(String digits) {
        int digitsLen = digits.length();
        List<String> rst = new ArrayList<>();
        if(digitsLen < 1) {
            return rst;
        }
        for (int i = 0; i < digitsLen; i++) {
            List<Character> letters = phone.get(digits.charAt(i));
            List<String> update = new ArrayList<>();
            if (rst.isEmpty()) {
                letters.forEach(letter -> update.add(String.valueOf(letter)));
            } else {
                for (String ele : rst) {
                    for (char item : letters) {
                        update.add(ele + String.valueOf(item));
                    }
                }
            }
            rst = update;
        }
        return rst;
    }

    public static void main(String[] args) {
        LeetCode0017Solution solution = new LeetCode0017Solution();
        List<String> list = solution.letterCombinations("234");
        System.out.println(list);
    }
}
