package util;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Definition for a binary tree node.
*/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    private static final String NULL_POINTER_EXPRESSION = "null";
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode reconstructTreeFromLevelOrder(List<String> array) {
        // assume the array pass all the null points 
        if (array.size() == 0) return null;
        TreeNode root = new TreeNode(Integer.valueOf(array.get(0)));
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (index < array.size()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode cur = queue.remove(0);
                if (cur != null) {
                    if (index < array.size()) {
                        String current = array.get(index);
                        cur.left = !NULL_POINTER_EXPRESSION.equals(current) ? new TreeNode(Integer.valueOf(current)) : null;
                        queue.add(cur.left);
                        index++;
                    } else {
                        cur.left = null;
                    }
                    if (index < array.size()) {
                        String current = array.get(index);
                        cur.right = !NULL_POINTER_EXPRESSION.equals(current) ? new TreeNode(Integer.valueOf(current)) : null;
                        queue.add(cur.right);
                        index++;
                    } else {
                        cur.right = null;
                    }
                } else {
                    if (index < array.size()) {
                        queue.add(null);
                        queue.add(null);
                        index += 2;
                    }
                }
            }
        }
        return root;
    }

    public static TreeNode reconstructTreeForLeetcode(List<Integer> array) {
        if (array == null || array.size() == 0) return null;
        Queue<TreeNode> queue = new ArrayDeque<>();
        int idx = 0;
        TreeNode root = new TreeNode(array.get(idx++));
        queue.offer(root);
        while (idx < array.size()) {
            TreeNode cur = queue.poll();
            Integer left = array.get(idx++);
            Integer right = idx < array.size()? array.get(idx++) : null;
            if (left != null) {
                cur.left = new TreeNode(left);
                queue.offer(cur.left);
            }
            if (right != null) {
                cur.right = new TreeNode(right);
                queue.offer(cur.right);
            }
        }
        return root;
    }
}
