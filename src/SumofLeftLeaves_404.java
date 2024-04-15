import util.TreeNode;

/*
404. Sum of Left Leaves
Solved

Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:

Input: root = [1]
Output: 0


Constraints:

The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
* */
public class SumofLeftLeaves_404 {
    public int sumOfLeftLeaves(TreeNode root) {
        int[] result = new int[1];
        helper(root, false, result);
        return result[0];
    }

    private void helper(TreeNode root, boolean isLeftLeaf, int[] result) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (isLeftLeaf) {
                result[0] += root.val;
            }
        }
        helper(root.left, true, result);
        helper(root.right, false, result);
    }
}
