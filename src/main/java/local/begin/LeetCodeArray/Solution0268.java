package local.begin.LeetCodeArray;

public class Solution0268 {

    public int missingNumber(int[] nums) {
        int range = nums.length;
        int[] record = new int[nums.length + 1];
        for(int i = 0; i < range; i++){
            record[nums[i]] = 1;
        }
        for(int j = 0; j <= range; j++){
            if(record[j] != 1){
                return j;
            }
        }
        throw new IllegalArgumentException("no result");
    }

}
