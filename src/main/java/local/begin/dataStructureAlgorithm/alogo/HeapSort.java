package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class HeapSort  {

    private HeapSort(){}

    public static String getName(){
        return "Heap Sort";
    }

    public static <E extends Comparable<E>> void sort(E[] data){

        if(data.length <= 1) {
            return;
        }

        for(int i = (data.length - 2) / 2; i >= 0; i--){
            siftDown(data, i, data.length);
        }

        for(int i = data.length - 1; i >= 0; i--){
            swap(data, 0, i);
            siftDown(data, 0, i);
        }

    }

    private static <E extends Comparable<E>> void siftDown(E[] data, int k, int n){

        while (2 * k + 1 < n){
            int j = 2 * k + 1 ;
            if(j + 1 < n && data[j + 1].compareTo(data[j]) > 0){
                j++;
            }
            // data[j] 是leftChild 和 rightChild 中的最大值
            if(data[k].compareTo(data[j]) >= 0){
                break;
            }
            swap(data, k, j);
            k = j;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("Merge Sort", arr);
        SortingHelper.sortTest("Quick Sort 2 Ways", arr2);
        SortingHelper.sortTest("Quick Sort 3 Ways", arr3);
        SortingHelper.sortTest("Heap Sort Simple", arr4);
        SortingHelper.sortTest("Heap Sort", arr5);
    }
}
