package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class ShellSortOpt2 {

    private ShellSortOpt2(){}

    public static String getName(){
        return "Shell Sort Opt2";
    }

    public static <E extends Comparable<E>> void sort(E[] data){

        int h = 1;
        while (h < data.length) {
            // 1, 4, 13, 40,
            h = h * 3 + 1;
        }

        while (h >= 1){

            for(int i = h; i < data.length; i++){
                E t = data[i];
                int j;
                for(j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h){
                    data[j] = data[j - h];
                }
                data[j] = t;
            }

            h /= 3;
        }

    }

    public static void main(String[] args) {
        int n = 5000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("Shell Sort", arr);
        SortingHelper.sortTest("Shell Sort Opt1", arr2);
        SortingHelper.sortTest("Shell Sort Opt2", arr3);
    }

}
