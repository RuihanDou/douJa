package local.begin.dataStructureAlgorithm.alogo;

public class BubbleSortBackwiseOpt2 {

    private BubbleSortBackwiseOpt2(){}

    public static <E extends Comparable<E>> void sort(E[] data){

        for(int i = 0; i + 1 < data.length; i++){

            // arr[0, i) 已经排好序
            // 通过冒泡在arr[i]位置上放上合适的元素
            int lastSwappedIndex = data.length - 1;
            for(int j = data.length - 1; j > i; j++){
                if(data[j - 1].compareTo(data[j]) > 0){
                    swap(data, j-1, j);
                    lastSwappedIndex = j - 1;
                }
            }

            i = lastSwappedIndex + 1;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
