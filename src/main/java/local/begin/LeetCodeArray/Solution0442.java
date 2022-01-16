package local.begin.LeetCodeArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 422. 有效的单词方块
 * 给你一个单词序列，判断其是否形成了一个有效的单词方块。
 *
 * 有效的单词方块是指此由单词序列组成的文字方块的 第 k 行 和 第 k 列 (0 ≤ k < max(行数, 列数)) 所显示的字符串完全相同。
 *
 * 注意：
 *
 * 给定的单词数大于等于 1 且不超过 500。
 * 单词长度大于等于 1 且不超过 500。
 * 每个单词只包含小写英文字母 a-z。
 *
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   "abcd",
 *   "bnrt",
 *   "crmy",
 *   "dtye"
 * ]
 *
 * 输出：
 * true
 *
 * 解释：
 * 第 1 行和第 1 列都是 "abcd"。
 * 第 2 行和第 2 列都是 "bnrt"。
 * 第 3 行和第 3 列都是 "crmy"。
 * 第 4 行和第 4 列都是 "dtye"。
 *
 * 因此，这是一个有效的单词方块。
 *
 *
 * 示例 2：
 *
 * 输入：
 * [
 *   "abcd",
 *   "bnrt",
 *   "crm",
 *   "dt"
 * ]
 *
 * 输出：
 * true
 *
 * 解释：
 * 第 1 行和第 1 列都是 "abcd"。
 * 第 2 行和第 2 列都是 "bnrt"。
 * 第 3 行和第 3 列都是 "crm"。
 * 第 4 行和第 4 列都是 "dt"。
 *
 * 因此，这是一个有效的单词方块。
 *
 *
 * 示例 3：
 *
 * 输入：
 * [
 *   "ball",
 *   "area",
 *   "read",
 *   "lady"
 * ]
 *
 * 输出：
 * false
 *
 * 解释：
 * 第 3 行是 "read" ，然而第 3 列是 "lead"。
 *
 * 因此，这 不是 一个有效的单词方块。
 */
public class Solution0442 {

//    // 直接比较
//    public boolean validWordSquare(List<String> words) {
//        int n = words.size();
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < words.get(i).length(); j++){
//                // 字符串长度超过矩阵行数，返回false
//                if(words.get(j).length() > n){
//                    return false;
//                }
//                // 如果word.get(j).charAt(i)不存在，返回false
//                if(words.get(j).length() - 1 < i){
//                    return false;
//                }
//                // 判断对角线上的位置是否相同
//                if(words.get(i).charAt(j) != words.get(j).charAt(i)){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    // 横向单词跟纵向单词比较
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        Set<String> seen = new HashSet<>();
        for(int i = 0; i < n; i++) {
            String str = words.get(i);
            int len = str.length();
            seen.add(str);

            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < n && j < len; j++) {
                if(i < words.get(j).length()) {
                    builder.append(words.get(j).charAt(i));
                } else {
                    return false;
                }
            }
            if(!seen.contains(builder.toString())) {
                return false;
            }
        }
        return true;
    }

}
