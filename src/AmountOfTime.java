import util.TreeNode;

import java.util.Arrays;

/*
2385. Amount of Time for Binary Tree to Be Infected
Medium

You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.



Example 1:


Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
Example 2:


Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.


Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.
* */
public class AmountOfTime {
    int globalMax = 0;
    public int amountOfTime(TreeNode root, int start) {
        boolean seeStart = false;
        int result = helper(root, start, seeStart);
        return globalMax;
    }

    private int helper(TreeNode root, int start, boolean seeStart) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, start, seeStart);
        int right = helper(root.right, start, seeStart);
        if (root.val == start) {
            seeStart = true;
            return 1;
        } else if (seeStart) {

        } else if ((left < 0 && right > 0) || (left > 0 && right < 0)) {
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        AmountOfTime aot = new AmountOfTime();
        //TreeNode root = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("1","5","3","null","4","10","6","null","null","9","2"));
        TreeNode root = TreeNode.reconstructTreeFromLevelOrder(Arrays.asList("1"));
        int result = aot.amountOfTime(root, 1);
        System.out.println(result);
    }
}

