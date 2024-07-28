import java.util.*;

/*
2045. Second Minimum Time to Reach Destination
Hard

A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.

Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.

The second minimum value is defined as the smallest value strictly larger than the minimum value.

For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.

Notes:

You can go through any vertex any number of times, including 1 and n.
You can assume that when the journey starts, all signals have just turned green.


Example 1:

       
Input: n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
Output: 13
Explanation:
The figure on the left shows the given graph.
The blue path in the figure on the right is the minimum time path.
The time taken is:
- Start at 1, time elapsed=0
- 1 -> 4: 3 minutes, time elapsed=3
- 4 -> 5: 3 minutes, time elapsed=6
Hence the minimum time needed is 6 minutes.

The red path shows the path to get the second minimum time.
- Start at 1, time elapsed=0
- 1 -> 3: 3 minutes, time elapsed=3
- 3 -> 4: 3 minutes, time elapsed=6
- Wait at 4 for 4 minutes, time elapsed=10
- 4 -> 5: 3 minutes, time elapsed=13
Hence the second minimum time is 13 minutes.
Example 2:


Input: n = 2, edges = [[1,2]], time = 3, change = 2
Output: 11
Explanation:
The minimum time path is 1 -> 2 with time = 3 minutes.
The second minimum time path is 1 -> 2 -> 1 -> 2 with time = 11 minutes.


Constraints:

2 <= n <= 104
n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
There are no duplicate edges.
Each vertex can be reached directly or indirectly from every other vertex.
1 <= time, change <= 103
 */
public class SecondMinimumTimetoReachDestination_2045 {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // Create adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Two-dimensional array to store shortest and second shortest time to each node
        int[][] dist = new int[n + 1][2];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[1][0] = 0;

        // Min-heap priority queue to explore nodes
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1, 0}); // {node, time}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int curTime = current[1];

            for (int neighbor : graph.get(node)) {
                int nextTime = curTime + time;

                // Check if we are in a red light period
                if ((curTime / change) % 2 == 1) {
                    nextTime += change - (curTime % change);
                }

                // Update the shortest time
                if (nextTime < dist[neighbor][0]) {
                    dist[neighbor][1] = dist[neighbor][0];
                    dist[neighbor][0] = nextTime;
                    pq.offer(new int[]{neighbor, nextTime});
                } else if (nextTime > dist[neighbor][0] && nextTime < dist[neighbor][1]) {
                    // Update the second shortest time
                    dist[neighbor][1] = nextTime;
                    pq.offer(new int[]{neighbor, nextTime});
                }
            }
        }

        return dist[n][1];
    }

    // best solution
    public int secondMinimum1(int n, int[][] edges, int time, int change) {
        /**
         1. traffic light is global
         - if a departs before b, a will always arrive first
         - if a delays bc of red light, b will be delayed as well
         - carry the time forward
         - round=curTime/change
         - if round is even, then it is green
         - nextTime=curTime+time
         - if round is odd, then it is red
         - wait
         - nextTime=curTime+2*time, (round+1)*change+time
         2. the question is asking for second minimum time
         - if A->...->B1->...->C, a second minumum time would be
         - A->...->B1->...->B2->...-->C
         - a node can be visited twice
         3. use an timeArr to keep track of the 2nd minimum time for every node
         - bfs into next only timeArr[i]<nextTime
         */

        List<Integer>[] graph=new ArrayList[n+1];
        for(int i=0;i<n+1;i++) graph[i]=new ArrayList<>();
        for(int[] edge:edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Deque<int[]> deque=new LinkedList<>();
        int[] visitedNum=new int[n+1];
        int[] timeArr=new int[n+1];
        Arrays.fill(timeArr,-1);
        deque.offerLast(new int[]{1,0});
        timeArr[0]=0;

        while(deque.size()>0){
            int curSize=deque.size();
            for(int i=0;i<curSize;i++){
                int[] cur=deque.pollFirst();
                //if(cur[0]==n && visitedNum[cur[0]]==2) return cur[1];

                int nextTime=0;
                int curLight=cur[1]/change;
                if(curLight%2==0){
                    nextTime=cur[1]+time;
                }
                //nextTime=(curLight+1)*change+time;
                else nextTime=(curLight+1)*change+time;

                for(int nextNode:graph[cur[0]]){
                    if(visitedNum[nextNode]<2 && timeArr[nextNode]<nextTime){
                        deque.offerLast(new int[]{nextNode,nextTime});
                        visitedNum[nextNode]++;
                        timeArr[nextNode]=nextTime;
                        if(nextNode==n && visitedNum[nextNode]==2) return nextTime;
                    }
                }

            }
        }
        return -1;
    }
}
