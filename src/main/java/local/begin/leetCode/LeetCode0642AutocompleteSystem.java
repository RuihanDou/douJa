package local.begin.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 642. 设计搜索自动补全系统
 * 为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。
 *
 * 给定一个字符串数组 sentences 和一个整数数组 times ，长度都为 n ，其中 sentences[i] 是之前输入的句子， times[i] 是该句子输入的相应次数。对于除 ‘#’ 以外的每个输入字符，返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。
 *
 * 下面是详细规则：
 *
 * 一条句子的热度定义为历史上用户输入这个句子的总次数。
 * 返回前 3 的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
 * 如果满足条件的句子个数少于 3 ，将它们全部输出。
 * 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
 * 实现 AutocompleteSystem 类：
 *
 * AutocompleteSystem(String[] sentences, int[] times): 使用数组sentences 和 times 对对象进行初始化。
 * List<String> input(char c) 表示用户输入了字符 c 。
 * 如果 c == '#' ，则返回空数组 [] ，并将输入的语句存储在系统中。
 * 返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。如果少于 3 个匹配项，则全部返回。
 *
 *
 * 示例 1：
 *
 * 输入
 * ["AutocompleteSystem", "input", "input", "input", "input"]
 * [[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
 * 输出
 * [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 *
 * 解释
 * AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
 * obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
 * obj.input("a"); // return []. There are no sentences that have prefix "i a".
 * obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 *
 * 提示:
 *
 * n == sentences.length
 * n == times.length
 * 1 <= n <= 100
 * 1 <= sentences[i].length <= 100
 * 1 <= times[i] <= 50
 * c 是小写英文字母， '#', 或空格 ' '
 * 每个被测试的句子将是一个以字符 '#' 结尾的字符 c 序列。
 * 每个被测试的句子的长度范围为 [1,200]
 * 每个输入句子中的单词用单个空格隔开。
 * input 最多被调用 5000 次
 */
public class LeetCode0642AutocompleteSystem {

    private class Node {
        Node(String st, int t) {
            sentence = st;
            times = t;
        }
        String sentence;
        int times;
    }

    private class Trie {
        int times;
        Trie[] branches = new Trie[27];
    }

    private int int_(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    private void insert(Trie t, String s, int times) {
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[int_(s.charAt(i))] == null)
                t.branches[int_(s.charAt(i))] = new Trie();
            t = t.branches[int_(s.charAt(i))];
        }
        t.times += times;
    }

    private List <Node> lookup(Trie t, String s) {
        List < Node > list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[int_(s.charAt(i))] == null)
                return new ArrayList<>();
            t = t.branches[int_(s.charAt(i))];
        }
        traverse(s, t, list);
        return list;
    }

    private void traverse(String s, Trie t, List < Node > list) {
        if (t.times > 0)
            list.add(new Node(s, t.times));
        for (char i = 'a'; i <= 'z'; i++) {
            if (t.branches[i - 'a'] != null)
                traverse(s + i, t.branches[i - 'a'], list);
        }
        if (t.branches[26] != null)
            traverse(s + ' ', t.branches[26], list);
    }

    private Trie root;

    public LeetCode0642AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }

    private String cur_sent = "";

    public List<String> input(char c) {
        List < String > res = new ArrayList < > ();
        if (c == '#') {
            insert(root, cur_sent, 1);
            cur_sent = "";
        } else {
            cur_sent += c;
            List < Node > list = lookup(root, cur_sent);
            Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++)
                res.add(list.get(i).sentence);
        }
        return res;
    }

}
