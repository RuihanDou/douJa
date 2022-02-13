package local.begin.leetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1189. “气球” 的最大数量
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 *
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：text = "nlaebolko"
 * 输出：1
 * 示例 2：
 *
 *
 *
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * 示例 3：
 *
 * 输入：text = "leetcode"
 * 输出：0
 */
public class LeetCode1189Solution {

//    private Map<Character, Integer> map = new HashMap<>();
//    char[] one = {'b', 'a', 'n'};
//    char[] two = {'l', 'o'};
//
//    public int maxNumberOfBalloons(String text) {
//        int len = text.length();
//        for(int i = 0; i < len; i++){
//            map.put(text.charAt(i), map.getOrDefault(text.charAt(i), 0) + 1);
//        }
//        int balloons = Integer.MAX_VALUE;
//        for(char c : one) {
//            balloons = Math.min(map.getOrDefault(c, 0), balloons);
//        }
//        for(char c : two){
//            balloons = Math.min(map.getOrDefault(c, 0) / 2, balloons);
//        }
//        return balloons;
//    }

    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[5];
        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);
            if (ch == 'b') {
                cnt[0]++;
            } else if (ch == 'a') {
                cnt[1]++;
            } else if (ch == 'l') {
                cnt[2]++;
            } else if (ch == 'o') {
                cnt[3]++;
            } else if (ch == 'n') {
                cnt[4]++;
            }
        }
        cnt[2] /= 2;
        cnt[3] /= 2;
        return Arrays.stream(cnt).min().getAsInt();
    }

    public static void main(String[] args) {
        String s = "nlaebolko";
        LeetCode1189Solution solution = new LeetCode1189Solution();
        System.out.println(solution.maxNumberOfBalloons(s));
    }

}
