package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Interface.Sort;

import java.util.Arrays;

public class MergeSortInsertionOpt implements Sort {

    private MergeSortInsertionOpt(){}

    public static String getName(){
        return "Merge Sort with Insertion Opt";
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp){

        if(r - l <= 15){
            InsertionSort.sort(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);
        sort(arr, mid + 1, r, temp);
        if(arr[mid].compareTo(arr[mid + 1]) > 0){
            merge(arr, l, mid, r, temp);
        }
    }

    // 合并有序区间 arr[l, mid] 和 arr[mid+1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp){
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;
        for(int k = l; k <= r; k++){
            if(i > mid){
                arr[k] = temp[j];
                j++;
            } else if(j > r) {
                arr[k] = temp[i];
                i++;
            } else if(temp[i].compareTo(temp[j]) <= 0){
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }
}
