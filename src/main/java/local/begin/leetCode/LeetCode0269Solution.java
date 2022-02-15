package local.begin.leetCode;


import java.util.*;

/**
 * 269. 火星词典
 * 现有一种使用英语字母的火星语言，这门语言的字母顺序与英语顺序不同。
 *
 * 给你一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 *
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 *
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 *
 * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 *
 *
 * 示例 1：
 *
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 * 示例 2：
 *
 * 输入：words = ["z","x"]
 * 输出："zx"
 * 示例 3：
 *
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 */
public class LeetCode0269Solution {

    /**
     * 使用拓扑排序
     *
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        // 构建图
        Map<Character, Set<Character>> map = new HashMap<>();
        for(int i = 0; i < words.length - 1; i++){
            for(int j = 0; j < words[i].length() && j < words[i + 1].length(); j++){
                //如果当前字符一样，比较下一个
                if(words[i].charAt(j) == words[i + 1].charAt(j)){
//                    if(j == words[i].length() - 1 && j != words[i + 1].length() - 1){
//                        return "";
//                    }
                    continue;
                }
                //保存第一个不同字符的顺序
                Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
                set.add(words[i + 1].charAt(j));
                map.put(words[i].charAt(j), set);
                //一定break掉，排序已经由现在的 words[i].charAt(j) 和 words[i + 1].charAt(j) 决定了，后面的不能参考
                break;
            }
        }

        //创建保存入度的数组
        int[] degrees = new int[26];
        Arrays.fill(degrees, -1);
        //注意，不是26字母都在words中出现，所以出度分为两种情况：没有出现的字母出度为-1，出现了的字母的出度为非负数
        for (String str : words) {
            //将出现过的字符的出度设定为0
            for (char c : str.toCharArray())
                degrees[c - 'a'] = 0;
        }
        for (char key : map.keySet()) {
            for (char val : map.get(key)) {
                degrees[val - 'a']++;
            }
        }

        // 拓扑排序
        // sb 用于保存 拓扑排序结果
        StringBuilder sb = new StringBuilder();
        // queue 用于保存入度置0的节点
        Queue<Character> queue = new LinkedList<>();
        // count 计数节点个数
        int count = 0;
        for(int i = 0; i < 26; i++){
            if(degrees[i] != -1){
                count++;
            }
            if(degrees[i] == 0){
                queue.offer((char)('a' + i));
            }
        }

        while (!queue.isEmpty()){
            Character cur = queue.poll();
            sb.append(cur);
            // 对cur相邻接的的节点入度减1
            if(map.containsKey(cur)){
                Set<Character> set = map.get(cur);
                for(Character c : set){
                    degrees[c-'a']--;
                    if(degrees[c-'a'] == 0){
                        queue.offer(c);
                    }
                }
            }
        }

        // 判断是否有环
        if(sb.length() != count || (map.isEmpty() && count > 1)){
            return "";
        }
        else {
            return sb.toString();
        }

    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrtkj"};
        LeetCode0269Solution solution = new LeetCode0269Solution();
        System.out.println(solution.alienOrder(words));
    }

}
