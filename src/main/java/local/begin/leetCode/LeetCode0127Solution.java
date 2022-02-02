package local.begin.leetCode;


import com.google.common.collect.Lists;

import java.util.*;

/**
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 *
 *
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 *
 * 提示：
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 */
public class LeetCode0127Solution {

    private Map<String, Integer> wordId = new HashMap<>();
    private List<List<Integer>> edges = new ArrayList<>();
    private int nodeNum = 0;

    private void addEdge(String word){
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for(int i = 0; i < length; i++){
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edges.get(id1).add(id2);
            edges.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    private void addWord(String word){
        if(!wordId.containsKey(word)){
            wordId.put(word, nodeNum++);
            edges.add(new ArrayList<>());
        }
    }
//    // 构建图 广度优先搜索
//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        for (String word : wordList) {
//            addEdge(word);
//        }
//        addEdge(beginWord);
//
//        if(!wordId.containsKey(endWord)){
//            return 0;
//        }
//        int[] dis = new int[nodeNum];
//        Arrays.fill(dis, Integer.MAX_VALUE);
//
//        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
//        dis[beginId] = 0;
//
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(beginId);
//        while (!queue.isEmpty()){
//            int x = queue.poll();
//            if(x == endId){
//                return dis[endId] / 2 + 1;
//            }
//            for(int it : edges.get(x)){
//                if(dis[it] == Integer.MAX_VALUE){
//                    dis[it] = dis[x] + 1;
//                    queue.offer(it);
//                }
//            }
//        }
//        return 0;
//    }

    // 双向搜索
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edges.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edges.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        List<String> wordList = Lists.newArrayList("hot","dot","dog","lot","log","cog");
        String begin = "hit", end = "cog";
        LeetCode0127Solution solution = new LeetCode0127Solution();
        int rst = solution.ladderLength(begin, end, wordList);
    }

}
