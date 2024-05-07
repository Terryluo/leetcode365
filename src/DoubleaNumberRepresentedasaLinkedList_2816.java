import util.ListNode;

import java.util.Arrays;

/*
2816. Double a Number Represented as a Linked List
Medium

You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.

Return the head of the linked list after doubling it.



Example 1:


Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
Example 2:


Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998.


Constraints:

The number of nodes in the list is in the range [1, 104]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
* */
public class DoubleaNumberRepresentedasaLinkedList_2816 {
    public ListNode doubleIt(ListNode head) {
        ListNode newHead = reverse(head);
        ListNode saveNewHead = newHead;
        boolean addOne = false;
        while (newHead.next != null) {
            int num = addOne ? newHead.val * 2 + 1 : newHead.val * 2;
            addOne = false;
            if (num >= 10) {
                addOne = true;
                newHead.val = num % 10;
            } else {
                newHead.val = num;
            }
            newHead = newHead.next;
        }
        int num = addOne ? newHead.val * 2 + 1 : newHead.val * 2;
        if (num >= 10) {
            newHead.val = num % 10;
            ListNode node = new ListNode(1);
            newHead.next = node;
            newHead = node;
        } else {
            newHead.val = num;
        }
        return reverse(saveNewHead);
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode nxt = head.next;
            head.next = pre;
            pre = head;
            head = nxt;
        }
        return pre;
    }

    public static void main(String[] args) {
        DoubleaNumberRepresentedasaLinkedList_2816 dnrll = new DoubleaNumberRepresentedasaLinkedList_2816();
        //ListNode result = dnrll.doubleIt(ListNode.createSinglyLinkedList(Arrays.asList(3,4,5,4,2,5,5,9,9,9)));
        ListNode result = dnrll.doubleIt(ListNode.createSinglyLinkedList(Arrays.asList(9,1,9,5,0,5,1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9)));
        System.out.println(result);
    }
}
