package local.begin.leetCode;

import local.begin.struct.ListNode;

/**
 *21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */


public class LeetCode0021Solution {

//    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
//        ListNode dummyHead =  new ListNode(0);
//        ListNode cur = dummyHead;
//        while (null != l1 && null != l2) {
//            if(l1.val < l2.val) {
//                cur.next = l1;
//                l1 = l1.next;
//            } else {
//                cur.next = l2;
//                l2 = l2.next;
//            }
//            cur = cur.next;
//
//        }
//        if (null == l2) {
//            cur.next = l1;
//        } else {
//            cur.next = l2;
//        }
//        return dummyHead.next;
//    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(null == list1) {
            return list2;
        }
        if(null == list2) {
            return list1;
        }

        if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }


}
