package local.begin.interview;

import java.util.ArrayList;
import java.util.List;

public class Boss {

    private int arrLength;


    public List<List<Integer>> getSubArray(int[] arr, int target){
        arrLength = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        if(arrLength < 1){
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        dfs(arr, res, 0, cur, target);
        return res;
    }

    private void dfs(int[] arr, List<List<Integer>> res, int pos, List<Integer> alreadyHave, int target){

        if(target == 0){
            res.add(new ArrayList<>(alreadyHave));
            return;
        }

        if(pos >= arrLength){
            return;
        }

//        if(target == 0){
//            res.add(new ArrayList<>(alreadyHave));
//            return;
//        }

        if(target - arr[pos] >= 0){
            alreadyHave.add(arr[pos]);
            dfs(arr, res, pos + 1, alreadyHave, target-arr[pos]);
            alreadyHave.remove(alreadyHave.size() - 1);
        }


        // 1、跳过本 pos 的元素
        dfs(arr, res, pos + 1, alreadyHave, target);

    }

    public static void main(String[] args) {
        int[] arr = {10, 1, 7, 6, 1, 5, 2};
        int target = 10;
        System.out.println(new Boss().getSubArray(arr, target));
    }

}
