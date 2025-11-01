package metrics;
import java.util.concurrent.ConcurrentHashMap;
public class SimpleMetrics implements Metrics {
    private long start = 0L, elapsed = 0L;
    private final ConcurrentHashMap<String, Long> counters = new ConcurrentHashMap<>();
    public void startTimer() { start = System.nanoTime(); }
    public void stopTimer() { elapsed = System.nanoTime() - start; }
    public long elapsedNanos() { return elapsed; }
    public void inc(String key) { counters.merge(key, 1L, Long::sum); }
    public long get(String key) { return counters.getOrDefault(key, 0L); }
}