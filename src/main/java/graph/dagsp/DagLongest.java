package graph.dagsp;
import common.Graph;
import common.Edge;
import metrics.Metrics;
import java.util.*;
public class DagLongest {
    public static class Result { public final long[] dp; public final int[] parent; public Result(long[] d, int[] p) { dp = d; parent = p; } }
    public static Result longest(Graph g, List<Integer> topo, Metrics m) {
        int n = g.n(); long NEG = Long.MIN_VALUE / 4;
        long[] dp = new long[n]; Arrays.fill(dp, NEG);
        int[] parent = new int[n]; Arrays.fill(parent, -1);
        int[] indeg = new int[n]; for (int u = 0; u < n; u++) for (Edge e : g.edgesFrom(u)) indeg[e.to()]++;
        for (int i = 0; i < n; i++) if (indeg[i] == 0) dp[i] = 0;
        for (int u : topo) {
            m.inc("dagsp_visit_vertices_longest");
            if (dp[u] == NEG) continue;
            for (Edge e : g.edgesFrom(u)) {
                m.inc("dagsp_relaxations_longest");
                long cand = dp[u] + e.weight();
                if (cand > dp[e.to()]) { dp[e.to()] = cand; parent[e.to()] = u; }
            }
        }
        return new Result(dp, parent);
    }
    public static List<Integer> reconstruct(int t, int[] parent) {
        List<Integer> path = new ArrayList<>(); for (int v = t; v != -1; v = parent[v]) path.add(v);
        Collections.reverse(path); return path;
    }
}