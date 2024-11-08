#include "2275_largest_combination.h"

/*
Initialize Variables:
We start by defining a variable ans to store the maximum count of elements that share the same bit position with a bit set to 1.
Iterate Over Bit Positions:
Since integers are 32 bits in length, we loop from 0 to 31 to cover all possible bit positions.
Count Set Bits for Each Position:
For each bit position i (from 0 to 31), we count how many elements in candidates have that specific bit set to 1.
To check if the i-th bit of a number candidate is set, we use the expression candidate & (1 << i).
1 << i shifts 1 to the i-th bit position, creating a mask (like 00010000 for i = 4).
candidate & (1 << i) checks if this bit is set by applying the mask; if the result is non-zero, then this bit is set in candidate.
Update Maximum Count:
After counting the elements with the i-th bit set, we update ans to hold the maximum of ans and cnt.
Return the Result:
After iterating over all bit positions, ans holds the largest combination of elements with a common set bit, which is returned as the answer.
*/
int largestCombination(vector<int>& candidates) {
    int n = candidates.size(), ans = 0;
    for (int i = 0; i < 32; ++i) {
        int cnt = 0;
        for (auto candidate : candidates) {
            if (candidate & (1 << i)) {
                cnt++;
            }
        }
        ans = max(ans, cnt);
    }
    return ans;
}