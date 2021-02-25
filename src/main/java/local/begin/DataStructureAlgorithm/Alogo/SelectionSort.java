package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Struct.ArrayGenerator;
import local.begin.DataStructureAlgorithm.Test.SortingHelper;

import java.util.Arrays;

public class SelectionSort {

    private SelectionSort(){}

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        // 维持的循环不变量
        // arr[0...i） 是有序的， arr[i...n] 是无序的
        for (int i = 0; i < arr.length - 1; i++){
            int minInedx = i;
            for(int j = i; j < arr.length; j++){
                if (arr[j].compareTo(arr[minInedx]) < 0) {
                    minInedx = j;
                }
            }
            swap(arr, i, minInedx);
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr) {

        for(int i = arr.length - 1; i > 0; i--){
            // 选取arr中最大值的索引
            int maxIndex = i;
            for(int j = i; j >= 0; j--){
                if(arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);

            SortingHelper.sortTest("SelectionSort2", arr);
            SortingHelper.sortTest("SelectionSort", arr2);
        }
    }


}
