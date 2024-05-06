import util.ListNode;

import java.util.Arrays;

/*
2487. Remove Nodes From Linked List
Medium

You are given the head of a linked list.

Remove every node which has a node with a greater value anywhere to the right side of it.

Return the head of the modified linked list.



Example 1:


Input: head = [5,2,13,3,8]
Output: [13,8]
Explanation: The nodes that should be removed are 5, 2 and 3.
- Node 13 is to the right of node 5.
- Node 13 is to the right of node 2.
- Node 8 is to the right of node 3.
Example 2:

Input: head = [1,1,1,1]
Output: [1,1,1,1]
Explanation: Every node has value 1, so no nodes are removed.


Constraints:

The number of the nodes in the given list is in the range [1, 105].
1 <= Node.val <= 10^5
* */
public class RemoveNodesFromLinkedList_2487 {
    public ListNode removeNodes(ListNode head) {
        // Iterate on nodes in reversed order.
        // When iterating in reversed order, save the maximum value that was passed before.
        ListNode newHead = reverse(head);
        int max = 0; // since our data range from 1 to 10^5
        ListNode dummy = new ListNode(0);
        dummy.next = newHead;
        ListNode pre = dummy;
        while (newHead != null) {
            if (newHead.val < max) {
                ListNode nxt = newHead.next;
                pre.next = nxt;
                newHead = nxt;
            } else {
                max = newHead.val;
                pre = newHead;
                newHead = newHead.next;
            }
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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
        RemoveNodesFromLinkedList_2487 rnfll = new RemoveNodesFromLinkedList_2487();
        ListNode result = rnfll.removeNodes(ListNode.createSinglyLinkedList(Arrays.asList(5,2,13,3,8)));
        System.out.println(result);
    }
}
