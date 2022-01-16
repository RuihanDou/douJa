package local.begin.leetCode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

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
