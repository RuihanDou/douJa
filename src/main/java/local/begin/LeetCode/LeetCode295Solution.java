package local.begin.LeetCode;


import local.begin.tools.DebugTools;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */

public class LeetCode295Solution {

    static class MedianFinder {

        // 我们规定incre优先队列可以比decre优先队列多加一个数字；
        // 如果 incre 的size 比 decre 的 size 大，中位数是incre 的peek

        private PriorityQueue<Integer> incre = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue).reversed());
        private PriorityQueue<Integer> decre = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue));

        /** initialize your data structure here. */
        public MedianFinder() {

        }

        public void addNum(int num) {

            // 如果MedianFinder为空，直接给incre添加
            if(incre.size() < 1){
                incre.offer(num);
            } else if(incre.size() == 1 && decre.size() == 0){ // 如果MedianFinder只有一个元素
                int increTop = incre.poll();
                if(increTop >= num){
                    incre.offer(num);
                    decre.offer(increTop);
                } else {
                    incre.offer(increTop);
                    decre.offer(num);
                }
            } else {
                // 如果incre的size大于decre的size
                if(incre.size() > decre.size()){
                    int increTop = incre.poll();
                    int decreBot = decre.poll();

                    if(num >= increTop){
                        decre.offer(decreBot);
                        decre.offer(num);
                        incre.offer(increTop);
                    } else {
                        incre.offer(num);
                        decre.offer(decreBot);
                        decre.offer(increTop);
                    }
                } else { // 如果incre的size等于decre的size

                    int increTop = incre.poll();
                    int decreBot = decre.poll();

                    if(num >= decreBot){
                        incre.offer(increTop);
                        incre.offer(decreBot);
                        decre.offer(num);
                    } else {
                        incre.offer(increTop);
                        incre.offer(num);
                        decre.offer(decreBot);
                    }

                }
            }
        }

        public double findMedian() {

            if(incre.size() < 1){
                throw new RuntimeException("MedianFinder is empty.");
            }

            if(incre.size() > decre.size()){
                return incre.peek();
            } else {
                return (incre.peek() + decre.peek()) / 2.0;
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        DebugTools.print(medianFinder.findMedian());
        medianFinder.addNum(3);
        DebugTools.print(medianFinder.findMedian());
    }


}
