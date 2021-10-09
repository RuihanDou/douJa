package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort2 {

    private BucketSort2(){}

    public static String getName(){
        return "Bucket Sort 2";
    }

    public static void sort(Integer[] arr, int c){

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

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("Bucket Sort", arr);
        SortingHelper.sortTest("Bucket Sort 2", arr2);
    }

}
