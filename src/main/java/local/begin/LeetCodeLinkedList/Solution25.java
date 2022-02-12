package local.begin.LeetCodeLinkedList;

import local.begin.struct.ListNode;


public class Solution25 {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;

        while (head != null){
            ListNode tail = pre;

            // 查看剩下的部分长度是不是 大于等于 k， 因为本题要求 小于k的的部分不翻转
            for(int i = 0; i < k; i++){
                tail = tail.next;
                if(tail == null){
                    return dummyHead.next;
                }
            }

            // 此时 [head, tail] 包括两端，区间有 k 个节点
            // next 保存翻转区间后面的 节点
            ListNode next = tail.next;
            ListNode[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            pre.next = head;
            tail.next = next;
            // 为下一次循环结构体准备，所以确定循环条件为 head是不是null
            pre = tail;
            head = next;
        }

        return dummyHead.next;

    }

    private ListNode[] reverse(ListNode head, ListNode tail){
        // 因为本题翻转，tail之后可能有链表部分，不能把 pre 定为 null
        ListNode pre = tail.next;
        ListNode cur = head;

        while (pre != tail){
            // 循环里的处理都是围绕cur
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时链表时 tail  --> -->  head 的
        return new ListNode[]{tail, head};
    }

}
