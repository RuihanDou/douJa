package local.begin.leetCode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 346. 数据流中的移动平均值
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 实现 MovingAverage 类：
 *
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 计算并返回数据流中最后 size 个值的移动平均值。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * 输出：
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * 解释：
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 *
 *
 * 提示：
 *
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * 最多调用 next 方法 104 次
 */
public class LeetCode0346MovingAverage {

    private Queue<Integer> queue;
    private int size;

    public LeetCode0346MovingAverage(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    public double next(int val) {
        queue.offer(val);
        if(queue.size() > size){
            queue.poll();
        }
        int num = queue.size(), sum = 0;
        for(Integer e : queue){
            sum += e;
        }
        return (double) sum / num;
    }

}
