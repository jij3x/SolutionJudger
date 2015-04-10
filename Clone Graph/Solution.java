public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode graph) {
        if (graph == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> s = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return DFS(graph, s);
    }
    
    public UndirectedGraphNode DFS(UndirectedGraphNode graph, Map<UndirectedGraphNode, UndirectedGraphNode> s) {
        if (s.containsKey(graph)) {
            return s.get(graph);
        }
        UndirectedGraphNode node = new UndirectedGraphNode(graph.label);
        s.put(graph, node);
        for (int i = 0; i < graph.neighbors.size(); i++) {
            node.neighbors.add(DFS(graph.neighbors.get(i), s));
        }
        return node;
    }
}
