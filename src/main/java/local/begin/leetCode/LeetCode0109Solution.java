package local.begin.leetCode;


import local.begin.struct.ListNode;
import local.begin.struct.TreeNode;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class LeetCode0109Solution {

//    // 分治
//    public TreeNode sortedListToBST(ListNode head) {
//        return buildTree(head, null);
//    }
//
//    private TreeNode buildTree(ListNode left, ListNode right){
//        if(left == right) {
//            return null;
//        }
//        ListNode mid = getMid(left, right);
//        TreeNode root = new TreeNode(mid.val);
//        root.left = buildTree(left, mid);
//        root.right = buildTree(mid.next, right);
//        return root;
//    }
//
//    private ListNode getMid(ListNode left, ListNode right){
//        ListNode fast = left;
//        ListNode slow = left;
//        while (fast != right && fast.next != right){
//            fast = fast.next;
//            fast = fast.next;
//            slow = slow.next;
//        }
//        return slow;
//    }


    // 分治 + 中序遍历优化
    ListNode globalHead;

    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        // length 为链表长度
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ret++;
            head = head.next;
        }
        return ret;
    }

    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();

        // 中序遍历的性质
        root.left = buildTree(left, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

}
