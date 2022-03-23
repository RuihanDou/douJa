package local.begin.leetCode;


import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 */
public class LeetCode0039Solution {

    /**
     * 使用递归的方式
     *
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> cur = new ArrayList<>();
        List<List<Integer>> rst = new ArrayList<>();
        recursive(candidates, cur, rst, 0, target);
        return rst;
    }

    private void recursive(int[] candidates, List<Integer> cur, List<List<Integer>> rst, int pos, int target) {

        if(pos >= candidates.length){
            return;
        }

        if(target == 0){
            rst.add(new ArrayList<>(cur));
            return;
        }

        // 添加本元素
        if(target - candidates[pos] >= 0){
            cur.add(candidates[pos]);
            recursive(candidates, cur, rst, pos, target - candidates[pos]);
            cur.remove(cur.size() - 1);
        }

        // 不添加本元素
        recursive(candidates, cur, rst, pos + 1, target);

    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        LeetCode0039Solution solution = new LeetCode0039Solution();
        List<List<Integer>> res = solution.combinationSum(candidates, 7);
        System.out.println(res);
    }

}
