package local.begin.leetCode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现次数超过 ⌊ n/3 ⌋ 次的元素。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 */
public class LeetCode0229Solution {

//    public List<Integer> majorityElement(int[] nums) {
//
//        Map<Integer, Integer> cnt = new HashMap<>();
//
//        for(int i = 0; i < nums.length; i++){
//            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
//        }
//
//        List<Integer> ans = new ArrayList<>();
//        for(int x : cnt.keySet()){
//            if(cnt.get(x) > nums.length / 3){
//                ans.add(x);
//            }
//        }
//
//        return ans;
//    }

    /**
     * 摩尔投票法，因为元素个数 大于 ⌊ n/3 ⌋，所以最多有两个
     * 每次，在数组中 删除三个互不相等的元素
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int ele1 = 0, ele2 = 0, vote1 = 0, vote2 = 0;

        for(int num : nums){
            //如果该元素为第一个元素，则计数加1
            if(vote1 > 0 && num == ele1){
                vote1++;
            }
            //如果该元素为第二个元素，则计数加1
            else if(vote2 > 0 && num == ele2){
                vote2++;
            }
            // 选择第一个元素
            else if(vote1 == 0){
                ele1 = num;
                vote1++;
            }
            // 选择第二个元素
            else if(vote2 == 0){
                ele2 = num;
                vote2++;
            }
            //如果三个元素均不相同，则相互抵消1次
            else {
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0, cnt2 = 0;
        for(int num : nums){
            if(vote1 > 0 && num == ele1){
                cnt1++;
            }
            if(vote2 > 0 && num == ele2){
                cnt2++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if(vote1 > 0 && cnt1 > nums.length / 3){
            res.add(ele1);
        }
        if(vote2 > 0 && cnt2 > nums.length / 3){
            res.add(ele2);
        }

        return res;
    }


}
