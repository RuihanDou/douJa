package local.begin.leetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 *
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class LeetCode0253Solution {

//    /**
//     * 使用排序和优先队列，
//     *
//     * 考察排序后的每个会议是否可以可以和优先队列里最早结束的会议公用一个会议室
//     *
//     *
//     * @param intervals
//     * @return
//     */
//    public int minMeetingRooms(int[][] intervals) {
//
//        // 如果没有会议就不需要会议室
//        if(intervals.length == 0){
//            return 0;
//        }
//
//        // 使用优先队列
//        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//
//        // 根据会议开始时间排序
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        });
//
//        // 在优先队列中添加第一个会议 的结束时间
//        allocator.add(intervals[0][1]);
//
//        // 循环遍历，考察之后会议的情况
//        for(int i = 1; i < intervals.length; i++){
//
//            // 如果当前会议的开始时间晚于队列里 最早结束的 会议，那么当前会议可以与 队首的会议使用同一个会议室，该队首出队
//            if(intervals[i][0] >= allocator.peek()){
//                allocator.poll();
//            }
//
//            allocator.offer(intervals[i][1]);
//
//        }
//
//        return allocator.size();
//
//    }

    /**
     * 使用排序的方式 分别把开始时间和结束时间进行排序
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals){

        // 如果没有会议就不需要会议室
        if(intervals.length == 0){
            return 0;
        }

//        Integer[] start = new Integer[intervals.length];
//        Integer[] end = new Integer[intervals.length];
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // 对开始时间和结束时间分别排序
        Arrays.sort(start);

        Arrays.sort(end);

        // 初始化 start 和 end 的指针
        int startPointer = 0, endPointer = 0;

        // 需要的会议室数量
        int usedRooms = 0;

        while (startPointer < intervals.length){

            // 当前 开始的会议 可以 和 当前位置结束的会议共用会议室，所以会议室数量缩小， 结束会议移动到下一个位置
            if(start[startPointer] >= end[endPointer]){
                usedRooms--;
                endPointer++;
            }
            // 一般情况 ： 会议室 数量增加，开始会议移到下一个位置
            usedRooms++;
            startPointer++;

        }

        return usedRooms;

    }



}
