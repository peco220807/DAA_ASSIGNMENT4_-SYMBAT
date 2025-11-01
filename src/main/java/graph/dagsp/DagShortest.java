package graph.dagsp;
import common.Graph;
import common.Edge;
import metrics.Metrics;
import java.util.*;
public class DagShortest {
    public static class Result { public final long[] dist; public final int[] parent; public Result(long[] d, int[] p) { dist = d; parent = p; } }
    public static Result shortestFrom(Graph g, int src, List<Integer> topo, Metrics m) {
        int n = g.n();
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n]; Arrays.fill(dist, INF); dist[src] = 0;
        int[] parent = new int[n]; Arrays.fill(parent, -1);
        for (int u : topo) {
            m.inc("dagsp_visit_vertices");
            if (dist[u] == INF) continue;
            for (Edge e : g.edgesFrom(u)) {
                m.inc("dagsp_relaxations");
                if (dist[e.to()] > dist[u] + e.weight()) {
                    dist[e.to()] = dist[u] + e.weight(); parent[e.to()] = u;
                }
            }
        }
        return new Result(dist, parent);
    }
    public static List<Integer> reconstruct(int t, int[] parent) {
        List<Integer> path = new ArrayList<>(); for (int v = t; v != -1; v = parent[v]) path.add(v);
        Collections.reverse(path); return path;
    }
}