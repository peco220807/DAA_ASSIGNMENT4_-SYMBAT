package graph.topo;
import graph.scc.Condensation;
import metrics.Metrics;
import java.util.*;
public class KahnTopo {
    public static List<Integer> topoOrder(Condensation c, Metrics m) {
        int mcnt = c.compCount();
        int[] indeg = new int[mcnt];
        List<List<Integer>> adj = c.adj();
        for (int u = 0; u < mcnt; u++) for (int v : adj.get(u)) { indeg[v]++; m.inc("kahn_edge_count"); }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < mcnt; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            m.inc("kahn_pops");
            int u = q.remove(); order.add(u);
            for (int v : adj.get(u)) {
                indeg[v]--; m.inc("kahn_relaxations");
                if (indeg[v] == 0) { q.add(v); m.inc("kahn_pushes"); }
            }
        }
        return order;
    }
}