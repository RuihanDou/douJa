package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Interface.Sort;

import java.util.Arrays;

/**
 * 自底向上的归并排序算法
 */
public class MergeSortBU implements Sort {

    private MergeSortBU(){}

    public static String getName(){
        return "Merge Sort Bottom Up";
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        for(int sz = 1; sz < n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz){
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
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
