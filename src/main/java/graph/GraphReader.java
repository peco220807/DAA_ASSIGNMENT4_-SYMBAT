package graph;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
public class GraphReader {
    public static Graph readGraph(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = GraphReader.class.getClassLoader().getResourceAsStream("data/" + filename);
        if (is == null) {
            throw new IOException("File not found in resources: data/" + filename);
        }
        JsonNode root = mapper.readTree(is);
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