package local.begin.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 731. 我的日程安排表 II
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 *
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 *
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 *
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 *
 *
 * 示例：
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * 解释：
 * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
 * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
 * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
 * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
 * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 *
 *
 * 提示：
 *
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
 */
public class LeetCode0731MyCalendarTwo {

    // int[0] 存储区间的最大值 int[1] 存储lazy标记值
    Map<Integer, int[]> tree;

    public LeetCode0731MyCalendarTwo() {
        tree = new HashMap<>();
    }

    public boolean book(int start, int end) {
        update(start, end - 1, 1, 0, 1000000000, 1);
        tree.putIfAbsent(1, new int[2]);
        // 如果arr[start, end-1]的最大元素大于2时，，则此时出现某个区间被安排了两次以上，此时返回false
        if(tree.get(1)[0] > 2){
            update(start, end - 1, -1, 0, 1000000000, 1);
            return false;
        }
        return true;
    }

    /**
     *
     * @param start  更新左区间  （闭区间）
     * @param end    更新右区间  （闭区间）
     * @param val    正常情况 为 1； 当该区间内的最大值 大于 2 时， val 设置为-1更新一次
     * @param l       线段左区间   最小为 0
     * @param r       线段右区间   最大为 1000000000
     * @param idx  线段树的节点索引idx，左子节点是 2 * idx ，右子节点是 2 * idx + 1
     */
    private void update(int start, int end, int val, int l, int r, int idx){
        // 超出 arr 边界
        if(r < start || end < l){
            return;
        }
        tree.putIfAbsent(idx, new int[2]);
        if(start <= l && r <= end){
            tree.get(idx)[0] += val;
            tree.get(idx)[1] += val;
        }
        else {
            int mid = l + (r - l) / 2;
            update(start, end, val, l, mid, 2 * idx);
            update(start, end, val, mid + 1, r, 2 * idx + 1);
            // 增加左子区间 和 右子区间
            tree.putIfAbsent(2 * idx, new int[2]);
            tree.putIfAbsent(2 * idx + 1, new int[2]);
            // 更新 idx 要 使用左子区间和右子区间 的 最大值 与 idx该位置上的lazy标记 之和
            tree.get(idx)[0] = tree.get(idx)[1] + Math.max(tree.get(2 * idx)[0], tree.get(2 * idx + 1)[0]);
        }
    }

}
