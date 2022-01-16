package local.begin.LeetCodeArray;


/**
 * 605. 种花问题
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 *
 * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：flowerbed = [1,0,0,0,1], n = 1
 * 输出：true
 * 示例 2：
 *
 * 输入：flowerbed = [1,0,0,0,1], n = 2
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= flowerbed.length <= 2 * 104
 * flowerbed[i] 为 0 或 1
 * flowerbed 中不存在相邻的两朵花
 * 0 <= n <= flowerbed.length
 */
public class Solution0605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int len = flowerbed.length;
        int prev = -1;

        for(int i = 0; i < len; i++){

            // 这么做是总结规律，减少循环中的操作次数
            // 当是第一盆花的位置时， count 加 前面的坑位数 / 2， （当出现0.5时向下取整）
            // 当不是第一盆花，上盆花的位置是 prev 时， count 加 （此时坑位编号 - prev -2） / 2， （出现0.5时向下取整），多减的2是代表prev右边和此时编号左边两个坑位
            if(flowerbed[i] == 1){
                if(prev < 0){
                    count += i / 2;
                }
                else {
                    count += (i - prev - 2) / 2;
                }
                prev = i;
            }
        }
        // 花坛里没有一盆花
        if (prev < 0) {
            count += (len + 1) / 2;
        } else { // 最后一盆花后面的坑位呢个种的花数量， 多减的一个1 是 prev 右边的坑位
            count += (len - prev - 1) / 2;
        }
        return count >= n;
    }

}
