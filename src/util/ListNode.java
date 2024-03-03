package util;

import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    // create a singly-linked list
    public static ListNode createSinglyLinkedList(List<Integer> array) {
        if (array == null || array.size() == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (Integer number : array) {
            curr.next = new ListNode(number);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void print(ListNode head) {
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
