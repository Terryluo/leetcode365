/*
959. Regions Cut By Slashes
Medium
Topics
Companies
An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.



Example 1:


Input: grid = [" /","/ "]
Output: 2
Example 2:


Input: grid = [" /","  "]
Output: 1
Example 3:


Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.


Constraints:

n == grid.length == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '.
 */
public class RegionsCutBySlashes_959 {
    class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf = new UnionFind(4 * n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int root = 4 * (i * n + j);
                char val = grid[i].charAt(j);

                if (val == '/' || val == ' ') {
                    uf.union(root + 0, root + 3);
                    uf.union(root + 1, root + 2);
                }
                if (val == '\\' || val == ' ') {
                    uf.union(root + 0, root + 1);
                    uf.union(root + 2, root + 3);
                }

                if (j + 1 < n) {
                    uf.union(root + 1, root + 7);
                }
                if (i + 1 < n) {
                    uf.union(root + 2, root + 4 * n);
                }
            }
        }

        int regions = 0;
        for (int i = 0; i < 4 * n * n; i++) {
            if (uf.find(i) == i) regions++;
        }

        return regions;
    }
}
