package local.begin.LeetCode;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */



import java.util.*;

/**
 *
 */

public class LeetCode0040Solution {

    List<int[]> freq = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sequence = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<>(sequence));
            return;
        }
        // pos == freq.size() 说明已经遍历完成
        // rest < freq.get(pos)[0] 表示当前位置及以后的元素组合进会超过target
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        // rest / freq.get(pos)[0] 表示至多加这么多元素进组和不会超过target
        // freq.get(pos)[1] 表示该值元素有这么多

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);

        for (int i = 1; i <= most; i++) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; i++) {
            sequence.remove(sequence.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};

        int target = 8;

        LeetCode0040Solution solution = new LeetCode0040Solution();

        List<List<Integer>> rst = solution.combinationSum2(candidates, target);

        System.out.println(rst);
    }

}
