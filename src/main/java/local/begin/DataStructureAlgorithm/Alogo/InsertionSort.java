package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Struct.ArrayGenerator;
import local.begin.DataStructureAlgorithm.Test.SortingHelper;

import java.util.Arrays;

public class InsertionSort {

    private  InsertionSort(){}

    public static String getName(){
        return "Insertion Sort";
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

    /**
     * 为高级排序算法优化（小区间排序）提供方法
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
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

    /**
     * R2L 方法循环都从索引大的开始， 计算复杂度没有变化
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortR2L(E[] arr){

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

}
