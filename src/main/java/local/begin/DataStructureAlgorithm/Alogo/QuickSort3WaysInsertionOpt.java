package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Helper.SortingHelper;
import local.begin.DataStructureAlgorithm.Interface.Sort;
import local.begin.DataStructureAlgorithm.Struct.ArrayGenerator;
import local.begin.DataStructureAlgorithm.Alogo.QuickSort2Ways;

import java.util.Arrays;
import java.util.Random;

public class QuickSort3WaysInsertionOpt implements Sort {

    private QuickSort3WaysInsertionOpt(){}

    public static String getName(){
        return "Quick Sort 3 Ways with Insertion Opt";
    }

    public static <E extends Comparable<E>> void sort3(E[] arr){
        Random rnd = new Random();
        sort3(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r, Random rnd){
        if(r - l <= 15){
            InsertionSort.sort(arr, l, r);
            return;
        }

        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1 ... lt] < v, arr[lt+1 ... i-1] == v, arr[gt, r] > v
        int lt = l, i = l + 1, gt = r + 1;
        while (i < gt){
            if(arr[i].compareTo(arr[l]) < 0){
                lt++;
                swap(arr, i, lt);
                i++;
            }
            else if(arr[i].compareTo(arr[l]) > 0){
                gt--;
                swap(arr, i, gt);
            }
            else {
                i++;
            }
        }
        swap(arr, l, lt);
        // arr[l ... lt-1] < v, arr[lt ... i-1] == v, arr[gt, r] > v
        sort3(arr, l, lt - 1, rnd);
        sort3(arr, gt, r, rnd);
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

        System.out.println("Random Array");

    }
}
