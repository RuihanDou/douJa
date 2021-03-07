package local.begin.LeetCode;


import local.begin.tools.DebugTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 *
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 *
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 */

public class LeetCod1239Solution {

    int maxLen = 0;

    public int maxLength(List<String> arr) {
        dfs(arr, 0, 0, 0);
        return maxLen;
    }

    /**
     * 定义深度优先搜索
     * @param arr
     * @param index 遍历到列表的位置
     * @param ck
     * @param tempMax
     */
    private void dfs(List<String> arr, int index, int ck, int tempMax) {

        if (index == arr.size()) {
            maxLen = Math.max(maxLen, tempMax);
            return;
        }

        String str = arr.get(index);

        int tempCk = getCk(ck, str);
        if (tempCk != -1){
            dfs(arr, index + 1, tempCk, tempMax + str.length());
        }
        dfs(arr, index + 1, ck, tempMax);
    }

    // 比较str和之前ck代表的字符串是否有相同字符，如果没有，则把str代表的ck加去，如果有，则返回-1
    private int getCk(int ck, String str) {

        char[] chars = str.toCharArray();
        for(char c: chars) {
            int tempInt = c - 'a';
            if ((ck & (1 << tempInt)) != 0) {
                return -1;
            }
            ck = ck | (1 << tempInt);
        }
        return ck;
    }


    public static void main(String[] args) {
        LeetCod1239Solution solution = new LeetCod1239Solution();

        List<String> arr = Arrays.asList(new String[]{"a", "abc", "d", "de", "def"});

        DebugTools.print(arr);

        DebugTools.print(solution.maxLength(arr));

    }

}
