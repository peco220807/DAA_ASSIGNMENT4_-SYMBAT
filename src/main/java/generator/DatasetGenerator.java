package generator;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.JsonModels;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
public class DatasetGenerator {
    private static final ObjectMapper M = new ObjectMapper();
    public static void generateExample(Path outDir) throws IOException {
        Map<String,Integer> sizes = Map.of(
                "small_1", 6, "small_2", 7, "small_3", 8,
                "medium_1", 12, "medium_2", 15, "medium_3", 18,
                "large_1", 25, "large_2", 30, "large_3", 40
        );
        Random rnd = new Random(12345);
        for (var ent : sizes.entrySet()) {
            JsonModels.GraphObj g = new JsonModels.GraphObj();
            g.directed = true; g.n = ent.getValue(); g.useNodeDurations = false; g.edges = new ArrayList<>();
            int n = g.n;
            double density = ent.getKey().startsWith("small") ? 0.18 : ent.getKey().startsWith("medium") ? 0.10 : 0.06;
            int edges = (int)(n * (n-1) * density);
            for (int i = 0; i < edges; i++) {
                JsonModels.EdgeObj e = new JsonModels.EdgeObj();
                e.u = rnd.nextInt(n); e.v = rnd.nextInt(n);
                if (e.u == e.v) { i--; continue; }
                e.w = (long)(1 + rnd.nextInt(20)); g.edges.add(e);
            }
            Path out = outDir.resolve("graph_" + ent.getKey() + ".json");
            M.writerWithDefaultPrettyPrinter().writeValue(out.toFile(), g);
        }
    }
}