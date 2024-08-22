import util.TreeNode;

import java.util.Arrays;
import java.util.List;

public class find_nodes_in_range_of_BST {
    public static void main(String[] args) {
        TreeNode root = TreeNode.reconstructTreeForLeetcode(Arrays.asList(8,3,10,1,6,null,14,null,null,4,7,13));
        List<TreeNode> result = TreeNode.findNodesInRangeOfBST(root, 3, 13);
        for (TreeNode tn : result) {
            System.out.println(tn.val);
        }
    }
}
