package local.begin.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode0046Solution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        for(int num : nums){
            output.add(num);
        }

        int n = nums.length;
        backTrace(n, output, res, 0);

        return res;
    }

    private void backTrace(int n, List<Integer> output, List<List<Integer>> res, int first) {

        // 结束情况，output填满了
        if(first == n){
            res.add(new ArrayList<>(output));
        }

        for(int i = first; i < n; i++){
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续填下一个数
            backTrace(n, output, res, first + 1);
            // 撤销上一个操作
            Collections.swap(output, first, i);
        }
    }

}
