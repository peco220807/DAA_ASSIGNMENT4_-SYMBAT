package common;
public final class Edge {
    private final int u;
    private final int v;
    private final long w;
    public Edge(int u, int v, long w) { this.u = u; this.v = v; this.w = w; }
    public int from() { return u; }
    public int to() { return v; }
    public long weight() { return w; }
    @Override public String toString() { return String.format("(%d->%d:%d)", u, v, w); }
}