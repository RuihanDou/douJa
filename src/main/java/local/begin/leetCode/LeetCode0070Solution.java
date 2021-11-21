package local.begin.leetCode;

/**
 *70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * 通过次数619,579提交次数1,165,568
 */
public class LeetCode0070Solution {

//    public int climbStairs(int n) {
//        int p = 0, q = 1, r = 1;
//        for(int i = 1; i < n; i++){
//            p = q;
//            q = r;
//            r = p + q;
//        }
//        return r;
//    }

    public int climbStairs(int n) {
        double n1 = n + 1.0;
        double rst = (1.0 / Math.sqrt(5.0)) * (Math.pow(((1.0 + Math.sqrt(5.0)) / 2.0), n1) - Math.pow(((1.0 - Math.sqrt(5.0)) / 2.0), n1));
        return (int) rst;
    }

    public static void main(String[] args) {

    }
}
