package graph.scc;
import common.Graph;
import common.Edge;
import java.util.*;
public class Condensation {
    private final int compCount;
    private final List<List<Integer>> cadj;
    public Condensation(Graph g, int[] compMap) {
        int max = Arrays.stream(compMap).max().orElse(-1);
        this.compCount = max + 1;
        cadj = new ArrayList<>(compCount);
        for (int i = 0; i < compCount; i++) cadj.add(new ArrayList<>());
        for (int u = 0; u < g.n(); u++) for (Edge e : g.edgesFrom(u)) {
            int cu = compMap[u], cv = compMap[e.to()];
            if (cu != cv) cadj.get(cu).add(cv);
        }
        for (List<Integer> l : cadj) {
            Set<Integer> s = new LinkedHashSet<>(l);
            l.clear(); l.addAll(s);
        }
    }
    public int compCount() { return compCount; }
    public List<List<Integer>> adj() { return cadj; }
}