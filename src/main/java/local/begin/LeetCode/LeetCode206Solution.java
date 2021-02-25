package local.begin.LeetCode;

import local.begin.struct.ListNode;

public class LeetCode206Solution {

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

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        System.out.println(l1.toString());
        System.out.println(LeetCode206Solution.reverseList(l1).toString());
    }

}
