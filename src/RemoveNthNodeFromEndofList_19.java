import util.ListNode;

import java.util.Arrays;

/*
19. Remove Nth Node From End of List
Medium

Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
* */
public class RemoveNthNodeFromEndofList_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode saveHead = head;
        int length = 0;
        while (saveHead != null) {
            saveHead = saveHead.next;
            length++;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int idx = length - n;
        /*
         * idx 0
         *   0   1   2   3   4   5
         *                   p
         *                       h
         * */
        while (idx > 0 && head != null) {
            pre = pre.next;
            head = head.next;
            idx--;
        }
        pre.next = head.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndofList_19 rnnfel = new RemoveNthNodeFromEndofList_19();
        ListNode head = ListNode.createSinglyLinkedList(Arrays.asList(1,2,3,4,5));
        ListNode result = rnnfel.removeNthFromEnd(head, 2);
        ListNode.print(result);
    }
}
