import util.ListNode;

/*
1669. Merge In Between Linked Lists
Medium

You are given two linked lists: list1 and list2 of sizes n and m respectively.

Remove list1's nodes from the ath node to the bth node, and put list2 in their place.

The blue edges and nodes in the following figure indicate the result:


Build the result list and return its head.



Example 1:


Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [10,1,13,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
Example 2:


Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges and nodes in the above figure indicate the result.


Constraints:

3 <= list1.length <= 10^4
1 <= a <= b < list1.length - 1
1 <= list2.length <= 10^4
* */
public class MergeInBetweenLinkedLists_1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummy = new ListNode(0);
        dummy.next = list1;
        ListNode pre = dummy, head = list1;
        int idx = 0;
        ListNode positionA = null;
        ListNode positionB = null;
        while (head != null) {
            if (idx == a) {
                positionA = pre;
            }
            if (idx == b) {
                positionB = head.next;
                break;
            }
            head = head.next;
            pre = pre.next;
            idx++;
        }
        ListNode head2 = list2;
        while (head2.next != null) {
            head2 = head2.next;
        }
        positionA.next = list2;
        head2.next = positionB;
        return dummy.next;
    }
}
