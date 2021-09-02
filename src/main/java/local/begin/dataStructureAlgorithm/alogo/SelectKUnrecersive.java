package local.begin.dataStructureAlgorithm.alogo;

import java.util.Random;

public class SelectKUnrecersive {

    private SelectKUnrecersive(){}

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

    public static int selectK(int[] arr, int k){
        Random rnd = new Random();

        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int p = partition(arr, l, r, rnd);

            if(k == p){
                return arr[p];
            }

            if(k < p){
                r = p - 1;
            } else { // k > p
                l = p + 1;
            }
        }
        throw new RuntimeException("No solution");
    }

}
