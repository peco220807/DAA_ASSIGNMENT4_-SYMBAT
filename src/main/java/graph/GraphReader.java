package graph;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GraphReader {
    public static Graph readGraph(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(filename));
        boolean directed = root.get("directed").asBoolean();
        int n = root.get("n").asInt();
        Graph graph = new Graph(n, directed);

        for (JsonNode edge : root.get("edges")) {
            int u = edge.get("u").asInt();
            int v = edge.get("v").asInt();
            int w = edge.get("w").asInt();
            graph.addEdge(u, v, w);
        }
        return graph;
    }
}
