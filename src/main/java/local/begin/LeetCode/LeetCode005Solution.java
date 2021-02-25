package local.begin.LeetCode;

import java.util.Arrays;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LeetCode005Solution {

    private static String manacherString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }

    private static boolean isPalindrometic(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        return reverse.equals(s);
    }
    //暴力求解  会超时
    public static String longestPalindromeV0(String s) {
        int n = s.length();
        if (null == s || n < 1) {
            return "";
        }
        String longest = "";
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n + 1; r++) {
                String sub = s.substring(l, r);
                if (isPalindrometic(sub) && longest.length() < r - l) {
                    longest = sub;
                }
            }
        }

        return longest;
    }

    // 使用动态规划思想
    public static String longestPalindromeV1(String s) {
        int n = s.length();
        if (null == s || n < 1) {
            return "";
        }
        String longest = "";
        boolean[][] tableLR = new boolean[n+1][n+1];
        for (int r = 0; r <= n; r++) {
            for (int l = 0; l <= r; l++) {
                String sub = s.substring(l, r);
                System.out.println(l);
                System.out.println(r);
                System.out.println(sub);
                if (r - l <= 1) {
                    tableLR[l][r] = true;
                    if(longest.length() < r - l) {
                        longest = sub;
                    }
                } else if (s.charAt(l) == s.charAt(r-1) && tableLR[l+1][r-1]) {
                    tableLR[l][r] = true;
                    if(longest.length() < r - l) {
                        longest = sub;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(tableLR));
        return longest;
    }

    // Manacher算法
    public static String longestPalindromeV2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        String charArr = manacherString(s);
        int[] radius = new int[charArr.length()];
        int R = -1;
        int c = -1;
        int max = -1;
        String longest = "";

        for (int i = 0; i < radius.length; i++) {
            radius[i] = R > i ? Math.min(radius[2*c-i], R-i+1) : 1;
            while(i+radius[i] < charArr.length() && i - radius[i] > -1){
                if (charArr.charAt(i-radius[i]) == charArr.charAt(i+radius[i])) {
                    radius[i]++;
                }else{
                    break;
                }
            }
            if(i + radius[i] > R) {
                R = i + radius[i] - 1;
                c = i;
            }
            if (radius[i] > max) {
                max = radius[i];
                int r = radius[i] - 1;
                longest = charArr.substring(i - r, i + r + 1);
            }
        }
        return longest.replaceAll("#", "");
    }

    public static void main(String[] args) {
        String rst = LeetCode005Solution.longestPalindromeV2("ccc");

        System.out.println(rst);
    }

}
