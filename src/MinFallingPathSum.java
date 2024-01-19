/*
931. Minimum Falling Path Sum

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

Example 1:


Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
Example 2:


Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
*
* */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        // dp is the matrix that record the minimum falling path until current slot
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    int preRowMin;
                    if (j == 0) {
                        preRowMin = Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                    } else if (j == matrix[0].length - 1) {
                        preRowMin = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                    } else {
                        preRowMin = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                        preRowMin = Math.min(preRowMin, dp[i - 1][j + 1]);
                    }
                    dp[i][j] = preRowMin + matrix[i][j];
                }
                if (i == matrix.length - 1) {
                    result = Math.min(dp[i][j], result);
                }
            }
        }
        return result;
    }
}
