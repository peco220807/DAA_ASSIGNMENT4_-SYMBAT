package graph.dagsp;
import graph.Graph;
import graph.Edge;
import graph.topo.TopologicalSort;
import java.util.Arrays;
import java.util.List;
public class DAGShortestPaths {
    private final Graph dag;
    public DAGShortestPaths(Graph dag) {
        this.dag = dag;
    }
    public int[] shortestPaths(int source) {
        int n = dag.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] topo = topologicalOrder();
        for (int u : topo) {
            if (dist[u] != Integer.MAX_VALUE) {
                for (Edge e : dag.getAdj(u)) {
                    if (dist[e.to] > dist[u] + e.weight) {
                        dist[e.to] = dist[u] + e.weight;
                    }
                }
            }
        }
        return dist;
    }
    public int[] longestPaths() {
        int n = dag.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MIN_VALUE);

        int[] topo = topologicalOrder();
        for (int u : topo) {
            if (dist[u] == Integer.MIN_VALUE) dist[u] = 0;
            for (Edge e : dag.getAdj(u)) {
                if (dist[e.to] < dist[u] + e.weight) {
                    dist[e.to] = dist[u] + e.weight;
                }
            }
        }
        return dist;
    }
    private int[] topologicalOrder() {
        TopologicalSort topo = new TopologicalSort(dag);
        List<Integer> order = topo.sort();
        return order.stream().mapToInt(i -> i).toArray();
    }
}

