import java.util.ArrayDeque;
import java.util.Queue;

/*
1700. Number of Students Unable to Eat Lunch
Easy

The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.



Example 1:

Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
Example 2:

Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3


Constraints:

1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
* */
public class NumberofStudentsUnabletoEatLunch_1700 {
    public int countStudents1(int[] students, int[] sandwiches) {
        Queue<Integer> studentQueue = new ArrayDeque<>();
        for (int studentPreference : students) {
            studentQueue.offer(studentPreference);
        }
        for (int i = 0; i < sandwiches.length; i++) {
            if (sandwiches[i] == studentQueue.peek()) {
                studentQueue.poll();
            } else {
                int size = studentQueue.size();
                boolean findStudent = false;
                while (size > 0) {
                    if (sandwiches[i] == studentQueue.peek()) {
                        studentQueue.poll();
                        findStudent = true;
                        break;
                    } else {
                        int top = studentQueue.poll();
                        studentQueue.offer(top);
                    }
                    size--;
                }
                if (!findStudent) {
                    break;
                }
            }
        }
        return studentQueue.size();
    }
    // The best solution is to calculate the total needed circular and square sandwiches and find out if there is how many sandwich remain
    public int countStudents(int[] students, int[] sandwiches) {
        int circularSandwiches = 0, squareSandwiches = 0; // sandwich needed
        for (int studentPreference : students) {
            if (studentPreference == 0) {
                circularSandwiches++;
            } else if (studentPreference == 1) {
                squareSandwiches++;
            }
        }
        for (int i = 0; i < sandwiches.length; i++) {
            if (sandwiches[i] == 0 && circularSandwiches > 0) {
                circularSandwiches--;
            } else if (sandwiches[i] == 1 && squareSandwiches > 0) {
                squareSandwiches--;
            } else {
                break;
            }
        }
        return circularSandwiches + squareSandwiches;
    }

    public static void main(String[] args) {
        NumberofStudentsUnabletoEatLunch_1700 nosutel = new NumberofStudentsUnabletoEatLunch_1700();
        int[] students = new int[]{1,1,0,0};
        int[] sandwiches = new int[]{0,1,0,1};
        System.out.println(nosutel.countStudents(students, sandwiches));
    }
}
