package local.begin.leetCode;

import local.begin.struct.ListNode;


/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class LeetCode0061Solution {

    public ListNode rotateRight(ListNode head, int k) {

        if(k == 0 || head == null || head.next == null){
            return head;
        }

        int n = 1;
        ListNode iter = head;
        while (iter.next != null){
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if(add == n){
            return head;
        }
        iter.next = head;
        while (add > 0){
            add--;
            iter = iter.next;
        }
        ListNode rst = iter.next;
        iter.next = null;
        return rst;

    }

}
