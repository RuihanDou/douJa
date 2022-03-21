package local.begin.leetCode;
import local.begin.struct.ListNode;

import java.util.PriorityQueue;

/**
 *23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * 通过次数349,569提交次数622,989
 */


public class LeetCode0023Solution {

    private class Status implements Comparable<Status>{
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    /**
     * 使用优先队列
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Status> queue = new PriorityQueue<>();
        for (ListNode node : lists){
            if(node != null){
                queue.offer(new Status(node.val, node));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()){
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null){
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return dummy.next;
    }

}
