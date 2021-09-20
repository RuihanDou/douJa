package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class BubbleSortOpt1 {

    private BubbleSortOpt1(){}

    public static String getName(){
        return "Bubble Sort Opt1";
    }

    public static <E extends Comparable<E>> void sort(E[] data){

        for(int i = 0; i + 1 < data.length; i++){
            // arr[n -i , n) 已排序好
            // 通过冒泡在arr[n - i - 1] 位置上放上合适的元素

            boolean isSwapped = false;
            for(int j = 0; j + 1<= data.length - i - 1;  j++){
                // j, j + 1
                if(data[j].compareTo(data[j + 1]) > 0){
                    swap(data, j, j+1);
                    isSwapped = true;
                }
            }
            if(!isSwapped){
                break;
            }
        }

    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("Bubble Sort", arr);
        SortingHelper.sortTest("Bubble Sort Opt1", arr2);

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("Bubble Sort", arr);
        SortingHelper.sortTest("Bubble Sort Opt1", arr2);
    }
}
