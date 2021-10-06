package local.begin.dataStructureAlgorithm.test;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class LSDMain {

    public static void main(String[] args) {

//        int n = 100000, w = 200;
        int n = 1000000, w = 2;
        String[] arr = ArrayGenerator.generateRandomStringArray(n, w);
        String[] arr2 = Arrays.copyOf(arr, arr.length);
        String[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("Quick Sort 1 Way", arr);
        SortingHelper.sortTest("LSD Sort", arr2);
        SortingHelper.sortTest("Quick Sort 3 Ways", arr3);


    }

}
