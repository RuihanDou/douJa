package local.begin.leetCode;


/**
 * 917. 仅仅反转字母
 * 给你一个字符串 s ，根据下述规则反转字符串：
 *
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 *
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 *
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *
 *
 * 提示
 *
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 */
public class LeetCode0917Solution {

    public String reverseOnlyLetters(String s) {
        char[] sArr = s.toCharArray();
        int l = 0, r = sArr.length - 1;
        while (l < r){
            while (l < r && !Character.isLetter(sArr[l])){
                l++;
            }
            while (r > l && !Character.isLetter(sArr[r])){
                r--;
            }
            if(l >= r){
                break;
            }
            swap(sArr, l, r);
            l++;
            r--;
        }
        return new String(sArr);
    }

    private void swap(char[] arr, int l, int r) {
        char temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args) {
        String s = "ab-cd";
        LeetCode0917Solution solution = new LeetCode0917Solution();
        System.out.println(solution.reverseOnlyLetters(s));
    }

}
