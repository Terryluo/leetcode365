package util;

import java.util.*;

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

    public static void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                System.out.printf("%d", cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
    }

    public static List<TreeNode> findNodesInRangeOfBST(TreeNode root, int min, int max) {
        // three situations
        // root value <= min -> go right, root.value >= max -> go left, max < root.value < max use bst maybe better
        List<TreeNode> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.val < min) {
                if (curr.right != null && curr.right.val > max) {
                    return new ArrayList<>();
                } else if (curr.right != null) {
                    queue.offer(curr.right);
                }
            } else if (curr.val > max) {
                if (curr.left != null && curr.left.val < min) {
                    return new ArrayList<>();
                } else if (curr.left != null) {
                    queue.offer(curr.left);
                }
            } else {
                result.add(curr);
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
            }
        }
        return result;
    }
}
