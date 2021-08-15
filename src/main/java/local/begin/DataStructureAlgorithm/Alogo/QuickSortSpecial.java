package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Struct.ArrayGenerator;
import local.begin.DataStructureAlgorithm.Test.SortingHelper;

import java.util.Arrays;

/**
 * 本类探索，如果快排 partition 每次取标定点都是数组区间的中点会造成什么样的算法退化
 * 需要更改 jvm 默认设定 为-Xss128m
 */
public class QuickSortSpecial {

    private QuickSortSpecial(){}

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r){

        // 使用中间值作为标定点
        swap(arr, l, (l + r) / 2);

        // arr[l+1...j] < v ; arr[j+1...i] >= v
        int j = l;
        for(int i = l + 1; i <= r; i ++)
            if(arr[i].compareTo(arr[l]) < 0){
                j ++;
                swap(arr, i, j);
            }

        swap(arr, l, j);
        return j;
    }

    public static Integer[] generateSpecialArray(int n){

        // 开空间
        Integer[] arr = new Integer[n];

        // 生成 arr[0...n-1] 的测试用例，其中最小值是 0
        generateSpecialArray(arr, 0, arr.length - 1, 0);

        return arr;
    }

    private static void generateSpecialArray(Integer[] arr, int l, int r, int value){
        // 递归到底的情况 ：如果 l > r, 处理区间为空的情况，直接返回
        if(l > r){
            return;
        }

        // 1、把最小值放到中间
        int mid = (l + r) / 2;
        arr[mid] = value;

        // 2、模拟 partition 过程，把中间元素和最左边元素交换位置
        swap(arr, l, mid);

        // 3、处理除了最左边元素之外，剩下的 n-1 个元素：
        // 所以， 处理区间变成了 arr[l+1  ...   r]. 同时，最小值 + 1
        generateSpecialArray(arr, l + 1, r, value + 1);

        // 4、处理号之后，还要把中间元素和最左边元素交换回来。
        swap(arr, l, mid);
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        sort2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r){

        if(l >= r){
            return;
        }

        int p = partition2(arr, l, r);
        sort2(arr, l, p - 1);
        sort2(arr, p + 1, r);
    }

    public static void main(String[] args){

        System.out.println(Arrays.toString(generateSpecialArray(8)));

        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Random Array");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2", arr2);

        System.out.println();


        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Ordered Array");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2", arr2);
        System.out.println();


        arr = generateSpecialArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Special Array");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2", arr2);
        System.out.println();
    }

}
