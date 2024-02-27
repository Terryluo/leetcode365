import util.TreeNode;

import java.util.Arrays;

/*
543. Diameter of Binary Tree
Easy

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.



Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1


Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100
* */
public class DiameterofBinaryTree_543 {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = new int[1];
        height(root, result);
        return result[0];
    }

    private int height(TreeNode root, int[] result) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            // leaf node
            return 1;
        }
        int left = height(root.left, result);
        int right = height(root.right, result);
        result[0] = Math.max(left + right, result[0]);;
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        DiameterofBinaryTree_543 dobt = new DiameterofBinaryTree_543();
        TreeNode root = TreeNode.reconstructTreeForLeetcode(Arrays.asList(4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2));
        System.out.println(dobt.diameterOfBinaryTree(root));
    }
}
