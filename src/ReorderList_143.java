import util.ListNode;

import java.util.Arrays;

/*
* 143. Reorder List
Medium

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]


Constraints:

The number of nodes in the list is in the range [1, 5 * 10^4].
1 <= Node.val <= 1000
* */
public class ReorderList_143 {
    public void reorderList1(ListNode head) {
        int[] listValues = new int[50000];
        int size = 0;
        ListNode cur1 = head;
        while (cur1 != null) {
            listValues[size++] = cur1.val;
            cur1 = cur1.next;
        }
        boolean last = false;
        if (size % 2 == 0) last = true;
        ListNode cur2 = head;
        int start = 1;
        size -= 1;
        while(start < size) {
            cur2.next = new ListNode(listValues[size--]);
            cur2 = cur2.next;
            cur2.next = new ListNode(listValues[start++]);
            cur2 = cur2.next;
        }
        if (last) {
            cur2.next = new ListNode(listValues[start++]);
        }
    }
    // best solution
    public static void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }
        reorderList2(head, head.next);
    }

    public static ListNode reorderList2(ListNode head, ListNode curr) {
        ListNode temp;
        if (curr.next != null) {
            temp = reorderList2(head, curr.next);
        } else {
            temp = head;
        }
        if(temp == null) {
            return null;
        }
        if (temp == curr  || temp.next == curr) {
            curr.next = null;
            return null;
        }

        curr.next = temp.next;
        temp.next = curr;
        return curr.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.createSinglyLinkedList(Arrays.asList(1,2,3,4,5));
        ReorderList_143 rl = new ReorderList_143();
        rl.reorderList(head);
        ListNode.print(head);
    }
}
