package local.begin.leetCode;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 210. 课程表 II
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 *
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3：
 *
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */

public class LeetCode0210Solution {
    // 这道题考察 拓扑排序

//    // 方法一：深度优先搜索
//    // 越深的节点放在栈底
//
//    // 存储有向图
//    List<List<Integer>> edges;
//    // 标记访问状态： 0 未搜索，1 搜索种，2已完成
//    int[] visited;
//    // 用数组模拟栈，下标 n-1 为栈底，0 为栈顶
//    int[] result;
//    // 判断有向图中是否有环，有环则无结果
//    boolean valid = true; // 无环
//    // 栈下标
//    int index;
//
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        // 构建图初始化工作
//        edges = new ArrayList<>();
//        for(int i = 0; i < numCourses; i++){
//            edges.add(new ArrayList<>());
//        }
//        visited = new int[numCourses];
//        result = new int[numCourses];
//        index = numCourses - 1;
//
//        for(int[] info : prerequisites){
//            // info [a, b] 表示 学习 a 课程前 需要学习 b 课程，所以图存在一条边 b 指向 a edge[b].add(a)
//            edges.get(info[1]).add(info[0]);
//        }
//
//        // 开始 DFS
//        for(int i = 0; i < numCourses; i++){
//            if(visited[i] == 0){
//                dfs(i);
//            }
//        }
//        // 讨论存在环无解的情况
//        if(!valid){
//            return new int[0];
//        }
//        return result;
//    }
//
//    private void dfs(int u) {
//        // 将 当前点置于 搜索中状态
//        visited[u] = 1;
//        // 只要发现环，立刻停止搜索
//        for(int v : edges.get(u)){
//            // 如果v没有被搜索，则搜索v
//            if(visited[v] == 0){
//                dfs(v);
//                if(!valid){
//                    return;
//                }
//            }
//            // 如果找到了搜索中的点，说明遇到环
//            else if(visited[v] == 1){
//                valid = false;
//                return;
//            }
//        }
//        // 将遍历过的点置于 搜索完成状态
//        visited[u] = 2;
//        // 将节点入栈
//        result[index--] = u;
//    }

    // 方法二：广度优先搜索
    // 合并并消减入度

    // 存储有向图
    List<List<Integer>> edges;
    // 存储每个节点的入度
    int[] indeg;
    // 存储答案
    int[] result;
    // 答案下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 构建图，初始化
        edges = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        result = new int[numCourses];
        index = 0;

        // 链接图，登记入度
        for (int[] info : prerequisites){
            // info [a, b] 表示 学习 a 课程前 需要学习 b 课程，所以图存在一条边 b 指向 a edge[b].add(a)
            edges.get(info[1]).add(info[0]);
            indeg[info[0]]++;
        }

        // 初始化队列进行广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        // 将所有入度为0的点放入队列
        for(int i = 0; i < numCourses; i++){
            if(indeg[i] == 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()){
            // 从队首取节点
            int u = queue.poll();
            // 放入答案中
            result[index++] = u;
            for(int v : edges.get(u)){
                // 因为 u 作为 v 的一个输入已经加入答案中了，所以u的入度减一
                indeg[v]--;
                // 如果v入度降到0，学习v的先决课程都已经加入结果，则可以考察v指出去的节点
                if(indeg[v] == 0){
                    queue.offer(v);
                }
            }
        }

        if(index != numCourses){
            return new int[0];
        }
        return result;
    }

}
