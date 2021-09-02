package local.begin.interview;

import local.begin.tools.DebugTools;

import java.util.Arrays;


/**
 * Microsoft onset 1
 *
 * 在你的面前有一列硬币，不等值也不按照价值排列，你只能每次从最左边或最右边取一枚，一共只能取硬币数量的一半
 *
 * 硬币如下列表所示 [3, 6, 2, 6, 8, 1, 3, 5]
 *
 * 可以从右边取[8, 1, 3, 5] 获取最大价值17
 * 也可以从左边取[3, 6, 2, 6] 获取最大价值17
 * 同样可以从左端取[3, 6] 从右端取[5, 3]获取最大价值同样为17
 *
 *
 * 注意有如下情况 当硬币序列为[1, 1, 100, 8, 9, 10]时
 * 贪心算法只考察最外沿硬币的方式会失效
 *
 *
 * 进阶：使用算法复杂度O(n)的方法解决，尽量不要占用新的内存空间
 *
 */
public class Solution {

    private Solution(){}

    /**
     * 使用滑动窗口， 确保窗口内的硬币价值最小
     * @param input
     * @return
     */
    public static int maxValue(int[] input){

        int all = Arrays.stream(input).sum();

        int windowSize = input.length / 2;

        int sumInWindow = 0;

        int minInWindow = Integer.MAX_VALUE;

        for(int i = 0; i < input.length; i++){

            if(i < windowSize){
                sumInWindow += input[i];
            } else {
                sumInWindow = sumInWindow - input[i - windowSize] + input[i];
                minInWindow = Math.min(minInWindow, sumInWindow);
            }
        }

        return all - minInWindow;

    }

    // 本题以 LeetCode 53 最大子序列和为基础
    // 相似题目有  LeetCode 41 缺失的第一个正数，
    //          LeetCode 42 接雨水，
    //          LeetCode 01 两数之和，
    //          LeetCode 287 寻找重复数，
    //          LeetCode 121 买卖股票的最佳时机
    // 本题和以上题目都有共同的特点，使用O(n)时间复杂度就可以解决

    public static void main(String[] args) {
        int[] coinsList = new int[]{3,6,2,6,8,1,3,5};
//        int[] coinsList = new int[]{1,1,100,8,9,10};
        int rst = Solution.maxValue(coinsList);
        DebugTools.print(rst);
    }

}
