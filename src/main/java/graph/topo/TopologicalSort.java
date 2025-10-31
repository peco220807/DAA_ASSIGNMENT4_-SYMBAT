package graph.topo;
import graph.Graph;
import graph.Edge;
import java.util.*;
public class TopologicalSort {
    private final Graph dag;
    public TopologicalSort(Graph dag) {
        this.dag = dag;
    }
    public List<Integer> sort() {
        int n = dag.size();
        int[] inDegree = new int[n];
        for (int v = 0; v < n; v++) for (Edge e : dag.getAdj(v)) inDegree[e.to]++;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (inDegree[i] == 0) queue.add(i);
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.add(v);
            for (Edge e : dag.getAdj(v)) {
                inDegree[e.to]--;
                if (inDegree[e.to] == 0) queue.add(e.to);
            }
        }
        return order;
    }
}