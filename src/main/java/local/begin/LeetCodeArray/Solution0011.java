package local.begin.LeetCodeArray;

public class Solution0011 {

    public int maxArea(int[] height) {

        int ret = 0;

        int l = 0, r = height.length - 1;

        while (l < r){
            ret = Math.max(ret, Math.min(height[l], height[r]) * (r - l));

            if(height[l] <= height[r]){
                l++;
            } else {
                r--;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Solution0011 solution0011 = new Solution0011();
        int[] height = new int[]{1,2,1};
        System.out.println(solution0011.maxArea(height));
    }

}
