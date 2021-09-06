package local.begin.dataStructureAlgorithm.helper;

import local.begin.dataStructureAlgorithm.alogo.*;

public class SortingHelper {
    private SortingHelper(){}

    public static <E extends Comparable> boolean isSorted(E[] arr) {

        for(int i = 1; i < arr.length; i++) {

            if(arr[i - 1].compareTo(arr[i]) > 0){
                return false;
            }
        }

        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr){

        long startTime = System.nanoTime();
        // TODO: 改变选择排序的方法
        if(InsertionSort.getName().equals(sortName)){
            InsertionSort.sort(arr);
        } else if(InsertionSort1.getName().equals(sortName)){
            InsertionSort1.sort(arr);
        } else if(MergeSort.getName().equals(sortName)){
            MergeSort.sort(arr);
        } else if(MergeSortBU.getName().equals(sortName)){
            MergeSortBU.sort(arr);
        } else if(MergeSortBUInsertionOpt.getName().equals(sortName)){
            MergeSortBUInsertionOpt.sort(arr);
        } else if(MergeSortInsertionOpt.getName().equals(sortName)){
            MergeSortInsertionOpt.sort(arr);
        } else if(QuickSort1Way.getName().equals(sortName)){
            QuickSort1Way.sort(arr);
        } else if(QuickSort1WayInsertionOpt.getName().equals(sortName)){
            QuickSort1WayInsertionOpt.sort(arr);
        } else if(QuickSort2Ways.getName().equals(sortName)){
            QuickSort2Ways.sort(arr);
        } else if(QuickSort2WaysInsertionOpt.getName().equals(sortName)){
            QuickSort2WaysInsertionOpt.sort(arr);
        } else if(QuickSort3Ways.getName().equals(sortName)){
            QuickSort3Ways.sort(arr);
        } else if(QuickSort3WaysInsertionOpt.getName().equals(sortName)){
            QuickSort3WaysInsertionOpt.sort(arr);
        } else if(QuickSortLeftFlag.getName().equals(sortName)){
            QuickSortLeftFlag.sort(arr);
        } else if(QuickSortMidFlag.getName().equals(sortName)){
            QuickSortMidFlag.sort(arr);
        } else if(HeapSortSimple.getName().equals(sortName)){
            HeapSortSimple.sort(arr);
        } else {
            throw new RuntimeException("Wrong Sort Name.");
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        if(!SortingHelper.isSorted(arr)){
            throw new RuntimeException(sortName + " failed");
        }

        System.out.println(String.format("%s , n = %d : %f s", sortName, arr.length, time));
    }

}
