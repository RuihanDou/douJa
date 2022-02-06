package local.begin.leetCode;


import java.util.Arrays;

/**
 * 1405. 最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 *
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 *
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 *
 * 提示：
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 */
public class LeetCode1405Solution {

    private class CharAux implements Comparable<CharAux> {
        char ch;
        int num;

        public CharAux(char ch, int num){
            this.ch = ch;
            this.num = num;
        }

        public int compareTo(CharAux another) {
            return another.num - num;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder res = new StringBuilder();
        CharAux[] charactors = {new CharAux('a', a), new CharAux('b', b), new CharAux('c', c)};
        while (true){
            Arrays.sort(charactors);
            boolean hasNext = false;
            for(CharAux charAux : charactors){
                if(charAux.num <= 0){
                    break;
                }
                int m = res.length();
                if(m >= 2 && res.charAt(m - 2) == charAux.ch && res.charAt(m - 1) == charAux.ch){
                    continue;
                }
                // 只要 有 CharAux的 num > 0 并且 没有连续三个的风险，都会走到这里
                hasNext = true;
                res.append(charAux.ch);
                charAux.num--;
                break;
            }
            if(!hasNext){
                break;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LeetCode1405Solution solution = new LeetCode1405Solution();
        System.out.println(solution.longestDiverseString(1,1,7));
    }

}
