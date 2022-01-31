package local.begin.leetCode;


/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */

// TODO  理解过程
public class LeetCode0567Solution {

//    public boolean checkInclusion(String s1, String s2) {
//
//        int len1 = s1.length(), len2 = s2.length();
//        if(len1 > len2){
//            return false;
//        }
//        int[] cnt = new int[26];
//        for(int i = 0; i < len1; i++){
//            cnt[s1.charAt(i) - 'a']--;
//            cnt[s2.charAt(i) - 'a']++;
//        }
//        // diff 记录 s2 的子串 和 s1 相比不同字符的个数
//        int diff = 0;
//        for(int c : cnt){
//            if(c != 0){
//                diff++;
//            }
//        }
//        if(diff == 0){
//            return true;
//        }
//        for(int i = len1; i < len2; i++){
//            int x = s2.charAt(i) - 'a', y = s2.charAt(i - len1) - 'a';
//            if(x == y){
//                continue;
//            }
//            //
//            if(cnt[x] == 0){
//                diff++;
//            }
//            cnt[x]++;
//            if(cnt[x] == 0){
//                diff--;
//            }
//            if(cnt[y] == 0){
//                diff++;
//            }
//            cnt[y]--;
//            if(cnt[y] == 0){
//                diff--;
//            }
//            if(diff == 0){
//                return true;
//            }
//        }
//        return false;
//
//    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            cnt[s1.charAt(i) - 'a']--;
        }
        int left = 0;
        for (int right = 0; right < m; right++) {
            int x = s2.charAt(right) - 'a';
            cnt[x]++;
            while (cnt[x] > 0) {
                cnt[s2.charAt(left) - 'a']--;
                left++;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }


}
