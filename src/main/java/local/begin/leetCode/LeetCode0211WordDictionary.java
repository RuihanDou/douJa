package local.begin.leetCode;


import java.util.TreeMap;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 *
 * 示例：
 *
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 50000 次 addWord 和 search
 */
public class LeetCode0211WordDictionary {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next = new TreeMap<>();

        public Node(boolean isWord){
            this.isWord = isWord;
        }

        public Node(){
            this.isWord = false;
        }
    }

    private Node root;

    public LeetCode0211WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index){
        if(index == word.length()){
            return node.isWord;
        }

        char c = word.charAt(index);
        if(c != '.'){
            if(node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char nextChar : node.next.keySet()){
                if(match(node.next.get(nextChar), word, index + 1)){
                    return true;
                }
            }
            return false;
        }
    }

}
