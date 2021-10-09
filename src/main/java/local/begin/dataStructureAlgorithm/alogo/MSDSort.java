package local.begin.dataStructureAlgorithm.alogo;

import java.util.Arrays;

public class MSDSort {

    private MSDSort(){}

    public static String getName(){
        return "MSD Sort";
    }

    public static void sort(String[] arr){
        int N = arr.length;
        String[] temp = new String[N];
        sort(arr, 0, N - 1, 0, temp);
    }

    private static void sort(String[] arr, int left, int right, int r, String[] temp){

        if(left >= right) {
            return;
        }

        int R = 256;
        int[] cnt = new int[R + 1]; // 256 + 空
        int[] index = new int[R + 2];

        // O(n)
        for(int i = left; i <= right; i++){
            // 统计数组里 0 位存储的是字符串为空，其他位存储向后顺移一位
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] ++;
        }

        // O(R)
        for(int i = 0; i < R + 1; i ++){
            index[i + 1] = index[i] + cnt[i];
        }

        // O(n)
        for(int i = left; i <= right; i++){
            int p = r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1);
            temp[left + index[p]] = arr[i];
            index[p] ++;
        }

        // O(n)
        for(int i = left; i <= right; i++){
            arr[i] = temp[i];
        }

        // 递归下去
        for(int i = 0; i < R; i++){
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
        }

    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for(String s: arr){
            System.out.println(s);
        }
    }

}
