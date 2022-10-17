package local.begin.interview;

public class ShenWei1 {

    /**
     * 动态规划
     *
     * dp[i] 为跳约到 第 i 级 楼梯的最低体力消耗
     *
     * 状态转移如下
     * dp[i] = min(cost[i - 1] * 1, cost[i - 2] * 2, cost[i - 3] * 3)
     *
     * 边界条件
     * dp[0] = 0
     *
     *
     *
     * @param cost
     * @return
     */
    public int getAllCost(int[] cost){
        if(cost == null || cost.length <= 0){
            return 0;
        }

        int steps = cost.length;

        int[] dp = new int[steps];

        for(int i = 1; i < steps; i++){
            int currCost = 0;
            if(i - 1 >= 0){
                currCost = dp[i-1] + cost[i - 1];
            }
            if(i - 2 >= 0){
                currCost = Math.min(currCost, dp[i-2] + cost[i - 2] * 2);
            }
            if(i - 3 >= 0){
                currCost = Math.min(currCost, dp[i-3] + cost[i - 3] * 3);
            }
            dp[i] = currCost;
        }

        return dp[steps - 1];
    }

    public static void main(String[] args) {
        int[] cost = {1,10,5,3,8,20,25,22,100,3};
        ShenWei1 shenWei1 = new ShenWei1();
        System.out.println(shenWei1.getAllCost(cost));
    }

}
