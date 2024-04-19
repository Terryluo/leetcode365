/*
200. Number of Islands
Medium

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
* */
public class NumberofIslands_200 {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        boolean[][] checked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                checked[i][j] = false;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!checked[i][j] && grid[i][j] == '1') {
                    dfs(i, j, checked, grid);
                    result++;
                }
            }
        }
        return result;
    }
    private void dfs(int i, int j, boolean[][] checked, char[][] grid) {
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || checked[i][j] || grid[i][j] == '0') {
            return;
        }
        checked[i][j] = true;
        dfs(i + 1, j, checked, grid);
        dfs(i, j + 1, checked, grid);
        dfs(i - 1, j, checked, grid);
        dfs(i, j - 1, checked, grid);
    }
}
