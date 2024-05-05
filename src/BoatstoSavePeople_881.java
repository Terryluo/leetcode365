import java.util.Arrays;

/*
881. Boats to Save People
Medium

You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.



Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)


Constraints:

1 <= people.length <= 5 * 10^4
1 <= people[i] <= limit <= 3 * 10^4
* */
public class BoatstoSavePeople_881 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0, end = people.length - 1, result = 0;
        while (start <= end) {
            // most two people in the same time
            // there are two cases, one is one people, the other is two people
            if (people[end] == limit) {
                while (people[end] == limit) {
                    result++;
                    end--;
                }
            } else {
                //two people
                int remain = limit - people[end];
                end--;
                if (people[start] <= remain  && start <= end) {
                    start++;
                }
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BoatstoSavePeople_881 bsp = new BoatstoSavePeople_881();
        int result = bsp.numRescueBoats(new int[]{2,49,10,7,11,41,47,2,22,6,13,12,33,18,10,26,2,6,50,10}, 50);
        System.out.println(result);
    }
}
