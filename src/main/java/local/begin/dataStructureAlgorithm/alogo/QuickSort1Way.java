package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.algoInterface.Sort;

import java.util.Random;

public class QuickSort1Way implements Sort {

    private QuickSort1Way(){}

    public static String getName(){
        return "Quick Sort 1 Way";
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
    
    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
