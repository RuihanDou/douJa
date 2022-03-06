package local.begin.leetCode;


import local.begin.struct.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 *
 * 提示：
 *
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class LeetCode0143Solution {

    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }

        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // 注意把mid的next置空
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private ListNode middleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private void mergeList(ListNode l1, ListNode l2){
        ListNode l1Tmp;
        ListNode l2Tmp;
        while (l1 != null && l2 != null){
            l1Tmp = l1.next;
            l2Tmp = l2.next;

            l1.next = l2;
            l1 = l1Tmp;
            l2.next = l1;
            l2 = l2Tmp;
        }
    }

}
