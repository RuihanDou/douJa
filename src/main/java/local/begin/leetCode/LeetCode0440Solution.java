package local.begin.leetCode;


/**
 * 440. 字典序的第K小数字
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 示例 2:
 *
 * 输入: n = 1, k = 1
 * 输出: 1
 *
 *
 * 提示:
 *
 * 1 <= k <= n <= 109
 */
public class LeetCode0440Solution {

    public int findKthNumber(int n, int k) {
        long cur = 1;  // 当前遍历到的数字，从根1出发
        // 转变成从0开始计数；
        k -= 1;

        while (k > 0){
            int nodes = getNodes(n, cur);
            // 向右走
            if(k >= nodes){
                k -= nodes; // 字典序跨过 nodes 位
                cur++; // 当前数字相当于 + 1
            }
            // 向下走
            else {
                k -= 1; // 字典序增加一位
                cur *= 10; // 数字相当于 * 10；
            }
        }

        return (int) cur; // 最后停留的数字是字典序中的第k小数字
    }

    /**
     * 计算以cur为根的子树节点数目，所有节点必须  <= n
     * @param n
     * @param cur
     * @return
     */
    private int getNodes(int n, long cur) {
        long next = cur + 1; // 记录当前节点的右节点的值
        long totalNodes = 0; // 记录当前节点子树中全部节点的个数

        while (cur <= n){
            totalNodes += Math.min(n - cur + 1, next - cur);
            next *= 10;
            cur *= 10;
        }
        return (int) totalNodes;
    }

    public static void main(String[] args) {
        LeetCode0440Solution solution = new LeetCode0440Solution();
        int rst = solution.findKthNumber(2, 2);
        System.out.println(rst);
    }

}
