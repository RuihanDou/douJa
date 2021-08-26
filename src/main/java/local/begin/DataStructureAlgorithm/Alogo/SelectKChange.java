package local.begin.DataStructureAlgorithm.Alogo;

import java.util.Random;

public class SelectKChange {

    private SelectKChange(){}

    private static void swap(int[] arr, int i, int j){

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static int partition(int[] arr, int l, int r, Random rnd){

        // 生成 [l, r] 之间的随机索引, 之前调用partition参数为(arr, l, r - 1, rnd)
        // 改为生成 [l, r) 之间的随机索引, 调用partition就可以一致化(arr, l, r, rnd)
        // 现在因为 r 是开区间，所以 arr[l, r) 中的区间长度是 r - l
        int p = l + rnd.nextInt(r - l);
        swap(arr, l, p);

        // arr[l+1 ... i-1] <= v; arr[j+1 ... r) >= v
        // 注意：注释中，arr[j+1...r) 后面也变成了开区间
        // 所以，j 的初始值变成了 r - 1，而不再是 r。
        // 当 j = r - 1 的时候，arr[j + 1, r) 为 arr[r, r)，是一个空区间
        int i = l + 1, j = r - 1;
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
        // 循环不变量改为在 [l ,r) 区间
        int p = partition(arr, l, r, rnd);
        if (k == p){
            return arr[p];
        }
        if(k < p){
            return selectK(arr, l, p, k, rnd);
        }
        return selectK(arr, p, r, k, rnd);
    }

    public static int selectK(int[] arr, int k){
        Random rnd = new Random();
        return selectK(arr, 0 ,arr.length, k, rnd);
    }

}
