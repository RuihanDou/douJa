package local.begin.leetCode;


import java.util.*;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 */
public class LeetCode0030Solution {

//    public List<Integer> findSubstring(String s, String[] words) {
//        int sLen = s.length(), wordLen = words[0].length(), wordsNum = words.length;
//        Map<String, Integer> wordMap = new HashMap<>();
//        for(String word : words){
//            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
//        }
//        List<Integer> rst = new ArrayList<>();
//        int i = 0;
//        while (i < sLen){
//            if(i + wordLen <= sLen && wordMap.containsKey(s.substring(i, i + wordLen))){
//                int start = i;
//                HashMap<String, Integer> temp = new HashMap<>(wordMap);
//                while (i + wordLen <= sLen && temp.containsKey(s.substring(i, i + wordLen))){
//                    int num = temp.get(s.substring(i, i + wordLen)) - 1;
//                    if(num == 0){
//                        temp.remove(s.substring(i, i + wordLen));
//                    }
//                    else {
//                        temp.put(s.substring(i, i + wordLen), num);
//                    }
//                    i += wordLen;
//                }
//                if(temp.isEmpty()){
//                    rst.add(start);
//                }
//                for(int j = 1; j <= sLen && start + j + wordLen <= sLen; j++){
//                    i = start + j;
//                    if(wordMap.containsKey(s.substring(start + j, start + j + wordLen))){
//                        break;
//                    }
//                }
//            }
//            else {
//                i++;
//            }
//        }
//        return rst;
//    }


    public List<Integer> findSubstring(String s, String[] words){
        int left, right, len;
        // 所有单词数量
        int size = words.length;

        List<Integer> res = new ArrayList<>();

        //如果目标数组为空，返回一个空集合
        if(size == 0){
            return res;
        }
        else {
            // len 是单词的长度
            len = words[0].length();
        }

        // 定义两个map集合，一个存目标单词，一个存滑动窗口
        Map<String, Integer> needs = new HashMap<>();
        Map<String, Integer> windows = new HashMap<>();

        // 初始化目标集合，讲单词和次数存入map集合中
        for(String word : words){
            needs.put(word, needs.getOrDefault(word, 0) + 1);
        }

        // 单词匹配数量
        int match;

        for(int i = 0; i < len; i++){
            // 初始化左右指针都为i，match初始化为0
            left = right = i;
            match = 0;
            // 右指针最多到字符串的最后一个单词开始位置
            while(right <= s.length() - len){

                // 向右滑动，存入单词和出现的次数
                String s1 = s.substring(right, right + len);
                // 以单词长度为步长移动右指针
                right += len;
                windows.put(s1, windows.getOrDefault(s1, 0) + 1);

                // 如果单词和出现的次数与目标一致，则匹配+1
                if (needs.containsKey(s1) && windows.get(s1).intValue() == needs.get(s1).intValue()){
                    match ++;
                }

                // 当匹配数等于目标集合的大小（说明已经覆盖了目标集合）
                while (left < right && match == needs.size()) {

                    // right - left / len求出窗口中单词数，如果等于目标单词数，则匹配成功，将左指针位置加入list
                    if ((right - left) / len == size) {
                        res.add(left);
                    }

                    // 左指针右移，类似右指针方法
                    String s2 = s.substring(left, left + len);
                    left += len;
                    windows.put(s2, windows.get(s2) - 1);

                    if (needs.containsKey(s2) && windows.get(s2).intValue() < needs.get(s2).intValue()){
                        match--;
                    }
                }
            }
            // 清空窗口，进行下一次遍历
            windows.clear();
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode0030Solution solution = new LeetCode0030Solution();
        String[] words = {"aa","aa"};
        System.out.println(solution.findSubstring("aaaaaaaaaaaaaa", words));
    }

}
