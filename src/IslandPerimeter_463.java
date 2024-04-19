/*
463. Island Perimeter
Easy

You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.



Example 1:


Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4


Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
* */
public class IslandPerimeter_463 {
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        int[][] dirs = new int[][]{{1, 0}, {0, 1},{-1, 0}, {0, -1}};
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 1) {
                    for (int[] dir : dirs) {
                        int neighborX = x + dir[0];
                        int neighborY = y + dir[1];
                        if (neighborX < 0 || neighborY < 0 || neighborX > grid.length - 1 || neighborY > grid[0].length - 1 || grid[neighborX][neighborY] == 0) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }
}
