package local.begin.LeetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class LeetCode018Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length<4){
            return result;
        }

        Arrays.sort(nums);
        int length=nums.length;
        // 第一锚点循环，第一锚点从数组最小开始，最大不超过数组倒数第三位
        for(int ancher1 = 0; ancher1 < length - 3; ancher1++){

            // 如果第一锚点的值与之前一位值一样，跳过
            if(ancher1 > 0 && nums[ancher1] == nums[ancher1-1]){
                continue;
            }

            // 第一锚点确定下的最小值，该最小值比target大，break
            int min1 = nums[ancher1] + nums[ancher1+1] + nums[ancher1+2 ]+ nums[ancher1+3];
            if(min1 > target){
                break;
            }

            // 第一锚点确定下的最大值，该最大值比target小，continue
            int max1=nums[ancher1]+nums[length-1]+nums[length-2]+nums[length-3];
            if(max1 < target){
                continue;
            }

            // 第二锚点循环，第二锚点从第一锚点的右一位开始，，虽大不超过数组的倒数第二位
            for(int ancher2 = ancher1 + 1; ancher2 < length - 2; ancher2++){
                // 如果第二锚点的值与之前一位值一样，跳过
                if(ancher2 > ancher1 + 1 && nums[ancher2] == nums[ancher2 - 1]){
                    continue;
                }
                // 开始左右双指针
                // 左
                int i = ancher2 + 1;
                // 右
                int r = length - 1;
                // 第一锚点和第二锚点确定下的最小值，如果比target大, break;
                int min = nums[ancher1] + nums[ancher2] + nums[i] + nums[i+1];
                if(min > target){
                    break;
                }
                // 第一锚点和第二锚点确定下的最大值，如果比targetxiao，continue；
                int max = nums[ancher1] + nums[ancher2] + nums[r] + nums[r-1];
                if(max < target){
                    continue;
                }

                while (i<r){
                    int curr = nums[ancher1] + nums[ancher2] + nums[i] + nums[r];
                    if(curr == target){
                        result.add(Arrays.asList(nums[ancher1], nums[ancher2], nums[i], nums[r]));
                        i++;
                        while(i < r && nums[i] == nums[i-1]){
                            i++;
                        }
                        r--;
                        while(i < r && ancher2 < r && nums[r] == nums[r+1]){
                            r--;
                        }
                    }else if(curr > target){
                        r--;
                    }else {
                        i++;
                    }
                }
            }
        }
        return result;
    }

}
