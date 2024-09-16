import java.util.*;

/*
539. Minimum Time Difference
Medium

Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.


Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0


Constraints:

2 <= timePoints.length <= 2 * 10^4
timePoints[i] is in the format "HH:MM".
 */
public class Minimum_Time_Difference_539 {
    public int findMinDifference(List<String> timePoints) {
        int result = Integer.MAX_VALUE;
        Collections.sort(timePoints, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int hour1 = (s1.charAt(0) - '0') * 10 + (s1.charAt(1) - '0');
                int hour2 = (s2.charAt(0) - '0') * 10 + (s2.charAt(1) - '0');
                int minute1 = (s1.charAt(3) - '0') * 10 + (s1.charAt(4) - '0');
                int minute2 = (s2.charAt(3) - '0') * 10 + (s2.charAt(4) - '0');
                return hour1 == hour2 ? minute1 - minute2 : hour1 - hour2;
            }
        });
        int i = 0, j = 1;
        while (j < timePoints.size()) {
            int diff = getDiff(timePoints.get(i), timePoints.get(j));
            result = Math.min(diff, result);
            i++;
            j++;
        }
        // handle corner case
        int lastDiff = getDiff(timePoints.get(0), timePoints.get(timePoints.size() - 1));
        result = Math.min(lastDiff, result);
        return result;
    }

    private int getDiff(String s1, String s2) {
        int hour1 = (s1.charAt(0) - '0') * 10 + (s1.charAt(1) - '0');
        int hour2 = (s2.charAt(0) - '0') * 10 + (s2.charAt(1) - '0');
        int minute1 = (s1.charAt(3) - '0') * 10 + (s1.charAt(4) - '0');
        int minute2 = (s2.charAt(3) - '0') * 10 + (s2.charAt(4) - '0');
        if (hour1 == hour2) {
            return minute2 - minute1;
        } else {
            return Math.min((hour2 - hour1) * 60 + minute2 - minute1, (hour1 + 24 - hour2) * 60 + minute1 - minute2);
        }
    }
    // best solution
    public int findMinDifference1(List<String> timePoints) {
        if (timePoints.size() > 1440) return 0;

        boolean[] seen = new boolean[1440];

        for (String time : timePoints) {
            int minutes = convertToMinutes(time);
            if (seen[minutes]) return 0;
            seen[minutes] = true;
        }

        int first = Integer.MAX_VALUE, prev = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < 1440; i++) {
            if (seen[i]) {
                if (first == Integer.MAX_VALUE) {
                    first = i;
                } else {
                    minDiff = Math.min(minDiff, i - prev);
                }
                prev = i;
            }
        }
        minDiff = Math.min(minDiff, 1440 - prev + first);

        return minDiff;
    }

    private int convertToMinutes(String time) {
        return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60
                + (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
    }

    public static void main(String[] args) {
        Minimum_Time_Difference_539 mtd = new Minimum_Time_Difference_539();
        List<String> timePoints = Arrays.asList("00:00","23:59","00:00");
        System.out.println(mtd.findMinDifference(timePoints));
    }
}
