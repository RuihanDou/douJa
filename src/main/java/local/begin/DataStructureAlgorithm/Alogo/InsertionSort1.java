package local.begin.DataStructureAlgorithm.Alogo;

import local.begin.DataStructureAlgorithm.Interface.Sort;

/**
 * 通过swap相邻交换的方式实现插入排序
 */
public class InsertionSort1 implements Sort {

    private InsertionSort1(){}

    public static String getName(){
        return "Insertion Sort with Neighbour Swap";
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

//    注释中是拆分逻辑实现插入排序
//    public static <E extends Comparable<E>> void sort(E[] arr){
//
//        for(int i = 0; i < arr.length; i++) {
//
//            // 将 arr[i] 插入到合适的位置
//            for(int j = i; j - 1 >= 0; j--) {
//                if(arr[j].compareTo(arr[j-1]) < 0){
//                    swap(arr, j, j-1);
//                }
//                else break;
//            }
//        }
//    }

    /**
     * 与注释中像比省略循环写法，更简洁实现插入排序
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sort(E[] arr){

        for(int i = 0; i < arr.length; i++) {

            // 将 arr[i] 插入到合适的位置
            for(int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j-1]) < 0; j--) {

                swap(arr, j, j-1);

            }
        }
    }
}
