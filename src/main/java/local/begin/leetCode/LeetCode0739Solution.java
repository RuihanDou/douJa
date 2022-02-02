package local.begin.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode0739Solution {


    // 使用单调栈解决
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < len; i++){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return res;
    }

}
