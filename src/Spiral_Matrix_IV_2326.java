import util.ListNode;

import java.util.Arrays;
import java.util.LinkedList;

/*
2326. Spiral Matrix IV
Medium

You are given two integers m and n, which represent the dimensions of a matrix.

You are also given the head of a linked list of integers.

Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.

Return the generated matrix.



Example 1:


Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
Explanation: The diagram above shows how the values are printed in the matrix.
Note that the remaining spaces in the matrix are filled with -1.
Example 2:


Input: m = 1, n = 4, head = [0,1,2]
Output: [[0,1,2,-1]]
Explanation: The diagram above shows how the values are printed from left to right in the matrix.
The last space in the matrix is set to -1.


Constraints:

1 <= m, n <= 10^5
1 <= m * n <= 10^5
The number of nodes in the list is in the range [1, m * n].
0 <= Node.val <= 1000
 */
public class Spiral_Matrix_IV_2326 {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] result = new int[m][];
        for (int i = 0; i < m; i++) {
            result[i] = new int [n];
            Arrays.fill(result[i], -1);
        }

        int topRow = 0, bottomRow = m - 1, leftColumn = 0, rightColumn = n - 1;
        while (head != null) {

            for (int col = leftColumn; col <= rightColumn && head != null; col++) {
                result[topRow][col] = head.val;
                head = head.next;
            }
            topRow++;


            for (int row = topRow; row <= bottomRow && head != null; row++) {
                result[row][rightColumn] = head.val;
                head = head.next;
            }
            rightColumn--;


            for (int col = rightColumn; col >= leftColumn && head != null; col--) {
                result[bottomRow][col] = head.val;
                head = head.next;
            }
            bottomRow--;


            for (int row = bottomRow; row >= topRow && head != null; row--) {
                result[row][leftColumn] = head.val;
                head = head.next;
            }
            leftColumn++;
        }

        return result;
    }

    public static void main(String[] args) {
        Spiral_Matrix_IV_2326 sm4 = new Spiral_Matrix_IV_2326();
        ListNode head = ListNode.createSinglyLinkedList(Arrays.asList(8,24,5,21,10,11,11,12,6,17));
        sm4.spiralMatrix(10, 1, head);
    }
}
