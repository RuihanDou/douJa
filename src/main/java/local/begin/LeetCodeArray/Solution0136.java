package local.begin.LeetCodeArray;


/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * 通过次数574,119提交次数798,103
 */
public class Solution0136 {

    // 不使用额外空间，要使用状态压缩
    // 异或 满足交换律
    // 任何 数 异或两个 相同的数都是 这个数本身
    // 0 异或 任何数 都是 该数本身
    public int singleNumber(int[] nums) {
        int single = 0;
        for(int num : nums){
            single ^= num;
        }
        return single;
    }

}
