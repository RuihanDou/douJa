package local.begin.leetCode;


import local.begin.struct.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 */
public class LeetCode0234Solution {

//    // 方法一：复制值到数组中，然后用双指针
//    public boolean isPalindrome(ListNode head) {
//
//        List<Integer> vals = new ArrayList<>();
//
//        // 将链表中的值复制到数组中
//        ListNode cur = head;
//        while (cur != null){
//            vals.add(cur.val);
//            cur = cur.next;
//        }
//
//        // 使用双指针
//        int l = 0, r = vals.size() - 1;
//        while (l < r){
//            if(!vals.get(l).equals(vals.get(r))){
//                return false;
//            }
//            l++;
//            r--;
//        }
//        return true;
//
//    }

//    // 方法二： 递归
//    private ListNode frontPointer;
//
//    private boolean recursivelyCheck(ListNode cur){
//        // cur != null
//        // 和(!recursivelyCheck(...)) 递归调用使得
//        // 第一次返回 recursivelyCheck 的结果时，已经是最后链表最后一个元素
//        if(cur != null){
//            if(!recursivelyCheck(cur.next)){
//                return false;
//            }
//            // 第一次运行到这个运行条件时，cur是最后一个元素
//            // 同理 第二次 是 cur 是倒数第二个元素， frontPointer是第二个元素
//            if(cur.val != frontPointer.val){
//                return false;
//            }
//            frontPointer = frontPointer.next;
//        }
//        return true;
//    }
//
//    public boolean isPalindrome(ListNode head) {
//        frontPointer = head;
//        return recursivelyCheck(head);
//    }

    // 方法三：快慢指针
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }

        // 找到前半部分链表的尾节点并反转链表的后半部分
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfEnd = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfEnd;
        boolean res = true;
        while (res && p2 != null){
            if(p1.val != p2.val){
                res = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfEnd);
        return res;

    }

    // 反转链表
    private ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    // 返回中点
    private ListNode endOfFirstHalf(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        LeetCode0234Solution solution = new LeetCode0234Solution();
        boolean rst = solution.isPalindrome(node0);
        System.out.println(rst);
    }

}
