import util.TreeNode;

import java.util.Arrays;

/*
1026. Maximum Difference Between Node and Ancestor
Solved
Medium

Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.

A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.



Example 1:


Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
Example 2:


Input: root = [1,null,2,null,0,3]
Output: 3


Constraints:

The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 10^5
* */
public class MaxAncestorDiff {
    int maximum = 0;
    class SubTreeInfo {
        int max;
        int min;
        SubTreeInfo(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }
    public int maxAncestorDiff(TreeNode root) {
        // For each subtree, find the minimum value and maximum value of its descendants.
        subTree(root);
        return maximum;
    }
    private SubTreeInfo subTree(TreeNode root) {
        if (root == null) {
            return new SubTreeInfo(Integer.MIN_VALUE,Integer.MAX_VALUE);
        }
        if (root.left == null && root.right == null) {
            return new SubTreeInfo(root.val, root.val);
        }
        SubTreeInfo left = subTree(root.left);
        SubTreeInfo right = subTree(root.right);
        // update result
        if (left.max == Integer.MIN_VALUE && left.min == Integer.MAX_VALUE) {
            left.max = left.min = root.val;
        }
        if (right.max == Integer.MIN_VALUE && right.min == Integer.MAX_VALUE) {
            right.max = right.min = root.val;
        }
        int leftDiff = Math.max(Math.abs(root.val - left.max), Math.abs(root.val - left.min));
        int rightDiff = Math.max(Math.abs(root.val - right.max), Math.abs(root.val - right.min));
        maximum = Math.max(maximum, Math.max(leftDiff, rightDiff));
        // pass to parent
        int max = Math.max(root.val, Math.max(left.max, right.max));
        int min = Math.min(root.val, Math.min(left.min, right.min));
        return new SubTreeInfo(max, min);
    }

    public static void main(String[] args) {
        MaxAncestorDiff mad = new MaxAncestorDiff();
        TreeNode root = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("1","null","2","null","null","null","0","null","null","null","null","null","null","3"));
        int result = mad.maxAncestorDiff(root);
        System.out.println(result);
    }
}
