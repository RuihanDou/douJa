package local.begin.leetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 */

public class LeetCode0042Solution {

//    public int trap(int[] height) {
//
//        int[] leftMaxArr = new int[height.length];
//
//        int leftMax = 0;
//        for(int i = 0; i < height.length; i++){
//
//            leftMax = leftMax > height[i] ? leftMax : height[i];
//            leftMaxArr[i] = leftMax;
//        }
//        int[] rightMaxArr = new int[height.length];
//
//        int rightMax = 0;
//        for(int i = height.length - 1; i > 0 ; i--){
//            rightMax = rightMax > height[i] ? rightMax : height[i];
//            rightMaxArr[i] = rightMax;
//        }
//
//        int rst = 0;
//
//        for(int i = 0; i < height.length; i++){
//
//            int edge = Math.min(leftMaxArr[i], rightMaxArr[i]);
//            if(edge > height[i]){
//                rst += (edge - height[i]);
//            }
//        }
//
//        return rst;
//    }

    public int trap(int[] height) {
        Deque<Integer> deque = new LinkedList<>();
        int i = 0, ans = 0, len = height.length;
        while (i < len){
            while (!deque.isEmpty() && height[i] > height[deque.peekLast()]){
                int top = deque.pollLast();
                if(deque.isEmpty()){
                    break;
                }
                int distance = i - deque.peekLast() - 1;
                int boundedHeight = Math.min(height[i], height[deque.peekLast()]) - height[top];
                ans += distance * boundedHeight;
            }
            deque.offerLast(i++);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] h = {0,1,0,2,1,0,1,3,2,1,2,1};
        LeetCode0042Solution solution = new LeetCode0042Solution();
        System.out.println(solution.trap(h));
    }
}
