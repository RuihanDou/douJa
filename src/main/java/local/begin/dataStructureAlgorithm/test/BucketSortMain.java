package local.begin.dataStructureAlgorithm.test;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class BucketSortMain {

    public static void main(String[] args) {

//        int n = 10000;
        int n = 10000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("Quick Sort 2 Ways", arr);
        SortingHelper.sortTest("Quick Sort 3 Ways", arr2);
        SortingHelper.sortTest("Merge Sort", arr3);
        SortingHelper.sortTest("Bucket Sort", arr4);
        SortingHelper.sortTest("Bucket Sort 2", arr5);

    }

}
