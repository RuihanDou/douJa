package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Struct.ArrayGenerator;
import local.begin.DataStructureAlgorithm.Test.SortingHelper;

import java.util.Arrays;
import java.util.Random;

public class QuickSort   {

    private  QuickSort(){}

    public static <E extends Comparable<E>> void sort2(E[] arr){
        Random rnd = new Random();
        sort2(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r, Random rnd){
        if(l >= r) {
            return;
        }
//        if(r - l <= 15){
//            InsertionSort.sort(arr, l, r);
//            return;
//        }
        int p = partition2(arr, l, r, rnd);
        sort2(arr, l, p - 1, rnd);
        sort2(arr, p + 1, r, rnd);
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        Random rnd = new Random();
        sort(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random rnd){
        if(l >= r) {
            return;
        }
//        if(r - l <= 15){
//            InsertionSort.sort(arr, l, r);
//            return;
//        }
        int p = partition(arr, l, r, rnd);
        sort(arr, l, p - 1, rnd);
        sort(arr, p + 1, r, rnd);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r, Random rnd){
        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);
        
        // arr[l + 1 ... j] < v ; arr[j + 1 ... i] >= v
        int j = l;
        for(int i = l + 1; i <= r; i++){
            if(arr[i].compareTo(arr[l]) < 0){
                swap(arr, i, ++j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random rnd){
        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1 ... i-1] <= v; arr[j+1 ... r] >= v
        int i = l + 1, j = r;
        while (true){
            while (i <= j && arr[i].compareTo(arr[l]) < 0){
                i++;
            }
            while (j >= i && arr[j].compareTo(arr[l]) > 0){
                j--;
            }

            if(i >= j){
                break;
            }

            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    public static <E extends Comparable<E>> void sort3(E[] arr){
        Random rnd = new Random();
        sort3(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r, Random rnd){
        if(l >= r) {
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

        System.out.println("Random Array");

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);

        System.out.println("Ordered Array");

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);

        System.out.println("Same Value Array");

        arr = ArrayGenerator.generateRandomArray(n, 1);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);
    }

}
