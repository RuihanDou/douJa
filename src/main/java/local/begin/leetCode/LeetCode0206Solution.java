package local.begin.leetCode;

import local.begin.struct.ListNode;


/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
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

    public static ListNode reverseListNR(ListNode head) {
        if(null == head || head.next == null){
            return head;
        }

        ListNode rev = reverseListNR(head.next);
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
