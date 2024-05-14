/*
1219. Path with Maximum Gold
Medium

In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.


Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
* */
public class PathwithMaximumGold_1219 {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] record = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                record[i][j] = false;
            }
        }
        int[] curr = new int[]{0};
        int[] result = new int[]{0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, record, curr, result);
                }
            }
        }
        return result[0];
    }
    private void dfs(int[][] grid, int x, int y, boolean[][] record, int[] curr, int[] result) {
        if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[0].length - 1 || record[x][y] || grid[x][y] == 0) {
            result[0] = Math.max(curr[0], result[0]);
            return;
        }
        for (int[] dir : dirs) {
            record[x][y] = true;
            curr[0] += grid[x][y];
            dfs(grid, x + dir[0], y + dir[1], record, curr, result);
            record[x][y] = false;
            curr[0] -= grid[x][y];
        }
    }
}
