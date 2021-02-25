package local.begin.DataStructureAlgorithm.Test;

import local.begin.DataStructureAlgorithm.Alogo.InsertionSort;
import local.begin.DataStructureAlgorithm.Alogo.SelectionSort;

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
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        if(!SortingHelper.isSorted(arr)){
            throw new RuntimeException(sortname + " failed");
        }

        System.out.println(String.format("%s , n = %d : %f s", sortname, arr.length, time));
    }

}
