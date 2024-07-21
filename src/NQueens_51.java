import java.util.ArrayList;
import java.util.List;

/*
51. N-Queens
Hard
Topics
Companies
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]


Constraints:

1 <= n <= 9
 */
public class NQueens_51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<Integer>();
        dfs(n, curr, result);
        return result;
    }

    private void dfs(int n, List<Integer> curr, List<List<String>> result) {
        if (curr.size() == n) {
            result.add(transformToString(curr));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (valid(curr,  i)) {
                curr.add(i);
                dfs(n, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> curr, int column) {
        int row = curr.size();
        for (int i = 0; i < row; i++) {
            if (curr.get(i) == column || Math.abs(curr.get(i) - column) == row - i) {
                return false;
            }
        }
        return true;
    }

    private List<String> transformToString(List<Integer> curr) {
        List<String> result = new ArrayList<>();
        int size = curr.size();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            int index = curr.get(i);
            for (int j = 0; j < size; j++) {
                if (j == index) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
}
