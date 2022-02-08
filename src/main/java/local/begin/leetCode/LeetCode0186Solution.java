package local.begin.leetCode;


/**
 * 186. 翻转字符串里的单词 II
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例：
 *
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 注意：
 *
 * 单词的定义是不包含空格的一系列字符
 * 输入字符串中不会包含前置或尾随的空格
 * 单词与单词之间永远是以单个空格隔开的
 * 进阶：使用 O(1) 额外空间复杂度的原地解法。
 */
public class LeetCode0186Solution {

    public void reverseWords(char[] s) {
        int l = 0, len = s.length;
        for(int r = 1; r < len; r++){
            if(s[r] == ' '){
                reverse(s, l, r - 1);
                l = r + 1;
            }
            if(r == len - 1){
                reverse(s, l, r);
            }
        }
        reverse(s, 0, len - 1);
    }

    // 翻转 数组[l, r]
    private void reverse(char[] s, int l, int r) {

        while (l < r){
            swap(s, l, r);
            l++;
            r--;
        }

    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }


}
