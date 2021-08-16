package local.begin.DataStructureAlgorithm.Test;

import local.begin.DataStructureAlgorithm.Alogo.*;

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

    public static <E extends Comparable<E>> void sortTest(String sortname, E[] arr){
        long startTime = System.nanoTime();

        if(sortname.equals("SelectionSort")){
            SelectionSort.sort(arr);
        } else if(sortname.equals("SelectionSort2")){
            SelectionSort.sort2(arr);
        } else if(sortname.equals("InsertionSort")){
            InsertionSort.sort0(arr);
        } else if(sortname.equals("InsertionSortExchange")){
            InsertionSort.sort2(arr);
        } else if(sortname.equals("InsertionSortExchange1")){
            InsertionSort.sort1(arr);
        } else if(sortname.equals("MergeSort")){
            MergeSort.sort(arr);
        } else if(sortname.equals("MergeSortBU")){
            MergeSort.sortBU(arr);
        } else if(sortname.equals("QuickSort")){
            QuickSort.sort(arr);
        } else if(sortname.equals("QuickSort2")){
            QuickSortSpecial.sort2(arr);
        } else if(sortname.equals("QuickSort2Ways")){
            QuickSort.sort2(arr);
        } else if(sortname.equals("QuickSort3Ways")){
            QuickSort.sort3(arr);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        if(!SortingHelper.isSorted(arr)){
            throw new RuntimeException(sortname + " failed");
        }

        System.out.println(String.format("%s , n = %d : %f s", sortname, arr.length, time));
    }

}
