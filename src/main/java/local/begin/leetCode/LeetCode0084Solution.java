package local.begin.leetCode;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 *
 *
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 *
 * 提示：
 *
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class LeetCode0084Solution {

//    // 暴力解决
//    public int largestRectangleArea(int[] heights) {
//        int length = heights.length;
//        int area = 0;
//        // 遍历height，求以该位置为height的矩形面积
//        for(int i = 0; i < length; i++) {
//           int curH = heights[i], width = 1;
//           // 向左看
//           for(int j = i - 1; j >= 0; j--){
//               if(heights[j] >= curH){
//                   width++;
//               }else {
//                   break;
//               }
//           }
//           // 向右看
//           for(int j = i + 1; j < length; j++){
//               if(heights[j] >= curH){
//                   width++;
//               }else {
//                   break;
//               }
//           }
//           area = Math.max(area, width * curH);
//        }
//        return area;
//    }

    public int largestRectangleArea(int[] heights) {

        // 处理边界问题，并给height两端添加哨兵
        int len = heights.length;
        if(len == 1){
            return heights[0];
        }
        int[] newHeights = new int[len + 2];
        for(int i = 0; i < len; i++){
            newHeights[i + 1] = heights[i];
        }
        len += 2;
        heights = newHeights;

        // 栈中存放无法确定长度的h的位置
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int area = 0;
        for(int i = 1; i < len; i++){
            while (heights[i] < heights[stack.peek()]){
                int height = heights[stack.pop()];
                while (heights[stack.peek()] == height){
                    stack.pop();
                }
                int width = i - stack.peek() - 1;
                area = Math.max(width * height, area);
            }
            stack.push(i);
        }
        return area;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,5,2,3};
        LeetCode0084Solution solution = new LeetCode0084Solution();

        System.out.println(solution.largestRectangleArea(heights));
    }

}
