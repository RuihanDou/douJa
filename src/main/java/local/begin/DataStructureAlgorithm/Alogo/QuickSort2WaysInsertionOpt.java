package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Interface.Sort;

import java.util.Random;

public class QuickSort2WaysInsertionOpt implements Sort {

    private QuickSort2WaysInsertionOpt(){}

    public static String getName(){
        return "Quick Sort 2 Ways with Insertion Opt";
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        Random rnd = new Random();
        sort(arr, 0, arr.length - 1, rnd);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random rnd){
//        if(l >= r) {
//            return;
//        }
        if(r - l <= 15){
            InsertionSort.sort(arr, l, r);
            return;
        }
        int p = partition2(arr, l, r, rnd);
        sort(arr, l, p - 1, rnd);
        sort(arr, p + 1, r, rnd);
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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

}
