package metrics;
public interface Metrics {
    void startTimer();
    void stopTimer();
    long elapsedNanos();
    void inc(String key);
    long get(String key);
}