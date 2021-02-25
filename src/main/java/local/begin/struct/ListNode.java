package local.begin.struct;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        ListNode node = this;
        StringBuilder sb = new StringBuilder();
        while (null != node ) {
            sb.append(node.val + " ");
            node = node.next;
        }
        return sb.toString();
    }
}
