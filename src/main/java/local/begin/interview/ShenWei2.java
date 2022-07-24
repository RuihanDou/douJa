package local.begin.interview;

public class ShenWei2 {

    /**
     * 使用 二分查找法
     *
     * 如果 获取到 abs(nums[mid]) 时
     *           abs(nums[mid]) <= abs(nums[mid - 1])
     *           abs(nums[mid]) <= abs(nums[mid + 1])
     *  找到 最小绝对值点，返回 nums[mid]
     *
     *  否则进行讨论
     *    如果 abs(nums[mid]) > abs(nums[mid + 1]) 搜索左边界从 mid + 1开始
     *    否则 右边界从mid - 1开始
     *
     *
     *  注意： mid + 1 和 mid - 1 有可能超过 nums 边界，所以比较的操作逻辑要重构
     *
     * @param nums
     * @return
     */
    public int minAbsNum(int[] nums){
        int n = nums.length;
        int left = 0, right = n - 1;
        int ans = Integer.MAX_VALUE;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if(compare(nums, mid-1, mid) > 0 && compare(nums, mid, mid+1) < 0){
                ans = nums[mid];
                break;
            }
            if(compare(nums, mid, mid+1) > 0){
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        return ans;
    }

    private int compare(int[] nums, int idx1, int idx2){
        if(idx1 < 0){
            return 1;
        }
        if(idx2 >= nums.length){
            return -1;
        }
        if(Math.abs(nums[idx1]) == Math.abs(nums[idx2])){
            return 0;
        }
        return Math.abs(nums[idx1]) > Math.abs(nums[idx2]) ? 1 : -1;
    }

    public static void main(String[] args) {
        int[] nums = {-3,-2,-1,0,1,2,3,4,5};
        System.out.println(new ShenWei2().minAbsNum(nums));
    }

}
