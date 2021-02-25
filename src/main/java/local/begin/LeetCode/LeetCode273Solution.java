package local.begin.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 *273. 整数转换英文表示
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 231 - 1 。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: "One Hundred Twenty Three"
 * 示例 2:
 *
 * 输入: 12345
 * 输出: "Twelve Thousand Three Hundred Forty Five"
 * 示例 3:
 *
 * 输入: 1234567
 * 输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4:
 *
 * 输入: 1234567891
 * 输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class LeetCode273Solution {
    private static String[] singles = {"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static String hundred = "Hundred";
    private static String thousand = "Thousand";
    private static String million = "Million";
    private static String billion = "Billion";
    private static String zero = "Zero";

    public String numberToWords(int num) {
        if(num > Integer.MAX_VALUE || num < 0) {
            throw new IllegalArgumentException("illegal integer");
        }

        List<String> listString = new ArrayList<>();

        int billionNum = num / 1000000000;
        if(billionNum > 0) {
            listString = simpleNumberToWords(billionNum, listString);
            listString.add(billion);
        }
        int millionNum = num % 1000000000 / 1000000;
        if(millionNum > 0) {
            listString = simpleNumberToWords(millionNum, listString);
            listString.add(million);
        }
        int thousandNum = num % 1000000 / 1000;
        if(thousandNum > 0) {
            listString = simpleNumberToWords(thousandNum, listString);
            listString.add(thousand);
        }
        int singleNum = num % 1000;
        if(singleNum > 0) {
            listString = simpleNumberToWords(singleNum, listString);
        } else if(listString.isEmpty() && singleNum == 0) {
            listString.add(zero);
        }

        StringBuilder rst = new StringBuilder();
        int length = listString.size();
        for (int i = 0; i < length; i++) {
            if (i < length -1) {
                rst.append(listString.get(i));
                rst.append(" ");
            } else {
                rst.append(listString.get(i));
            }
        }

        return rst.toString();
    }

    private static List<String> simpleNumberToWords(int num, List<String> listSring) {
        if(num > 999 || num < 1) {
            throw new IllegalArgumentException("illegal integer");
        }
        int hundredNum = num / 100;
        if(hundredNum > 0){
            listSring.add(singles[hundredNum]);
            listSring.add(hundred);
        }

        int numSmall = num % 100;
        if(numSmall > 0){
            if(numSmall < 20) {
                listSring.add(singles[numSmall]);
            } else {
                listSring.add(tens[numSmall / 10]);
                int single = numSmall % 10;
                if (single > 0) {
                    listSring.add(singles[numSmall % 10]);
                }
            }
        }

        return listSring;
    }

    public static void main(String[] args) {
        LeetCode273Solution solution = new LeetCode273Solution();
        System.out.println(solution.numberToWords(20));
    }

}
