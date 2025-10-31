package graph;
import graph.Graph;
import graph.GraphReader;
import graph.scc.SCCTarjan;
import graph.topo.TopologicalSort;
import graph.dagsp.DAGShortestPaths;
import java.util.List;
public class Main {
    public static void main(String[] args) throws Exception {
        Graph graph = GraphReader.readGraph("graph_small_1.json");

        SCCTarjan scc = new SCCTarjan(graph);
        List<List<Integer>> components = scc.findSCCs();
        System.out.println("SCCs: " + components);

        Graph dag = scc.buildCondensation(components);

        TopologicalSort topo = new TopologicalSort(dag);
        List<Integer> topoOrder = topo.sort();
        System.out.println("Topological Order: " + topoOrder);

        DAGShortestPaths dagSP = new DAGShortestPaths(dag);
        int[] shortest = dagSP.shortestPaths(0);
        int[] longest = dagSP.longestPaths();
        System.out.println("Shortest paths from 0: " + java.util.Arrays.toString(shortest));
        System.out.println("Longest paths (critical path): " + java.util.Arrays.toString(longest));
    }
}

