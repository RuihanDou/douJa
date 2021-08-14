package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Struct.ArrayGenerator;
import local.begin.DataStructureAlgorithm.Test.SortingHelper;

import java.util.Arrays;

public class InsertionSort {

    private  InsertionSort(){}

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <E extends Comparable<E>> void sort1(E[] arr){

        for(int i = 0; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
            for(int j = i; j - 1 >= 0; j--) {
                if(arr[j].compareTo(arr[j-1]) < 0){
                    swap(arr, j, j-1);
                }
                else break;
            }
        }
    }

    // 省略循环
    public static <E extends Comparable<E>> void sort2(E[] arr){

        for(int i = 0; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
            for(int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j-1]) < 0; j--) {

                swap(arr, j, j-1);

            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr){

        // i可以从第二个元素开始遍历
        for (int i = 1; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--){
                arr[j] = arr[j - 1];
            }
            arr[j] = t;

        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r){

        // i可以从第二个元素开始遍历
        for (int i = l; i <= r; i++) {

            // 将 arr[i] 插入到合适的位置
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= l && t.compareTo(arr[j - 1]) < 0; j--){
                arr[j] = arr[j - 1];
            }
            arr[j] = t;

        }
    }

    public static <E extends Comparable<E>> void sort0(E[] arr){

        // i可以从第二个元素开始遍历
        for (int i = arr.length - 2; i >= 0; i--) {

            // 将 arr[i] 插入合适的位置
            E t = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && t.compareTo(arr[j+1]) > 0; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = t;
        }

    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {

            System.out.println("Random Array : ");

            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);

            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arr2);

            System.out.println();
            System.out.println("Ordered Array : ");
            arr = ArrayGenerator.generateOrderedArray(n);
            arr2 = Arrays.copyOf(arr, arr.length);

            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arr2);

            System.out.println();
        }



    }
}
