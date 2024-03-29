package local.begin.dataStructureAlgorithm.alogo;

import local.begin.tools.DebugTools;

import java.util.Random;

public class SelectKRecursive {
    private SelectKRecursive(){}

    private static void swap(int[] arr, int i, int j){

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static int partition(int[] arr, int l, int r, Random rnd){

        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1 ... i-1] <= v; arr[j+1 ... r] >= v
        int i = l + 1, j =r;
        while (true){
            while (i <= j && arr[i] < arr[l]){
                i++;
            }
            while (j >= i && arr[j] > arr[l]){
                j--;
            }
            if(i >= j){
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private static int selectK(int[] arr, int l, int r, int k, Random rnd){

        int p = partition(arr, l, r, rnd);

        if(k == p){
            return arr[p];
        }

        if(k < p) {
            return selectK(arr, l, p - 1, k, rnd);
        }
        return selectK(arr, p + 1, r, k, rnd);
    }

    public static int selectK(int[] arr, int k){
        Random rnd = new Random();
        return selectK(arr, 0, arr.length - 1, k, rnd);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{10,9,8,7,6,5,4,3,2,1};
        DebugTools.print(SelectKRecursive.selectK(nums, 3));
    }
}
