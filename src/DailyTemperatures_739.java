/*
739. Daily Temperatures

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures_739 {
    public int[] dailyTemperatures1(int[] temperatures) {
        // we use a mono decreasing stack to save the index of the lowest temperature(top),
        // if current temperature is higher than the lowest, pop out the lowest one, fill the result[idx] with cur - idx
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int cur = 0; cur < temperatures.length; cur++) {
            if (stack.isEmpty() || temperatures[cur] < temperatures[stack.peekFirst()]) {
                stack.offerFirst(cur);
            } else { // temperatures[cur] > temperatures[stack.peek()]
                // which means we face the warmer temperature
                while (!stack.isEmpty() && temperatures[cur] > temperatures[stack.peekFirst()]) {
                    int idx = stack.pollFirst();
                    result[idx] = cur - idx;
                }
                stack.offerFirst(cur);
            }
        }
        return result;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        // we use a mono decreasing stack to save the index of the lowest temperature(top),
        // if current temperature is higher than the lowest, pop out the lowest one, fill the result[idx] with cur - idx
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int cur = 0; cur < temperatures.length; cur++) {
            while (!stack.isEmpty() && temperatures[cur] > temperatures[stack.peekFirst()]) {
                int idx = stack.pollFirst();
                result[idx] = cur - idx;
            }
            stack.offerFirst(cur);
        }
        return result;
    }

    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;

        int hottest = 0;

        int answer[] = new int[n];

        for (int currDay = n - 1; currDay >= 0; currDay--) {

            int currentTemp = temperatures[currDay];

            // hottest temp seen so far moving from the right
            if (currentTemp >= hottest) {
                hottest = currentTemp;
                continue;
            }

            int days = 1;

            while (temperatures[currDay + days] <= currentTemp) {
                // Use information from answer to search for the next warmer day
                days += answer[currDay + days];
            }

            answer[currDay] = days;
        }

        return answer;
    }

    public static void main(String[] args) {
        DailyTemperatures_739 dt = new DailyTemperatures_739();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = dt.dailyTemperatures(temperatures);
        for (int i : result) {
            System.out.printf("%d,", i);
        }
    }
}
