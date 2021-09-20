package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class BubbleSortOpt2 {

    private BubbleSortOpt2(){}

    public static String getName(){
        return "Bubble Sort Opt2";
    }

    public static <E extends Comparable<E>> void sort(E[] data){

        for(int i = 0; i + 1 < data.length; ){
            // arr[n -i , n) 已排序好
            // 通过冒泡在arr[n - i - 1] 位置上放上合适的元素

            int lastSwappedIndex = 0;
            for(int j = 0; j + 1<= data.length - i - 1;  j++){
                // j, j + 1
                if(data[j].compareTo(data[j + 1]) > 0){
                    swap(data, j, j+1);
                    lastSwappedIndex = j+1;
                }
            }
//            if(lastSwappedIndex == 0){
//                break;
//            }
            i = data.length - lastSwappedIndex;
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
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("Bubble Sort", arr);
        SortingHelper.sortTest("Bubble Sort Opt1", arr2);
        SortingHelper.sortTest("Bubble Sort Opt2", arr3);

        System.out.println();

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("Bubble Sort", arr);
        SortingHelper.sortTest("Bubble Sort Opt1", arr2);
        SortingHelper.sortTest("Bubble Sort Opt2", arr3);
    }

}
