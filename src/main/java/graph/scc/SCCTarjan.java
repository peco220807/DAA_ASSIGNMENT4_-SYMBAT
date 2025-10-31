package graph.scc;
import graph.Graph;
import graph.Edge;
import java.util.*;
public class SCCTarjan {
    private final Graph graph;
    private int index = 0;
    private final int[] indices, lowlink;
    private final boolean[] onStack;
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final List<List<Integer>> sccs = new ArrayList<>();
    public SCCTarjan(Graph graph) {
        this.graph = graph;
        int n = graph.size();
        indices = new int[n];
        Arrays.fill(indices, -1);
        lowlink = new int[n];
        onStack = new boolean[n];
    }
    public List<List<Integer>> findSCCs() {
        for (int v = 0; v < graph.size(); v++) {
            if (indices[v] == -1) {
                dfs(v);
            }
        }
        return sccs;
    }
    private void dfs(int v) {
        indices[v] = index;
        lowlink[v] = index;
        index++;
        stack.push(v);
        onStack[v] = true;

        for (Edge e : graph.getAdj(v)) {
            int u = e.to;
            if (indices[u] == -1) {
                dfs(u);
                lowlink[v] = Math.min(lowlink[v], lowlink[u]);
            } else if (onStack[u]) {
                lowlink[v] = Math.min(lowlink[v], indices[u]);
            }
        }
        if (lowlink[v] == indices[v]) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                onStack[w] = false;
                scc.add(w);
            } while (w != v);
            sccs.add(scc);
        }
    }
    public Graph buildCondensation(List<List<Integer>> sccs) {
        int n = sccs.size();
        Graph dag = new Graph(n, true);
        Map<Integer, Integer> nodeToSCC = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++) {
            for (int node : sccs.get(i)) nodeToSCC.put(node, i);
        }
        for (int v = 0; v < graph.size(); v++) {
            for (Edge e : graph.getAdj(v)) {
                int uComp = nodeToSCC.get(v);
                int vComp = nodeToSCC.get(e.to);
                if (uComp != vComp) {
                    dag.addEdge(uComp, vComp, 1); // вес 1 для конденсации
                }
            }
        }
        return dag;
    }
}