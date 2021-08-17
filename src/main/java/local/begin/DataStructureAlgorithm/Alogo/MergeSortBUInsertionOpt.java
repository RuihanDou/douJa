package local.begin.DataStructureAlgorithm.Alogo;

import java.util.Arrays;

public class MergeSortBUInsertionOpt {

    private MergeSortBUInsertionOpt(){}

    public static String getName(){
        return "Merge Sort Bottom Up with Insertion Opt";
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        // 使用插入排序优化
        // 遍历一遍，对所有 arr[i, i + 15] 的区间，使用插入排序法
        // 注意 i 每次加 16
        for(int i = 0; i < n; i += 16){
            InsertionSort.sort(arr, i, Math.min(i + 15, n - 1)); // 也要注意这里 Math.min 的用法
        }
        // ... 然后进行自底向上的归并排序
        // 遍历合并的区间长度
        // 注意，sz 从 16 开始
        for(int sz = 16; sz < n; sz += sz) {
            // 遍历合并的两个区间的起始位置 i
            // 合并 [i, i + sz - 1] 和 [i + sz, i + sz + sz - 1]
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
