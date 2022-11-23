package local.begin.interview;

/**
 *
 */
public class BaiduRec {

    public int maxSubArray(int[] nums){

        int sumPre = 0;
        int ansMax = nums[0];
        for(int num : nums){
            // 如果之前的子串和加上当前的数小于 当前数本身，那么子串和从当前数开始重记
            sumPre = Math.max(num, num + sumPre);
            // 此时 sumPre 已经包含该轮循环 的 num
            ansMax = Math.max(ansMax, sumPre);
        }

        return ansMax;
    }


    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        BaiduRec baiduRec = new BaiduRec();
        System.out.println(baiduRec.maxSubArray(nums));

    }


}
