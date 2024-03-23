import util.ListNode;

import java.util.Arrays;

/*
* 234. Palindrome Linked List
Solved
Easy
Topics
Companies
Given the head of a singly linked list, return true if it is a
palindrome
 or false otherwise.



Example 1:


Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false


Constraints:

The number of nodes in the list is in the range [1, 10^5].
0 <= Node.val <= 9


Follow up: Could you do it in O(n) time and O(1) space?
* */
public class PalindromeLinkedList_234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return true;
        // find the middle node and reverse the second half linked list
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        /*
        *       1   2   1
        *           s   f
        *       1   2   2   1
        *           s   f
        * */
        ListNode slowNext = slow.next;
        slow.next = null;
        ListNode secondHead = reverse(slowNext);
        while (head != null && secondHead != null) {
            if (head.val != secondHead.val) {
                return false;
            }
            head = head.next;
            secondHead = secondHead.next;
        }
        return true;
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
        PalindromeLinkedList_234 pll = new PalindromeLinkedList_234();
        System.out.println(pll.isPalindrome(ListNode.createSinglyLinkedList(Arrays.asList(1,2,2,1))));
    }
}
