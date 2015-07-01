public class Solution {
    public int[] findOrder(int courses, int[][] dependencies) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < courses; i++)
            graph.add(new ArrayList<Integer>());
        for (int i = 0; i < dependencies.length; i++) {
            int s = dependencies[i][1],
                e = dependencies[i][0];
            graph.get(s).add(e);
        }
        return DFS(graph);
    }
    private int currentLabel;
    private int[] DFS(List<List<Integer>> graph) {
        Set<Integer> explored = new HashSet<Integer>();
        currentLabel = graph.size();
        int[] f = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++)
            if (!explored.contains(i))
                if (!DFS(i, graph, explored, f)) return new int[0];
        int[] g = new int[f.length];
        for (int i = 0; i < f.length; i++) {
            if (f[i] > 0)
                g[f[i]-1] = i;
        }
        return g;
    }
    private boolean DFS(int course, List<List<Integer>> graph, Set<Integer> explored, int[] f) {
        if (explored.contains(course)) return f[course] > 0;
        explored.add(course);
        for (int v : graph.get(course))
            if (!DFS(v, graph, explored, f)) return false;
        f[course] = currentLabel;
        currentLabel--;
        return true;
    }
}
