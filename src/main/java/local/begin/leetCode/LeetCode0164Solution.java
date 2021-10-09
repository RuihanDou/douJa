package local.begin.leetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class LeetCode0164Solution {

    public int maximumGap(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        sort(nums, 1000);
        int maxv = Integer.MIN_VALUE;
        for(int i = 1; i < nums.length; i++){
            maxv = Math.max(maxv, nums[i] - nums[i - 1]);
        }
        return maxv;
    }

    private static void sort(int[] arr, int c){

        if(c <= 0)
            throw new IllegalArgumentException("c must be > 0");

        int maxv = Integer.MIN_VALUE, minv = Integer.MAX_VALUE;
        for(int e: arr){
            maxv = Math.max(maxv, e);
            minv = Math.min(minv, e);
        }

        int range = maxv - minv + 1; // arr 中的数据范围
        int B = range / c + (range % c > 0 ? 1 : 0); // 根据数据范围决定桶的个数

        LinkedList<Integer>[] buckets = new LinkedList[B];
        for(int i = 0; i < B; i ++){
            buckets[i] = new LinkedList<>();
        }

        for(int e: arr){
            buckets[(e - minv) / range].add(e);
        }

        for(int i = 0; i < B; i ++){
            Collections.sort(buckets[i]);
        }

        int index = 0;
        for(int i = 0; i < B; i ++){
            for(int e: buckets[i]){
                arr[index ++] = e;
            }
        }
    }
}
