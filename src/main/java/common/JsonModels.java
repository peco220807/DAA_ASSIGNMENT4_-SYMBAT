package common;
import java.util.List;
public class JsonModels {
    public static class EdgeObj { public int u; public int v; public Long w; }
    public static class GraphObj {
        public Boolean directed;
        public Integer n;
        public List<EdgeObj> edges;
        public Boolean useNodeDurations;
        public Integer source; // optional
    }
}
