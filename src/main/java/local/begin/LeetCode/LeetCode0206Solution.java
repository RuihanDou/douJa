package local.begin.LeetCode;

import local.begin.struct.ListNode;


/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class LeetCode0206Solution {

    public static ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode curNode = head;

        while (curNode != null) {
            ListNode nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }

        return preNode;
    }

    public static ListNode reverseList1(ListNode head) {
        if(null == head || head.next == null){
            return head;
        }

        ListNode rev = reverseList1(head.next);
        head.next.next = head;
        head.next = null;

        return rev;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        System.out.println(l1.toString());
        System.out.println(LeetCode0206Solution.reverseList(l1).toString());
    }

}
