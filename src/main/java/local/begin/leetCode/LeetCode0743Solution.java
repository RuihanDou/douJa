package local.begin.leetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 743. 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 */
public class LeetCode0743Solution {

    private class Node implements Comparable<Node>{
        public  int v, dis;
        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another){
            return dis - another.dis;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {

        // 建立图
        HashMap<Integer, Integer>[] g = new HashMap[n];
        for(int i = 0; i < n; i++){
            g[i] = new HashMap<>();
        }
        // 因为 times 计 图的方法是从 1 ~ n
        // 转变为 0 ~ n - 1, k 对应 - 1
        for(int[] info : times){
            g[info[0] - 1].put(info[1] - 1, info[2]);
        }
        k = k - 1;

        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k] = 0;
        boolean[] visited = new boolean[n];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(k, 0));

        while (!pq.isEmpty()){

            int cur = pq.remove().v;
            if(visited[cur]){
                continue;
            }

            visited[cur] = true;
            for(int w : g[cur].keySet()){
                if(!visited[w]){
                    if(dis[cur] + g[cur].get(w) < dis[w]){
                        dis[w] = dis[cur] + g[cur].get(w);
                        pq.add(new Node(w, dis[w]));
                    }
                }
            }
        }

        int time = -1;
        for(int t : dis){
            time = Math.max(time, t);
        }

        return time != Integer.MAX_VALUE ? time : -1;

    }

}
