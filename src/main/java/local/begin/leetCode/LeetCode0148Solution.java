package local.begin.leetCode;


import local.begin.dataStructureAlgorithm.dataStruct.PriorityQueue;
import local.begin.struct.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class LeetCode0148Solution {

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    // [head, tail)
    private ListNode sortList(ListNode head, ListNode tail){
        if(head == null){
            return head;
        }
        if(head.next == tail){
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail){
            slow = slow.next;
            fast = fast.next;
            if(fast != tail){
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1Head = sortList(head, mid);
        ListNode list2Head = sortList(mid, tail);
        ListNode sorted = merge(list1Head, list2Head);
        return sorted;
    }

    private ListNode merge(ListNode h1, ListNode h2){
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = h1, temp2 = h2;
        while (temp1 != null && temp2 != null){
            if(temp1.val < temp2.val){
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if(temp1 != null){
            temp.next = temp1;
        } else {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

}
