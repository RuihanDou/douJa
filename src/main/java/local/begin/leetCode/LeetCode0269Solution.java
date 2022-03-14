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

    boolean error=false;
    public String alienOrder(String[] words) {
        //bfs实现
        int n=26;
        int[] arr = new int[n];
        boolean[] visited = new boolean[n];
        boolean[] used = new boolean[n];
        Deque<Integer> deque = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        List<Character> res = new ArrayList<>();
        for(int i = 0; i < n ; i++){
            list.add(new ArrayList<>());
        }
        //这种写法要求至少有两个字符串
        //现在先特殊处理只有一个字符串的情况
        get("", words[0], visited);

        for(int i=0,j=1;j<words.length;i++,j++){
            char[]cs=get(words[i],words[j],visited);
            if(error) return "";
            if(cs==null) continue;
            int x=cs[0]-'a';
            int y=cs[1]-'a';
            //x 先于 y
            arr[y]++;
            list.get(x).add(y);
        }
        //一共有多少个字母
        int count=0;
        for(boolean b:visited){
            if(b) count++;
        }
        //deque初始化

        for(int i=0;i<n;i++){
            if(visited[i]&&arr[i]==0) deque.offer(i);
        }
        while (!deque.isEmpty()){
            int index=deque.pop();
            used[index]=true;
            res.add((char)(index+'a'));
            for(int x:list.get(index)){
                arr[x]--;
                if(arr[x]==0){
                    deque.offer(x);
                }
            }
        }
        StringBuilder builder=new StringBuilder();
        if(res.size()!=count) return  "";
        for(int i=0;i<n;i++){
            if(visited[i]&&!used[i]) builder.append((char)(i+'a'));
        }
        for(char c:res) builder.append(c);
        return  builder.toString();
    }
    //假设 s1 先于 s2
    public char[] get(String s1, String s2, boolean[]visited){
        for(int i = 0; i < s1.length(); i++){
            visited[s1.charAt(i) - 'a']=true;
        }
        for(int i = 0; i < s2.length(); i++){
            visited[s2.charAt(i) - 'a']=true;
        }
        if(s1.equals(s2)){
            //检测null
            return null;
        }
        int n = Math.min(s1.length(), s2.length());
        for(int i = 0; i < n; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                char x=s1.charAt(i);
                char y=s2.charAt(i);
                return new char[]{x,y};
            }
        }
        if(s1.length() > s2.length()){
            error=true;
        }
        //如果一个字符串比较结束了还没有结果 return null
        return  null;
    }

}
