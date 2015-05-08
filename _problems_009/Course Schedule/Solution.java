public class Solution {
    private boolean dfs(int course, int[] visit, ArrayList<ArrayList<Integer>> adjacencyList) {
        visit[course] = -1;
        for (int next : adjacencyList.get(course)) {
            if (visit[next] == -1 || (visit[next] == 0 && !dfs(next, visit, adjacencyList)))
                return false;
        }
        visit[course] = 1;
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numCourses; i++)
            adjacencyList.add(new ArrayList<Integer>());
        for (int[] prerequisite : prerequisites)
            adjacencyList.get(prerequisite[0]).add(prerequisite[1]);

        int[] visit = new int[numCourses];
        for (int i = 0; i < visit.length; i++) {
            if (visit[i] == 0 && !dfs(i, visit, adjacencyList))
                return false;
        }
        return true;
    }
}