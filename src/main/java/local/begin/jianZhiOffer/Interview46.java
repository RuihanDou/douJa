package local.begin.jianZhiOffer;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 *
 * 提示：
 *
 * 0 <= num < 231
 * 通过次数128,463提交次数240,859
 */
public class Interview46 {

//    public int translateNum(int num) {
//
//        String s = String.valueOf(num);
//        int len = s.length();
//        if(len < 2){
//            return len;
//        }
//
//        char[] charArray = s.toCharArray();
//        //dp[i] : s[0:i) 能翻译的种类数
//        int[] dp = new int[len + 1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for (int i = 1; i < len; i++){
//            dp[i + 1] = dp[i];
//            int currentNum = 10 * (charArray[i-1] - '0') + (charArray[i] - '0');
//            if(currentNum > 9 && currentNum < 26) {
//                dp[i + 1] = dp[i] + dp[i - 1];
//            }
//        }
//        return dp[len];
//    }

    public int translateNum(int num){
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for(int i = 0; i < src.length(); i++){
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0){
                continue;
            }
            // src[i-1, i+1) == src[i-1, i]
            String pre = src.substring(i - 1, i + 1);
            if(pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0){
                r += p;
            }
        }
        return r;
    }



}
