package local.begin.LeetCode;

import local.begin.tools.DebugTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */


public class LeetCode0047Solution {

    List<List<Integer>> rst = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

        Arrays.sort(nums);
        List<int[]> freq = new ArrayList<>();
        int limit = nums.length;

        for(int i = 0; i < nums.length; i++) {
            int size = freq.size();
            int num = nums[i] ;
            if(freq.isEmpty() || freq.get(size - 1)[0] != num){
                freq.add(new int[]{num, 1});
            } else {
                freq.get(size - 1)[1]++;
            }
        }
        List<Integer> posi = new ArrayList<>();
        recursion(posi, freq, limit);
        return rst;
    }

    private void recursion(List<Integer> posi, List<int[]> freq, int limit){

        if(posi.size() == limit){
            if(!rst.contains(posi)){
                rst.add(new ArrayList<>(posi));
            }
            return;
        }

        for (int[] ele : freq) {
            if(ele[1] > 0){
                posi.add(ele[0]);
                ele[1]--;
                recursion(posi, freq, limit);
                posi.remove(posi.size() - 1);
                ele[1]++;
            }
        }
    }

    public static void main(String[] args) {

        LeetCode0047Solution solution = new LeetCode0047Solution();

        int[] num = new int[]{1,1,2};
        List<List<Integer>> rst = solution.permuteUnique(num);
        DebugTools.print(rst);
    }

}
