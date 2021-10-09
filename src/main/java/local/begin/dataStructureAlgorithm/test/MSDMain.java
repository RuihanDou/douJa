package local.begin.dataStructureAlgorithm.test;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class MSDMain {

    public static void main(String[] args) {
        //        int n = 100000, w = 200;
        int n = 10000000, w = 20;
        String[] arr = ArrayGenerator.generateRandomStringArray(n, w);
        String[] arr2 = Arrays.copyOf(arr, arr.length);
        String[] arr3 = Arrays.copyOf(arr, arr.length);
//  "Quick Sort 1 Way" 会溢出

        SortingHelper.sortTest("Quick Sort 2 Ways", arr);
        SortingHelper.sortTest("Quick Sort 3 Ways", arr3);
        SortingHelper.sortTest("MSD Sort", arr2);
    }

}
