import util.TreeNode;

import java.util.Arrays;

/*
1325. Delete Leaves With a Given Value
Medium

Given a binary tree root and an integer target, delete all the leaf nodes with value target.

Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you cannot).



Example 1:



Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
Example 2:



Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]
Example 3:



Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with value (target = 2) are removed at each step.


Constraints:

The number of nodes in the tree is in the range [1, 3000].
1 <= Node.val, target <= 1000
* */
public class DeleteLeavesWithaGivenValue_1325 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);// do not use TreeNode left or TreeNode right to get value, because we cannot relink to the nodes,
        root.right = removeLeafNodes(root.right, target);// must be dereferencing the node could link the node together.
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

    public static void main(String[] args) {
        DeleteLeavesWithaGivenValue_1325 dlwgv = new DeleteLeavesWithaGivenValue_1325();
        TreeNode root = dlwgv.removeLeafNodes(TreeNode.reconstructTreeForLeetcode(Arrays.asList(1,2,3,2,null,2,4)),2);
        TreeNode.printLevelOrder(root);
    }
}
