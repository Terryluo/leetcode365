/*
988. Smallest String Starting From Leaf
Medium

You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.

Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

As a reminder, any shorter prefix of a string is lexicographically smaller.

For example, "ab" is lexicographically smaller than "aba".
A leaf of a node is a node that has no children.



Example 1:


Input: root = [0,1,2,3,4,3,4]
Output: "dba"
Example 2:


Input: root = [25,1,3,1,3,0,2]
Output: "adz"
Example 3:


Input: root = [2,2,1,null,1,0,null,0]
Output: "abc"


Constraints:

The number of nodes in the tree is in the range [1, 8500].
0 <= Node.val <= 25
* */
import util.TreeNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SmallestStringStartingFromLeaf_988 {
    public String smallestFromLeaf(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        helper(root, sb, pq);
        return pq.peek();
    }
    private void helper(TreeNode root, StringBuilder sb, PriorityQueue<String> pq) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            sb.append((char) ('a' + root.val));
            String s = sb.toString();
            pq.offer(reverse(s));
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        sb.append((char) ('a' + root.val));
        helper(root.left, sb, pq);
        helper(root.right, sb, pq);
        sb.deleteCharAt(sb.length() - 1);
    }
    private String reverse(String s) {
        int start = 0, end = s.length() - 1;
        char[] c = s.toCharArray();
        while (start < end) {
            char temp = c[start];
            c[start] = c[end];
            c[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        SmallestStringStartingFromLeaf_988 sssfl = new SmallestStringStartingFromLeaf_988();
        TreeNode root = TreeNode.reconstructTreeForLeetcode(Arrays.asList(0,1,2,3,4,3,4));
        System.out.println(sssfl.smallestFromLeaf(root));

    }
}
