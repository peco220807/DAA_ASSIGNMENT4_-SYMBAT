package common;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
public class GraphIO {
    private static final ObjectMapper M = new ObjectMapper();
    public static Graph loadFromJson(Path path) throws IOException {
        JsonModels.GraphObj go = M.readValue(path.toFile(), JsonModels.GraphObj.class);
        if (go.n == null) throw new IOException("Missing 'n' in JSON");
        Graph g = new Graph(go.n);
        if (go.useNodeDurations != null) g.setUseNodeDurations(go.useNodeDurations);
        if (go.edges != null) {
            for (JsonModels.EdgeObj e : go.edges) {
                long w = e.w == null ? 1L : e.w;
                g.addEdge(e.u, e.v, w);
            }
        }
        return g;
    }
}