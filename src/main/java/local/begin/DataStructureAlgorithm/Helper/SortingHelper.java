package local.begin.DataStructureAlgorithm.Helper;

import local.begin.DataStructureAlgorithm.Interface.Sort;

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

    public static <E extends Comparable<E>> void sortTest(Sort sort, E[] arr){
        long startTime = System.nanoTime();

        // TODO: 改变选择排序的方法

        long endTime = System.nanoTime();
        Sort.sort(arr);
        double time = (endTime - startTime) / 1000000000.0;

        if(!SortingHelper.isSorted(arr)){
            throw new RuntimeException(Sort.getName() + " failed");
        }

        System.out.println(String.format("%s , n = %d : %f s", Sort.getName(), arr.length, time));
    }

}
