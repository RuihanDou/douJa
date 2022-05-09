package local.begin.interview;

import java.util.Arrays;


/**
 *  在一个数组中，寻找可以组成三角形的 三元组数量
 *  实例一：[2,2,3,4]
 *
 *  可以有三个三角形
 *      [2,2,3]
 *      [2,3,4]
 *      [2,3,4]
 *   下面两个[2,3,4]对应的三角形的最短边是两个不同的2
 */
public class Baidu {



    public int triangleNum(int[] nums){

        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                ans += Math.max(k - j, 0);
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {2,2,3,4};
        Baidu baidu = new Baidu();
        System.out.println(baidu.triangleNum(nums));
    }

}
