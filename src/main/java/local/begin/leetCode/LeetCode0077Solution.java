package local.begin.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class LeetCode0077Solution {

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    // 回溯
    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    private void dfs(int cur, int n, int k) {
        // 如果 temp 的长度 加上[cur, n] 的长度 小于 k， 不可能构造出长度为k的temp
        if(temp.size() + (n - cur + 1) < k){
            return;
        }

        // 满足条件
        if(temp.size() == k){
            ans.add(new ArrayList<>(temp));
            return;
        }

        // 考虑当前情况
        // 当 选择把cur 加入 temp
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        // 当 选择 不把cur 加入 temp
        dfs(cur + 1, n, k);
    }


}
