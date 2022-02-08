package local.begin.leetCode;


import java.util.*;

/**
 * 218. 天际线问题
 * 城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。
 *
 * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
 *
 * lefti 是第 i 座建筑物左边缘的 x 坐标。
 * righti 是第 i 座建筑物右边缘的 x 坐标。
 * heighti 是第 i 座建筑物的高度。
 * 你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。
 *
 * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 *
 * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * 解释：
 * 图 A 显示输入的所有建筑物的位置和高度，
 * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
 * 示例 2：
 *
 * 输入：buildings = [[0,2,3],[2,5,3]]
 * 输出：[[0,3],[5,0]]
 *
 *
 * 提示：
 *
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings 按 lefti 非递减排序
 */
public class LeetCode0218Solution {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 优先队列按照 高由大向小排列
        // pq中存的数组 索引1为 右边界，索引2为 高
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            // boundaries 加入 building 左边界 闭区间
            boundaries.add(building[0]);
            // boundaries 加入 building 有边界 开区间
            boundaries.add(building[1]);
        }
        // boundaries 按找 从左到右出现建筑边界排列
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            // 左边界小于或等于 boundary 的元素 加入pq 这栋楼加入考察范围
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            // pq 中 右边界小于 boundary 的元素 取出 这栋楼已经过去了
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }
            // 该条 boundary 的最高点是 maxn
            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            // 如果结果集为空，加入第一个结果，如果结果集里右结果，结果的最后一个的高 与 该bound一致，证明该bound被更高的天际线盖住，不加入结果集
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        LeetCode0218Solution solution = new LeetCode0218Solution();
        System.out.println(solution.getSkyline(buildings));
    }

}
