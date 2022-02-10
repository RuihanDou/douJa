package local.begin.leetCode;


/**
 * 365. 水壶问题
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 */
public class LeetCode0365Solution {


    // 贝祖公式
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if(jug1Capacity + jug2Capacity < targetCapacity){
            return false;
        }

        if(jug1Capacity == 0|| jug2Capacity == 0){
            return targetCapacity == 0 || jug1Capacity + jug2Capacity == targetCapacity;
        }

        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;

    }


    /**
     * 辗转相除法 求 x，y的最大公约数
     * @param x
     * @param y
     * @return
     */
    private int gcd(int x, int y){
        return y != 0 ? gcd(y, x % y) : x;
    }

}
