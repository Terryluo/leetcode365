import util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
872. Leaf-Similar Trees
Easy

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.



Example 1:


Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true
Example 2:


Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false
* */
public class LeafSimilar {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // pre-order traverse the tree and get the leaf-sequence
        // compare the two sequence
        // Time complexity would be O(n)
        List<Integer> sq1 = new ArrayList<>();
        List<Integer> sq2 = new ArrayList<>();
        preOrderTraverse(root1, sq1);
        preOrderTraverse(root2, sq2);
        return compare(sq1, sq2);
    }
    private void preOrderTraverse(TreeNode root, List<Integer> sq) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sq.add(root.val);
        }
        preOrderTraverse(root.left, sq);
        preOrderTraverse(root.right, sq);
    }
    private boolean compare(List<Integer> sq1, List<Integer> sq2) {
        if (sq1.size() != sq2.size()) {
            return false;
        }
        for (int i = 0; i < sq1.size(); i++) {
            if (sq1.get(i) != sq2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeafSimilar ls = new LeafSimilar();
        TreeNode root1 = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("3","5","1","6","2","9","8","null","null","7","4"));
        TreeNode root2 = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("3","5","1","6","7","4","2","null","null","null","null","null","null","9","8"));
        System.out.println(ls.leafSimilar(root1, root2));
    }
}
