package graph;
import java.util.ArrayList;
import java.util.List;
public class Graph {
    private final int n;
    private final boolean directed;
    private final List<Edge>[] adj;
    public Graph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    }
    public void addEdge(int u, int v, int w) {
        adj[u].add(new Edge(u, v, w));
        if (!directed) adj[v].add(new Edge(v, u, w));
    }
    public List<Edge> getAdj(int u) {
        return adj[u];
    }
    public int size() {
        return n;
    }
}

