package common;
import java.util.*;
public class Graph {
    private final int n;
    private final List<List<Edge>> adj;
    private boolean useNodeDurations = false;
    public Graph(int n) {
        this.n = n;
        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }
    public int n() { return n; }
    public void setUseNodeDurations(boolean v) { useNodeDurations = v; }
    public boolean useNodeDurations() { return useNodeDurations; }
    public void addEdge(int u, int v, long w) { adj.get(u).add(new Edge(u, v, w)); }
    public List<Edge> edgesFrom(int u) { return Collections.unmodifiableList(adj.get(u)); }
}