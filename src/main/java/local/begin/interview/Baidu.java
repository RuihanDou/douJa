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

    // 右侧的k固定，在k左边选 l 和 r

    public int triangleNum(int[] nums){

        int rst = 0;

        Arrays.sort(nums);
        int n = nums.length;
        if(n < 3){
            return rst;
        }

        for(int k = 2; k < n; k++){
            int l = 0, r = k - 1;
            if(nums[l] + nums[r] < nums[k]){
                continue;
            }
            while (l < r){
                if(nums[l] + nums[r] > nums[k]){
                    rst += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,3,4};
        Baidu baidu = new Baidu();
        System.out.println(baidu.triangleNum(nums));
    }

}
