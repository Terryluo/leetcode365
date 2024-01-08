import com.sun.source.tree.Tree;
import util.TreeNode;

import java.util.Arrays;

/*
938. Range Sum of BST
Easy

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].



Example 1:


Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
Example 2:


Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.


Constraints:

The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.
* */
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int[] result = new int[1];
        if (root.val < low) {
            findSum(root.right, low, high, result);
        } else if (root.val > high) {
            findSum(root.left, low, high, result);
        } else {
            result[0] += root.val;
            findSum(root.left, low, root.val, result);
            findSum(root.right, root.val, high, result);
        }
        return result[0];
    }
    private void findSum(TreeNode root, int low, int high, int[] result) {
        if (root == null) {
            return;
        }
        if (root.val < low) {
            findSum(root.right, low, high, result);
        } else if (root.val > high) {
            findSum(root.left, low, high, result);
        } else {
            result[0] += root.val;
            findSum(root.left, low, root.val, result);
            findSum(root.right, root.val, high, result);
        }
    }

    public static void main(String[] args) {
        RangeSumBST rsb = new RangeSumBST();
        TreeNode root1 = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("10","5","15","3","7","null","18"));
        int result1 = rsb.rangeSumBST(root1, 7, 15);
        System.out.println(result1);
        TreeNode root2 = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("10","5","15","3","7","13","18","1","null","6"));
        int result2 = rsb.rangeSumBST(root2, 6, 10);
        System.out.println(result2);
    }

}
