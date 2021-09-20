package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.helper.ArrayGenerator;
import local.begin.dataStructureAlgorithm.helper.SortingHelper;

public class BubbleSort {

    private BubbleSort(){}

    public static String getName(){
        return "Bubble Sort";
    }

    public static <E extends Comparable<E>> void sort(E[] data){

        for(int i = 0; i + 1 < data.length; i++){
            // arr[n -i , n) 已排序好
            // 通过冒泡在arr[n - i - 1] 位置上放上合适的元素
            for(int j = 0; j + 1<= data.length - i - 1;  j++){
                // j, j + 1
                if(data[j].compareTo(data[j + 1]) > 0){
                    swap(data, j, j+1);
                }
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
        SortingHelper.sortTest("Bubble Sort", arr);

    }

}
