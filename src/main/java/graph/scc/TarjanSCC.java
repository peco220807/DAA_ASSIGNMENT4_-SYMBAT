package graph.scc;
import common.Graph;
import common.Edge;
import metrics.Metrics;
import java.util.*;
public class TarjanSCC {
    private final Graph g;
    private final Metrics m;
    private int time = 0;
    private int[] ids, low;
    private boolean[] onStack;
    private Deque<Integer> stack = new ArrayDeque<>();
    private final List<List<Integer>> comps = new ArrayList<>();
    public TarjanSCC(Graph g, Metrics m) { this.g = g; this.m = m; }
    public void run() {
        int n = g.n();
        ids = new int[n]; Arrays.fill(ids, -1);
        low = new int[n]; onStack = new boolean[n];
        for (int i = 0; i < n; i++) if (ids[i] == -1) dfs(i);
    }
    private void dfs(int v) {
        m.inc("scc_dfs_calls");
        ids[v] = low[v] = time++;
        stack.push(v); onStack[v] = true;
        for (Edge e : g.edgesFrom(v)) {
            m.inc("scc_edges_seen");
            int to = e.to();
            if (ids[to] == -1) { dfs(to); low[v] = Math.min(low[v], low[to]); }
            else if (onStack[to]) low[v] = Math.min(low[v], ids[to]);
        }
        if (low[v] == ids[v]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int u = stack.pop(); onStack[u] = false; comp.add(u);
                if (u == v) break;
            }
            comps.add(comp);
        }
    }
    public List<List<Integer>> getComponents() { return comps; }
    public int[] getComponentMap() {
        int[] map = new int[g.n()]; Arrays.fill(map, -1);
        for (int i = 0; i < comps.size(); i++) for (int v : comps.get(i)) map[v] = i;
        return map;
    }
}