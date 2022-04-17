package local.begin.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 *
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 示例 2：
 *
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 105
 * s[i] 为 'A'、'C'、'G' 或 'T'
 */
public class LeetCode0187Solution {

    /**
     * 暴力方式解决
     *
     * @param s
     * @return
     */
//    public List<String> findRepeatedDnaSequences(String s) {
//
//        HashSet<String> seen = new HashSet<>();
//        HashSet<String> res = new HashSet<>();
//        for(int i = 0; i + 10 <= s.length(); i++){
//            String key = s.substring(i, i + 10); // substring 里 为 开区间
//            if(seen.contains(key)){
//                res.add(key);
//            }
//            else {
//                seen.add(key);
//            }
//        }
//        return new ArrayList<String>(res);
//
//    }

    /**
     * 使用滚动哈希解决
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s){

        if(s.length() < 10){
            return new ArrayList<>();
        }

        int[] map = new int[256];
        map['A'] = 1;
        map['C'] = 2;
        map['G'] = 3;
        map['T'] = 4;

        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        // hash 用来编码 看过的字符串 ; ten9 为了计算观察窗口右移后 hash移除左边字符的结果
        long hash = 0, ten9 = (long) 1e9;

        for(int i = 0; i < 9; i++){
            hash = hash * 10 + map[s.charAt(i)];
        }

        for(int i = 9; i < s.length(); i++){

            hash = hash * 10 + map[s.charAt(i)];

            if(seen.contains(hash)){
                // s[i - 9, i + 1)
                res.add(s.substring(i - 9, i + 1));
            }
            else {
                seen.add(hash);
            }

            hash -= map[s.charAt(i - 9)] * ten9;
        }

        return new ArrayList<>(res);

    }

}
