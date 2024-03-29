package local.begin.leetCode;


import local.begin.struct.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class LeetCode0019Solution {

    /**
     * 使用一次遍历，记录 cur 节点 和 cur 节点后 n + 1 个节点 pioneer 节点
     *
     * 然后 cur 和 pioneer 都向后走，pioneer的下个节点为空时，pioneer节点到达链表结尾
     *
     * cur 的 next 删除掉
     *
     * @param head
     * @param n
     * @return
     */
    // 改进增加鲁棒性，考虑到n的大小是否大于零小于ListNode的大小
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0) {
            throw new IllegalArgumentException(" n == 0 is illegal");
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pioneer = dummy, cur = dummy;

        // start 先向前移动 n
        while (n != 0) {
            if(pioneer.next != null) {
                pioneer = pioneer.next;
                n--;
            } else {
                throw new IllegalArgumentException("n is beyond the list node's size");
            }

        }
        // end 和 start 共同向前移动到 start 到最后的节点
        while (pioneer.next != null) {
            pioneer = pioneer.next;
            cur = cur.next;
        }
        // 删除掉此时的end的节点
        cur.next = cur.next.next;
        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode pre = new ListNode(0);
        pre.next = head;
        int n = 2;
        while (n <= 5) {
            head.next = new ListNode(n++);
            head = head.next;
        }
        head = pre.next;
        System.out.println(head.toString());
        LeetCode0019Solution solution = new LeetCode0019Solution();
        System.out.println(solution.removeNthFromEnd(head, 6).toString());
    }

}
