package local.begin.dataStructureAlgorithm.alogo;

import local.begin.dataStructureAlgorithm.algoInterface.Sort;

import java.util.Arrays;

public class MergeSortChange implements Sort {

    private MergeSortChange(){}

    public static String getName(){
        return "Merge Sort with changed loop invariant";
    }

    // 自顶向下的归并排序
    public static <E extends Comparable<E>> void sort(E[] arr){

        E[] temp = Arrays.copyOf(arr, arr.length);
        sort(arr, 0, arr.length, temp);
    }

    // 对 arr[l, r) 范围进行排序
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp){

        // 原来：if (l >= r)
        // 现在，因为 r 是开区间，所以 r 比 l 多 1 的时候，整个数组中只有一个元素
        if (r - l <= 1) {
            return;
        }

        int mid = l + (r - l) / 2;
        // 注意：这句没有变化，但是现在它的语义是对 arr[l, mid) 进行排序
        sort(arr, l, mid, temp);
        // 原来：sort(arr, mid + 1, r, temp);
        // 现在，右侧区间的左边界变成了 mid
        // 右边界虽然还是 r，但现在是开区间
        sort(arr, mid, r, temp);

        // 原来：if(arr[mid].compareTo(arr[mid + 1]) > 0)
        // 现在，左侧数组的最后一个元素变成了 arr[mid - 1]，右侧数组的第一个元素变成了 arr[mid]
        if(arr[mid - 1].compareTo(arr[mid]) > 0){
            merge(arr, l, mid, r, temp);
        }
    }

    // 合并有序区间 arr[l, mid) 和 arr[mid, r)
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] aux){

        // 原来：System.arraycopy(arr, l, temp, l, r - l + 1);
        // 因为现在 r 为开区间，所以整个 arr[l,r) 的元素个数变成了 r - l 个，而非 r - l + 1 个
        System.arraycopy(arr, l, aux, l, r - l);
        // 原来 j 的初始值为 mid + 1
        // 现在右侧区间的第一个元素是 mid，所以 j 的初始值为 mid
        int i = l, j = mid;
        // 每轮循环为 arr[k] 赋值
        // 原来：for(int k = l; k <= r; k ++)
        // 现在因为 r 为开区间，所以取不到，循环继续的条件为 k < r
        for(int k = l; k < r; k++){
            // 原来：if(i > mid)
            // 现在当 i == mid 的时候，已经超过了左侧的数组范围
            // 所以，当 i >= mid 的时候，就不用再管左侧的数组了
            if(i >= mid){
                arr[k] = aux[j];
                j++;
            }
            // 原来：if(j > r)
            // 现在因为 r 为开区间，当 j == r 的时候，已经超过了右侧的数组范围
            // 所以，当 j >= r 的时候，就不用再管右侧的数组了
            else if(j >= r) {
                arr[k] = aux[i];
                i++;
            } else if(aux[i].compareTo(aux[j]) <= 0){
                arr[k] = aux[i];
                i++;
            } else {
                arr[k] = aux[j];
                j++;
            }
        }
    }

}
