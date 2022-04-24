package local.begin.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 500. 键盘行
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * 美式键盘 中：
 *
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * American keyboard
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 *
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 */
public class LeetCode0500Solution {

    private static Map<Character, Integer> map = new HashMap<>();
    static {
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);

        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);

        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);
    }

    public String[] findWords(String[] words) {

        List<String> rst = new ArrayList<>();
        if(words.length < 1){
            return new String[0];
        }
        for (String word : words) {
            String word1 = word.toLowerCase();
            int len = word1.length();
            int group = map.get(word1.charAt(0));
            boolean same = true;
            for(int i = 1; i < len; i++){
                if(map.get(word1.charAt(i)) != group){
                    same = false;
                    break;
                }
            }
            if(same){
                rst.add(word);
            }
        }
        int size = rst.size();
        String[] res = new String[size];
        for(int j = 0; j < size; j++){
            res[j] = rst.get(j);
        }
        return res;
    }

}
