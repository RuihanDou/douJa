package local.begin.LeetCodeArray;

import java.util.HashMap;
import java.util.Map;

public class Solution0001 {

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        // map 的 key 是 target - cur 元素， value 是 cur 的序号
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            int cur = nums[i];
            if(map.containsKey(cur)){
                return new int[]{map.get(cur), i};
            }
            map.put(target - cur, i);
        }
        throw new IllegalArgumentException("no match result");
    }

}
