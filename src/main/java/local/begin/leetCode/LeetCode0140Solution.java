package local.begin.leetCode;


import java.util.*;

/**
 * 140. 单词拆分 II
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
 *
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 *
 *
 *
 * 示例 1：
 *
 * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 * 示例 2：
 *
 * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * 输出:[]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 */
public class LeetCode0140Solution {

    /**
     * 使用哈希表存储字符串 ss 的每个下标和从该下标开始的部分可以组成的句子列表，
     * 在回溯过程中如果遇到已经访问过的下标，则可以直接从哈希表得到结果，而不需要重复计算。
     * 如果到某个下标发现无法匹配，则哈希表中该下标对应的是空列表，因此可以对不能拆分的情况进行剪枝优化。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // map中存的是 s 字符串中 从 k 索引开始 可以拆分的句子们（ 句子:List<String> 句子们 List<List<String>> ）
        // key : 索引, value : 句子们
        Map<Integer, List<List<String>>> map = new HashMap<>();
        // map.get(0)就是要的结果, 进行回溯
        List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<>(wordDict), 0, map);
        // 把 句子 List<String> 变成 句子 String ---> 句子们 List<List<String>> 变成句子们 List<String>
        List<String> breakList= new LinkedList<>();
        for(List<String> wordBreak : wordBreaks){
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    private List<List<String>> backtrack(String s, int length, HashSet<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        // 如果map中没有当前索引，证明该情况没有遍历到
        if(!map.containsKey(index)){
            List<List<String>> wordBreaks = new LinkedList<>();
            // 如果 index 到了 s 结尾，句子们为空，之后的for循环条件不满足，直接存储到map中跳出条件分支
            if(index == length){
                wordBreaks.add(new LinkedList<>());
            }
            for(int i = index + 1; i <= length; i++){
                // 当 s[index, i) 在字典中，回溯 index = i 然后在 句子们前 加上 s[index, i)
                String word = s.substring(index, i);
                if (wordSet.contains(word)) {
                    List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);

    }

}
