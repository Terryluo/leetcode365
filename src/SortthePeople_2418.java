/*
2418. Sort the People
Easy

You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.

For each index i, names[i] and heights[i] denote the name and height of the ith person.

Return names sorted in descending order by the people's heights.



Example 1:

Input: names = ["Mary","John","Emma"], heights = [180,165,170]
Output: ["Mary","Emma","John"]
Explanation: Mary is the tallest, followed by Emma and John.
Example 2:

Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
Output: ["Bob","Alice","Bob"]
Explanation: The first Bob is the tallest, followed by Alice and the second Bob.


Constraints:

n == names.length == heights.length
1 <= n <= 103
1 <= names[i].length <= 20
1 <= heights[i] <= 105
names[i] consists of lower and upper case English letters.
All the values of heights are distinct.
 */
public class SortthePeople_2418 {
    // hint: Find the tallest person and swap with the first person,
    // then find the second tallest person and swap with the second person, etc.
    // Repeat until you fix all n people. (Use selection sort to solve the problem)
    public String[] sortPeople(String[] names, int[] heights) {
        for (int i = 0; i < heights.length - 1; i++) {
            int maxIdx = i;
            for (int j = i; j < heights.length; j++) {
                if (heights[j] > heights[maxIdx]) {
                    maxIdx = j;
                }
            }
            swap(names, heights, maxIdx, i);
        }
        return names;
    }

    private void swap(String[] names, int[] heights, int a, int b) {
        int temp = heights[a];
        heights[a] = heights[b];
        heights[b] = temp;
        String tempName = names[a];
        names[a] = names[b];
        names[b] = tempName;
    }
}
