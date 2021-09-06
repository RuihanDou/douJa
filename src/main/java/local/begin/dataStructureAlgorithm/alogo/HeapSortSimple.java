package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.dataStruct.MaxHeap;
import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

import java.util.Arrays;

public class HeapSortSimple {

    private HeapSortSimple(){}

    public static String getName(){
        return "Heap Sort Simple";
    }

    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for(E e : data){
            maxHeap.add(e);
        }

        for(int i = data.length - 1; i >= 0; i--){
            data[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("Merge Sort", arr);
        SortingHelper.sortTest("Quick Sort 2 Ways", arr2);
        SortingHelper.sortTest("Quick Sort 3 Ways", arr3);
        SortingHelper.sortTest("Heap Sort Simple", arr4);
    }
}
